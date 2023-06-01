package com.ccsw.codewar.tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.codewar.tag.model.TagEntity;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<TagEntity> getAllTags(){
        return tagRepository.getAllTags();
    }
}
