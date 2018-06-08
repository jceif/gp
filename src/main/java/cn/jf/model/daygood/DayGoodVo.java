package cn.jf.model.daygood;


import java.io.Serializable;
import java.util.Date;


public class DayGoodVo implements Serializable {

    private int id; //

    private String companyName;//公司名称
    private String companyCode; // 公司代码
    private Double marketWorth;//市值

    private int companyType;//所属机构

    private Double price; // 当前价格

    private Double rate; // 当前跌幅

    private Double preRate1;//上半个小时涨幅
    private Double preRate2;//上一个小时涨幅

    private Double mainMoney; // 主力净流入 万为单位

    private Double mainRate; // 主力占比

    private Double chaodaMoney; // 超大单净额  万为单位

    private Double chaodaRate; // 超大单占比

    private Double daMoney; // 大单净额  万为单位

    private Double daRate; // 大单占比

    private int date; // 当前日期 20180302

    private String time; // 当前时间 121212 时分

    private Double preDayRate1;//前一日涨幅

    private Double preDayRate2;//前二日涨幅

    private Date originalTime; // 原始时间(准确时间）


    private Double preMainMoneyRate;//当前较上个半小时 主力增长率
    private Double preMainMoneyCha;//当前较上个半小时 主力增长差值

    private Double preTimeMainMoney1;//上半个小时主力资金
    private Double preTimeRate1;//上半个小时主力资金占比
    private Double preMainMoneyRate1;//（上半个小时）较上上半个小时主力增长率

    private Double preMainMoneyCha1;//上半个小时）较上上半个小时主力增长差值

    private Double preTimeMainMoney2;//上上半个小时主力资金
    private Double preTimeRate2;//上上半个小时主力资金占比

    public Double getPreMainMoneyCha() {
        return preMainMoneyCha;
    }

    public void setPreMainMoneyCha(Double preMainMoneyCha) {
        this.preMainMoneyCha = preMainMoneyCha;
    }

    public Double getPreMainMoneyCha1() {
        return preMainMoneyCha1;
    }

    public void setPreMainMoneyCha1(Double preMainMoneyCha1) {
        this.preMainMoneyCha1 = preMainMoneyCha1;
    }


    public Double getPreDayRate1() {
        return preDayRate1;
    }

    public void setPreDayRate1(Double preDayRate1) {
        this.preDayRate1 = preDayRate1;
    }

    public Double getPreDayRate2() {
        return preDayRate2;
    }

    public void setPreDayRate2(Double preDayRate2) {
        this.preDayRate2 = preDayRate2;
    }

    public Double getPreMainMoneyRate() {
        return preMainMoneyRate;
    }

    public void setPreMainMoneyRate(Double preMainMoneyRate) {
        this.preMainMoneyRate = preMainMoneyRate;
    }

    public Double getPreTimeMainMoney1() {
        return preTimeMainMoney1;
    }

    public void setPreTimeMainMoney1(Double preTimeMainMoney1) {
        this.preTimeMainMoney1 = preTimeMainMoney1;
    }

    public Double getPreTimeRate1() {
        return preTimeRate1;
    }

    public void setPreTimeRate1(Double preTimeRate1) {
        this.preTimeRate1 = preTimeRate1;
    }

    public Double getPreMainMoneyRate1() {
        return preMainMoneyRate1;
    }

    public void setPreMainMoneyRate1(Double preMainMoneyRate1) {
        this.preMainMoneyRate1 = preMainMoneyRate1;
    }

    public Double getPreTimeMainMoney2() {
        return preTimeMainMoney2;
    }

    public void setPreTimeMainMoney2(Double preTimeMainMoney2) {
        this.preTimeMainMoney2 = preTimeMainMoney2;
    }

    public Double getPreTimeRate2() {
        return preTimeRate2;
    }

    public void setPreTimeRate2(Double preTimeRate2) {
        this.preTimeRate2 = preTimeRate2;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String name) {
        this.companyName = name;
    }

    public Double getMarketWorth() {
        return marketWorth;
    }

    public void setMarketWorth(Double marketWorth) {
        this.marketWorth = marketWorth;
    }

    public int getCompanyType() {
        return companyType;
    }

    public void setCompanyType(int type) {
        this.companyType = type;
    }


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


    public Double getPreRate1() {
        return preRate1;
    }

    public void setPreRate1(Double preRate1) {
        this.preRate1 = preRate1;
    }

    public Double getPreRate2() {
        return preRate2;
    }

    public void setPreRate2(Double preRate2) {
        this.preRate2 = preRate2;
    }
}
