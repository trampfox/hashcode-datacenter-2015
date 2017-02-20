package com.reply.hashcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Davide Monfrecola
 */
public class Pool {

  private Integer id;

  private Integer minCapacity = 0;

  private Integer maxCapacity = 0;

  private List<Integer> servers = new ArrayList<>();

  private List<Integer> rowCapacities = new ArrayList<>();

  public Pool(int id) {
	  this.id = id;
	  
  }
  

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

  public void addServer(final Server server, final Integer rowId) {
    servers.add(server.getId());

    if (server.getCapacity() < minCapacity) {
      this.minCapacity = server.getCapacity();
    }

    if (server.getCapacity() > maxCapacity) {
      this.maxCapacity = server.getCapacity();
    }

    updateRowCapacity(server, rowId);
  }

  public void updateRowCapacity(final Server server, final Integer rowId) {
    final Integer capacity = this.rowCapacities.get(rowId);

    this.rowCapacities.set(rowId, capacity + server.getCapacity());
  }

}
