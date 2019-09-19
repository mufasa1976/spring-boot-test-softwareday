package io.github.mufasa1976.spring.test.softwareday.services;

import io.github.mufasa1976.spring.test.softwareday.entities.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
  List<Person> getAll();

  Optional<Person> getOneById(long id);

  Person create(Person person);

  Optional<Person> update(long id, Person person);

  boolean delete(long id);
}
