package app.mrquan.service;

import app.mrquan.pojo.Order;
import app.mrquan.pojo.SportVenue;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IAdministratorService {
    /**
     * 查找当前场馆的全部订单
     * @param stadium 场地名
     * @return 返回订单 order 集合 没有size==0
     * @throws SQLException
     */
    List<Order> findOrdersByReservationStadiumSerialNumber(String stadium) throws SQLException;

    /**
     * 按照预定量对本场馆的所有场地排序
     * @param stadium 场地名
     * @return 返回订单 order 集合 没有size==0
     * @throws SQLException
     */
    List<SportVenue> listSportVenueByReserve(String stadium) throws SQLException;

    /**
     * 统计营业额
     * @param stadium 场地名
     * @return 返回营业额
     * @throws SQLException
     */
    int turnover(String stadium) throws SQLException;

    /**
     * 年纪分布
     * @return
     * @throws SQLException
     */
    Map<String,Integer> ageDistribution () throws SQLException;

    /**
     * 根据营业额排序
     * @return
     * @throws SQLException
     */
    List<SportVenue> listSportVenueByTurnover () throws SQLException;

    /**
     * 统计男士或女士喜欢的运动
     * @param manOrWoman true 为男士 false 为女士
     * @return
     * @throws SQLException
     */
    String favoriteSport(boolean manOrWoman) throws SQLException;
}
