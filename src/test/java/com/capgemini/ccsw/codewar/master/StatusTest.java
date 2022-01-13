package com.capgemini.ccsw.codewar.master;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.ccsw.codewar.master.model.StatusEntity;

@SpringBootTest
public class StatusTest {

   final private String statusCodeNotExists = "NOT_EXISTS";
   final private String statusCode = "PND";

   @Autowired
   MasterService service;

   @Test
   public void contextLoads() throws Exception {
      assertThat(service).isNotNull();
   }

   @Test
   public void existsStatusByCodeMustReturnStatus() {
      Optional<StatusEntity> status = service.getStatusByCode(statusCode);

      assertThat(status).isNotNull();
      assertThat(status.get()).isNotNull();
      assertEquals(statusCode, status.get().getCode());
   }

   @Test
   public void notExistsStatusByCodeMustReturnNull() {
      Optional<StatusEntity> status = service.getStatusByCode(statusCodeNotExists);

      assertThat(status).isNotNull();
      assertThat(status.orElse(null)).isNull();
   }
}
