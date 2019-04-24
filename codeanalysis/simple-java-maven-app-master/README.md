Original: https://github.com/jenkins-docs/simple-java-maven-app

Notas del curso 

     <reporting>
      <plugins>
         <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-pmd-plugin</artifactId>
         </plugin>
      </plugins>
   </reporting>

Agregamos esa lineas al pom.xml  y obtuvimos el reportes al ejectuar mvn  como se explica en el documento ubicado en el directorio superior 

# simple-java-maven-app

This repository is for the
[Build a Java app with Maven](https://jenkins.io/doc/tutorials/build-a-java-app-with-maven/)
tutorial in the [Jenkins User Documentation](https://jenkins.io/doc/).

The repository contains a simple Java application which outputs the string
"Hello world!" and is accompanied by a couple of unit tests to check that the
main application works as expected. The results of these tests are saved to a
JUnit XML report.

The `jenkins` directory contains an example of the `Jenkinsfile` (i.e. Pipeline)
you'll be creating yourself during the tutorial and the `scripts` subdirectory
contains a shell script with commands that are executed when Jenkins processes
the "Deliver" stage of your Pipeline.
