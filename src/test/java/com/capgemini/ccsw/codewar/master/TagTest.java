package com.capgemini.ccsw.codewar.master;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.ccsw.codewar.master.model.TagEntity;

@SpringBootTest
public class TagTest {

   final private String tagCode = "FACIL";
   final private String tagCodeNotExists = "NOT_EXISTS";

   @Autowired
   MasterService service;

   @Test
   public void contextLoads() throws Exception {
      assertThat(service).isNotNull();
   }

   @Test
   public void existsTagByCodeMustReturnTag() {

      Optional<TagEntity> tag = service.getTagByCode(tagCode);

      assertThat(tag).isNotNull();
      assertThat(tag.get()).isNotNull();
      assertEquals(tagCode, tag.get().getCode());
   }

   @Test
   public void notExistsTagByCodeMustReturnNull() {

      Optional<TagEntity> tag = service.getTagByCode(tagCodeNotExists);

      assertThat(tag).isNotNull();
      assertThat(tag.orElse(null)).isNull();
   }
}
