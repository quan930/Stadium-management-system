package app.mrquan.dao;

import app.mrquan.pojo.SportVenue;

import java.sql.SQLException;
import java.util.List;

public interface ISportVenueDAO {
    /**
     * 根据名字返回 SportVenue 集合
     * @param name 要查找的名字
     * @return SportVenue 集合,没有集合长度为0
     * @throws SQLException
     */
    List<SportVenue> findSportVenuesByName(String name) throws SQLException;

    /**
     * 根据id返回 SportVenue 对象
     * @param id 要查找的id
     * @return 返回pojo对象,没有返回null
     * @throws SQLException
     */
    SportVenue findSportVenuesBySerialNumber (String id) throws SQLException;

    /**
     * 返回场地集合
     * @return SportVenue 集合,没有集合长度为0
     * @throws SQLException
     */
    List<SportVenue> list() throws SQLException;

    /**
     * 添加场地
     * @param pojo 要增加的SportVenue
     * @return 成功返回1 否则返回0
     * @throws SQLException
     */
    int add(SportVenue pojo) throws SQLException;
}