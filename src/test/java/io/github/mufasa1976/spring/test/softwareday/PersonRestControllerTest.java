package io.github.mufasa1976.spring.test.softwareday;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mufasa1976.spring.test.softwareday.entities.Person;
import io.github.mufasa1976.spring.test.softwareday.repositories.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonRestControllerTest {
  @Autowired
  private MockMvc restControllerWeb;

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private EntityManager entityManager;

  @Test
  public void getAll_OK() throws Exception {
    restControllerWeb.perform(get("/api/persons"))
                     .andExpect(status().isOk())
                     .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                     .andExpect(jsonPath("$").isArray())
                     .andExpect(jsonPath("*", hasSize(3)))
                     .andExpect(jsonPath("[0].id").value(is(1)))
                     .andExpect(jsonPath("[0].lastName").value(is("Stadler")))
                     .andExpect(jsonPath("[0].firstName").value(is("Erich")))
                     .andExpect(jsonPath("[1].id").value(is(2)))
                     .andExpect(jsonPath("[1].lastName").value(is("Tuma")))
                     .andExpect(jsonPath("[1].firstName").value(is("Heinz")))
                     .andExpect(jsonPath("[2].id").value(is(3)))
                     .andExpect(jsonPath("[2].lastName").value(is("Fleischmann")))
                     .andExpect(jsonPath("[2].firstName").value(is("Peter")));
  }

  @Test
  @Transactional
  public void getAll_OK_noDataFound() throws Exception {
    personRepository.deleteAll();

    restControllerWeb.perform(get("/api/persons"))
                     .andExpect(status().isOk())
                     .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                     .andExpect(jsonPath("$").isArray())
                     .andExpect(jsonPath("$").isEmpty());
  }

  @Test
  public void getOneById_OK() throws Exception {
    restControllerWeb.perform(get("/api/persons/1"))
                     .andExpect(status().isOk())
                     .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                     .andExpect(jsonPath("id").value(is(1)))
                     .andExpect(jsonPath("lastName").value(is("Stadler")))
                     .andExpect(jsonPath("firstName").value(is("Erich")));
  }

  @Test
  public void getOneById_NOK_notFound() throws Exception {
    restControllerWeb.perform(get("/api/persons/9999999999"))
                     .andExpect(status().isNotFound());
  }

  @Test
  public void getOneById_NOK_invalidId() throws Exception {
    restControllerWeb.perform(get("/api/persons/invalidNumber"))
                     .andExpect(status().isBadRequest());
  }

  @Test
  @Transactional
  public void create_OK() throws Exception {
    final Person person = objectMapper.readValue(
        restControllerWeb.perform(post("/api/persons")
            .contentType(APPLICATION_JSON_UTF8)
            .content(objectMapper.writeValueAsString(
                Person.builder()
                      .lastName("Stadler")
                      .firstName("Andrea")
                      .build())))
                         .andExpect(status().isCreated())
                         .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                         .andExpect(jsonPath("id").isNotEmpty())
                         .andExpect(jsonPath("lastName").value(is("Stadler")))
                         .andExpect(jsonPath("firstName").value(is("Andrea")))
                         .andReturn()
                         .getResponse()
                         .getContentAsString(),
        Person.class);
    entityManager.flush();
    assertThat(personRepository.findById(person.getId()))
        .isPresent()
        .isEqualTo(Optional.of(person));
  }

  @Test
  @Transactional
  public void update_OK() throws Exception {
    final Person person = objectMapper.readValue(
        restControllerWeb.perform(put("/api/persons/1")
            .contentType(APPLICATION_JSON_UTF8)
            .content(objectMapper.writeValueAsString(
                Person.builder()
                      .lastName("Stadler")
                      .firstName("Fabian")
                      .build())))
                         .andExpect(status().isOk())
                         .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                         .andExpect(jsonPath("id").value(is(1)))
                         .andExpect(jsonPath("lastName").value(is("Stadler")))
                         .andExpect(jsonPath("firstName").value(is("Fabian")))
                         .andReturn()
                         .getResponse()
                         .getContentAsString(),
        Person.class);
    entityManager.flush();
    assertThat(personRepository.findById(1L))
        .isPresent()
        .isEqualTo(Optional.of(person));
  }

  @Test
  public void update_NOK_notFound() throws Exception {
    restControllerWeb.perform(put("/api/persons/9999999999")
        .contentType(APPLICATION_JSON_UTF8)
        .content(objectMapper.writeValueAsString(
            Person.builder()
                  .lastName("Mustermann")
                  .firstName("Max")
                  .build())))
                     .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  public void delete_OK() throws Exception {
    assertThat(personRepository.findById(1L)).isPresent();
    restControllerWeb.perform(delete("/api/persons/1"))
                     .andExpect(status().isNoContent());
    assertThat(personRepository.findById(1L)).isNotPresent();
  }

  @Test
  public void delete_NOK_notFound() throws Exception {
    restControllerWeb.perform(delete("/api/persons/9999999999"))
                     .andExpect(status().isNotFound());
  }
}
