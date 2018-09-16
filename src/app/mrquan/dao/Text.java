package app.mrquan.dao;

import app.mrquan.dbc.DatabaseConnection;
import app.mrquan.factory.DAOFactory;
import app.mrquan.pojo.Order;
import app.mrquan.pojo.Personnel;
import app.mrquan.pojo.SportVenue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Text {
    public static void main(String[] args) throws SQLException, ParseException {
//        DatabaseConnection databaseConnection = new DatabaseConnection();
//        Connection con = databaseConnection.getConnection();
//        PreparedStatement preparedStatement;
//        preparedStatement = con.prepareStatement("select * from student");
//        ResultSet rs = preparedStatement.executeQuery();
//        while(rs.next()){
//            //Student表
//            int id = rs.getInt(1);
//            String name = rs.getString(2);
//            System.out.println("id:"+id+"\t名字:"+name);
//        }

//        preparedStatement = con.prepareStatement("insert into student values (?,?)");
//        preparedStatement.setInt(1,4);
//        preparedStatement.setString(2,"阿斯顿");


        /**
         * 人员表测试
         */
//        preparedStatement = con.prepareStatement("insert into personnels values (?,?,?,?,?,?,?,?,?,?,?)");
//        preparedStatement.setString(1,"1ffg899r");
//        preparedStatement.setString(2,"quan");
//        preparedStatement.setString(3,"pjkbv");
//        preparedStatement.setBoolean(4,true);
//        preparedStatement.setInt(5,24);
//        preparedStatement.setString(6,"13804128609");
//        preparedStatement.setString(7,"14@qq.com");
//        preparedStatement.setDouble(8,100);
//        preparedStatement.setString(9,null);
//        preparedStatement.setBoolean(10,false);
//        preparedStatement.setString(11,"鸟巢体育馆");
//
////        pojo.setId(rs.getString(1));//id
////        pojo.setPassword(rs.getString(2));//密码
////        pojo.setName(rs.getString(3));//名字
////        pojo.setSex(rs.getBoolean(4));//性别
////        pojo.setAge(rs.getInt(5));//年龄
////        pojo.setTelephone(rs.getString(6));//电话
////        pojo.setEmail(rs.getString(7));//邮箱
////        pojo.setBalance(rs.getDouble(8));//余额
////        pojo.setDistrict(rs.getString(9));//区域
//////                pojo.setAbrogate();
////        pojo.setAdministrator(rs.getBoolean(10));//管理员
////        pojo.setStadium(rs.getString(11));//区域
//
//        int len = preparedStatement.executeUpdate();
//        System.out.printf(String.valueOf(len));
//        preparedStatement.close();
//        con.close();
//        databaseConnection.close();
//         DAOFactory.getIPersonnelDAOInstance().findPersonnelById("a00001");
//        System.out.println(DAOFactory.getIPersonnelDAOInstance().findPersonnelById("a00002"));
//        Personnel pojo = DAOFactory.getIPersonnelDAOInstance().findPersonnelById("a00001");
//        System.out.println(pojo);
//        List<Personnel> list = DAOFactory.getIPersonnelDAOInstance().clientList();
//        for (int i = 0; i <list.size() ; i++) {
//            System.out.println(list.get(i));
//        }

//        System.out.println(list.size());
//        order.setSerialNumber("qweasdzxc");
//        int i = DAOFactory.getIOrderDAOInstance().add(order);


        /**
         * 订单表测试
         */
//        List<Order> orders = new ArrayList<>();
//
//        Order order1 = new Order();
//        order1.setSerialNumber("1234per201809111111");
//        order1.setReservationDate(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:09:11:11:11"));
//        order1.setReservationStadiumSerialNumber("212bab");
//        order1.setLoanDate(new SimpleDateFormat("yyyy:MM:dd").parse("2018:9:20"));
//        order1.setStartTime(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:9:20:09:00"));
//        order1.setEndTime(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:9:20:12:30"));
//        order1.setOnTime(true);
//        order1.setId("1234per");
//
//        Order order2 = new Order();
//        order2.setSerialNumber("1234per201809100915");
//        order2.setReservationDate(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:09:10:09:15"));
//        order2.setReservationStadiumSerialNumber("111aaa");
//        order2.setLoanDate(new SimpleDateFormat("yyyy:MM:dd").parse("2018:9:19"));
//        order2.setStartTime(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:9:19:13:20"));
//        order2.setEndTime(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:9:19:17:50"));
//        order2.setOnTime(true);
//        order2.setId("1234per");
//
//        Order order3 = new Order();
//        order3.setSerialNumber("1234per201809091950");
//        order3.setReservationDate(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:09:09:19:50"));
//        order3.setReservationStadiumSerialNumber("212bab");
//        order3.setLoanDate(new SimpleDateFormat("yyyy:MM:dd").parse("2018:9:18"));
//        order3.setStartTime(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:9:18:16:15"));
//        order3.setEndTime(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:9:18:20:45"));
//        order3.setOnTime(true);
//        order3.setId("1234per");
//
//        orders.add(order1);
//        orders.add(order2);
//        orders.add(order3);
//
////
////        int m = DAOFactory.getIOrderDAOInstance().add(order1);
////        System.out.println(m);
//
//        List<Order> list = DAOFactory.getIOrderDAOInstance().AllList();
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
////
////        order1.setOnTime(null);
////        int m = DAOFactory.getIOrderDAOInstance().update(order1);
////        System.out.println(m);


        /**
         * 场地表
         */
        List <SportVenue> pojos = DAOFactory.getISportVenueDAOInstance().list();
        for (int i = 0; i < pojos.size(); i++) {
            System.out.println(pojos.get(i));
        }
//        List<SportVenue> pojos1 = DAOFactory.getISportVenueDAOInstance().findSportVenuesByName("quanquan");
//        for (int i = 0; i < pojos1.size(); i++) {
//            System.out.println(pojos1.get(i));
//        }
        SportVenue sportVenue1 = new SportVenue();
        sportVenue1.setSerialNumber("111aaa");
        sportVenue1.setSerialName("沈北羽毛球场");
        sportVenue1.setDistrict("沈北");
        sportVenue1.setStadium("沈北大体育场");
        sportVenue1.setMotionType("羽毛球");
        sportVenue1.setMotionProfile("羽毛球真好啊！！！");
        sportVenue1.setAgeUpperLimit(40);
        sportVenue1.setAgeLowerLimit(15);
        sportVenue1.setRent(15.8);
        sportVenue1.setOrderNumber(1);
        int m = DAOFactory.getISportVenueDAOInstance().add(sportVenue1);
        System.out.println(m);
    }
}
