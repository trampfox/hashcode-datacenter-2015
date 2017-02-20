package com.reply.hashcode;

public class Server implements Comparable<Server> {

  private Integer id;

  private Integer size;

  private Integer capacity;

  private Integer poolId;

  private Double performanceIndex;

  public Server(Integer id, Integer size, Integer capacity) {
    this.id = id;
    this.size = size;
    this.capacity = capacity;
    this.performanceIndex = capacity.doubleValue() / size.doubleValue();
  }


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }

  public Integer getPoolId() {
    return poolId;
  }

  public void setPoolId(Integer poolId) {
    this.poolId = poolId;
  }

  public Double getPerformanceIndex() {
    return performanceIndex;
  }

  public void setPerformanceIndex(Double performanceIndex) {
    this.performanceIndex = performanceIndex;
  }


  @Override
  public int compareTo(Server arg0) {
    return this.performanceIndex == arg0.performanceIndex ? 0 :
        this.performanceIndex < arg0.performanceIndex ? -1 : 1;
  }


}
