package com.capgemini.ccsw.codewar.compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capgemini.ccsw.codewar.compiler.to.CodeDiagnosticTo;

/**
 * @author pajimene
 *
 */
public class JavaClassUtils {
   private static final Logger LOG = LoggerFactory.getLogger(JavaClassUtils.class);

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

   /*
   public static File createAndCompileMainClass(CompilationDataTo data, File compilationPath) throws CompilerException {
   
      File javaFile = null;
   
      try {
         ParametersEntity outParameter = getOutParameter(data);
         List<ParametersEntity> inParameters = getOrderedInParameters(data);
   
         String contentJavaFile = getMainClass();
   
         if (TypesEntity.STRING.equals(outParameter.getType())) {
            contentJavaFile = contentJavaFile.replace("${expectedResult}", "args[0]");
            contentJavaFile = contentJavaFile.replace("${commentStringStart}", "");
            contentJavaFile = contentJavaFile.replace("${commentStringEnd}", "");
         }
         if (TypesEntity.LONG.equals(outParameter.getType())) {
            contentJavaFile = contentJavaFile.replace("${expectedResult}", "convertToLong(args[0])");
            contentJavaFile = contentJavaFile.replace("${commentLongStart}", "");
            contentJavaFile = contentJavaFile.replace("${commentLongEnd}", "");
         }
         if (TypesEntity.ARRAY_LONG.equals(outParameter.getType())) {
            contentJavaFile = contentJavaFile.replace("${expectedResult}", "convertToArrayLong(args[0])");
            contentJavaFile = contentJavaFile.replace("${commentArrayLongStart}", "");
            contentJavaFile = contentJavaFile.replace("${commentArrayLongEnd}", "");
         }
         if (TypesEntity.ARRAY_STRING.equals(outParameter.getType())) {
            contentJavaFile = contentJavaFile.replace("${expectedResult}", "convertToArrayString(args[0])");
            contentJavaFile = contentJavaFile.replace("${commentArrayStringStart}", "");
            contentJavaFile = contentJavaFile.replace("${commentArrayStringEnd}", "");
         }
   
         contentJavaFile = contentJavaFile.replace("${commentArrayStringStart}", "/**");
         contentJavaFile = contentJavaFile.replace("${commentArrayStringEnd}", "**//*");  Quitar /*
                                                                                    contentJavaFile = contentJavaFile.replace("${commentArrayLongStart}", "/**");
                                                                                    contentJavaFile = contentJavaFile.replace("${commentArrayLongEnd}", "**//*"); Quitar /*
                                                                                                                                                             contentJavaFile = contentJavaFile.replace("${commentStringStart}", "/**");
                                                                                                                                                             contentJavaFile = contentJavaFile.replace("${commentStringEnd}", "**//*");  Quitar /*
                                                                                                                                                                                                                                   contentJavaFile = contentJavaFile.replace("${commentLongStart}", "/**");
                                                                                                                                                                                                                                   contentJavaFile = contentJavaFile.replace("${commentLongEnd}", "**//*");  Quitar /*
                                                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                                       contentJavaFile = contentJavaFile.replace("${className}", data.getClassName());
                                                                                                                                                                                                                                                                                                       contentJavaFile = contentJavaFile.replace("${methodName}", data.getMethodName());
                                                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                                       contentJavaFile = contentJavaFile.replace("${outType}", getJavaType(outParameter.getType()));
                                                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                                       String inParams = "";
                                                                                                                                                                                                                                                                                                       for (int i = 0; i < inParameters.size(); i++) {
                                                                                                                                                                                                                                                                                                       ParametersEntity inParameter = inParameters.get(i);
                                                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                                       if (i > 0)
                                                                                                                                                                                                                                                                                                       inParams += ",";
                                                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                                       String arg = "args[" + (i + 1) + "]";
                                                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                                       if (TypesEntity.STRING.equals(inParameter.getType())) {
                                                                                                                                                                                                                                                                                                       inParams += arg + ".replace(\"\\\"\", \"\")";
                                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                                       if (TypesEntity.LONG.equals(inParameter.getType())) {
                                                                                                                                                                                                                                                                                                       inParams += "convertToLong(" + arg + ")";
                                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                                       if (TypesEntity.ARRAY_LONG.equals(inParameter.getType())) {
                                                                                                                                                                                                                                                                                                       inParams += "convertToArrayLong(" + arg + ")";
                                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                                       if (TypesEntity.ARRAY_STRING.equals(inParameter.getType())) {
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
                                                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                                       public static String getJavaType(String type) {
                                                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                                       if (TypesEntity.ARRAY_LONG.equals(type))
                                                                                                                                                                                                                                                                                                       return "long[]";
                                                                                                                                                                                                                                                                                                       if (TypesEntity.ARRAY_STRING.equals(type))
                                                                                                                                                                                                                                                                                                       return "String[]";
                                                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                                       if (TypesEntity.LONG.equals(type))
                                                                                                                                                                                                                                                                                                       return "long";
                                                                                                                                                                                                                                                                                                       if (TypesEntity.STRING.equals(type))
                                                                                                                                                                                                                                                                                                       return "String";
                                                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                                       return null;
                                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                                       private static ParametersEntity getOutParameter(CompilationDataTo data) {
                                                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                                       TestEntity test = data.getTests().get(0);
                                                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                                       for (ParameterValueEntity parameterValue : test.getParametersValue()) {
                                                                                                                                                                                                                                                                                                       if (!parameterValue.getParameter().getInput())
                                                                                                                                                                                                                                                                                                       return parameterValue.getParameter();
                                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                                       return null;
                                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                                       private static List<ParametersEntity> getOrderedInParameters(CompilationDataTo data) {
                                                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                                       TestEntity test = data.getTests().get(0);
                                                                                                                                                                                                                                                                                                       List<ParametersEntity> inParameters = new ArrayList<>();
                                                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                                       for (ParameterValueEntity parameterValue : test.getParametersValue()) {
                                                                                                                                                                                                                                                                                                       if (parameterValue.getParameter().getInput())
                                                                                                                                                                                                                                                                                                       inParameters.add(parameterValue.getParameter());
                                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                                       Collections.sort(inParameters, new Comparator<ParametersEntity>() {
                                                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                                       @Override
                                                                                                                                                                                                                                                                                                       public int compare(ParametersEntity o1, ParametersEntity o2) {
                                                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                                       return o1.getOrder() - o2.getOrder();
                                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                                       });
                                                                                                                                                                                                                                                                                                       
                                                                                                                                                                                                                                                                                                       return inParameters;
                                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                                       */

