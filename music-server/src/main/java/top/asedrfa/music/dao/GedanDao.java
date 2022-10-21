package top.asedrfa.music.dao;

import top.asedrfa.music.entity.Gedan;
import org.apache.ibatis.annotations.Param;
import top.asedrfa.music.entity.GetGedan;

import java.util.List;

/**
 * (Gedan)表数据库访问层
 *
 * @author makejava
 * @since 2021-09-08 15:15:53
 */
public interface GedanDao {
    // 通过 uId
    List<GetGedan> queryByUId(String uId);

    /**
     * 通过ID查询单条数据
     *
     * @param gdId 主键
     * @return 实例对象
     */
    GetGedan queryById(String gdId);


    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Gedan> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param gedan 实例对象
     * @return 对象列表
     */
    List<Gedan> queryAll(Gedan gedan);

    /**
     * 新增数据
     *
     * @param gedan 实例对象
     * @return 影响行数
     */
    int insert(Gedan gedan);

    /**
     * 修改数据
     *
     * @param gedan 实例对象
     * @return 影响行数
     */
    int update(Gedan gedan);

    /**
     * 通过主键删除数据
     *
     * @param gdId 主键
     * @return 影响行数
     */
    int deleteById(String gdId);

}