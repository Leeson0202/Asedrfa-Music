package top.asedrfa.music.service;

import top.asedrfa.music.entity.TLoad;
import java.util.List;

/**
 * (TLoad)表服务接口
 *
 * @author makejava
 * @since 2021-09-08 00:20:15
 */
public interface TLoadService {

    /**
     * 通过ID查询单条数据
     *
     * @param lId 主键
     * @return 实例对象
     */
    TLoad queryById(String lId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TLoad> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tLoad 实例对象
     * @return 实例对象
     */
    TLoad insert(TLoad tLoad);

    /**
     * 修改数据
     *
     * @param tLoad 实例对象
     * @return 实例对象
     */
    TLoad update(TLoad tLoad);

    /**
     * 通过主键删除数据
     *
     * @param lId 主键
     * @return 是否成功
     */
    boolean deleteById(String lId);

}