   /**
   * Lo siento, dentro de Docker no lee los resources externos, lo dejo en una constante.
   * @return
   */
   /*
   private static String getMainClass() {
   
      return "public class Main {" + //
            "" + //
            "  public static void main(String[] args) {" + //
            "" + //
            "    ${className} object = new ${className}();" + //
            "    " + //
            "    ${outType} expectedResult = ${expectedResult};" + //
            "    ${outType} result = object.${methodName}(${inParams});" + //
            "    " + //
            "    ${commentArrayStringStart}" + //
            "    for (int i = 0; i < expectedResult.length; i++) {" + //
            "      String expectedData = expectedResult[i];" + //
            "      String resultData = result[i];" + //
            "      " + //
            "      if (!expectedData.equals(resultData))" + //
            "        System.exit(-1);        " + //
            "    }" + //
            "    ${commentArrayStringEnd}" + //
            "    " + //
            "    ${commentArrayLongStart}" + //
            "    for (int i = 0; i < expectedResult.length; i++) {" + //
            "      long expectedData = expectedResult[i];" + //
            "      long resultData = result[i];" + //
            "      " + //
            "      if (expectedData != resultData)" + //
            "        System.exit(-1);" + //
            "    }" + //
            "    ${commentArrayLongEnd}    " + //
            "    " + //
            "    ${commentStringStart}" + //
            "    if (!expectedResult.equals(result))" + //
            "      System.exit(-1);" + //
            "    ${commentStringEnd}" + //
            "    " + //
            "    ${commentLongStart}" + //
            "    if (expectedResult != result)" + //
            "      System.exit(-1);" + //
            "    ${commentLongEnd}" + //
            "    " + //
            "    System.exit(1);" + //
            "  }" + //
            "  " + //
            "  " + //
            "  private static long convertToLong(String data) {" + //
            "    data = data.replace(\"\\\"\", \"\");" + //
            "    return Long.parseLong(data);" + //
            "  }" + //
            "" + //
            "  private static long[] convertToArrayLong(String data) {" + //
            "    data = data.replace(\"\\\"\", \"\");" + //
            "    String[] arrayData = data.split(\",\");" + //
            "    long[] arrayLong = new long[arrayData.length];" + //
            "    " + //
            "    for (int i = 0; i < arrayData.length; i++) {" + //
            "      arrayLong[i] = Long.parseLong(arrayData[i]);" + //
            "    }" + //
            "    " + //
            "    return arrayLong;" + //
            "  }" + //
            "" + //
            "  private static String[] convertToArrayString(String data) {" + //
            "    data = data.replace(\"\\\"\", \"\");" + //
            "    return data.split(\",\");" + //
            "  }" + //
            "  " + //
            "}" + //
            "";
   }
   */

}
