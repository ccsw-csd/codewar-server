package com.capgemini.ccsw.codewar.compiler;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capgemini.ccsw.codewar.challenge.to.ChallengeParameterTo;
import com.capgemini.ccsw.codewar.challenge.to.ChallengeSaveTo;
import com.capgemini.ccsw.codewar.compiler.to.CodeDiagnosticTo;
import com.capgemini.ccsw.codewar.compiler.to.CompilerException;
import com.capgemini.ccsw.codewar.master.model.ParameterType;

/**
 * @author pajimene
 *
 */
public class JavaClassUtils {
   private static final Logger LOG = LoggerFactory.getLogger(JavaClassUtils.class);

   /**
    * Compila la clase Java y devuelve los fallos de compilación, si es que tuviera
    * @param javaContent
    * @param className
    * @param compilationPath
    * @return
    * @throws IOException
    */
   public static ArrayList<CodeDiagnosticTo> compileJavaContentInMemory(String javaContent, String className, File compilationPath) throws IOException {

      JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
      DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

      JavaFileObject file = new JavaSourceFromString(className, javaContent);

      Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);

      StandardJavaFileManager fileManager = ToolProvider.getSystemJavaCompiler().getStandardFileManager(diagnostics, null, null);
      fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(compilationPath));

      CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);

      boolean success = task.call();

      if (success)
         return null;

      ArrayList<CodeDiagnosticTo> diagnosticsTO = new ArrayList<CodeDiagnosticTo>();

      for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
         CodeDiagnosticTo diagnosticTO = new CodeDiagnosticTo();
         diagnosticTO.setCode(diagnostic.getCode()); // Message code
         diagnosticTO.setKind(diagnostic.getKind().toString()); // ERROR
         diagnosticTO.setPosition(diagnostic.getPosition()); // Position from the first character
         diagnosticTO.setLinePosition(diagnostic.getLineNumber()); // Error line
         diagnosticTO.setFile(className); // File name
         diagnosticTO.setErrorMessage(diagnostic.getMessage(null)); // Error message

         diagnosticsTO.add(diagnosticTO);
      }

      return diagnosticsTO;
   }

   /**
    * Crea y compila una clase Main para ejecutar los tests del reto
    * @param challenge
    * @param compilationPath
    * @return
    * @throws CompilerException
    */
   public static File createAndCompileMainClass(ChallengeSaveTo challenge, File compilationPath) throws CompilerException {

      File javaFile = null;

      try {

         ChallengeParameterTo outParameter = challenge.getOutParameter();
         List<ChallengeParameterTo> inParameters = challenge.getInParameter();

         ParameterType outParameterType = ParameterType.fromString(outParameter.getType());

         String contentJavaFile = getMainClass();

         if (ParameterType.String.equals(outParameterType)) {
            contentJavaFile = contentJavaFile.replace("${expectedResult}", "args[0]");
            contentJavaFile = contentJavaFile.replace("${commentStringStart}", "");
            contentJavaFile = contentJavaFile.replace("${commentStringEnd}", "");
         }
         if (ParameterType.Long.equals(outParameterType)) {
            contentJavaFile = contentJavaFile.replace("${expectedResult}", "convertToLong(args[0])");
            contentJavaFile = contentJavaFile.replace("${commentLongStart}", "");
            contentJavaFile = contentJavaFile.replace("${commentLongEnd}", "");
         }
         if (ParameterType.Long_array.equals(outParameterType)) {
            contentJavaFile = contentJavaFile.replace("${expectedResult}", "convertToArrayLong(args[0])");
            contentJavaFile = contentJavaFile.replace("${commentArrayLongStart}", "");
            contentJavaFile = contentJavaFile.replace("${commentArrayLongEnd}", "");
         }
         if (ParameterType.String_array.equals(outParameterType)) {
            contentJavaFile = contentJavaFile.replace("${expectedResult}", "convertToArrayString(args[0])");
            contentJavaFile = contentJavaFile.replace("${commentArrayStringStart}", "");
            contentJavaFile = contentJavaFile.replace("${commentArrayStringEnd}", "");
         }

         contentJavaFile = contentJavaFile.replace("${commentArrayStringStart}", "/**");
         contentJavaFile = contentJavaFile.replace("${commentArrayStringEnd}", "**/");
         contentJavaFile = contentJavaFile.replace("${commentArrayLongStart}", "/**");
         contentJavaFile = contentJavaFile.replace("${commentArrayLongEnd}", "**/");
         contentJavaFile = contentJavaFile.replace("${commentStringStart}", "/**");
         contentJavaFile = contentJavaFile.replace("${commentStringEnd}", "**/");
         contentJavaFile = contentJavaFile.replace("${commentLongStart}", "/**");
         contentJavaFile = contentJavaFile.replace("${commentLongEnd}", "**/");

         contentJavaFile = contentJavaFile.replace("${className}", challenge.getClassName());
         contentJavaFile = contentJavaFile.replace("${methodName}", challenge.getFunctionName());

         contentJavaFile = contentJavaFile.replace("${outType}", outParameter.getType());

         String inParams = "";
         for (int i = 0; i < inParameters.size(); i++) {
            ChallengeParameterTo inParameter = inParameters.get(i);

            ParameterType inParameterType = ParameterType.fromString(inParameter.getType());

            if (i > 0)
               inParams += ",";

            String arg = "args[" + (i + 1) + "]";

            if (ParameterType.String.equals(inParameterType)) {
               inParams += arg + ".replace(\"\\\"\", \"\")";
            }
            if (ParameterType.Long.equals(inParameterType)) {
               inParams += "convertToLong(" + arg + ")";
            }
            if (ParameterType.Long_array.equals(inParameterType)) {
               inParams += "convertToArrayLong(" + arg + ")";
            }
            if (ParameterType.String_array.equals(inParameterType)) {
               inParams += "convertToArrayString(" + arg + ")";
            }

         }
         contentJavaFile = contentJavaFile.replace("${inParams}", inParams);

         javaFile = new File(compilationPath, "Main.java");
         FileUtils.write(javaFile, contentJavaFile, Charset.forName("UTF-8"));

         String javaCompileCommand = "javac " + javaFile.getName();
         SystemCommandExecutor.ExecuteSyncCommand(javaCompileCommand, compilationPath);
      } catch (IOException e) {
         LOG.error("Error al compilar el fichero Main.java", e);
         throw new CompilerException("Compilation error", e.getCause());
      }

      return javaFile;
   }

   /**
   * Lo siento, dentro de Docker no lee los resources externos, lo dejo en una constante.
   * @return
   */
   private static String getMainClass() {

      return "public class Main {\n" + //
            "\n" + //
            "  public static void main(String[] args) {\n" + //
            "\n" + //
            "    ${className} object = new ${className}();\n" + //
            "    \n" + //
            "    ${outType} expectedResult = ${expectedResult};\n" + //
            "    ${outType} result = object.${methodName}(${inParams});\n" + //
            "    \n" + //
            "    ${commentArrayStringStart}\n" + //
            "    for (int i = 0; i < expectedResult.length; i++) {\n" + //
            "      String expectedData = expectedResult[i];\n" + //
            "      String resultData = result[i];\n" + //
            "      \n" + //
            "      if (!expectedData.equals(resultData))\n" + //
            "        System.exit(-1);        \n" + //
            "    }\n" + //
            "    ${commentArrayStringEnd}\n" + //
            "    \n" + //
            "    ${commentArrayLongStart}\n" + //
            "    for (int i = 0; i < expectedResult.length; i++) {\n" + //
            "      long expectedData = expectedResult[i];\n" + //
            "      long resultData = result[i];\n" + //
            "      \n" + //
            "      if (expectedData != resultData)\n" + //
            "        System.exit(-1);\n" + //
            "    }\n" + //
            "    ${commentArrayLongEnd}    \n" + //
            "    \n" + //
            "    ${commentStringStart}\n" + //
            "    if (!expectedResult.equals(result))\n" + //
            "      System.exit(-1);\n" + //
            "    ${commentStringEnd}\n" + //
            "    \n" + //
            "    ${commentLongStart}\n" + //
            "    if (expectedResult != result)\n" + //
            "      System.exit(-1);\n" + //
            "    ${commentLongEnd}\n" + //
            "    \n" + //
            "    System.exit(1);\n" + //
            "  }\n" + //
            "  \n" + //
            "  \n" + //
            "  private static long convertToLong(String data) {\n" + //
            "    data = data.replace(\"\\\"\", \"\");\n" + //
            "    return Long.parseLong(data);\n" + //
            "  }\n" + //
            "\n" + //
            "  private static long[] convertToArrayLong(String data) {\n" + //
            "    data = data.replace(\"\\\"\", \"\");\n" + //
            "    String[] arrayData = data.split(\",\");\n" + //
            "    long[] arrayLong = new long[arrayData.length];\n" + //
            "    \n" + //
            "    for (int i = 0; i < arrayData.length; i++) {\n" + //
            "      arrayLong[i] = Long.parseLong(arrayData[i]);\n" + //
            "    }\n" + //
            "    \n" + //
            "    return arrayLong;\n" + //
            "  }\n" + //
            "\n" + //
            "  private static String[] convertToArrayString(String data) {\n" + //
            "    data = data.replace(\"\\\"\", \"\");\n" + //
            "    return data.split(\",\");\n" + //
            "  }\n" + //
            "  \n" + //
            "}\n" + //
            "";
   }

}
