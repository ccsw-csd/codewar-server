package com.ccsw.codewar.schedule;

public interface ScheduleTaskListener {

   /**
    * Busca tareas pendientes de ejecución y las ejecuta en función de su tipo
    */
   void searchAndExecuteScheduleTask();
}
