package com.ccsw.codewar.person;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.codewar.person.model.Person;
import com.ccsw.codewar.person.model.PersonDto;

@RequestMapping(value = "/person")
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private DozerBeanMapper mapper;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<PersonDto> findAll() {

        List<Person> persons = this.personService.findAll();

        return persons.stream().map(e -> mapper.map(e, PersonDto.class)).collect(Collectors.toList());

    }
}
