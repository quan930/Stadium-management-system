package app.mrquan.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单
 * 订单编号，预定日期，预定场地编号，租借场地日期，租借场地开始时间，租借场地结束时间，是否按时到场,顾客id,是否取消
 */
public class Order implements Serializable {
    private String serialNumber;
    private Date reservationDate;
    private String reservationStadiumSerialNumber;
    private Date loanDate;
    private Date startTime;
    private Date endTime;
    private Boolean onTime;
    private String id;//new
    private Boolean cancel;//new 取消true

    @Override
    public String toString() {
        return "订单编号:"+serialNumber+"\t预定日期:"+reservationDate+"\t预定场地编号:"+reservationStadiumSerialNumber+"\t租借场地日期:"+loanDate+"\t租借场地开始时间:"+startTime+"\t租借场地结束时间:"+endTime+"\t是否按时到场:"+onTime;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationStadiumSerialNumber() {
        return reservationStadiumSerialNumber;
    }

    public void setReservationStadiumSerialNumber(String reservationStadiumSerialNumber) {
        this.reservationStadiumSerialNumber = reservationStadiumSerialNumber;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getOnTime() {
        return onTime;
    }

    public void setOnTime(Boolean onTime) {
        this.onTime = onTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getCancel() {
        return cancel;
    }

    public void setCancel(Boolean cancel) {
        this.cancel = cancel;
    }
}
