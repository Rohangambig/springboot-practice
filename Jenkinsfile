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

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .'
            }
        }

        stage('Display Image Version') {
            steps {
                echo "Docker Image: ${IMAGE_NAME}:${IMAGE_TAG}"
            }
        }
    }
}
