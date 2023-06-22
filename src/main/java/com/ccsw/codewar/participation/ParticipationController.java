package com.ccsw.codewar.participation;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.codewar.participation.model.Participation;
import com.ccsw.codewar.participation.model.ParticipationDto;

@RequestMapping(value = "/participation")
@RestController
public class ParticipationController {

    @Autowired
    ParticipationService participationService;

    @Autowired
    DozerBeanMapper mapper;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<ParticipationDto> findAll() {

        List<Participation> participations = this.participationService.findAll();

        return participations.stream().map(e -> mapper.map(e, ParticipationDto.class)).collect(Collectors.toList());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ParticipationDto findById(@PathVariable Long id) {
        Participation participation = this.participationService.findById(id);
        return mapper.map(participation, ParticipationDto.class);
    }
}
