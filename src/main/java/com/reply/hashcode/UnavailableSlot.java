package com.reply.hashcode;

/**
 * @author Davide Monfrecola
 */
public class UnavailableSlot {

  private Integer x;

  private Integer y;


  public UnavailableSlot(String coordinates) {
    String[] tmp = coordinates.split(" ");
    this.x = Integer.valueOf(tmp[0]);
    this.y = Integer.valueOf(tmp[1]);
  }

  public Integer getX() {
    return x;
  }

  public void setX(Integer x) {
    this.x = x;
  }

  public Integer getY() {
    return y;
  }

  public void setY(Integer y) {
    this.y = y;
  }

}
