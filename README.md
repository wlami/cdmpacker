# cdmpacker-maven-plugin
This plugin helps creating a parcel for deployment of artifacts to Cloudera Data Manager (CDM). This project is in no way related to Cloudera.

For documentation on parcels please see [Cloudera Manager Extensions](https://github.com/cloudera/cm_ext/wiki).

## Requirements
This plugin requires Java 8 and Maven 3.3.9 or later.

## Example
This example configures the plugin and generates files required for parcel generation.

    <project>
     ...
     <build>
       <plugins>
         <plugin>
           <groupId>de.wlami</groupId>
           <artifactId>cdmpacker-maven-plugin</artifactId>
           <version>0.2.0-SNAPSHOT</version>
           <executions>
             <execution>
               <goals>
                 <goal>generate-parcel-json</goal>
               </goals>
             </execution>
           </executions>
         </plugin>
         ...
       </plugins>
     </build>
     ...
    </project>
    
For a more complete example (override default values) see the example section on the plugin documentation. 