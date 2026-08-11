pipeline {

    agent any

    tools {
        maven 'Mvn-jome'
    }

    environment {
        IMAGE_NAME = 'rohanambig/my-spring-app'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Create Image Tag') {
            steps {
                script {
                    def dateTime = sh(
                        script: "date '+%Y.%m.%d-%H.%M.%S'",
                        returnStdout: true
                    ).trim()

                    def branch = env.BRANCH_NAME ?: 'main'

                    env.IMAGE_TAG = "v${dateTime}-${branch}"

                    echo "Docker Image: ${IMAGE_NAME}:${IMAGE_TAG}"
                }
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

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh '''
                        mvn sonar:sonar \
                        -Dsonar.projectKey=spring-second-project
                    '''
                }
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

        stage('Display Image') {
            steps {
                echo "Docker Image: ${IMAGE_NAME}:${IMAGE_TAG}"
            }
        }
    }
}
