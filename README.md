You need to have the following installed on your machine.

JDK 1.8   http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

Maven 3.3.9   https://maven.apache.org/download.cgi

ZooKeeper  3.4.8    https://zookeeper.apache.org/releases.html

Kafka   >=2.11-0.10.2.0   http://kafka.apache.org/downloads.html

Flink   1.9.1     Include in the maven repository

OpenCV    3.2.0   http://opencv.org/releases.html

Instruction to run:

1. Keep the Zookeeper and Kafka servers up and running
2. We need OpenCV native libraries to run this application. 
Mention them in the VM options in IntelliJ or add the below while running through the command line

-Djava.library.path="<Your download path>/opencv-3.2.0/build/lib" 

3. Create a new Kafka topic using below 

kafka-topics.sh --create --zookeeper localhost:2181 --topic video-stream-event --replication-factor 1 --partitions 3

4. Ensure that the input path camera.url, output path processed.output.dir and the log directories for stream-collector.log,stream-processor.log are created as mentioned in your code.

5. To install the OpenCV JAR in a local Maven repository go to the video-stream-processor folder and execute 

mvn clean

6. Start the video stream processor first
Use IntelliJ and create a configuration in corresponding main class and VM options or run the below from command line.

mvn clean package exec:java -Dexec.mainClass="com.iot.video.app.flink.processor.VideoStreamProcessor" -Dexec.cleanupDaemonThreads=false

7. Start the video stream collector

mvn clean package exec:java -Dexec.mainClass="com.iot.video.app.kafka.collector.VideoStreamCollector" -Dexec.cleanupDaemonThreads=false

There you go!

Check your output directory(processed.output.dir) and see the processed frames.