# Setup Instructions

## JDK Version 
                - JDK 17

## Steps to Run
1. Install JDK from https://www.oracle.com/java/technologies/downloads/
2. Verify installation:
   java -version
3. Open project in IntelliJ IDEA
4. Run `Main.java`

## Hello World Program Verification

Before starting the LearnTrack project, a simple **Hello World** Java program was created
and executed to verify that the Java Development Kit (JDK) was installed correctly
and that the development environment was working as expected.

### HelloWorld.java
public class HelloWorld {
    public static void main(String[] args) {
                    System.out.println("Hello, World!");
                    }
    }

----The class HelloWorld defines a basic Java class.
----The main method is the entry point of every Java application.
----System.out.println() is used to print text to the console.

The program was compiled using:
javac HelloWorld.java

And executed using:
java HelloWorld

--------------------------------------------------------

Successful execution of this program confirmed that:

1.Java was installed correctly
2.The compiler (javac) and JVM were functioning properly
3.The system was ready for developing the LearnTrack application