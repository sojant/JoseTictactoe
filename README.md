# Jose Tictactoe

Project aiming to build a platform for a TicTacToe game, featuring an unbeatable AI.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

In the docs/ directory of the project, there is a file with the algorithm followed to achieve my unbeatable AI, and a spreadsheet with some concepts to Map the states of the board.

### Prerequisites

This project is coded in Java, so the Java JDK platform is needed in order to run, test and build the project.

```
Java JDK 7 or 8 (due to default Gradle build)
Java 9 Can be used but you'll need to download Gradle 4
```

Once installed, make sure to point the JAVA_HOME environment variable to the location where the JDK is installed.

### Installing

Make a clone of the project, then use the Gradle Wraper (which comes bundled) to build, run or test.

In Linux first give the Gradle Wrapper exec Permissons
```
chmod +x ./gradlew
```

## Running the tests

To run the Tests 

```
.\gradlew.bat test (Windows)
./gradlew test (Unix)
```

This will execute the tests and leave the results at:
<ProjectDir>\build\reports\tests\test

## Building and Running

To build the project run:

```
.\gradlew.bat build (Windows)
./gradlew build (Unix)
```

This will build the binaries at:
<ProjectDir>build\distributions

Here you'll find a zip file, Unzip and then open a terminal/console in:
<ProjectDir>build\distributions\tictactoe-1.0-SNAPSHOT\bin

To run the project:

```
.\tictactoe.bat (Windows)
./tictactoe  (Unix)
```

## Built With

* [Java](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java SE
* [Gradle](https://docs.gradle.org/) - Build Tool
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - IDE

## Author

* **Jose Vazquez** - (https://github.com/sojant)
