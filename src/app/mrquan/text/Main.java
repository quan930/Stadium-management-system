package app.mrquan.text;

import app.mrquan.factory.DAOFactory;
import app.mrquan.factory.ServiceFactory;
import app.mrquan.pojo.Order;
import app.mrquan.pojo.Personnel;
import app.mrquan.pojo.SportVenue;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) throws SQLException, ParseException {
//        List<SportVenue> pojos = ServiceFactory.getClientServiceInstance().listSportsByRent();
//        List<SportVenue> pojos =  ServiceFactory.getClientServiceInstance().findSportsByReserve(false);
//        for (int i = 0; i < pojos.size(); i++) {
//            System.out.println(pojos.get(i));
//        }
//        List<SportVenue> pojos = ServiceFactory.getClientServiceInstance().findSportByName("沈北羽毛球场");
////        List<SportVenue> pojos = ServiceFactory.getClientServiceInstance().findSportsByTypeAndDistrict("羽毛球","和平");
//        for (int i = 0; i < pojos.size(); i++) {
//            System.out.println(pojos.get(i));
//        }

//        List<SportVenue> list = ServiceFactory.getClientServiceInstance().findSportByName("沈阳羽毛球场地");
//        list = ServiceFactory.getClientServiceInstance().findSportsByStadium("沈阳奥体中心");
//        list = ServiceFactory.getClientServiceInstance().findSportsByTypeAndDistrict("羽毛球","沈阳");
//        list = ServiceFactory.getClientServiceInstance().findSportsByReserve(false);
//        list = ServiceFactory.getClientServiceInstance().listSportsByRent();
//        list = ServiceFactory.getClientServiceInstance().listSportsByReserve();
//
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }

//        List<Personnel> list = DAOFactory.getIPersonnelDAOInstance().clientList();
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
//        List<Personnel> list = DAOFactory.getIPersonnelDAOInstance().clientList();
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));a00002
//        }

//        List<Order> list = DAOFactory.getIOrderDAOInstance().list();
//        Order order;
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
////        order = list.get(0);
////
//        order.setSerialNumber("1234per201809093008");
//        order.setReservationDate(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:9:9:30:08"));
//        order.setStartTime(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:9:30:11:00"));
//        order.setEndTime(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:9:30:15:00"));
//
//
//        order = list.get(1);
//        order.setSerialNumber("a00002201809091008");
//        order.setReservationDate(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:9:9:10:08"));
//        order.setStartTime(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:9:30:15:00"));
//        order.setEndTime(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:9:30:17:00"));


////
////
//        ServiceFactory.getClientServiceInstance().reserve(list,"a00002");

//        SportVenue sportVenue = DAOFactory.getISportVenueDAOInstance().findSportVenuesBySerialNumber("aaa0001");
//        System.out.println(sportVenue);
//        List<Order> list = DAOFactory.getIOrderDAOInstance().findOrdersBySerialNumber("aaa0001");
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }

//        List<Order> list = ServiceFactory.getIClientServiceInstance().findOrdersByPersonnel("a00002");
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }


//        Map<String,Set<String>> map = ServiceFactory.getIClientServiceInstance().listSportsInit();
//        Set<String> name = map.get("name");
//        for (String str:name) {
//            System.out.println(str);
//        }

//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.add(Calendar.DAY_OF_YEAR,7);
//        String minDate = new SimpleDateFormat("yyyy:MM:dd").format(calendar.getTime());
//        calendar.setTime(new Date());
//        calendar.add(Calendar.YEAR,1);
//        String maxTime = new SimpleDateFormat("yyyy:MM:dd").format(calendar.getTime());
//        System.out.println(maxTime);
        Order order = new Order();
        order.setLoanDate(new Date());
        order.setEndTime(new Date());
        order.setStartTime(new Date());
        order.setReservationDate(new Date());
        System.out.println(order);
    }

//    private static boolean fun(String s) {//判断邮箱
//        if ((s.split("@")) != null) {
//
//        } else {
//            return false;
//        }
//        if ((s.split("\\.")) != null) {
//
//        } else {
//            return false;
//        }
//        int length = s.length();
//        int m = s.indexOf("@");
//        int n = s.indexOf(".");
//        if ((m > 0) && (n < length - 1) && ((n - m) > 0)) {
//            return true;
//        } else {
//            return false;
//        }
//
//    }
}