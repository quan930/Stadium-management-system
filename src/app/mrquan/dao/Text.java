package app.mrquan.dao;

import app.mrquan.dbc.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Text {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection con = databaseConnection.getConnection();
        PreparedStatement preparedStatement;
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


        preparedStatement = con.prepareStatement("insert into personnels values (?,?,?,?,?,?,?,?,?,?,?)");
        preparedStatement.setString(1,"1ffg899r");
        preparedStatement.setString(2,"quan");
        preparedStatement.setString(3,"pjkbv");
        preparedStatement.setBoolean(4,true);
        preparedStatement.setInt(5,24);
        preparedStatement.setString(6,"13804128609");
        preparedStatement.setString(7,"14@qq.com");
        preparedStatement.setDouble(8,100);
        preparedStatement.setString(9,null);
        preparedStatement.setBoolean(10,false);
        preparedStatement.setString(11,"鸟巢体育馆");

//        pojo.setId(rs.getString(1));//id
//        pojo.setPassword(rs.getString(2));//密码
//        pojo.setName(rs.getString(3));//名字
//        pojo.setSex(rs.getBoolean(4));//性别
//        pojo.setAge(rs.getInt(5));//年龄
//        pojo.setTelephone(rs.getString(6));//电话
//        pojo.setEmail(rs.getString(7));//邮箱
//        pojo.setBalance(rs.getDouble(8));//余额
//        pojo.setDistrict(rs.getString(9));//区域
////                pojo.setAbrogate();
//        pojo.setAdministrator(rs.getBoolean(10));//管理员
//        pojo.setStadium(rs.getString(11));//区域

        int len = preparedStatement.executeUpdate();
        System.out.printf(String.valueOf(len));
        preparedStatement.close();
        con.close();
        databaseConnection.close();
    }
}
