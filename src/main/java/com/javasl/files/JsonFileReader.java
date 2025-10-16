package com.javasl.files;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import tools.jackson.databind.ObjectMapper;

public class JsonFileReader {

  public String readJsonFile() {
    return readJsonFile("classpath:src/main/resources/json/person.json");
  }

  /**
   * Support for lower Java versions and libraries requiring an InputStream. Not intended for huge
   * files.
   *
   * @param path the path to the file.
   * @return the content as a {@link ByteArrayInputStream}.
   */
  public InputStream parseAsStream(String path) {
    var pathToFile = Paths.get(path);
    try {
      return new ByteArrayInputStream(Files.readAllBytes(pathToFile));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public String readJsonFile(String path) {
    // example file is under resources, use this path: "classpath:src/main/resources/json/person.json"
    var pathToFile = Paths.get(path);
    try {
      return Files.readString(pathToFile);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Person parsePerson(String path) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      var json = Files.readString(Paths.get(path));
      return objectMapper.readValue(json, Person.class);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Process large files.
   *
   * @param path the path to the file.
   *
   */
  public void parseBigFile(String path) {
    var pathToFile = Paths.get(path);
    try (BufferedReader br = Files.newBufferedReader(pathToFile)) {
      String line;
      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
