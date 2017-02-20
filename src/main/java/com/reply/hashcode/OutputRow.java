package com.reply.hashcode;

/**
 * @author Davide Monfrecola
 */
public class OutputRow {

  private Integer serverId;

  private Integer rowId;

  private Integer slotId;

  private Integer poolId;


  public OutputRow(Integer serverId, Integer rowId, Integer slotId, Integer poolId) {
    this.serverId = serverId;
    this.rowId = rowId;
    this.slotId = slotId;
    this.poolId = poolId;
  }

  public Integer getServerId() {
    return serverId;
  }

  public void setServerId(Integer serverId) {
    this.serverId = serverId;
  }

  public Integer getRowId() {
    return rowId;
  }

  public void setRowId(Integer rowId) {
    this.rowId = rowId;
  }

  public Integer getSlotId() {
    return slotId;
  }

  public void setSlotId(Integer slotId) {
    this.slotId = slotId;
  }

  public Integer getPoolId() {
    return poolId;
  }

  public void setPoolId(Integer poolId) {
    this.poolId = poolId;
  }

  @Override
  public String toString() {
    String output;

    if (rowId == null && slotId == null) {
      output = "";
    }
    else {
       output = rowId + " " + slotId + " " + poolId + "\n";
    }

    return output;
  }
}
