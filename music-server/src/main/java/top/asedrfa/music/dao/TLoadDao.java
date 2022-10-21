package top.asedrfa.music.dao;

import top.asedrfa.music.entity.TLoad;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TLoad)表数据库访问层
 *
 * @author makejava
 * @since 2021-09-08 00:20:15
 */
public interface TLoadDao {

    /**
     * 通过ID查询单条数据
     *
     * @param lId 主键
     * @return 实例对象
     */
    TLoad queryById(String lId);
    // 查找  appid
    TLoad queryByappId(String appId);
    // 查找  uId
    TLoad queryByuId(String uId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TLoad> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tLoad 实例对象
     * @return 对象列表
     */
    List<TLoad> queryAll(TLoad tLoad);

    /**
     * 新增数据
     *
     * @param tLoad 实例对象
     * @return 影响行数
     */
    int insert(TLoad tLoad);

    /**
     * 修改数据
     *
     * @param tLoad 实例对象
     * @return 影响行数
     */
    int update(TLoad tLoad);

    /**
     * 通过主键删除数据
     *
     * @param lId 主键
     * @return 影响行数
     */
    int deleteById(String lId);

}