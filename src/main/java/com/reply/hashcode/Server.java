package com.reply.hashcode;

/**
 * @author
 */
public class Server {

  private Integer id;

  private Integer size;

  private Integer capacity;

  private Integer poolId;

  private Double performanceIndex;


  public Server(Integer id, String size, String capacity) {
    this.id = id;
    this.size = Integer.valueOf(size);
    this.capacity = Integer.valueOf(capacity);
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
}
