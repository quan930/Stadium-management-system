package app.mrquan.service.impl;

import app.mrquan.pojo.Order;
import app.mrquan.pojo.Personnel;
import app.mrquan.pojo.SportVenue;
import app.mrquan.service.IClientService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClientServiceImpl implements IClientService {
    private Personnel personnel;
    private List<Order> orders;
    private List<SportVenue> sportVenues;
    public ClientServiceImpl(){
        personnel = new Personnel();
        orders = new ArrayList<>();
        sportVenues = new ArrayList<>();
        try {
            init();
        } catch (ParseException e) {
            System.out.println("数据错误");
            e.printStackTrace();
        }
    }

    @Override
    public SportVenue findSportByName(String name) {
        return null;
    }

    @Override
    public List<SportVenue> findSportsByStadium(String stadium) {
        return null;
    }

    @Override
    public List<SportVenue> findSportsByTypeAndDistrict(String motionType, String district) {
        return null;
    }

    @Override
    public List<SportVenue> findSportsByReserve(boolean yOrN) {
        List<SportVenue> pojos = new ArrayList<>();

        Set<String> serialNumbers = new HashSet<>();//场地编号
        for (int i = 0; i < orders.size(); i++) {
            serialNumbers.add(orders.get(i).getReservationStadiumSerialNumber());
        }

        if (yOrN){
            for (int i = 0; i < sportVenues.size(); i++) {
                if(serialNumbers.contains(sportVenues.get(i).getSerialNumber())){
                    pojos.add(sportVenues.get(i));
                }else {
                    continue;
                }
            }
        }else {
            for (int i = 0; i < sportVenues.size(); i++) {
                if(!serialNumbers.contains(sportVenues.get(i).getSerialNumber())){
                    pojos.add(sportVenues.get(i));
                }else {
                    continue;
                }
            }
        }
        return pojos;
    }

    @Override
    public List<SportVenue> listSportsByRent() {
        List<SportVenue> sportVenues = this.sportVenues;
        Collections.sort(sportVenues, new Comparator<SportVenue>() {
            @Override
            public int compare(SportVenue o1, SportVenue o2) {
                if (o1.getRent() > o2.getRent()){
                    return 1;
                }
                if (o1.getRent() == o2.getRent()){
                    return 0;
                }
                return -1;
            }
        });
        return sportVenues;
    }

    @Override
    public List<SportVenue> listSportsByReserve() {
//        Map<String,Integer>

        return null;
    }

    private void init() throws ParseException {
        personnel = new Personnel();
        personnel.setId("1234per");
        personnel.setName("顾客1");
        personnel.setPassword("12345");
        personnel.setSex(true);
        personnel.setAge(25);
        personnel.setTelephone("13804128609");
        personnel.setEmail("123@163.com");
        personnel.setBalance(190.5);
        personnel.setDistrict("沈北");
        personnel.setAdministrator(false);

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

        SportVenue sportVenue2 = new SportVenue();
        sportVenue2.setSerialNumber("112aab");
        sportVenue2.setSerialName("沈北蓝球场");
        sportVenue2.setDistrict("沈北");
        sportVenue2.setStadium("沈北大体育场");
        sportVenue2.setMotionType("篮球");
        sportVenue2.setMotionProfile("篮球很好么？？？");
        sportVenue2.setAgeUpperLimit(30);
        sportVenue2.setAgeLowerLimit(18);
        sportVenue2.setRent(36.9);
        sportVenue2.setOrderNumber(0);

        SportVenue sportVenue3 = new SportVenue();
        sportVenue3.setSerialNumber("211baa");
        sportVenue3.setSerialName("和平羽毛球场");
        sportVenue3.setDistrict("和平");
        sportVenue3.setStadium("和平大体育场");
        sportVenue3.setMotionType("羽毛球");
        sportVenue3.setMotionProfile("羽毛球真好啊！！！");
        sportVenue3.setAgeUpperLimit(29);
        sportVenue3.setAgeLowerLimit(17);
        sportVenue3.setRent(5.6);
        sportVenue3.setOrderNumber(0);

        SportVenue sportVenue4 = new SportVenue();
        sportVenue4.setSerialNumber("212bab");
        sportVenue4.setSerialName("和平篮球场");
        sportVenue4.setDistrict("和平");
        sportVenue4.setStadium("和平大体育场");
        sportVenue4.setMotionType("篮球");
        sportVenue4.setMotionProfile("篮球很好么？？？");
        sportVenue4.setAgeUpperLimit(20);
        sportVenue4.setAgeLowerLimit(10);
        sportVenue4.setRent(43.8);
        sportVenue4.setOrderNumber(2);

        sportVenues.add(sportVenue1);
        sportVenues.add(sportVenue2);
        sportVenues.add(sportVenue3);
        sportVenues.add(sportVenue4);

        Order order1 = new Order();
        order1.setSerialNumber("1234per2018911");
        order1.setReservationDate(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:9:11:11:11"));
        order1.setReservationStadiumSerialNumber("212bab");
        order1.setLoanDate(new SimpleDateFormat("yyyy:MM:dd").parse("2018:9:20"));
        order1.setStartTime(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:9:20:09:00"));
        order1.setEndTime(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:9:20:12:30"));
        order1.setOnTime(true);
        order1.setId("1234per");

        Order order2 = new Order();
        order2.setSerialNumber("1234per2018910");
        order2.setReservationDate(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:9:10:9:15"));
        order2.setReservationStadiumSerialNumber("111aaa");
        order2.setLoanDate(new SimpleDateFormat("yyyy:MM:dd").parse("2018:9:19"));
        order2.setStartTime(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:9:19:13:20"));
        order2.setEndTime(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:9:19:17:50"));
        order2.setOnTime(true);
        order2.setId("1234per");

        Order order3 = new Order();
        order3.setSerialNumber("1234per201899");
        order3.setReservationDate(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:9:9:19:50"));
        order3.setReservationStadiumSerialNumber("212bab");
        order3.setLoanDate(new SimpleDateFormat("yyyy:MM:dd").parse("2018:9:18"));
        order3.setStartTime(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:9:18:16:15"));
        order3.setEndTime(new SimpleDateFormat("yyyy:MM:dd:HH:mm").parse("2018:9:18:20:45"));
        order3.setOnTime(true);
        order3.setId("1234per");

        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
    }
}