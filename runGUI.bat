@ECHO OFF
ECHO Starting script
set path=%path%;C:\Program Files\Java\jdk1.7.0_21\bin
javac Algorithm.java
javac Controller.java
javac CumulativeStats.java
javac UnitSquareAlgorithm.java
javac MyCanvas.java
javac Sensor.java
javac SensorDomain.java
javac Statistics.java
javac UnitLineAlgorithm.java
javac UnitPerimeterAlgorithm.java
java  Controller
