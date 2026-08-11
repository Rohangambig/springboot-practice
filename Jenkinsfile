pipeline {

    agent any

    tools {
        maven 'Maven-3.9'
    }

    environment {
        IMAGE_NAME = 'rohanambig/my-spring-app'
        IMAGE_TAG = "${BUILD_NUMBER}"
    }

    stages {

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh """
                    docker build \
                    -t ${IMAGE_NAME}:${IMAGE_TAG} .
                """
            }
        }
    }
}