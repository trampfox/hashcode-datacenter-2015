package com.reply.hashcode;

import com.reply.hashcode.helpers.FileHelper;
import com.reply.hashcode.helpers.MatrixHelper;

import java.util.List;

/**
 * @author Davide Monfrecola
 */
public class Application {

  public static void main(String[] args) {
    FileHelper fileHelper = new FileHelper();
    String inputFilePath = args[0];

    List<String> header = fileHelper.readFileHeader(inputFilePath);
    List<String> strings = fileHelper.readFileContentByLine(inputFilePath);

    System.out.println("Matrix parameters: ");
    MatrixHelper.readMatrixParameters(header);
    System.out.println("Matrix read from file: ");
    strings.forEach(System.out::println);
    System.out.println("Building matrix...");
    String[][] matrix = MatrixHelper.buildMatrix(strings, 6, 7);

  }

}
