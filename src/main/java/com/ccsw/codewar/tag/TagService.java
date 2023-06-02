package com.ccsw.codewar.tag;

import java.util.List;

import com.ccsw.codewar.tag.model.TagEntity;

public interface TagService {

    List<TagEntity> findAll();
}
