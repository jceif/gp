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

  private int date; // 日期 20180306

  private Date createTime; // 创建时间


  private float circulationMoney;
  private float maxPrice;
  private float minPrice;
  private float volume;
  private float amount;
  private float diff;
  private float dea;
  private float macd;
  private float k;
  private float d;
  private float j;







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



  public float getCirculationMoney() {
    return circulationMoney;
  }

  public void setCirculationMoney(float circulationMoney) {
    this.circulationMoney = circulationMoney;
  }

  public float getMaxPrice() {
    return maxPrice;
  }

  public void setMaxPrice(float maxPrice) {
    this.maxPrice = maxPrice;
  }

  public float getMinPrice() {
    return minPrice;
  }

  public void setMinPrice(float minPrice) {
    this.minPrice = minPrice;
  }

  public float getVolume() {
    return volume;
  }

  public void setVolume(float volume) {
    this.volume = volume;
  }

  public float getAmount() {
    return amount;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }

  public float getDiff() {
    return diff;
  }

  public void setDiff(float diff) {
    this.diff = diff;
  }

  public float getDea() {
    return dea;
  }

  public void setDea(float dea) {
    this.dea = dea;
  }

  public float getMacd() {
    return macd;
  }

  public void setMacd(float macd) {
    this.macd = macd;
  }

  public float getK() {
    return k;
  }

  public void setK(float k) {
    this.k = k;
  }

  public float getD() {
    return d;
  }

  public void setD(float d) {
    this.d = d;
  }

  public float getJ() {
    return j;
  }

  public void setJ(float j) {
    this.j = j;
  }
}
