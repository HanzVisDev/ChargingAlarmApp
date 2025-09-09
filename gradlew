#!/usr/bin/env sh

DIRNAME=$(cd "$(dirname "$0")" && pwd)
APP_HOME=$DIRNAME

if [ -z "$JAVA_HOME" ]; then
  JAVA_EXE=java
else
  JAVA_EXE="$JAVA_HOME/bin/java"
fi

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar
exec "$JAVA_EXE" -Xms64m -Xmx64m -cp "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"

