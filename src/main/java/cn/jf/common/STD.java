package cn.jf.common;

public class STD {

  private int[] array = new int[10];
  private int num = 10;

  public int getRandomDigit() {
    return (int) (Math.random() * 1000);
  }

  public void getTargetDigit() {

    for (int i = 0; i < num; i++) {
      array[i] = getRandomDigit();
      System.out.println(array[i]);
    }
  }

  //獲取平均值
  public double getAverage() {
    int sum = 0;
    for (int i = 0; i < num; i++) {
      sum += array[i];
    }
    return (double) (sum / num);
  }

  //獲取標準差
  public double getStandardDevition() {
    double sum = 0;
    for (int i = 0; i < num; i++) {
      sum += Math.sqrt(((double) array[i] - getAverage()) * (array[i] - getAverage()));
    }
    return (sum / (num - 1));
  }


}


