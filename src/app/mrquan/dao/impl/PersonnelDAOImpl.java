package app.mrquan.dao.impl;

import app.mrquan.dao.IPersonnelDAO;
import app.mrquan.dbc.DatabaseConnection;
import app.mrquan.pojo.Personnel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public Personnel findPersonnelById(String id) throws SQLException {
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
                pojo.setBalance(rs.getDouble(8));//余额
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
                pojo.setAbrogate(abrogate);
            }
        } catch (SQLException e) {
            throw e;
        }finally {
            databaseConnection.close();
            return pojo;
        }
    }

    @Override
    public List<Personnel> clientList() throws SQLException {
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
                pojo.setBalance(rs.getDouble(8));//余额
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
                pojo.setAbrogate(abrogate);
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
    public int add(Personnel pojo) throws SQLException {
        int lenAll = 0;
        try {
            con.setAutoCommit(false);//1,首先把Auto commit设置为false,不让它自动提交
            //人员表 personnels
            preparedStatement = con.prepareStatement("insert into personnels values (?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,  pojo.getId());
            preparedStatement.setString(2,pojo.getPassword());
            preparedStatement.setString(3,pojo.getName());
            preparedStatement.setBoolean(4,pojo.getSex());
            preparedStatement.setInt(5,pojo.getAge());
            preparedStatement.setString(6,pojo.getTelephone());
            preparedStatement.setString(7,pojo.getEmail());
            preparedStatement.setDouble(8,pojo.getBalance());
            preparedStatement.setString(9,pojo.getDistrict());
            preparedStatement.setBoolean(10,pojo.getAdministrator());
            preparedStatement.setString(11,pojo.getStadium());

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
    public int update(Personnel pojo) throws SQLException {
        int lenAll = 0;
        try {
            con.setAutoCommit(false);//1,首先把Auto commit设置为false,不让它自动提交
            //人员表 personnels
            preparedStatement = con.prepareStatement("delete from tstudent where id = ?;");
            preparedStatement.setString(1,pojo.getId());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();

            preparedStatement = con.prepareStatement("insert into personnels values (?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,  pojo.getId());
            preparedStatement.setString(2,pojo.getPassword());
            preparedStatement.setString(3,pojo.getName());
            preparedStatement.setBoolean(4,pojo.getSex());
            preparedStatement.setInt(5,pojo.getAge());
            preparedStatement.setString(6,pojo.getTelephone());
            preparedStatement.setString(7,pojo.getEmail());
            preparedStatement.setDouble(8,pojo.getBalance());
            preparedStatement.setString(9,pojo.getDistrict());
            preparedStatement.setBoolean(10,pojo.getAdministrator());
            preparedStatement.setString(11,pojo.getStadium());

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
