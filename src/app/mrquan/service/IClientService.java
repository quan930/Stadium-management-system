package app.mrquan.service;

import app.mrquan.pojo.Order;
import app.mrquan.pojo.Personnel;
import app.mrquan.pojo.SportVenue;

import java.util.List;

public interface IClientService {
    /**
     * 根据场地名称进行查询 返回pojo对象
     * 祝小杭
     * @param name 需要查询的场地名称
     * @return 返回SportVenue对象集合，没有size为0
     */
    List<SportVenue> findSportByName(String name);

    /**
     * 根据场馆名称进行查询 返回pojo集合
     * 王新
     * @param stadium 需要查询的场馆名称
     * @return 返回SportVenue对象集合，没有size为0
     */
    List<SportVenue> findSportsByStadium(String stadium);

    /**
     * 根据场地类别和所属区域查询 返回pojo集合
     * 祝小杭 王新
     * @param motionType 需要查询的场地类别
     * @param district  需要查询的所属区域
     * @return  返回SportVenue对象集合，没有size为0
     */
    List<SportVenue> findSportsByTypeAndDistrict(String motionType,String district);

    /**
     * 根据是否预定查询场地 返回pojo集合
     * @param yOrN 为true查询有预定的场地，反之则查询没有预定的场地
     * @return 返回SportVenue对象集合，没有size为0
     */
    List<SportVenue> findSportsByReserve(boolean yOrN);

    /**
     * 根据租金排序所有场地 返回pojo集合
     * @return 返回SportVenue对象集合，没有size为0
     */
    List<SportVenue> listSportsByRent();

    /**
     * 根据预定量排序所有场地 返回pojo集合
     * @return 返回SportVenue对象集合，没有size为0
     */
    List<SportVenue> listSportsByReserve();

    /**
     * 顾客预定 单个订单 成功返回true 失败返回 false
     * @param order 订单对象
     * @return  预定成功返回true 反之则false
     */
    boolean reserve(Order order);

    /**
     * 顾客预定多个订单 成功返回true 失败返回false
     * @param orders 订单集合
     * @return 预定成功返回true 反之则false
     */
    boolean reserve(List<Order> orders);

    /**
     * 信息管理 成功返回true 失败返回false
     * @param personnel 需要更新的属性放到personnel对象中 不需要更新的对象为null
     * @return 预定成功返回true 反之则false
     */
    boolean update(Personnel personnel);

    /**
     * 找到当前用户订单 返回pojo集合
     * @return 返回Order对象集合，没有size为0
     */
    List<Order> findOrdersByPersonnel();

    /**
     * 根据编号取消订单 成功返回true 失败返回false
     * @param serialNumber 需要取消订单的编号
     * @return 取消成功返回true 反之则false
     */
    boolean cancelReserve(String serialNumber);
}
