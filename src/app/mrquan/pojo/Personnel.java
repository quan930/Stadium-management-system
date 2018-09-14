package app.mrquan.pojo;

import java.io.Serializable;

/**
 *  管理员/顾客
 *  公共属性:ID，密码，名字，性别(true为男)，年龄，电话，邮箱
 *  管理员与普通用户:administrator为真管理员，反之则顾客
 *  顾客特有属性:余额，区域
 *  管理员特有属性:所属场馆
 *  注：如personnel为顾客场馆属性为null，反之余额，区域皆为null
 */
public class Personnel implements Serializable {
    /**
     * 爽约
     */
    private String id;//key
    private String password;
    private String name;
    private Boolean sex;
    private Integer age;
    private String telephone;
    private String email;
    private Double balance;//余额
    private String district;//区域
    private Boolean administrator;//管理员
    private String stadium;//场馆

    @Override
    public String toString() {
        return "ID:"+id+"\t密码:"+password+"\t名字:"+name+"\t性别:"+sex+"\t年龄:"+age+"\t电话:"+telephone+"\t邮箱:"+email+"\t是否管理员:"+administrator+"\t顾客余额:"+balance+"\t顾客区域:"+district+"管理员场馆"+stadium;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Boolean getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Boolean administrator) {
        this.administrator = administrator;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }
}
