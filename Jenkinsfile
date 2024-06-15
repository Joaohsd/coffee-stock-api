pipeline {

    agent any

    environment {
        EMAIL_DEST='joaohsdelfino@gmail.com'
    }

    stages {

        stage('Setup'){

            steps{
                echo 'Setting up tests...'
                sh 'make run-setup'
            }

        }

        stage('Unit Test'){

            steps {
                echo 'Unit tests...'
                sh 'make run-unit-test'
                archiveArtifacts 'build/reports/tests/test/'
            }

        }

        stage('Build'){

            steps {
                echo 'Building...'
                sh 'make run-build'
                archiveArtifacts 'build/libs/'
            }

        }

        stage('Notification') {
            parallel {
                stage('Test Notification') {

                    steps {
                        echo 'Notifying tests...'
                        sh 'make run-notify-test'
                    }

                }
                stage('Build Notification') {

                    steps {
                        echo 'Notifying build...'
                        sh 'make run-notify-build'
                    }

                }
            }
        }

        stage('Docker Build') {

            steps{
                echo 'Building docker image...'
                sh 'docker build -t coffee-image -f Dockerfile-API .'
            }

        }

        stage('Docker Run API') {

            steps{
                echo 'Running containers...'
                sh 'docker compose -f docker-compose-test.yml up -d'
                sleep 10
            }

        }

        stage('API Integration Tests') {

            steps {
                echo 'API Integration tests...'
                sh 'make run-integration-test'
                archiveArtifacts 'tests/cypress/reports/html/'
            }

        }

    }

    post {
        always {
            script {
                echo 'Cleaning up...'
                // Stop containers
                sh 'docker compose down'
                // Remove all images
                sh 'docker rmi -f mysql:8.4.0'
                sh 'docker rmi -f coffee-image:latest'
            }
        }
    }
}