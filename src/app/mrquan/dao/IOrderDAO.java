package app.mrquan.dao;

import app.mrquan.pojo.Order;

import java.sql.SQLException;
import java.util.List;

public interface IOrderDAO {
    /**
     * 返回订单集合 包括已经使用的订单 和违约的订单
     * @return 返回Order 集合，没有size=0
     * @throws SQLException
     */
    List<Order> AllList() throws SQLException;

    /**
     * 返回订单集合 不包括（已经使用的订单 和违约的订单）
     * @return 返回Order 集合，没有size=0
     * @throws SQLException
     */
    List<Order> list() throws SQLException;

    /**
     * 添加单一订单 成功 返回1，否则返回0
     * @param pojo 要增加的order对象
     * @return 数据保存成功返回1否则返回0
     * @throws SQLException
     */
    int add(Order pojo) throws SQLException;

    /**
     * 实现数据批处理增加操作
     * @param pojos 要增加的order对象集合
     * @return 数据保存成功返回增加数量否则返回0
     * @throws SQLException
     */
    int add(List<Order> pojos) throws SQLException;

    /**
     * 数据更新
     * @param order
     * @return 成功返回1 失败返回0
     * @throws SQLException
     */
    int update(Order order) throws SQLException;
}
