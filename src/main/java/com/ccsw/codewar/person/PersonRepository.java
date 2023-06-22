package com.ccsw.codewar.person;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccsw.codewar.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, String> {

    List<Person> findAll();
}
