package top.asedrfa.music.service;

import org.springframework.web.multipart.MultipartFile;
import top.asedrfa.music.entity.GetUser;
import top.asedrfa.music.entity.TLoad;
import top.asedrfa.music.entity.User;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2021-09-07 20:11:02
 */
public interface UserService {
    // 找回密码
    void findpwd(String tel, String pwd);

    // 更新密码
    void updatepwd(TLoad tLoad, String pwd);

    // 上传头像
    void updatehead(User user);

    // 获取用户信息
    GetUser getUser(String appId, String msg);

    // 登录
    TLoad login(TLoad tLoad, String usr, String pwd);


    /**
     * 通过ID查询单条数据
     *
     * @param uId 主键
     * @return 实例对象
     */
    User queryById(String uId);

    // 通过 tel 查找
    User queryByTel(String tel);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    void insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    GetUser update(User user, String appId, String msg);

    /**
     * 通过主键删除数据
     *
     * @param uId 主键
     * @return 是否成功
     */
    boolean deleteById(String uId);

    // 判断是否登录
    TLoad isLogin(String appId, String msg);

}