# Area Placer Application

### About
Calculates all possibilities of placing furniture represented by different shapes 
into a room represented by 2D area of a given shape.

### Building the Application

1. Set JavaHome to Java 11:
    * windows: `set JAVA_HOME=C:\Program Files\Java\jdk-11.0.6`
    
2. To build this project run:
    * unix: `./mvnw clean install`
    * windows: `./mvnw.cmd clean install`

### Running the Application

Take the executable JAR file generated by the Maven build:

 `.../areaplacer/areaplacer-boot/target/areaplacer-boot-0.0.1-SNAPSHOT.jar`
 
and run it with following arguments:
1. absolute path to input file containing room: `<path-to-ROOM-file>`
REQUIRED
2. absolute path to input file containing furniture: `<path-to-FURNITURE-file>`
REQUIRED
3. absolute path to output file: `[path-to-OUTPUT-file]`
OPTIONAL

Example:
`java -jar areaplacer-boot-0.0.1-SNAPSHOT.jar <path-to-ROOM-file> <path-to-FURNITURE-file> [path-to-OUTPUT-file]`

### Input Files Examples

This is how a room input file  is specified:

`3,4`\
`###.`\
`####`\
` .##.`

This is how a furniture input file is specified:

`A2#.##`\
`B1##`\
`C2##`

### Limitations

* maximal width of the furniture is 9
