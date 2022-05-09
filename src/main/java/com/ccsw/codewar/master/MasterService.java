package com.ccsw.codewar.master;

import java.util.List;
import java.util.Optional;

import com.ccsw.codewar.master.model.StatusEntity;
import com.ccsw.codewar.master.model.ParameterTypeEntity;
import com.ccsw.codewar.master.model.TagEntity;

public interface MasterService {

   /**
    * Recupera todos los tags disponibles
    * @return
    */
   List<TagEntity> findAllTags();

   /**
   * Recupera todos los tipos de parametros disponibles
   * @return
   */
   List<ParameterTypeEntity> findAllParameterType();

   /**
    * Recupera un estado en base a su código
    * @param statusCode
    * @return
    */
   Optional<StatusEntity> getStatusByCode(String statusCode);

   /**
    * Recupera el tipo de parametro en base a su código
    * @param typeCode
    * @return
    */
   Optional<ParameterTypeEntity> getParameterTypeByCode(String typeCode);

   /**
    * Recupera un tag en base a su código
    * @param tagCode
    * @return
    */
   Optional<TagEntity> getTagByCode(String tagCode);

}
