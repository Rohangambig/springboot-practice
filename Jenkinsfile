pipeline {

    agent any

    tools {
        maven 'Mvn-jome'
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
