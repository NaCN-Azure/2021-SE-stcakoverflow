FROM openjdk:8u262-jre-slim-buster
ADD ./eureka/target/eureka-0.0.1-SNAPSHOT.jar /testname-coin/testname-eureka.jar
ADD runboot-eureka.sh testname-coin/
WORKDIR /testname-coin/
RUN chmod a+x runboot-eureka.sh
CMD /testname-coin/runboot-eureka.sh