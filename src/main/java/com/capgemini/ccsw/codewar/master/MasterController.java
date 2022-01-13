package com.capgemini.ccsw.codewar.master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ccsw.codewar.configuration.mapper.BeanMapper;
import com.capgemini.ccsw.codewar.master.to.ParameterTypeTo;
import com.capgemini.ccsw.codewar.master.to.TagTo;

@RequestMapping(value = "")
@RestController
public class MasterController {

   @Autowired
   private MasterService masterService;

   @Autowired
   private BeanMapper beanMapper;

   @RequestMapping(path = "/tags", method = RequestMethod.GET)
   List<TagTo> findAllTags() {

      return this.beanMapper.mapList(this.masterService.findAllTags(), TagTo.class);

   }

   @RequestMapping(path = "/parameter-types", method = RequestMethod.GET)
   List<ParameterTypeTo> findAllParameterType() {

      return this.beanMapper.mapList(this.masterService.findAllParameterType(), ParameterTypeTo.class);

   }

}
