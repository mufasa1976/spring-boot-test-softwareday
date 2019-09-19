package io.github.mufasa1976.spring.test.softwareday.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@Builder
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(NONE)
  private Long id;

  @NotNull
  @Size(max = 1000)
  private String lastName;

  @Size(max = 1000)
  private String firstName;
}
