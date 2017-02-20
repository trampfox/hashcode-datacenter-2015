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

  public void writeOutputFile(final List<Server> servers,
                              final String outputFilename) {
    final List<OutputRow> outputRows = new ArrayList<>();

    for (Server server: servers) {
      outputRows.add(new OutputRow(server.getId(), server.getRow(), server.getSlot(), server.getPoolId()));
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
