package top.asedrfa.music.dao;

import top.asedrfa.music.entity.GedanMusic;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (GedanMusic)表数据库访问层
 *
 * @author makejava
 * @since 2021-09-11 00:34:23
 */
public interface GedanMusicDao {
    List<GedanMusic> queryByMId(String mId);


    GedanMusic queryByGedanMId(String gdId,String mId);

    /**
     * 通过ID查询单条数据
     *
     * @param gdId 主键
     * @return 实例对象
     */
    List<GedanMusic> queryById(String gdId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<GedanMusic> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param gedanMusic 实例对象
     * @return 对象列表
     */
    List<GedanMusic> queryAll(GedanMusic gedanMusic);

    /**
     * 新增数据
     *
     * @param gedanMusic 实例对象
     * @return 影响行数
     */
    int insert(GedanMusic gedanMusic);

    /**
     * 修改数据
     *
     * @param gedanMusic 实例对象
     * @return 影响行数
     */
    int update(GedanMusic gedanMusic);

    /**
     * 通过主键删除数据  删除歌单的所有数据
     *
     * @param gdId 主键
     * @return 影响行数
     */
    int deleteById(String gdId);

    // 通过 gdId 和MId 删除记录  删除歌单的一条数据
    int deleteByIdMId(String gdId,String mId);

}