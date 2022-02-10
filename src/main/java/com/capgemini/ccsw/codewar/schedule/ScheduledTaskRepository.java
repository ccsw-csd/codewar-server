package com.capgemini.ccsw.codewar.schedule;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.codewar.schedule.model.ScheduledTaskEntity;
import com.capgemini.ccsw.codewar.schedule.model.ScheduledTaskStatus;

public interface ScheduledTaskRepository extends CrudRepository<ScheduledTaskEntity, Long> {

   /**
    * Recupera las tareas filtradas por estado y ordenadas por ID
    * @param status
    * @return
    */
   List<ScheduledTaskEntity> findByStatusOrderById(ScheduledTaskStatus status);
}
