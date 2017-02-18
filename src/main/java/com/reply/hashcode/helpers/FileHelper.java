package com.reply.hashcode.helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

}
