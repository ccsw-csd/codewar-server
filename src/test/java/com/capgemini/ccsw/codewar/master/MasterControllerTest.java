package com.capgemini.ccsw.codewar.master;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.ccsw.codewar.master.to.ParameterTypeTo;
import com.capgemini.ccsw.codewar.master.to.TagTo;

@SpringBootTest
public class MasterControllerTest {

   @Autowired
   MasterController controller;

   @Test
   public void contextLoads() throws Exception {
      assertThat(controller).isNotNull();
   }

   @Test
   public void findAllTagsMustReturnAllTags() {
      List<TagTo> tags = controller.findAllTags();

      assertThat(tags).isNotNull();
      assertEquals(3, tags.size());
      assertEquals("FACIL", tags.get(0).getCode());
      assertEquals("MEDIO", tags.get(1).getCode());
      assertEquals("DIFICIL", tags.get(2).getCode());
   }

   @Test
   public void findAllParameterTypeMustReturnAllParameterTypes() {
      List<ParameterTypeTo> types = controller.findAllParameterType();

      assertThat(types).isNotNull();
      assertEquals(4, types.size());
      assertEquals("String", types.get(0).getCode());
      assertEquals("long", types.get(1).getCode());
      assertEquals("String[]", types.get(2).getCode());
      assertEquals("long[]", types.get(3).getCode());
   }
}
