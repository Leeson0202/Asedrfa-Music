package top.asedrfa.music.service;

import top.asedrfa.music.entity.Gedan;
import top.asedrfa.music.entity.GetGedan;

import java.util.List;

/**
 * (Gedan)表服务接口
 *
 * @author makejava
 * @since 2021-09-08 15:15:53
 */
public interface GedanService {
    // 通过 uid 查找歌单
    List<GetGedan> queryByUId(String uId);


    /**
     * 通过ID查询单条数据
     *
     * @param gdId 主键
     * @return 实例对象
     */
    GetGedan queryById(String gdId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Gedan> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param gedan 实例对象
     * @return 实例对象
     */
    GetGedan insert(Gedan gedan);

    /**
     * 修改数据
     *
     * @param gedan 实例对象
     * @return 实例对象
     */
    GetGedan update(Gedan gedan);

    /**
     * 通过主键删除数据
     *
     * @param gdId 主键
     * @return 是否成功
     */
    boolean deleteById(String gdId);

}