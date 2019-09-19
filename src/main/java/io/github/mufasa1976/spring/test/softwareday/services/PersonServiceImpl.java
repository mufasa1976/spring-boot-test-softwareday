package io.github.mufasa1976.spring.test.softwareday.services;

import io.github.mufasa1976.spring.test.softwareday.entities.Person;
import io.github.mufasa1976.spring.test.softwareday.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
  private final PersonRepository personRepository;

  @Override
  public List<Person> getAll() {
    return personRepository.findAll();
  }

  @Override
  public Optional<Person> getOneById(long id) {
    return personRepository.findById(id);
  }

  @Override
  @Transactional
  public Person create(Person person) {
    return personRepository.save(person);
  }

  @Override
  @Transactional
  public Optional<Person> update(long id, Person person) {
    return personRepository.findById(id)
                           .map(existingPerson -> {
                             existingPerson.setLastName(person.getLastName());
                             existingPerson.setFirstName(person.getFirstName());
                             return personRepository.save(existingPerson);
                           });
  }

  @Override
  @Transactional
  public boolean delete(long id) {
    return personRepository.findById(id)
                           .map(existingPerson -> {
                             personRepository.delete(existingPerson);
                             return true;
                           })
                           .orElse(false);
  }
}
