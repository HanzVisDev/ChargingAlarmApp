@ECHO OFF
SET DIRNAME=%~dp0
SET APP_HOME=%DIRNAME%

IF NOT "%JAVA_HOME%"=="" (
  SET JAVA_EXE=%JAVA_HOME%\bin\java.exe
) ELSE (
  SET JAVA_EXE=java.exe
)

SET CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar
"%JAVA_EXE%" -Xms64m -Xmx64m -cp "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*

