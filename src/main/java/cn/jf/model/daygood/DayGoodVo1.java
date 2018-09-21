package cn.jf.model.daygood;


import java.io.Serializable;



public class DayGoodVo1 implements Serializable {


  private String companyCode; // 公司代码
  private int date; // 当前日期 20180302

  private Double prePrice; // 当前价格
  private Double preRate; // 当前跌幅
  private Double preInflow; // 主力净流入 万为单位
  private Double preTime; // 收盘主力净流入 万为单位

  private Double lastPrice; // 收盘价格
  private Double lastRate; // 收盘跌幅
  private Double lastInflow; // 收盘主力净流入 万为单位
  private Double lastTime; // 收盘主力净流入 万为单位


  private Double twoRate; //收盘价格
  private Double twoStartPrice; //第二天开始 价格
  private Double twoEndPrice; //第二天收盘 价格



  public String getCompanyCode() {
    return companyCode;
  }

  public void setCompanyCode(String companyCode) {
    this.companyCode = companyCode;
  }

  public int getDate() {
    return date;
  }

  public void setDate(int date) {
    this.date = date;
  }

  public Double getPrePrice() {
    return prePrice;
  }

  public void setPrePrice(Double prePrice) {
    this.prePrice = prePrice;
  }

  public Double getPreRate() {
    return preRate;
  }

  public void setPreRate(Double preRate) {
    this.preRate = preRate;
  }

  public Double getPreInflow() {
    return preInflow;
  }

  public void setPreInflow(Double preInflow) {
    this.preInflow = preInflow;
  }

  public Double getPreTime() {
    return preTime;
  }

  public void setPreTime(Double preTime) {
    this.preTime = preTime;
  }

  public Double getLastPrice() {
    return lastPrice;
  }

  public void setLastPrice(Double lastPrice) {
    this.lastPrice = lastPrice;
  }

  public Double getLastRate() {
    return lastRate;
  }

  public void setLastRate(Double lastRate) {
    this.lastRate = lastRate;
  }

  public Double getLastInflow() {
    return lastInflow;
  }

  public void setLastInflow(Double lastInflow) {
    this.lastInflow = lastInflow;
  }

  public Double getLastTime() {
    return lastTime;
  }

  public void setLastTime(Double lastTime) {
    this.lastTime = lastTime;
  }

  public Double getTwoRate() {
    return twoRate;
  }

  public void setTwoRate(Double twoRate) {
    this.twoRate = twoRate;
  }

  public Double getTwoStartPrice() {
    return twoStartPrice;
  }

  public void setTwoStartPrice(Double twoStartPrice) {
    this.twoStartPrice = twoStartPrice;
  }

  public Double getTwoEndPrice() {
    return twoEndPrice;
  }

  public void setTwoEndPrice(Double twoEndPrice) {
    this.twoEndPrice = twoEndPrice;
  }
}
