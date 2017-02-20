package com.reply.hashcode.helpers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



}
