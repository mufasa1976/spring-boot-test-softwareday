package io.github.mufasa1976.spring.test.softwareday.representation.rest;

import io.github.mufasa1976.spring.test.softwareday.entities.Person;
import io.github.mufasa1976.spring.test.softwareday.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/persons")
public class PersonRestController {
  private final PersonService personService;

  @GetMapping
  public List<Person> getAll() {
    return personService.getAll();
  }

  @GetMapping("{id}")
  public ResponseEntity<Person> getOneById(@PathVariable("id") long id) {
    return personService.getOneById(id)
                        .map(ResponseEntity::ok)
                        .orElseGet(ResponseEntity.notFound()::build);
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public Person create(@RequestBody @Valid Person person) {
    return personService.create(person);
  }

  @PutMapping("{id}")
  public ResponseEntity<Person> update(@PathVariable("id") long id, @RequestBody @Valid Person person) {
    return personService.update(id, person)
                        .map(ResponseEntity::ok)
                        .orElseGet(ResponseEntity.notFound()::build);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") long id) {
    if (personService.delete(id)) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
