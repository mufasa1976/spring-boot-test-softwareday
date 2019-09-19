package io.github.mufasa1976.spring.test.softwareday.repositories;

import io.github.mufasa1976.spring.test.softwareday.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}
