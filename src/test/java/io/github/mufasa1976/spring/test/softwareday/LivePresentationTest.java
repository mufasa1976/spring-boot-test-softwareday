package io.github.mufasa1976.spring.test.softwareday;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Live Demo for the Presentation - remember to remove the Exclusion if run by Maven")
class LivePresentationTest {
  @Autowired
  private MockMvc restInterface;

  @Test
  @DisplayName("Find the Person with ID 1 and expect its Name is Erich Stadler")
  void findPerson_OK_byId_1() throws Exception {
    fail("not implemented now");
  }

  @Test
  @DisplayName("Find the Person with ID 9999999999 and expect it hasn't been found")
  void findPerson_NOK_unknownId() throws Exception {
    fail("not implemented now");
  }

  @Test
  @DisplayName("Find all Persons and expect 3 have been found")
  void findAllPersons_OK() throws Exception {
    fail("not implemented now");
  }

  @Test
  @DisplayName("remove all Persons from the Database and expect no Persons have been found")
  @Transactional
  void findAllPersons_OK_allPersonsRemovedBeforeTest() throws Exception {
    fail("not implemented now. Remember: autowire EntityManager to run this Test");
  }
}
