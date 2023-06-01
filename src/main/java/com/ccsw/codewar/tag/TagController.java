package com.ccsw.codewar.tag;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.codewar.tag.model.TagDto;
import com.ccsw.codewar.tag.model.TagEntity;

@RequestMapping(value = "/tag")
@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private DozerBeanMapper mapper;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<TagDto> getAllTags() {

        List<TagEntity> tags = this.tagService.getAllTags();

        return tags.stream().map(e -> mapper.map(e, TagDto.class)).collect(Collectors.toList());
    }
}
