package com.javasl.files;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

class JsonFileReaderTest {

  JsonFileReader jsonFileReader = new JsonFileReader();

  @Test
  void jsonFileReader() {
    var pathToFile = "src/main/resources/json/person.json";
    var content = jsonFileReader.readJsonFile(pathToFile);
    assertNotNull(content);
    assertTrue(content.contains("Daniel Joshua"));
    assertTrue(content.contains("Solomon"));
  }

  @Test
  void parseAsStream() {
    var pathToFile = "src/main/resources/json/person.json";
    var jsonFileStream = jsonFileReader.parseAsStream(pathToFile);
    assertNotNull(jsonFileStream);

    var objectMapper = new ObjectMapper();
    var testPerson = objectMapper.readValue(jsonFileStream, Person.class);
    assertNotNull(testPerson);
    assertEquals("Daniel Joshua", testPerson.givennames());
    assertEquals("Solomon", testPerson.surname());
  }

  @Test
  void readJsonFile() {
    var person = jsonFileReader.readJsonFile("src/main/resources/json/person.json");
    ObjectMapper objectMapper = new ObjectMapper();
    var testPerson = objectMapper.readValue(person, Person.class);
    assertNotNull(testPerson);
    assertEquals("Daniel Joshua", testPerson.givennames());
    assertEquals("Solomon", testPerson.surname());
  }

  @Test
  void parsePerson() {
    var person = jsonFileReader.parsePerson("src/main/resources/json/person.json");
    assertNotNull(person);

    var pathToFile = Paths.get("src/test/resources/json/testPerson.json");
    String content = "";
    try {
      content = Files.readString(pathToFile);
    } catch (Exception e) {
      fail("Failed to read file: " + e.getMessage());
    }
    assertNotNull(content);
    assertFalse(content.isEmpty());
    ObjectMapper objectMapper = new ObjectMapper();
    var testPerson = objectMapper.readValue(content, Person.class);
    assertNotNull(testPerson);
    assertEquals( testPerson, person);
  }
}
