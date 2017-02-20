package com.reply.hashcode.helpers;

import com.reply.hashcode.OutputRow;
import com.reply.hashcode.Server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Davide Monfrecola
 */
public class FileHelper {

  public List<String> readFileContentByLine(final String filename) {
    List<String> lines = new ArrayList<>();

    try(Stream<String> stream = Files.lines(Paths.get(filename))) {

      lines = stream.skip(1).collect(Collectors.toList());

    } catch (IOException e) {
      e.printStackTrace();
    }

    return lines;
  }

  public List<String> readFileHeader(final String filename) {
    List<String> header = new ArrayList<>();

    try (Stream<String> stream = Files.lines(Paths.get(filename))) {

      header = stream.findFirst().map(l -> Arrays.asList(l.split(" "))).get();

    } catch (IOException e) {
      e.printStackTrace();
    }

    return header;
  }

  public void writeOutputFile(final Map<String, Integer> parameters,
                              final Map<Integer, Server> servers,
                              final Integer[][] datacenterMatrix,
                              final String outputFilename) {
    final Integer rows = parameters.get("rows");
    final Integer columns = parameters.get("slots");
    final List<OutputRow> outputRows = new ArrayList<>();

    for (Map.Entry<Integer, Server> server: servers.entrySet()) {
      outputRows.add(new OutputRow(server.getKey(), server.getValue().getRow(), server.getValue().getSlot(), server.getValue().getPoolId()));
    }

    //Use try-with-resource to get auto-closeable writer instance
    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilename)))
    {
      outputRows.forEach(row -> {
        try {
          writer.write(row.toString());
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
