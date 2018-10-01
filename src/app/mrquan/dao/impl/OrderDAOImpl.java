package app.mrquan.dao.impl;

import app.mrquan.dao.IOrderDAO;
import app.mrquan.dbc.DatabaseConnection;
import app.mrquan.pojo.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAOImpl implements IOrderDAO {
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private Connection con;
    private PreparedStatement preparedStatement;
    public OrderDAOImpl(){
        this.con = databaseConnection.getConnection();
    }

    @Override
    public Order findOrderByNumber(String number) throws SQLException {
        Order pojo = null;
        try {
            preparedStatement = con.prepareStatement("select * from orders where serialNumber = ?");
            preparedStatement.setString(1,number);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                pojo = new Order();
                pojo.setSerialNumber(rs.getString(1));
                pojo.setReservationDate(rs.getTimestamp(2));
                pojo.setReservationStadiumSerialNumber(rs.getString(3));
                pojo.setLoanDate(rs.getDate(4));
                pojo.setStartTime(rs.getTimestamp(5));
                pojo.setEndTime(rs.getTimestamp(6));

                Boolean balance = rs.getBoolean(7);//是否按时到场
                if (rs.wasNull()){
                    //为null
                    pojo.setOnTime(null);
                }else {
                    pojo.setOnTime(balance);
                }
                pojo.setId(rs.getString(8));

                Boolean cancel = rs.getBoolean(9);//取消
                if (rs.wasNull()){
                    pojo.setCancel(null);
                }else {
                    pojo.setCancel(cancel);
                }
            }
        } catch (SQLException e) {
            throw e;
        }finally {
            databaseConnection.close();
            return pojo;
        }
    }

    @Override
    public List<Order> findAllOrdersBySportNumber(String number) throws SQLException {
        List<Order> all = new ArrayList<>();
        try {
            preparedStatement = con.prepareStatement("select * from orders where reservationStadiumSerialNumber = ?");
            preparedStatement.setString(1,number);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Order pojo = new Order();
                pojo.setSerialNumber(rs.getString(1));
                pojo.setReservationDate(rs.getTimestamp(2));
                pojo.setReservationStadiumSerialNumber(rs.getString(3));
                pojo.setLoanDate(rs.getDate(4));
                pojo.setStartTime(rs.getTimestamp(5));
                pojo.setEndTime(rs.getTimestamp(6));

                Boolean balance = rs.getBoolean(7);//是否按时到场
                if (rs.wasNull()){
                    //为null
                    pojo.setOnTime(null);
                }else {
                    pojo.setOnTime(balance);
                }
                pojo.setId(rs.getString(8));

                Boolean cancel = rs.getBoolean(9);//取消
                if (rs.wasNull()){
                    pojo.setCancel(null);
                }else {
                    pojo.setCancel(cancel);
                }

                all.add(pojo);
            }
        } catch (SQLException e) {
            throw e;
        }finally {
            databaseConnection.close();
            return all;
        }
    }

    @Override
    public List<Order> findOrdersBySportNumber(String number) throws SQLException {
        List<Order> all = new ArrayList<>();
        try {
            preparedStatement = con.prepareStatement("select * from orders where reservationStadiumSerialNumber = ? and (cancel = ? or cancel is ?)");
            preparedStatement.setString(1,number);
            preparedStatement.setBoolean(2,false);
            preparedStatement.setNull(3,Types.BOOLEAN);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                if(new Date().getTime() < rs.getDate(5).getTime()){
                    Order pojo = new Order();
                    pojo.setSerialNumber(rs.getString(1));
                    pojo.setReservationDate(rs.getTimestamp(2));
                    pojo.setReservationStadiumSerialNumber(rs.getString(3));
                    pojo.setLoanDate(rs.getDate(4));
                    pojo.setStartTime(rs.getTimestamp(5));
                    pojo.setEndTime(rs.getTimestamp(6));

                    Boolean balance = rs.getBoolean(7);//是否按时到场
                    if (rs.wasNull()){
                        //为null
                        pojo.setOnTime(null);
                    }else {
                        pojo.setOnTime(balance);
                    }
                    pojo.setId(rs.getString(8));

                    Boolean cancel = rs.getBoolean(9);//取消
                    if (rs.wasNull()){
                        pojo.setCancel(null);
                    }else {
                        pojo.setCancel(cancel);
                    }
                    all.add(pojo);
                }else {
                    continue;
                }
            }
        } catch (SQLException e) {
            throw e;
        }finally {
            databaseConnection.close();
            return all;
        }
    }

    @Override
    public List<Order> findAllOrdersByStadium(String stadium) throws SQLException {
        List<Order> all = new ArrayList<>();
        try {
            preparedStatement = con.prepareStatement("select * from orders left join sportVenues on orders.reservationStadiumSerialNumber = sportVenues.serialNumber where sportVenues.stadium = ？");
            preparedStatement.setString(1,stadium);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Order pojo = new Order();
                pojo.setSerialNumber(rs.getString(1));
                pojo.setReservationDate(rs.getTimestamp(2));
                pojo.setReservationStadiumSerialNumber(rs.getString(3));
                pojo.setLoanDate(rs.getDate(4));
                pojo.setStartTime(rs.getTimestamp(5));
                pojo.setEndTime(rs.getTimestamp(6));

                Boolean balance = rs.getBoolean(7);//是否按时到场
                if (rs.wasNull()){
                    //为null
                    pojo.setOnTime(null);
                }else {
                    pojo.setOnTime(balance);
                }
                pojo.setId(rs.getString(8));

                Boolean cancel = rs.getBoolean(9);//取消
                if (rs.wasNull()){
                    pojo.setCancel(null);
                }else {
                    pojo.setCancel(cancel);
                }

                all.add(pojo);
            }
        } catch (SQLException e) {
            throw e;
        }finally {
            databaseConnection.close();
            return all;
        }
    }

    @Override
    public List<Order> findOrdersByStadium(String stadium) throws SQLException {
        List<Order> all = new ArrayList<>();
        try {
            preparedStatement = con.prepareStatement("select * from orders left join sportVenues on orders.reservationStadiumSerialNumber = sportVenues.serialNumber where sportVenues.stadium = ? and (orders.cancel = ? or orders.cancel is ?)");
            preparedStatement.setString(1,stadium);
            preparedStatement.setBoolean(2,false);
            preparedStatement.setNull(3,Types.BOOLEAN);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                if(new Date().getTime() < rs.getDate(5).getTime()){
                    Order pojo = new Order();
                    pojo.setSerialNumber(rs.getString(1));
                    pojo.setReservationDate(rs.getTimestamp(2));
                    pojo.setReservationStadiumSerialNumber(rs.getString(3));
                    pojo.setLoanDate(rs.getDate(4));
                    pojo.setStartTime(rs.getTimestamp(5));
                    pojo.setEndTime(rs.getTimestamp(6));

                    Boolean balance = rs.getBoolean(7);//是否按时到场
                    if (rs.wasNull()){
                        //为null
                        pojo.setOnTime(null);
                    }else {
                        pojo.setOnTime(balance);
                    }
                    pojo.setId(rs.getString(8));

                    Boolean cancel = rs.getBoolean(9);//取消
                    if (rs.wasNull()){
                        pojo.setCancel(null);
                    }else {
                        pojo.setCancel(cancel);
                    }
                    all.add(pojo);
                }else {
                    continue;
                }
            }
        } catch (SQLException e) {
            throw e;
        }finally {
            databaseConnection.close();
            return all;
        }
    }

    @Override
    public List<Order> findAllOrdersByUser(String id) throws SQLException {
        List<Order> all = new ArrayList<>();
        try {
            preparedStatement = con.prepareStatement("select * from orders where id = ?");
            preparedStatement.setString(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Order pojo = new Order();
                pojo.setSerialNumber(rs.getString(1));
                pojo.setReservationDate(rs.getTimestamp(2));
                pojo.setReservationStadiumSerialNumber(rs.getString(3));
                pojo.setLoanDate(rs.getDate(4));
                pojo.setStartTime(rs.getTimestamp(5));
                pojo.setEndTime(rs.getTimestamp(6));

                Boolean balance = rs.getBoolean(7);//是否按时到场
                if (rs.wasNull()){
                    //为null
                    pojo.setOnTime(null);
                }else {
                    pojo.setOnTime(balance);
                }
                pojo.setId(rs.getString(8));

                Boolean cancel = rs.getBoolean(9);//取消
                if (rs.wasNull()){
                    pojo.setCancel(null);
                }else {
                    pojo.setCancel(cancel);
                }

                all.add(pojo);
            }
        } catch (SQLException e) {
            throw e;
        }finally {
            databaseConnection.close();
            return all;
        }
    }

    @Override
    public List<Order> findOrdersByUser(String id) throws SQLException {
        List<Order> all = new ArrayList<>();

//        select * from orders where (id = 'a00002' and startTime > now()) and (cancel = false or cancel is null);
        try {
            preparedStatement = con.prepareStatement("select * from orders where (id = ? and startTime > now()) and (cancel = ? or cancel is ?)");
            preparedStatement.setString(1,id);
            preparedStatement.setBoolean(2,false);
            preparedStatement.setNull(3,Types.BOOLEAN);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Order pojo = new Order();
                pojo.setSerialNumber(rs.getString(1));
                pojo.setReservationDate(rs.getTimestamp(2));
                pojo.setReservationStadiumSerialNumber(rs.getString(3));
                pojo.setLoanDate(rs.getDate(4));
                pojo.setStartTime(rs.getTimestamp(5));
                pojo.setEndTime(rs.getTimestamp(6));

                Boolean balance = rs.getBoolean(7);//是否按时到场
                if (rs.wasNull()){
                    //为null
                    pojo.setOnTime(null);
                }else {
                    pojo.setOnTime(balance);
                }
                pojo.setId(rs.getString(8));

                Boolean cancel = rs.getBoolean(9);//取消
                if (rs.wasNull()){
                    pojo.setCancel(null);
                }else {
                    pojo.setCancel(cancel);
                }

                all.add(pojo);
            }
        } catch (SQLException e) {
            throw e;
        }finally {
            databaseConnection.close();
            return all;
        }
    }

    @Override
    public List<Order> AllList() throws SQLException {//ok
        List<Order> all = new ArrayList<>();
        try {
            preparedStatement = con.prepareStatement("select * from orders");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Order pojo = new Order();
                pojo.setSerialNumber(rs.getString(1));
                pojo.setReservationDate(rs.getTimestamp(2));
                pojo.setReservationStadiumSerialNumber(rs.getString(3));
                pojo.setLoanDate(rs.getDate(4));
                pojo.setStartTime(rs.getTimestamp(5));
                pojo.setEndTime(rs.getTimestamp(6));

                Boolean balance = rs.getBoolean(7);//是否按时到场
                if (rs.wasNull()){
                    //为null
                    pojo.setOnTime(null);
                }else {
                    pojo.setOnTime(balance);
                }
                pojo.setId(rs.getString(8));

                Boolean cancel = rs.getBoolean(9);//取消
                if (rs.wasNull()){
                    pojo.setCancel(null);
                }else {
                    pojo.setCancel(cancel);
                }

                all.add(pojo);
            }
        } catch (SQLException e) {
            throw e;
        }finally {
            databaseConnection.close();
            return all;
        }
    }

    @Override
    public List<Order> list() throws SQLException {//ok
        List<Order> all = new ArrayList<>();
        try {
            preparedStatement = con.prepareStatement("select * from orders where cancel = ? or cancel is ?");
            preparedStatement.setBoolean(1,false);
            preparedStatement.setNull(2,Types.BOOLEAN);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                if(new Date().getTime() < rs.getDate(5).getTime()){
                    Order pojo = new Order();
                    pojo.setSerialNumber(rs.getString(1));
                    pojo.setReservationDate(rs.getTimestamp(2));
                    pojo.setReservationStadiumSerialNumber(rs.getString(3));
                    pojo.setLoanDate(rs.getDate(4));
                    pojo.setStartTime(rs.getTimestamp(5));
                    pojo.setEndTime(rs.getTimestamp(6));

                    Boolean balance = rs.getBoolean(7);//是否按时到场
                    if (rs.wasNull()){
                        //为null
                        pojo.setOnTime(null);
                    }else {
                        pojo.setOnTime(balance);
                    }
                    pojo.setId(rs.getString(8));

                    Boolean cancel = rs.getBoolean(9);//取消
                    if (rs.wasNull()){
                        pojo.setCancel(null);
                    }else {
                        pojo.setCancel(cancel);
                    }
                    all.add(pojo);
                }else {
                    continue;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }finally {
            databaseConnection.close();
            return all;
        }
    }

    @Override
    public int add(Order pojo) throws SQLException {
        int lenAll = 0;
        try {
            con.setAutoCommit(false);//1,首先把Auto commit设置为false,不让它自动提交
            //表 orders
            preparedStatement = con.prepareStatement("insert into orders values (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,pojo.getSerialNumber());
            preparedStatement.setTimestamp(2,new java.sql.Timestamp(pojo.getReservationDate().getTime()));
            preparedStatement.setString(3,pojo.getReservationStadiumSerialNumber());
            preparedStatement.setDate(4,new java.sql.Date(pojo.getLoanDate().getTime()));
            preparedStatement.setTimestamp(5,new java.sql.Timestamp(pojo.getStartTime().getTime()));
            preparedStatement.setTimestamp(6,new java.sql.Timestamp(pojo.getEndTime().getTime()));

            if (pojo.getOnTime()==null){
//                System.out.println("空值！！！！！");
                preparedStatement.setNull(7,Types.DOUBLE);
            }else {
//                System.out.println("不为空");
                preparedStatement.setBoolean(7,pojo.getOnTime());
            }
            preparedStatement.setString(8,pojo.getId());
            if (pojo.getCancel()==null){
//                System.out.println("空值！！！！！");
                preparedStatement.setNull(9,Types.DOUBLE);
            }else {
//                System.out.println("不为空");
                preparedStatement.setBoolean(9,pojo.getCancel());
            }

            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            con.commit();//2,进行手动提交（commit
            lenAll = 1;
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        }finally {
            con.setAutoCommit(true);//3,提交完成后回复现场将Auto commit,还原为true,
            databaseConnection.close();
            return lenAll;
        }
    }

    @Override
    public int add(List<Order> pojos) throws SQLException {
        int lenAll=0;
        try {
            con.setAutoCommit(false);//1,首先把Auto commit设置为false,不让它自动提交
            for (int i = 0; i < pojos.size(); i++) {
                //表 orders
                preparedStatement = con.prepareStatement("insert into orders values (?,?,?,?,?,?,?,?,?)");
                preparedStatement.setString(1,pojos.get(i).getSerialNumber());
                preparedStatement.setTimestamp(2,new java.sql.Timestamp(pojos.get(i).getReservationDate().getTime()));
                preparedStatement.setString(3,pojos.get(i).getReservationStadiumSerialNumber());
                preparedStatement.setDate(4,new java.sql.Date(pojos.get(i).getLoanDate().getTime()));
                preparedStatement.setTimestamp(5,new java.sql.Timestamp(pojos.get(i).getStartTime().getTime()));
                preparedStatement.setTimestamp(6,new java.sql.Timestamp(pojos.get(i).getEndTime().getTime()));

                if (pojos.get(i).getOnTime()==null){
//                System.out.println("空值！！！！！");
                    preparedStatement.setNull(7,Types.DOUBLE);
                }else {
//                System.out.println("不为空");
                    preparedStatement.setBoolean(7,pojos.get(i).getOnTime());
                }
                preparedStatement.setString(8,pojos.get(i).getId());
                if (pojos.get(i).getCancel()==null){
//                System.out.println("空值！！！！！");
                    preparedStatement.setNull(9,Types.DOUBLE);
                }else {
//                System.out.println("不为空");
                    preparedStatement.setBoolean(9,pojos.get(i).getCancel());
                }

                preparedStatement.addBatch();
                preparedStatement.executeBatch();
            }
            con.commit();//2,进行手动提交（commit
            lenAll = pojos.size();
        }catch (SQLException e){
//            e.printStackTrace();
            con.rollback();
            throw e;
        }finally {
            con.setAutoCommit(true);//3,提交完成后回复现场将Auto commit,还原为true,
            databaseConnection.close();
            return lenAll;
        }
    }

    @Override
    public int update(Order order) throws SQLException {
        int lenAll = 0;
        try {
            con.setAutoCommit(false);//1,首先把Auto commit设置为false,不让它自动提交
            //表 orders
            preparedStatement = con.prepareStatement("delete from orders where serialNumber = ?");
            preparedStatement.setString(1,order.getSerialNumber());
            preparedStatement.addBatch();
            int as[] = preparedStatement.executeBatch();
            if (as[0]==0){
//                System.out.println("没有数据");
            }else {
                //表 orders
                preparedStatement = con.prepareStatement("insert into orders values (?,?,?,?,?,?,?,?,?)");
                preparedStatement.setString(1,order.getSerialNumber());
                preparedStatement.setTimestamp(2,new java.sql.Timestamp(order.getReservationDate().getTime()));
                preparedStatement.setString(3,order.getReservationStadiumSerialNumber());
                preparedStatement.setDate(4,new java.sql.Date(order.getLoanDate().getTime()));
                preparedStatement.setTimestamp(5,new java.sql.Timestamp(order.getStartTime().getTime()));
                preparedStatement.setTimestamp(6,new java.sql.Timestamp(order.getEndTime().getTime()));

                if (order.getOnTime()==null){
//                    System.out.println("空值！！！！！");
                    preparedStatement.setNull(7,Types.DOUBLE);
                }else {
//                System.out.println("不为空");
                    preparedStatement.setBoolean(7,order.getOnTime());
                }
                preparedStatement.setString(8,order.getId());
                if (order.getCancel()==null){
//                System.out.println("空值！！！！！");
                    preparedStatement.setNull(9,Types.DOUBLE);
                }else {
//                System.out.println("不为空");
                    preparedStatement.setBoolean(9,order.getCancel());
                }

                preparedStatement.addBatch();
                preparedStatement.executeBatch();
                con.commit();//2,进行手动提交（commit
                lenAll = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        }finally {
            con.setAutoCommit(true);//3,提交完成后回复现场将Auto commit,还原为true,
            databaseConnection.close();
            return lenAll;
        }
    }
}