package com.ccsw.codewar.master.model;

public enum ParameterType {

   String, Long, String_array, Long_array;

   public static ParameterType fromString(String type) {

      if (type == null)
         return null;

      if ("String".equals(type)) {
         return ParameterType.String;
      }

      if ("String[]".equals(type)) {
         return ParameterType.String_array;
      }

      if ("long".equals(type)) {
         return ParameterType.Long;
      }

      if ("long[]".equals(type)) {
         return ParameterType.Long_array;
      }

      return null;

   }
}
