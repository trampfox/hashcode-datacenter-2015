package com.reply.hashcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Davide Monfrecola
 */
public class Pool {

  private Integer id;

  private Integer minCapacity;

  private Integer maxCapacity;

  private List<Integer> servers = new ArrayList<>();

  private List<Integer> rowCapacities = new ArrayList<>();


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getMinCapacity() {
    return minCapacity;
  }

  public void setMinCapacity(Integer minCapacity) {
    this.minCapacity = minCapacity;
  }

  public Integer getMaxCapacity() {
    return maxCapacity;
  }

  public void setMaxCapacity(Integer maxCapacity) {
    this.maxCapacity = maxCapacity;
  }

  public List<Integer> getServers() {
    return servers;
  }

  public void setServers(List<Integer> servers) {
    this.servers = servers;
  }

  public List<Integer> getRowCapacities() {
    return rowCapacities;
  }

  public void setRowCapacities(List<Integer> rowCapacities) {
    this.rowCapacities = rowCapacities;
  }
}
