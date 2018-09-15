package app.mrquan.dao.impl;

import app.mrquan.dao.IOrderDAO;
import app.mrquan.dbc.DatabaseConnection;
import app.mrquan.pojo.Order;
import app.mrquan.pojo.Personnel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public List<Order> AllList() throws SQLException {
        List<Order> all = new ArrayList<>();
        try {
            preparedStatement = con.prepareStatement("select * from orders");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Order pojo = new Order();
                pojo.setSerialNumber(rs.getString(1));
                pojo.setReservationDate(rs.getDate(2));
                pojo.setReservationStadiumSerialNumber(rs.getString(3));
                pojo.setLoanDate(rs.getDate(4));
                pojo.setStartTime(rs.getDate(5));
                pojo.setEndTime(rs.getDate(6));
                pojo.setOnTime(rs.getBoolean(7));
                pojo.setId(rs.getString(8));
                pojo.setCancel(rs.getBoolean(9));
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
    public List<Order> list() throws SQLException {
        List<Order> all = new ArrayList<>();
        try {
            preparedStatement = con.prepareStatement("select * from orders where cancel = ?");
            preparedStatement.setBoolean(1,false);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                if(new Date().getTime() < rs.getDate(5).getTime()){
                    Order pojo = new Order();
                    pojo.setSerialNumber(rs.getString(1));
                    pojo.setReservationDate(rs.getDate(2));
                    pojo.setReservationStadiumSerialNumber(rs.getString(3));
                    pojo.setLoanDate(rs.getDate(4));
                    pojo.setStartTime(rs.getDate(5));
                    pojo.setEndTime(rs.getDate(6));
                    pojo.setOnTime(rs.getBoolean(7));
                    pojo.setId(rs.getString(8));
                    pojo.setCancel(rs.getBoolean(9));
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
    public int add(Order pojo) throws SQLException {
        int lenAll = 0;
        try {
            con.setAutoCommit(false);//1,首先把Auto commit设置为false,不让它自动提交
            //表 orders
            preparedStatement = con.prepareStatement("insert into orders values (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,pojo.getSerialNumber());
            preparedStatement.setDate(2,new java.sql.Date(pojo.getReservationDate().getTime()));
            preparedStatement.setString(3,pojo.getReservationStadiumSerialNumber());
            preparedStatement.setDate(4,new java.sql.Date(pojo.getLoanDate().getTime()));
            preparedStatement.setDate(5,new java.sql.Date(pojo.getStartTime().getTime()));
            preparedStatement.setDate(6,new java.sql.Date(pojo.getEndTime().getTime()));
            preparedStatement.setBoolean(7,pojo.getOnTime());
            preparedStatement.setString(8,pojo.getId());
            preparedStatement.setBoolean(9,pojo.getCancel());

            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            con.commit();//2,进行手动提交（commit
            lenAll = 1;
        } catch (SQLException e) {
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
                preparedStatement.setDate(2,new java.sql.Date(pojos.get(i).getReservationDate().getTime()));
                preparedStatement.setString(3,pojos.get(i).getReservationStadiumSerialNumber());
                preparedStatement.setDate(4,new java.sql.Date(pojos.get(i).getLoanDate().getTime()));
                preparedStatement.setDate(5,new java.sql.Date(pojos.get(i).getStartTime().getTime()));
                preparedStatement.setDate(6,new java.sql.Date(pojos.get(i).getEndTime().getTime()));
                preparedStatement.setBoolean(7,pojos.get(i).getOnTime());
                preparedStatement.setString(8,pojos.get(i).getId());
                preparedStatement.setBoolean(9,pojos.get(i).getCancel());

                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            con.commit();//2,进行手动提交（commit
            lenAll = pojos.size();
        }catch (SQLException e){
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
            preparedStatement = con.prepareStatement("delete from orders where serialNumbe = ?;");
            preparedStatement.setString(1,order.getSerialNumber());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();

            //表 orders
            preparedStatement = con.prepareStatement("insert into orders values (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,order.getSerialNumber());
            preparedStatement.setDate(2,new java.sql.Date(order.getReservationDate().getTime()));
            preparedStatement.setString(3,order.getReservationStadiumSerialNumber());
            preparedStatement.setDate(4,new java.sql.Date(order.getLoanDate().getTime()));
            preparedStatement.setDate(5,new java.sql.Date(order.getStartTime().getTime()));
            preparedStatement.setDate(6,new java.sql.Date(order.getEndTime().getTime()));
            preparedStatement.setBoolean(7,order.getOnTime());
            preparedStatement.setString(8,order.getId());
            preparedStatement.setBoolean(9,order.getCancel());

            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            con.commit();//2,进行手动提交（commit
            lenAll = 1;
        } catch (SQLException e) {
            con.rollback();
            throw e;
        }finally {
            con.setAutoCommit(true);//3,提交完成后回复现场将Auto commit,还原为true,
            databaseConnection.close();
            return lenAll;
        }
    }
}
