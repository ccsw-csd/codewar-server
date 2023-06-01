package com.ccsw.codewar.tag;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccsw.codewar.tag.model.TagEntity;

public interface TagRepository extends JpaRepository<TagEntity, Long> {

    List<TagEntity> getAllTags();
}
