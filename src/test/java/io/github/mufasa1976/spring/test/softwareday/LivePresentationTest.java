package io.github.mufasa1976.spring.test.softwareday;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LivePresentationTest {
  @Autowired
  private MockMvc restInterface;

  @Test
  public void findPerson_OK_byId_1() throws Exception {
    fail("not implemented now");
  }

  @Test
  public void findPerson_NOK_unknownId() throws Exception {
    fail("not implemented now");
  }

  @Test
  public void findAllPersons_OK() throws Exception {
    fail("not implemented now");
  }

  @Test
  @Transactional
  public void findAllPersons_OK_allPersonsRemovedBeforeTest() throws Exception {
    fail("not implemented now. Remember: autowire EntityManager to run this Test");
  }
}
