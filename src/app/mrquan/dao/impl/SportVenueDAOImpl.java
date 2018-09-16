package app.mrquan.dao.impl;

import app.mrquan.dao.ISportVenueDAO;
import app.mrquan.dbc.DatabaseConnection;
import app.mrquan.pojo.SportVenue;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SportVenueDAOImpl implements ISportVenueDAO {
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private Connection con;
    private PreparedStatement preparedStatement;
    public SportVenueDAOImpl(){
        this.con = databaseConnection.getConnection();
    }

    @Override
    public List<SportVenue> findSportVenuesByName(String name) throws SQLException {//ok
        List<SportVenue> all = new ArrayList<>();
        try {
            preparedStatement = con.prepareStatement("select * from sportVenues where serialName = ?");
            preparedStatement.setString(1,name);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                SportVenue pojo = new SportVenue();
                //场地表 sportVenues
                String serialNumber = rs.getString(1);
                pojo.setSerialNumber(serialNumber);//场地编号
                pojo.setSerialName(rs.getString(2));//场地名称
                pojo.setDistrict(rs.getString(3));//所属区域
                pojo.setStadium(rs.getString(4));//所属场馆
                pojo.setMotionType(rs.getString(5));//适合运动类型
                pojo.setMotionProfile(rs.getString(6));//运动简介
                pojo.setAgeUpperLimit(rs.getInt(7));//准入年龄上限
                pojo.setAgeLowerLimit(rs.getInt(8));//准入年龄下限
                pojo.setRent(rs.getDouble(9));//租金
                /**
                 *  订单数量
                 *  orders表
                 *  pojo.setOrderNumber();//当前订单数量
                 */
                int abrogate = 0;
                preparedStatement = con.prepareStatement("select startTime from orders where reservationStadiumSerialNumber = ? and (cancel = ? or cancel is ?)");
                preparedStatement.setString(1,serialNumber);
                preparedStatement.setBoolean(2,false);
                preparedStatement.setNull(3,Types.BOOLEAN);
                ResultSet rsOrd = preparedStatement.executeQuery();
                while (rsOrd.next()){
                    rsOrd.getDate(1);
                    if((new Date().getTime())<(rsOrd.getDate(1).getTime())){
                        abrogate++;
                    }
                }
                pojo.setOrderNumber(abrogate);
                all.add(pojo);
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
    public List<SportVenue> list() throws SQLException {//ok
        List<SportVenue> all = new ArrayList<>();
        try {
            preparedStatement = con.prepareStatement("select * from sportVenues");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                SportVenue pojo = new SportVenue();
                //场地表 sportVenues
                String serialNumber = rs.getString(1);
                pojo.setSerialNumber(serialNumber);//场地编号
                pojo.setSerialName(rs.getString(2));//场地名称
                pojo.setDistrict(rs.getString(3));//所属区域
                pojo.setStadium(rs.getString(4));//所属场馆
                pojo.setMotionType(rs.getString(5));//适合运动类型
                pojo.setMotionProfile(rs.getString(6));//运动简介
                pojo.setAgeUpperLimit(rs.getInt(7));//准入年龄上限
                pojo.setAgeLowerLimit(rs.getInt(8));//准入年龄下限
                pojo.setRent(rs.getDouble(9));//租金
                /**
                 *  订单数量
                 *  orders表
                 *  pojo.setOrderNumber();//当前订单数量
                 *  判断取消
                 */
                int abrogate = 0;
                preparedStatement = con.prepareStatement("select startTime from orders where reservationStadiumSerialNumber = ? and (cancel = ? or cancel is ?)");
                preparedStatement.setString(1,serialNumber);
                preparedStatement.setBoolean(2,false);
                preparedStatement.setNull(3,Types.BOOLEAN);
                ResultSet rsOrd = preparedStatement.executeQuery();
                while (rsOrd.next()){
                    rsOrd.getDate(1);
                    if(new Date().getTime()<rsOrd.getDate(1).getTime()){
                        abrogate++;
                    }
                }
                pojo.setOrderNumber(abrogate);
                all.add(pojo);
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
    public int add(SportVenue pojo) throws SQLException {
        int lenAll = 0;
        try {
            con.setAutoCommit(false);//1,首先把Auto commit设置为false,不让它自动提交
            //表 sportVenues
            preparedStatement = con.prepareStatement("insert into sportVenues values (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,pojo.getSerialNumber());
            preparedStatement.setString(2,pojo.getSerialName());
            preparedStatement.setString(3,pojo.getDistrict());
            preparedStatement.setString(4,pojo.getStadium());
            preparedStatement.setString(5,pojo.getMotionType());
            preparedStatement.setString(6,pojo.getMotionProfile());
            preparedStatement.setInt(7,pojo.getAgeUpperLimit());
            preparedStatement.setInt(8,pojo.getAgeLowerLimit());
            preparedStatement.setDouble(9,pojo.getRent());

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
}
