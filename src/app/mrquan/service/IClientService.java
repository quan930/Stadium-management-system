package app.mrquan.service;

import app.mrquan.pojo.SportVenue;

import java.util.List;

public interface IClientService {
    /**
     * 根据场地名称进行查询 返回pojo对象
     * 祝小杭
     * @param name 需要查询的场地名称
     * @return 如果有返回 SportVenue 对象，反之返回null;
     */
    SportVenue findSportByName(String name);

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
}
