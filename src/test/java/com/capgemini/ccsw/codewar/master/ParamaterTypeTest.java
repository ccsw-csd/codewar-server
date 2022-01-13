package com.capgemini.ccsw.codewar.master;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.ccsw.codewar.master.model.ParameterTypeEntity;

@SpringBootTest
public class ParamaterTypeTest {

   final private String typeCode = "String";
   final private String typeCodeNotExists = "NOT_EXISTS";

   @Autowired
   MasterService service;

   @Test
   public void contextLoads() throws Exception {
      assertThat(service).isNotNull();
   }

   @Test
   public void existsParameterTypeByCodeMustReturnParameterType() {

      Optional<ParameterTypeEntity> type = service.getParameterTypeByCode(typeCode);

      assertThat(type).isNotNull();
      assertThat(type.get()).isNotNull();
      assertEquals(typeCode, type.get().getCode());
   }

   @Test
   public void notExistsParameterTypeByCodeMustReturnNull() {

      Optional<ParameterTypeEntity> type = service.getParameterTypeByCode(typeCodeNotExists);

      assertThat(type).isNotNull();
      assertThat(type.orElse(null)).isNull();
   }
}
