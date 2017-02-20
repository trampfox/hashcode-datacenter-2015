package com.reply.hashcode;

import com.reply.hashcode.helpers.FileHelper;
import com.reply.hashcode.helpers.MatrixHelper;

import java.util.List;
import java.util.Map;

/**
 * @author Davide Monfrecola
 */
public class Application {

  public static void main(String[] args) {
    FileHelper fileHelper = new FileHelper();
    String inputFilePath = args[0];

    List<String> header = fileHelper.readFileHeader(inputFilePath);
    Map<String, Integer> parameters = MatrixHelper.readMatrixParameters(header);

    List<String> lines = fileHelper.readFileContentByLine(inputFilePath);
    List<UnavailableSlot> unavailableSlots = MatrixHelper.readUnavailableSlots(parameters, lines);
    List<Server> servers = MatrixHelper.readServers(parameters, lines);

    System.out.println("Matrix parameters: ");
    MatrixHelper.readMatrixParameters(header);
    System.out.println("Matrix read from file: ");
    lines.forEach(System.out::println);
    System.out.println("Building matrix...");
    String[][] matrix = MatrixHelper.buildMatrix(lines, 6, 7);

  }

}
