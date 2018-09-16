package app.mrquan.dao.impl;

import app.mrquan.dao.IPersonnelDAO;
import app.mrquan.dbc.DatabaseConnection;
import app.mrquan.factory.DAOFactory;
import app.mrquan.pojo.Personnel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonnelDAOImpl implements IPersonnelDAO {
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private Connection con;
    private PreparedStatement preparedStatement;
    public PersonnelDAOImpl(){
        this.con = databaseConnection.getConnection();
    }
    @Override
    public Personnel findPersonnelById(String id) throws SQLException {//ok
        Personnel pojo = null;
        try {
            preparedStatement = con.prepareStatement("select * from personnels where id = ?");
            preparedStatement.setString(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                //人员表 personnels
                pojo = new Personnel();
                pojo.setId(rs.getString(1));//id
                pojo.setPassword(rs.getString(2));//密码
                pojo.setName(rs.getString(3));//名字
                pojo.setSex(rs.getBoolean(4));//性别
                pojo.setAge(rs.getInt(5));//年龄
                pojo.setTelephone(rs.getString(6));//电话
                pojo.setEmail(rs.getString(7));//邮箱

                Double balance = rs.getDouble(8);//余额
                if (rs.wasNull()){
                    //为null
                    pojo.setBalance(null);
                }else {
                    pojo.setBalance(balance);
                }
                pojo.setDistrict(rs.getString(9));//区域
//                pojo.setAbrogate();
                pojo.setAdministrator(rs.getBoolean(10));//管理员
                pojo.setStadium(rs.getString(11));//区域
                /**
                 *  爽约
                 *  orders表
                 */
                int abrogate = 0;
                preparedStatement = con.prepareStatement("select cancel from orders where id = ?");
                preparedStatement.setString(1,id);
                ResultSet rsOrd = preparedStatement.executeQuery();
                while (rsOrd.next()){
//                    rsOrd.getBoolean("cancel");
                    if (rsOrd.getBoolean("cancel")){
                        abrogate++;
                    }
                }
                if (abrogate==0){
                    pojo.setAbrogate(null);
                }else {
                    pojo.setAbrogate(abrogate);
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
    public List<Personnel> clientList() throws SQLException {//ok
        List<Personnel> all = new ArrayList<>();
        try {
            preparedStatement = con.prepareStatement("select * from personnels where administrator = false");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                //人员表 personnels
                Personnel pojo = new Personnel();
                String id = rs.getString(1);
                pojo.setId(id);//id
                pojo.setPassword(rs.getString(2));//密码
                pojo.setName(rs.getString(3));//名字
                pojo.setSex(rs.getBoolean(4));//性别
                pojo.setAge(rs.getInt(5));//年龄
                pojo.setTelephone(rs.getString(6));//电话
                pojo.setEmail(rs.getString(7));//邮箱

                Double balance = rs.getDouble(8);//余额
                if (rs.wasNull()){
                    //为null
                    pojo.setBalance(null);
                }else {
                    pojo.setBalance(balance);
                }

                pojo.setDistrict(rs.getString(9));//区域
//                pojo.setAbrogate();
                pojo.setAdministrator(rs.getBoolean(10));//管理员
                pojo.setStadium(rs.getString(11));//所属场馆
                /**
                 *  爽约
                 *  orders表
                 */
                int abrogate = 0;
                preparedStatement = con.prepareStatement("select cancel from orders where id = ?");
                preparedStatement.setString(1,id);
                ResultSet rsOrd = preparedStatement.executeQuery();
                while (rsOrd.next()){
//                    rsOrd.getBoolean("cancel");
                    if (rsOrd.getBoolean("cancel")){
                        abrogate++;
                    }
                }
                if (abrogate==0){
                    pojo.setAbrogate(null);
                }else {
                    pojo.setAbrogate(abrogate);
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
    public int add(Personnel pojo) throws SQLException {//ok
        int lenAll = 0;
        try {
            con.setAutoCommit(false);//1,首先把Auto commit设置为false,不让它自动提交
            //人员表 personnels
            preparedStatement = con.prepareStatement("insert into personnels values (?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,pojo.getId());
            preparedStatement.setString(2,pojo.getPassword());
            preparedStatement.setString(3,pojo.getName());
            preparedStatement.setBoolean(4,pojo.getSex());
            preparedStatement.setInt(5,pojo.getAge());
            preparedStatement.setString(6,pojo.getTelephone());
            preparedStatement.setString(7,pojo.getEmail());
            if (pojo.getBalance()==null){
//                System.out.println("空值！！！！！");
                preparedStatement.setNull(8,Types.DOUBLE);
            }else {
//                System.out.println("不为空");
                preparedStatement.setDouble(8,pojo.getBalance());
            }
            preparedStatement.setString(9,pojo.getDistrict());
            preparedStatement.setBoolean(10,pojo.getAdministrator());
            preparedStatement.setString(11,pojo.getStadium());

            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            con.commit();//2,进行手动提交（commit
            lenAll = 1;
        } catch (SQLException e) {
            System.out.println(e);
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
    public int update(Personnel pojo) throws SQLException {//ok
        int lenAll = 0;
        try {
            con.setAutoCommit(false);//1,首先把Auto commit设置为false,不让它自动提交
            //人员表 personnels
            preparedStatement = con.prepareStatement("delete from personnels where id = ?;");
            preparedStatement.setString(1,pojo.getId());
            preparedStatement.addBatch();
            int as[] = preparedStatement.executeBatch();
            if (as[0]==0){
//                System.out.println("没有数据");
            }else {
                preparedStatement = con.prepareStatement("insert into personnels values (?,?,?,?,?,?,?,?,?,?,?)");
                preparedStatement.setString(1,  pojo.getId());
                preparedStatement.setString(2,pojo.getPassword());
                preparedStatement.setString(3,pojo.getName());
                preparedStatement.setBoolean(4,pojo.getSex());
                preparedStatement.setInt(5,pojo.getAge());
                preparedStatement.setString(6,pojo.getTelephone());
                preparedStatement.setString(7,pojo.getEmail());

                if (pojo.getBalance()==null){
                    preparedStatement.setNull(8,Types.DOUBLE);
                }else {
                    preparedStatement.setDouble(8,pojo.getBalance());
                }

                preparedStatement.setString(9,pojo.getDistrict());
                preparedStatement.setBoolean(10,pojo.getAdministrator());
                preparedStatement.setString(11,pojo.getStadium());

                preparedStatement.addBatch();
                preparedStatement.executeBatch();
                con.commit();//2,进行手动提交（commit
                lenAll = 1;
            }
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