package com.reply.hashcode.helpers;

import com.reply.hashcode.Server;
import com.reply.hashcode.UnavailableSlot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Davide Monfrecola
 */
public class MatrixHelper {

  public static Map<String, Integer> readMatrixParameters(final List<String> headerValues) {
    Map<String, Integer> parameters = new HashMap<>();

    parameters.put("rows", Integer.valueOf(headerValues.get(0)));
    parameters.put("slots", Integer.valueOf(headerValues.get(1)));
    parameters.put("unavailableSlots", Integer.valueOf(headerValues.get(2)));
    parameters.put("pools", Integer.valueOf(headerValues.get(3)));
    parameters.put("servers", Integer.valueOf(headerValues.get(3)));

    System.out.println(
        String.format("%d rows, %d columns, min %d of each ingredient per slice, max %d cells per slice",
            parameters.get("rows"), parameters.get("columns"), parameters.get("minIngredientPerSlice"),
            parameters.get("maxCellsPerSlice")));

    return parameters;
  }

  /**
   * Build a matrix instance reading row elements from a list passed as parameter
   *
   * @param rowsList
   * @param rowLen
   * @param colLen
   * @return a new matrix instance
   */
  public static String[][] buildMatrix(final List<String> rowsList, int rowLen, int colLen) {
    final String[][] matrix = new String[rowLen][colLen];
    String[] rows = new String[rowLen];
    rows = rowsList.toArray(rows);

    for (int i = 0;i < rowLen;i++) {
      String[] stringArray = rows[i].split("");

      for (int j = 0;j < colLen;j++) {
        System.out.println(String.format("Add matrix element %s for row %d.", stringArray[j], i));
        matrix[i][j] = stringArray[j];
      }

    }

    return matrix;
  }

  public static List<UnavailableSlot> readUnavailableSlots(final Map<String, Integer> parameters, final List<String> lines) {
    final List<UnavailableSlot> unavailableSlot = new ArrayList<>();
    final Integer unavailableSlotNumber = parameters.get("unavailableSlots");

    for(int i = 0;i < unavailableSlotNumber;i++) {
      unavailableSlot.add(new UnavailableSlot(lines.get(i)));
    }

    return unavailableSlot;
  }

  public static Map<Integer, Server> readServers(final Map<String, Integer> parameters, final List<String> lines) {
    final Map<Integer, Server> servers = new HashMap<>();
    final Integer unavailableSlotNumber = parameters.get("unavailableSlots");

    final AtomicInteger count = new AtomicInteger();

    lines.stream().skip(unavailableSlotNumber).forEach(line -> {
      String[] tmp = line.split(" ");
      Integer id = count.getAndIncrement();
      servers.put(id, new Server(id, Integer.valueOf(tmp[0]), Integer.valueOf(tmp[1])));
    });

    return servers;
  }

  public static Integer[][] buildDatacenterMatrix(final Map<String, Integer> parameters,
                                                  final List<UnavailableSlot> unavailableSlots) {
    final Integer rows = parameters.get("rows");
    final Integer columns = parameters.get("slots");
    final Integer[][] datacenterMatrix = new Integer[rows][columns];

    // init matrix, mark all slot as empty
    for (int i = 0;i < rows;i++) {
      for (int j = 0;j < columns;j++) {
        datacenterMatrix[i][j] = -2;
      }
    }
    // put unavailable slot in datacenter matrix
    unavailableSlots.forEach(uSlot -> datacenterMatrix[uSlot.getX()][uSlot.getY()] = -1);

    return datacenterMatrix;
  }

}
