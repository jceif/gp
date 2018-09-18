package cn.jf.model.dayvalue;

import java.io.Serializable;
import java.util.Date;


public class DayValueVO implements Serializable {
  private int id;
  private String companyCode; // 公司代码
  private int date; // 日期 20180306


  private Double preTotalMoney; // 流入流出总资金（单位：万）
  private Double preRate; // 涨跌值
  private Double preStartPrice; // 开盘价
  private Double preEndPrice; // 收盘价格
  private float preMaxPrice;
  private float preMinPrice;


  private Double nextTotalMoney; // 流入流出总资金（单位：万）
  private Double nextRate; // 涨跌值
  private Double nextStartPrice; // 开盘价
  private Double nextEndPrice; // 收盘价格
  private float nextMaxPrice;
  private float nextMinPrice;

  private Double threeRate;//第三天增长率
  private float threeMaxPrice;//第三天最高
  private Double threeEndPrice;//第三天价格

  private int isNew;//是否为新股 1 是，0否


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

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

  public Double getPreTotalMoney() {
    return preTotalMoney;
  }

  public void setPreTotalMoney(Double preTotalMoney) {
    this.preTotalMoney = preTotalMoney;
  }

  public Double getPreRate() {
    return preRate;
  }

  public void setPreRate(Double preRate) {
    this.preRate = preRate;
  }

  public Double getPreStartPrice() {
    return preStartPrice;
  }

  public void setPreStartPrice(Double preStartPrice) {
    this.preStartPrice = preStartPrice;
  }

  public Double getPreEndPrice() {
    return preEndPrice;
  }

  public void setPreEndPrice(Double preEndPrice) {
    this.preEndPrice = preEndPrice;
  }

  public float getPreMaxPrice() {
    return preMaxPrice;
  }

  public void setPreMaxPrice(float preMaxPrice) {
    this.preMaxPrice = preMaxPrice;
  }

  public float getPreMinPrice() {
    return preMinPrice;
  }

  public void setPreMinPrice(float preMinPrice) {
    this.preMinPrice = preMinPrice;
  }

  public Double getNextTotalMoney() {
    return nextTotalMoney;
  }

  public void setNextTotalMoney(Double nextTotalMoney) {
    this.nextTotalMoney = nextTotalMoney;
  }

  public Double getNextRate() {
    return nextRate;
  }

  public void setNextRate(Double nextRate) {
    this.nextRate = nextRate;
  }

  public Double getNextStartPrice() {
    return nextStartPrice;
  }

  public void setNextStartPrice(Double nextStartPrice) {
    this.nextStartPrice = nextStartPrice;
  }

  public Double getNextEndPrice() {
    return nextEndPrice;
  }

  public void setNextEndPrice(Double nextEndPrice) {
    this.nextEndPrice = nextEndPrice;
  }

  public float getNextMaxPrice() {
    return nextMaxPrice;
  }

  public void setNextMaxPrice(float nextMaxPrice) {
    this.nextMaxPrice = nextMaxPrice;
  }

  public float getNextMinPrice() {
    return nextMinPrice;
  }

  public void setNextMinPrice(float nextMinPrice) {
    this.nextMinPrice = nextMinPrice;
  }

  public int getIsNew() {
    return isNew;
  }

  public void setIsNew(int isNew) {
    this.isNew = isNew;
  }


  public Double getThreeRate() {
    return threeRate;
  }

  public void setThreeRate(Double threeRate) {
    this.threeRate = threeRate;
  }

  public float getThreeMaxPrice() {
    return threeMaxPrice;
  }

  public void setThreeMaxPrice(float threeMaxPrice) {
    this.threeMaxPrice = threeMaxPrice;
  }

  public Double getThreeEndPrice() {
    return threeEndPrice;
  }

  public void setThreeEndPrice(Double threeEndPrice) {
    this.threeEndPrice = threeEndPrice;
  }
}
