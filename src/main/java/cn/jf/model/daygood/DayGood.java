package cn.jf.model.daygood;


import java.util.Date;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


public class DayGood implements Serializable {

  private int id; //

  private String companyCode; // 公司代码

  private Double price; // 当前价格

  private Double rate; // 当前跌幅

  private Double mainMoney; // 主力净流入 万为单位

  private Double mainRate; // 主力占比

  private Double chaodaMoney; // 超大单净额  万为单位

  private Double chaodaRate; // 超大单占比

  private Double daMoney; // 大单净额  万为单位

  private Double daRate; // 大单占比

  private int date; // 当前日期 20180302

  private String time; // 当前时间 121212 时分

  private Date originalTime; // 原始时间(准确时间）


  private Double endRate;//收盘Rate，数据库无此字段
  private Double endMainMoney;//收盘流入，数据库无此字段

  private Double nextRate;//第二天收盘价，数据库无此字段


  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCompanyCode() {
    return this.companyCode;
  }

  public void setCompanyCode(String companyCode) {
    this.companyCode = companyCode;
  }

  public Double getPrice() {
    return this.price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Double getRate() {
    return this.rate;
  }

  public void setRate(Double rate) {
    this.rate = rate;
  }

  public Double getMainMoney() {
    return this.mainMoney;
  }

  public void setMainMoney(Double mainMoney) {
    this.mainMoney = mainMoney;
  }

  public Double getMainRate() {
    return this.mainRate;
  }

  public void setMainRate(Double mainRate) {
    this.mainRate = mainRate;
  }

  public Double getChaodaMoney() {
    return this.chaodaMoney;
  }

  public void setChaodaMoney(Double chaodaMoney) {
    this.chaodaMoney = chaodaMoney;
  }

  public Double getChaodaRate() {
    return this.chaodaRate;
  }

  public void setChaodaRate(Double chaodaRate) {
    this.chaodaRate = chaodaRate;
  }

  public Double getDaMoney() {
    return this.daMoney;
  }

  public void setDaMoney(Double daMoney) {
    this.daMoney = daMoney;
  }

  public Double getDaRate() {
    return this.daRate;
  }

  public void setDaRate(Double daRate) {
    this.daRate = daRate;
  }

  public int getDate() {
    return this.date;
  }

  public void setDate(int date) {
    this.date = date;
  }

  public String getTime() {
    return this.time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public Date getOriginalTime() {
    return this.originalTime;
  }

  public void setOriginalTime(Date originalTime) {
    this.originalTime = originalTime;
  }


  public Double getEndRate() {
    return endRate;
  }

  public void setEndRate(Double endRate) {
    this.endRate = endRate;
  }

  public Double getNextRate() {
    return nextRate;
  }

  public void setNextRate(Double nextRate) {
    this.nextRate = nextRate;
  }

  public Double getEndMainMoney() {
    return endMainMoney;
  }

  public void setEndMainMoney(Double endMainMoney) {
    this.endMainMoney = endMainMoney;
  }
}
