package top.asedrfa.music.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import top.asedrfa.music.dao.GedanDao;
import top.asedrfa.music.dao.TLoadDao;
import top.asedrfa.music.entity.*;
import top.asedrfa.music.dao.UserDao;
import top.asedrfa.music.service.GedanService;
import top.asedrfa.music.service.UserService;
import org.springframework.stereotype.Service;
import top.asedrfa.music.tools.AssertUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2021-09-07 20:11:02
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private TLoadDao tLoadDao;
    @Resource
    private GedanDao gedanDao;
    @Resource
    private GedanService gedanDaoService;

    // 找回密码
    @Override
    public void findpwd(String tel, String pwd) {
        User user = userDao.queryByTel(tel);
        AssertUtil.isTrue(user == null, "用户未注册");
        user.setPwd(pwd);
        AssertUtil.isTrue(userDao.update(user) < 1, "找回失败");
    }

    // 更改密码
    @Override
    public void updatepwd(TLoad tLoad, String pwd) {
        // 重新组合
        String uId = tLoad.getUId();
        User user = new User();
        user.setUId(uId);
        user.setPwd(pwd);
        AssertUtil.isTrue(userDao.update(user) < 1, "修改失败");
    }

    // 上传文件
    @Override
    public void updatehead(User user) {
        AssertUtil.isTrue(userDao.update(user) < 1, "更新头像数据失败");
    }

    // 获取用户信息
    @Override
    public GetUser getUser(String appId, String msg) {
        // 1. 判断是否登录
        TLoad tLoaded = this.isLogin(appId, msg);
        AssertUtil.isTrue(tLoaded == null, "未登录");
        // 2. 认证通过 返回信息
        // 通过uId查询 得到没有歌单的信息
        GetUser getUser = userDao.GetUserByuId(tLoaded.getUId());
        List<GetGedan> gedans = gedanDaoService.queryByUId(tLoaded.getUId());// 获取歌单列表
        getUser.setGedanList(gedans);
        return getUser;
    }

    //登录  usr pwd appId
    @Override
    public TLoad login(TLoad tLoad, String usr, String pwd) {
        // 1. 不能为空
        AssertUtil.isTrue(StringUtils.isBlank(usr), "账户不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(pwd), "密码不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(tLoad.getAppId()), "appId不能为空");
        // 2. 密码是否正确
        User user = userDao.queryByTel(usr);
        if (user == null)
            AssertUtil.isTrue((user = userDao.queryByUsr(usr)) == null, "用户不存在，请注册");
        AssertUtil.isTrue(!pwd.equalsIgnoreCase(user.getPwd()), "账户或密码错误");
        // 3.查询是否登录
        // 3.1 app唯一
        // 3.2 u_id唯一
        TLoad tLoaded = tLoadDao.queryByappId(tLoad.getAppId());// 通过 appID 查询是否有登录记录
        if (tLoaded != null)  // 3.1 如果app有用户存在，删除登陆记录
            tLoadDao.deleteById(tLoaded.getLId());
        tLoaded = tLoadDao.queryByuId(user.getUId());  // 通过 uId 查询是否有登录记录
        if (tLoaded != null)  // 3.2 如果用户有登录记录，删除登陆记录
            tLoadDao.deleteById(tLoaded.getLId());

        // 4. 验证成功
        tLoad.setLId(UUID.randomUUID().toString());//登录随机数
        tLoad.setUId(user.getUId());
        tLoad.setMsg(UUID.randomUUID().toString()); //meg随机数,传给app
        AssertUtil.isTrue(tLoadDao.insert(tLoad) < 1, "注册失败");
        return tLoad;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param uId 主键
     * @return 实例对象
     */
    @Override
    public User queryById(String uId) {
        return this.userDao.queryById(uId);
    }

    // 通过 tel 查找
    @Override
    public User queryByTel(String tel) {
        return this.userDao.queryByTel(tel);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public void insert(User user) {
        // 1. 不能为空
        AssertUtil.isTrue(StringUtils.isBlank(user.getTel()), "账户不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getPwd()), "密码不能为空");
        // 2. tel 验证
        String tel = user.getTel();
        AssertUtil.isTrue(userDao.queryByTel(tel) != null, "用户已经存在");
        // 3. 进行注册q
        user.setUId(UUID.randomUUID().toString());
        user.setUsr(user.getTel());
        user.setNName(user.getUsr());
        user.setUsrDate(new Date());
        user.setHUrl("/users/head/default.png");
        String gdId = UUID.randomUUID().toString();  //随机生产歌单id
        user.setGdId(gdId);  //修改歌单id
        // 调用Dao层
        this.userDao.insert(user);
        //创建 我的喜爱 歌单
        Gedan gedan = new Gedan(gdId, user.getUId(), "Like", "/users/files/collection.png", 0, new Date());
        gedanDao.insert(gedan);
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public GetUser update(User user, String appId, String msg) {
        // 1. 判断是否登录
        TLoad tLoaded = this.isLogin(appId, msg);
        AssertUtil.isTrue(tLoaded == null, "未登录");
        // 2. 判断用户是否存在
        User olduser = userDao.queryByTel(user.getTel());//通过Tel 查询
        AssertUtil.isTrue(olduser == null, "无该用户");// 没有该用户
        if (user.getUsr() != null) {
            User user1 = userDao.queryByUsr(user.getUsr());
            AssertUtil.isTrue(user1 != null && !user1.getUId().equals(tLoaded.getUId()) , "账户已存在");
        }
        user.setUId(olduser.getUId()); //修改 uId
        // 3. 更新用户
        // 设置必要字段为空
        user.setUsrDate(null);
        AssertUtil.isTrue(userDao.update(user) < 1, "修改失败");
        // 返回用户信息 GetUser
        GetUser updateuser = this.getUser(appId, msg);
        return updateuser;
    }

    /**
     * 通过主键删除数据
     *
     * @param uId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String uId) {
        return this.userDao.deleteById(uId) > 0;
    }

    @Override
    public TLoad isLogin(String appId, String msg) {
        // 1. 判空
        AssertUtil.isTrue(StringUtils.isBlank(appId), "信息错误");
        AssertUtil.isTrue(StringUtils.isBlank(msg), "信息错误");
        // 2. 查询判断是否有登录状态
        TLoad tLoaded = tLoadDao.queryByappId(appId);
        AssertUtil.isTrue(tLoaded == null, "信息错误");
        AssertUtil.isTrue(!msg.equals(tLoaded.getMsg()), "信息错误");
        return tLoaded;
    }
}