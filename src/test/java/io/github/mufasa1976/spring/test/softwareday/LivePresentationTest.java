package io.github.mufasa1976.spring.test.softwareday;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Live Demo for the Presentation - remember to remove the Exclusion if run by Maven")
class LivePresentationTest {
  @Autowired
  private MockMvc restInterface;

  // @Autowired
  // private EntityManager entityManager;

  @Test
  @DisplayName("Find the Person with ID 1 and expect its Name is Erich Stadler")
  void findPerson_OK_byId_1() throws Exception {
    // restInterface.perform(get("/api/persons/1"))
    //              .andExpect(status().isOk())
    //              .andExpect(content().contentType(APPLICATION_JSON_UTF8))
    //              .andExpect(jsonPath("lastName").value(is("Stadler")))
    //              .andExpect(jsonPath("firstName").value(is("Erich")));

    fail("Not implemented now");
  }

  @Test
  @DisplayName("Find the Person with ID 9999999999 and expect it hasn't been found")
  void findPerson_NOK_unknownId() throws Exception {
    // restInterface.perform(get("/api/persons/9999999999"))
    //              .andExpect(status().isNotFound());

    fail("Not implemented now");
  }

  @Test
  @DisplayName("Find all Persons and expect 3 have been found")
  void findAllPersons_OK() throws Exception {
    // restInterface.perform(get("/api/persons"))
    //              .andExpect(status().isOk())
    //              .andExpect(content().contentType(APPLICATION_JSON_UTF8))
    //              .andExpect(jsonPath("$").isArray())
    //              .andExpect(jsonPath("*", hasSize(3)));

    fail("Not implemented now");
  }

  @Test
  @DisplayName("remove all Persons from the Database and expect no Persons have been found")
  @Transactional
  void findAllPersons_OK_allPersonsRemovedBeforeTest() throws Exception {
    // entityManager.createQuery("DELETE FROM Person")
    //              .executeUpdate();

    // restInterface.perform(get("/api/persons"))
    //              .andExpect(status().isOk())
    //              .andExpect(content().contentType(APPLICATION_JSON_UTF8))
    //              .andExpect(jsonPath("$").isArray())
    //              .andExpect(jsonPath("$").isEmpty());

    fail("Not implemented now");
  }
}
