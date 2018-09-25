package app.mrquan.service.impl;

import app.mrquan.factory.DAOFactory;
import app.mrquan.pojo.Order;
import app.mrquan.pojo.Personnel;
import app.mrquan.pojo.SportVenue;
import app.mrquan.service.IClientService;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClientServiceImpl implements IClientService {
//    private Personnel personnel;
//    private List<Order> orders;
//    private List<SportVenue> sportVenues;
//    private Personnel personnel;
//    public ClientServiceImpl(String id){
//        try {
//            personnel = DAOFactory.getIPersonnelDAOInstance().findPersonnelById(id);
//        } catch (SQLException e) {
//            personnel = null;
//            e.printStackTrace();
//        }
//    }

    @Override
    public List<SportVenue> findSportByName(String name) throws SQLException{//ok
        DAOFactory.getISportVenueDAOInstance().findSportVenuesByName(name);
//        List<SportVenue> pojos = new ArrayList<>();
//        for (int i = 0; i < sportVenues.size(); i++) {
//            if(sportVenues.get(i).getSerialName().equals(name)) {
//                pojos.add(sportVenues.get(i));
//            }else {
//                continue;
//            }
//        }
//        return pojos;
        return DAOFactory.getISportVenueDAOInstance().findSportVenuesByName(name);
    }

    @Override
    public List<SportVenue> findSportsByStadium(String stadium) throws SQLException{//ok
        List<SportVenue> all = DAOFactory.getISportVenueDAOInstance().list();
        List<SportVenue> pojos = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            if(all.get(i).getStadium().equals(stadium)) {
                pojos.add(all.get(i));
            }else {
                continue;
            }
        }
        return pojos;
    }

    @Override
    public List<SportVenue> findSportsByTypeAndDistrict(String motionType, String district) throws SQLException{//ok
        List<SportVenue> pojos=new ArrayList<>();
        List<SportVenue> sportVenues = DAOFactory.getISportVenueDAOInstance().list();
        for(int i=0;i<sportVenues.size();i++) {
            if((sportVenues.get(i).getMotionType().equals(motionType))&&(sportVenues.get(i).getDistrict().equals(district))) {
                pojos.add(sportVenues.get(i));
            }else {
                continue;
            }
        }
        return pojos;
    }

    @Override
    public List<SportVenue> findSportsByReserve(boolean yOrN) throws SQLException{//ok
        List<SportVenue> sportVenues=DAOFactory.getISportVenueDAOInstance().list();
        List<SportVenue> pojos = new ArrayList<>();
        if(yOrN) {
            for (int i = 0; i < sportVenues.size(); i++) {
                if(sportVenues.get(i).getOrderNumber()>0) {
                    pojos.add(sportVenues.get(i));
                }
            }
        }else {
            for (int i = 0; i < sportVenues.size(); i++) {
                if(sportVenues.get(i).getOrderNumber()<=0) {
                    pojos.add(sportVenues.get(i));
                }
            }
        }
        return pojos;
    }

    @Override
    public List<SportVenue> listSportsByRent() throws SQLException{//ok
        List<SportVenue> sportVenues = DAOFactory.getISportVenueDAOInstance().list();
        Collections.sort(sportVenues, new Comparator<SportVenue>() {
            @Override
            public int compare(SportVenue o1, SportVenue o2) {
                if(o1.getRent()>o2.getRent()) {
                    return 1;
                }
                if(o1.getRent()==o2.getRent()) {
                    return 0;
                }
                return -1;
            }
        });
        return sportVenues;
    }

    @Override
    public List<SportVenue> listSportsByReserve() throws SQLException{//ok
        List<SportVenue> sportVenues = DAOFactory.getISportVenueDAOInstance().list();
        Collections.sort(sportVenues, new Comparator<SportVenue>() {
            @Override
            public int compare( SportVenue o1, SportVenue o2) {
                if(o1.getOrderNumber()<o2.getOrderNumber()) {
                    return 1;
                }
                if (o1.getOrderNumber()==o2.getOrderNumber()) {
                    return 0;
                }
                return -1;
            }
        });
        return sportVenues;
    }

    @Override
    public boolean reserve(Order order, String id) throws SQLException {
        if (yOrN(order,id)){
            System.out.println("可以！");
            DAOFactory.getIOrderDAOInstance().add(order);
        }
        return false;
    }

    @Override
    public boolean reserve(List<Order> orders, String id) throws SQLException {
        boolean yOrN = true;
        for (int i = 0; i < orders.size(); i++) {
            if (!yOrN(orders.get(i),id)){
//                System.out.println("++++++++++++++++++");
                yOrN = false;
                break;
            }
        }
        if (yOrN){
//            System.out.println("okok");
            int m =DAOFactory.getIOrderDAOInstance().add(orders);
            System.out.println(m);
            if (m==orders.size()){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(Personnel personnel)throws SQLException {
        int m = DAOFactory.getIPersonnelDAOInstance().update(personnel);
        if (m==0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public List<Order> findAllOrdersByPersonnel(String id) throws SQLException {
        return DAOFactory.getIOrderDAOInstance().findAllOrdersByUser(id);
    }

    @Override
    public List<Order> findOrdersByPersonnel(String id)throws SQLException {
        return DAOFactory.getIOrderDAOInstance().findOrdersByUser(id);
    }

    @Override
    public boolean cancelReserve(String serialNumber)throws SQLException {
        Order order = DAOFactory.getIOrderDAOInstance().findOrderByNumber(serialNumber);
        order.getStartTime();
        double m = (order.getStartTime().getTime()-(new Date().getTime()))/1000/60/60/24;
        if (m>7){
//            System.out.println("可以！！！");
            order.setCancel(true);
            int yesOrNo = DAOFactory.getIOrderDAOInstance().update(order);
            if (yesOrNo!=0){
//                System.out.println("成功！！！");
                return true;
            }
        }
        return false;
    }

    private boolean yOrN(Order order, String id) throws SQLException {
        //费用 时间冲突 年龄 爽约
        //顾客对象
        Personnel personnel = DAOFactory.getIPersonnelDAOInstance().findPersonnelById(id);
        if (personnel.getAbrogate()<=3){//爽约
            System.out.println("爽约没有达到3次以上");
            //场馆对象
            SportVenue sportVenue = DAOFactory.getISportVenueDAOInstance().findSportVenuesBySerialNumber(order.getReservationStadiumSerialNumber());
//            System.out.println(sportVenue);
            if ((personnel.getAge()>=sportVenue.getAgeLowerLimit())&&(personnel.getAge()<=sportVenue.getAgeUpperLimit())){//年龄判断
                System.out.println("年龄允许");
                /**
                 * 判断费用
                 */
                Date startTime= order.getStartTime();
                Date endTime = order.getEndTime();
                double rent = sportVenue.getRent();
                long hour= (endTime.getTime()-startTime.getTime())/1000/60/60;
                if ((rent*hour)>sportVenue.getRent()){
                    System.out.println("费用够");
                    /**
                     * 判断订单上限
                     */
                    //预约订单集合 不包括（已经使用的订单 和违约的订单）
                    List<Order> orderList = DAOFactory.getIOrderDAOInstance().list();
                    int orderNumber = 0;
                    for (int i = 0; i < orderList.size(); i++) {
                        if (orderList.get(i).getId().equals(personnel.getId())){
                            orderNumber++;
                        }else {
                            continue;
                        }
                    }
                    if (orderNumber<3){
                        System.out.println("订单没有达到上限");
                        /**
                         *时间冲突
                         */
                        for (int i = 0; i < orderList.size(); i++) {
                            if (orderList.get(i).getReservationStadiumSerialNumber().equals(sportVenue.getSerialNumber())){
//                                System.out.println("+++++++++++++++++");
                                System.out.println(order.getEndTime());
                                if ((order.getEndTime().getTime()>orderList.get(i).getStartTime().getTime())&&(order.getEndTime().getTime()<=orderList.get(i).getEndTime().getTime())){
                                    System.out.println("时间冲突1");
                                    break;
                                }else {
                                    if ((order.getStartTime().getTime()>=orderList.get(i).getStartTime().getTime())&&(order.getStartTime().getTime()<=orderList.get(i).getEndTime().getTime())){
                                        System.out.println("时间冲突2");
                                        break;
                                    }else {
                                        System.out.println("成功");
                                        return true;
                                    }
                                }
                            }else {
                                continue;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
