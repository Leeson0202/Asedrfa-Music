package top.asedrfa.music.dao;

import top.asedrfa.music.entity.Music;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Music)表数据库访问层
 *
 * @author makejava
 * @since 2021-09-11 00:16:53
 */
public interface MusicDao {
    // 更具歌名查找
    List<Music> queryByMName(String mName);

    /**
     * 通过ID查询单条数据
     *
     * @param mId 主键
     * @return 实例对象
     */
    Music queryById(String mId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Music> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param music 实例对象
     * @return 对象列表
     */
    List<Music> queryAll(Music music);

    /**
     * 新增数据
     *
     * @param music 实例对象
     * @return 影响行数
     */
    int insert(Music music);

    /**
     * 修改数据
     *
     * @param music 实例对象
     * @return 影响行数
     */
    int update(Music music);

    /**
     * 通过主键删除数据
     *
     * @param mId 主键
     * @return 影响行数
     */
    int deleteById(String mId);

}