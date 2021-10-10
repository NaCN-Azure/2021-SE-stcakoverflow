pipeline {
    agent any

    stages {
        stage('Maven Build') {
            steps{
                echo 'Maven Build and Cobertura Stage'
                sh '/opt/java/apache-maven-3.6.3/bin/mvn clean package -Dmaven.test.skip=true'
            }
	    }
 //       stage('Jacoco Report') {
 //           steps{
  //               echo 'Jacoco Stage'
   //              sh '/opt/java/apache-maven-3.6.3/bin/mvn test'
    //         }
     //   }

	    stage('Image-eureka Clear'){
	        steps{
	            echo 'Image-eureka Clear Stage'
	            sh "if (docker ps -a| grep testname-eureka) then (docker container stop testname-eureka && docker container rm testname-eureka) fi"
                sh "if (docker images | grep testname-eureka) then (docker rmi \$(docker images testname-eureka -q)) fi"
	        }
	    }

        stage('Image-eureka Build'){
            steps{
                echo 'Image-eureka Build Stage'
                sh "docker build . -t testname-eureka:${BUILD_ID}"
            }
        }


       stage('Image-coin-so Clear'){
            steps{
                echo 'Image-coin-so Clear Stage'
                sh "if (docker ps -a| grep coin-so) then (docker container stop coin-so && docker container rm coin-so) fi"
                   sh "if (docker images | grep coin-so) then (docker rmi \$(docker images coin-so -q)) fi"
            }
        }
        stage('Image-user Clear'){
            steps{
                echo 'Image-user Clear Stage'
                sh "if (docker ps -a| grep testname-user) then (docker container stop testname-user && docker container rm testname-user) fi"
                   sh "if (docker images | grep testname-user) then (docker rmi \$(docker images testname-user -q)) fi"
            }
               	    }
        stage('Image-gateway Clear'){
            steps{
                echo 'Image-gateway Clear Stage'
                sh "if (docker ps -a| grep testname-gateway) then (docker container stop testname-gateway && docker container rm testname-gateway) fi"
                sh "if (docker images | grep testname-gateway) then (docker rmi \$(docker images testname-gateway -q)) fi"
            }
        }
         stage('Deploy-eureka'){
            steps{
                echo 'Deploy-eureka'
                sh "docker run -p 5555:5555 --name testname-eureka -v /log:/log -dit testname-eureka:${BUILD_ID}"
            }
        }
         stage('Image-coin-so Build'){
            steps{
                sh "mv Dockerfile Dockerfile-eureka"
                sh "mv Dockerfile-coin Dockerfile"
                echo 'Image-coin-so Build Stage'
                sh "docker build . -t coin-so:${BUILD_ID}"
            }
        }

        stage('Image-user Build'){
            steps{
                sh "mv Dockerfile Dockerfile-coin"
                sh "mv Dockerfile-user Dockerfile"
                echo 'Image-user Build Stage'
                sh "docker build . -t testname-user:${BUILD_ID}"
            }
        }
        stage('Image-gateway Build'){
            steps{
                sh "mv Dockerfile Dockerfile-kbqa"
                sh "mv Dockerfile-gateway Dockerfile"
                echo 'Image-gateway Build Stage'
                sh "docker build . -t testname-gateway:${BUILD_ID}"
            }
        }
        stage('Deploy-service'){
            steps{
                sh "mv Dockerfile Dockerfile-gateway"
                sh "mv Dockerfile-eureka Dockerfile"
                sh "docker run -p 7001:7001 --name coin-so -v /log:/log -dit coin-so:${BUILD_ID}"
                sh "docker run -p 6001:6001 --name testname-user -v /log:/log -dit testname-user:${BUILD_ID}"
                sh "docker run -p 8330:8330 --name testname-gateway -v /log:/log -dit testname-gateway:${BUILD_ID}"

            }
        }
    }
    post {
            success {
                // publish html
                publishHTML target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: '',
                    reportFiles: 'index.html',
                    reportName: 'Jacoco Report'
                ]
            }
        }
}