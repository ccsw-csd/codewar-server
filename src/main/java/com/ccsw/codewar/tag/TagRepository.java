package com.ccsw.codewar.tag;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccsw.codewar.tag.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findAll();
}
