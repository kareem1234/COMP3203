@ECHO OFF

set path=%PATH%;C:\Program Files\Java\jdk1.7.0_45\bin
javac Sensor.java
javac SensorDomain.java
javac Algorithm.java
javac UnitLineAlgorithm.java
javac UnitPerimeterAlgorithm.java
javac UnitSquareAlgorithm.java
javac Statistics.java
javac CumulativeStats.java
javac MyCanvas.java
javac TestingProgram.java
java TestingProgram
