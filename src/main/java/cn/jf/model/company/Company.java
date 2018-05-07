package cn.jf.model.company;


import java.util.Date;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


public class Company implements Serializable {

  private int id; // 自动增长列

  private String code; // 编号

  private String name; // 企业名称

  private int type; // 证券类型

  private Date createTime; // 创建时间


  private float marketWorth;//市值


  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getType() {
    return this.type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public Date getCreateTime() {
    return this.createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public float getMarketWorth() {
    return marketWorth;
  }

  public void setMarketWorth(float marketWorth) {
    this.marketWorth = marketWorth;
  }



}
