pipeline {
    agent none
    stages {
        stage('Image Clear'){
            agent any
	        steps{
	            echo 'Image Clear Stage'
	            sh "if (docker ps | grep frontend-so) then (docker container stop frontend-so && docker container rm frontend-so) fi"
                sh "if (docker images | grep frontend-so) then (docker rmi \$(docker images frontend-so -q)) fi"
	        }
	    }
        stage('Build'){
              agent {
                         docker { image 'node:7-alpine' }
                       }
	        steps{
	            echo 'Build Stage'
	            sh "ls"
	            sh "npm install --registry=https://mirrors.huaweicloud.com/repository/npm/"
                sh "npm run build"
	        }
	    }
        stage('Image Build'){
            agent any
            steps{
                echo 'Image Build Stage'
                sh "docker build . -t frontend-so:${BUILD_ID}"
            }
        }
        stage('Deploy'){
            agent any
            steps{
                sh "docker run -p 8080:8080 --name frontend-so -v /log:/log -d frontend-so:${BUILD_ID}"
            }
        }
    }
}
