package app.mrquan.pojo;

import java.io.Serializable;

/**
 * 场地
 * 场地编号，场地名称，所属区域，所属场馆，适合运动类型，运动简介，准入年龄上限，准入年龄下限，租金,当前订单数量
 */
public class SportVenue implements Serializable {
    private String serialNumber;//key
    private String serialName;
    private String district;//区域
    private String stadium;//场馆
    private String motionType;//运动类型
    private String motionProfile;//运动简介
    private Integer ageUpperLimit;//年龄上限
    private Integer ageLowerLimit;//年龄下限
    private Double rent;//租金
    private Integer orderNumber;//当前订单数量 new
    @Override
    public String toString() {
        return "场地编号:"+serialNumber+"\t场地名称:"+serialName+"\t场地所属区域:"+district+"\t所属场馆:"+stadium+"\t运动类型:"+motionType+"\t运动简介:"+motionProfile+"\t年龄上限:"+ageUpperLimit+"\t年龄下限:"+ageLowerLimit+"租金:"+rent+"\t订单数量:"+orderNumber;
    }
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSerialName() {
        return serialName;
    }

    public void setSerialName(String serialName) {
        this.serialName = serialName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getMotionType() {
        return motionType;
    }

    public void setMotionType(String motionType) {
        this.motionType = motionType;
    }

    public String getMotionProfile() {
        return motionProfile;
    }

    public void setMotionProfile(String motionProfile) {
        this.motionProfile = motionProfile;
    }

    public Integer getAgeUpperLimit() {
        return ageUpperLimit;
    }

    public void setAgeUpperLimit(Integer ageUpperLimit) {
        this.ageUpperLimit = ageUpperLimit;
    }

    public Integer getAgeLowerLimit() {
        return ageLowerLimit;
    }

    public void setAgeLowerLimit(Integer ageLowerLimit) {
        this.ageLowerLimit = ageLowerLimit;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}
