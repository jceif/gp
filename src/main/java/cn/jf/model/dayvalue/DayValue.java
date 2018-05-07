package cn.jf.model.dayvalue;

import java.util.Date;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


public class DayValue implements Serializable {

  private int id; //

  private String companyCode; // 公司代码

  private Double startPrice; // 开盘价

  private Double endPrice; // 收盘价格

  private Double rate; // 涨跌值

  private Double totalMoney; // 流入流出总资金（单位：万）

  private Double huanShou; // 换手率

  private int date; // 日期 20180306

  private Date createTime; // 创建时间


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

  public Double getStartPrice() {
    return this.startPrice;
  }

  public void setStartPrice(Double startPrice) {
    this.startPrice = startPrice;
  }

  public Double getEndPrice() {
    return this.endPrice;
  }

  public void setEndPrice(Double endPrice) {
    this.endPrice = endPrice;
  }

  public Double getRate() {
    return this.rate;
  }

  public void setRate(Double rate) {
    this.rate = rate;
  }

  public Double getTotalMoney() {
    return this.totalMoney;
  }

  public void setTotalMoney(Double totalMoney) {
    this.totalMoney = totalMoney;
  }

  public Double getHuanShou() {
    return this.huanShou;
  }

  public void setHuanShou(Double huanShou) {
    this.huanShou = huanShou;
  }

  public int getDate() {
    return this.date;
  }

  public void setDate(int date) {
    this.date = date;
  }

  public Date getCreateTime() {
    return this.createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }


}
