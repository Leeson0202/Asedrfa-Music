package top.asedrfa.music.service.impl;

import top.asedrfa.music.entity.TLoad;
import top.asedrfa.music.dao.TLoadDao;
import top.asedrfa.music.service.TLoadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TLoad)表服务实现类
 *
 * @author makejava
 * @since 2021-09-08 00:20:15
 */
@Service("tLoadService")
public class TLoadServiceImpl implements TLoadService {
    @Resource
    private TLoadDao tLoadDao;

    /**
     * 通过ID查询单条数据
     *
     * @param lId 主键
     * @return 实例对象
     */
    @Override
    public TLoad queryById(String lId) {
        return this.tLoadDao.queryById(lId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TLoad> queryAllByLimit(int offset, int limit) {
        return this.tLoadDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tLoad 实例对象
     * @return 实例对象
     */
    @Override
    public TLoad insert(TLoad tLoad) {
        this.tLoadDao.insert(tLoad);
        return tLoad;
    }

    /**
     * 修改数据
     *
     * @param tLoad 实例对象
     * @return 实例对象
     */
    @Override
    public TLoad update(TLoad tLoad) {
        this.tLoadDao.update(tLoad);
        return this.queryById(tLoad.getLId());
    }

    /**
     * 通过主键删除数据
     *
     * @param lId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String lId) {
        return this.tLoadDao.deleteById(lId) > 0;
    }
}