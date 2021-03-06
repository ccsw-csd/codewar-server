package com.ccsw.codewar.challenge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccsw.codewar.challenge.to.ChallengeActivateResponseTo;
import com.ccsw.codewar.challenge.to.ChallengeParameterTo;
import com.ccsw.codewar.challenge.to.ChallengeSaveTo;
import com.ccsw.codewar.challenge.to.ChallengeTestTo;
import com.ccsw.codewar.challenge.to.ChallengeTestValueTo;
import com.ccsw.codewar.compiler.JavaCompiler;
import com.ccsw.codewar.compiler.to.CodeDiagnosticTo;
import com.ccsw.codewar.compiler.to.CompilerException;
import com.ccsw.codewar.master.model.ParameterType;

@Service
public class ChallengeValidatorImpl implements ChallengeValidator {

   @Autowired
   private JavaCompiler compiler;

   /**
    * {@inheritDoc}
    */
   @Override
   public ChallengeActivateResponseTo check(ChallengeSaveTo challenge) {
      List<String> errors = new ArrayList<>();
      if (challenge == null) {
         errors.add("No existe el reto");
         return new ChallengeActivateResponseTo(errors);
      }

      checkGeneralErrors(challenge, errors);
      checkCodeErrors(challenge, errors);
      checkTestErrors(challenge, errors);

      return new ChallengeActivateResponseTo(errors);
   }

   @Override
   public String generateBaseCode(ChallengeSaveTo challenge) {
      String className = challenge.getClassName();
      StringBuilder code = new StringBuilder();

      code.append("/**\n");
      code.append(" *\n");
      code.append(" * Reto: " + challenge.getName() + "\n");
      code.append(" *\n");
      code.append(" */\n");
      code.append("public class " + className + " {\n\n");

      String outParam = challenge.getOutParameter().getType();

      StringBuilder inParams = new StringBuilder();
      for (ChallengeParameterTo parameter : challenge.getInParameter()) {
         if (inParams.length() > 0)
            inParams.append(", ");

         inParams.append(parameter.getType() + " " + parameter.getName());
      }

      code.append("\tpublic " + outParam + " " + challenge.getFunctionName() + "(" + inParams.toString() + ") {\n");

      if (outParam.equals("long"))
         code.append("\t\treturn 0;\n");
      else
         code.append("\t\treturn null;\n");
      code.append("\t}\n\n");

      code.append("}");

      return code.toString();
   }

   private void checkCodeErrors(ChallengeSaveTo challenge, List<String> errors) {
      if (StringUtils.hasText(challenge.getClassName()) == false)
         errors.add("El reto debe tener un nombre de clase");

      if (StringUtils.hasText(challenge.getFunctionName()) == false)
         errors.add("El reto debe tener un nombre de m??todo");

      if (challenge.getInParameter() == null || challenge.getInParameter().size() == 0) {
         errors.add("El reto debe tener alg??n par??metro de entrada");
      } //
      else {
         int order = 1;
         for (ChallengeParameterTo parameter : challenge.getInParameter()) {

            if (StringUtils.hasText(parameter.getName()) == false) {
               errors.add("El par??metro de entrada " + order + " debe tener un nombre");
            }

            if (StringUtils.hasText(parameter.getType()) == false) {
               errors.add("El par??metro de entrada " + order + " debe tener un tipo");
            }

            order++;
         }

         checkCompilerErrors(challenge, errors);
      }
   }

   private void checkGeneralErrors(ChallengeSaveTo challenge, List<String> errors) {
      if (StringUtils.hasText(challenge.getName()) == false)
         errors.add("El reto debe tener un nombre");

      if (challenge.getDescription() == null || challenge.getDescription().length() < 20)
         errors.add("El reto debe tener una descripci??n");

      if (challenge.getTags() == null || challenge.getTags().size() == 0)
         errors.add("El reto debe tener alg??n tag asociado");
   }

   private void checkTestErrors(ChallengeSaveTo challenge, List<String> errors) {

      if (challenge.getTest().size() == 0) {
         errors.add("El reto debe tener alguna prueba");
         return;
      }

      for (ChallengeTestTo test : challenge.getTest()) {

         int order = 0;
         checkTypeParameter(test.getName(), errors, order++, challenge.getOutParameter(), test.getValueOut());

         for (int i = 0; i < challenge.getInParameter().size(); i++) {
            checkTypeParameter(test.getName(), errors, order++, challenge.getInParameter().get(i), test.getValueIn().get(i));
         }
      }

   }

   private void checkTypeParameter(String name, List<String> errors, int order, ChallengeParameterTo parameter, ChallengeTestValueTo value) {

      boolean parameterError = false;

      ParameterType type = ParameterType.fromString(parameter.getType());

      if (type == null) {
         parameterError = true;
      } //

      else {

         if (type.equals(ParameterType.String)) {
            parameterError = false;
         }

         if (type.equals(ParameterType.String_array)) {
            parameterError = false;
         }

         if (type.equals(ParameterType.Long)) {
            try {
               Long.parseLong(value.getValue());
            } catch (Exception e) {
               parameterError = true;
            }
         }

         if (type.equals(ParameterType.Long_array)) {
            String[] values = value.getValue().split(";");
            for (String singleValue : values) {
               try {
                  Long.parseLong(singleValue);
               } catch (Exception e) {
                  parameterError = true;
                  break;
               }
            }
         } //
      }

      if (parameterError) {
         if (order == 0)
            errors.add("Error en el par??metro de salida de la prueba '" + name + "'");
         else
            errors.add("Error en el par??metro de entrada [" + order + "] de la prueba '" + name + "'");
      }
   }

   private void checkCompilerErrors(ChallengeSaveTo challenge, List<String> errors) {
      String className = challenge.getClassName();
      String code = generateBaseCode(challenge);

      try {
         List<CodeDiagnosticTo> codeErrors = compiler.compileInMemory(code, className);
         if (codeErrors != null) {
            errors.add("Errores al intentar compilar la clase:");

            for (CodeDiagnosticTo codeError : codeErrors) {
               errors.add("\tError en la l??nea " + codeError.getLinePosition() + ":" + codeError.getPosition() + " -> " + codeError.getErrorMessage());
            }
         }
      } catch (CompilerException e) {
         e.printStackTrace();
      } catch (IllegalArgumentException e) {
         e.printStackTrace();
         errors.add("Excepci??n al intentar compilar la clase. Revise la secci??n de c??digo");
      }
   }

}
