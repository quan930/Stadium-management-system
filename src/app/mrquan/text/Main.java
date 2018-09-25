package app.mrquan.text;

import app.mrquan.factory.DAOFactory;
import app.mrquan.factory.ServiceFactory;
import app.mrquan.pojo.Order;
import app.mrquan.pojo.Personnel;
import app.mrquan.pojo.SportVenue;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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

        List<Order> list = ServiceFactory.getClientServiceInstance().findOrdersByPersonnel("a00002");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
