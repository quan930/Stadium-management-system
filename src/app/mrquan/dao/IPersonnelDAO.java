package app.mrquan.dao;

import app.mrquan.pojo.Personnel;

import java.sql.SQLException;
import java.util.List;

/**
 * 定义Personnel对象的数据层的操作标准
 */
public interface IPersonnelDAO {
    /**
     * 根据id返回pojo 对象
     * @param id 需要查询的id
     * @return 存在返回personnel对象 否则返回null
     * @throws SQLException
     */
    Personnel findPersonnelById(String id) throws SQLException;

    /**
     * 返回顾客用户集合
     * @return student集合,没有集合长度为0
     * @throws SQLException
     */
    List<Personnel> clientList() throws SQLException;

    /**
     * 数据增加
     * @param pojo 要增加的Personnel
     * @return 成功返回1失败返回0
     * @throws SQLException
     */
    int add(Personnel pojo) throws SQLException;

    /**
     * 数据更新
     * @param pojo 需要更新的Personnel
     * @return 成功返回1 失败返回0
     * @throws SQLException
     */
    int update (Personnel pojo) throws SQLException;
}