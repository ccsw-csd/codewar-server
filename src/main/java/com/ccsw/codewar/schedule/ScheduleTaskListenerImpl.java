package com.ccsw.codewar.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ccsw.codewar.schedule.model.ScheduledTaskEntity;
import com.ccsw.codewar.schedule.model.ScheduledTaskStatus;

@Service
public class ScheduleTaskListenerImpl implements ScheduleTaskListener {

   private final long HALF_HOUR = 30 * 60 * 1000;

   @Autowired
   ScheduledTaskRepository repository;

   /**
    * {@inheritDoc}
    */
   @Override
   @Scheduled(fixedDelay = HALF_HOUR)
   public void searchAndExecuteScheduleTask() {

      List<ScheduledTaskEntity> scheduledTasks = repository.findByStatusOrderById(ScheduledTaskStatus.PENDING);

   }

}
