package cn.jf.model.dayvalue;

/**
 * 按照 最近几天的 总流入量 进行查询的 对象
 * @date 20181213
 */
public class DayValueVo1 {
    private String companyName;//名称
    private String companyCode;//代码
    private double inflow;//净流入
    private double rate;//增长率
    private int date;//日期

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public double getInflow() {
        return inflow;
    }

    public void setInflow(double inflow) {
        this.inflow = inflow;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
