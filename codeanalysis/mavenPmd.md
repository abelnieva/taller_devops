== Integrar PMD y maven ==

[http://pmd.sourceforge.net/ '''PMD'''] es una herramienta que permite pasar métricas a nuestro código java.

[[:Categoría:Maven|'''Maven''']] es otra herramienta que desde línea de comandos nos permite crear desde cero la estructura de directorios de un proyecto, compilarlo, generar jars, etc, de forma más o menos automática.

Teniendo instalado maven, no es necesario bajarse ni instalar pmd. Maven lo hace por nosotros. Para poder utilizar las métricas de pmd con maven, en nuestro fichero pom.xml de maven debemos añadir lo siguiente:

<pre>
<project>
...
   <reporting>
      <plugins>
         <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-pmd-plugin</artifactId>
         </plugin>
      </plugins>
   </reporting>
</project>
</pre>

Una vez echo esto, ejecutando

 mvn pmd:pmd

Nos analizará las métricas por defecto que pmd tiene instaladas y generará en target/site/pmd.html un report con todas las métricas que hemos "violado".

Podemos decidir nosotros qué métricas queremos que se pasen, poniéndolas en un fichero xml. El siguiente fichero, por ejemplo, es un fichero de configuración para pmd que le indica que sólo mire la complejidad ciclomática

<pre>
<?xml version="1.0"?>
<ruleset name="Custom ruleset"
    xmlns="http://pmd.sf.net/ruleset/1.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://pmd.sf.net/ruleset/1.0.0 http://pmd.sf.net/ruleset_xml_schema.xsd"
    xsi:noNamespaceSchemaLocation="http://pmd.sf.net/ruleset_xml_schema.xsd">

  <description>
  This ruleset checks my code for bad stuff
  </description>

  <!-- Now we'll customize a rule's property value -->
  <rule ref="rulesets/codesize.xml/CyclomaticComplexity">
    <properties>
        <property name="reportLevel" value="20"/>
    </properties>
  </rule>
</ruleset>
</pre>

Podemos guardar este fichero, por ejemplo con nombre ciclomatica.xml en el directorio raiz de nuestro proyecto maven, junto al pom.xml. Modificamos el pom.xml para indicar a pmd que use este fichero. Quedaría así

<pre>
<project>
...
   <reporting>
      <plugins>
         <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-pmd-plugin</artifactId>
            <configuration>
               <rulesets>
                  <ruleset>ciclomatica.xml</ruleset>
               </rulesets>
            </configuration>
         </plugin>
      </plugins>
   </reporting>
</project>
</pre>

Ahora

 mvn pmd:pmd

Hace lo mismo de antes, pero sólo nos avisa cuando la complejidad ciclomática de algún método supere el valor de 20.

Si no nos gusta esta ubicación de ciclomatica.xml, podemos ponerlo en otro directorio e indicar en el pom.xml dónde se encuentra.


== Configuracion del jdk ==

A partir de java 5 se admiten los genéricos, por lo que si usamos java 5 o superior necesitamos configurar pmd para que los tenga en cuenta. Para ello, dentro de configuration, podemos poner algo como esto para indicar qué vesrión de JDK estamos usando.

<pre>
<configuration>
   <targetJdk>1.5</targetJdk>
</configuration>
</pre>

== Hacer que falle la compilación si no se cumplen las métricas ==

Podemos hacer que falle la compilación si no se cumplen las métricas. Los comandos ''mvn compile'' y ''mvn package'' funcionarán correctamente, pero ''mvn install'' o ''mvn deploy'' fallarán.

Para hacer esto, en nuestro fichero pom.xml debemos añadir algo así

<pre>
<project>
...
   <build>
      <plugins>
         <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-pmd-plugin</artifactId>
            <configuration>
               <rulesets>
                  <ruleset>ciclomatica.xml</ruleset>
               </rulesets>
            </configuration>
            <executions>
               <execution>
                  <goals>
                     <goal>check</goal>
                  </goals>
               </execution>
            </executions>
         </plugin>
         ...
      </plugins>
      ...
   </build>
   ...
</project>
</pre>

Al hacer

 mvn install

El proceso fallará indicando cuántas violaciones de código hemos cometido


== Enlaces externos ==

* [http://foro.chuidiang.com/maven-y-ant/ Foro de Maven y ant]

[[Categoría:Herramientas y librerías]]
[[Categoría:Maven]]

Fuente: http://chuwiki.chuidiang.org/index.php?title=Maven_y_PMD