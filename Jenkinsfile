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
                echo 'Copy schema.sql to shared-volume'
                sh 'docker run --rm -v shared-volume:/shared -v $(pwd)/schema:/source alpine cp /source/schema.sql /shared/schema.sql'
                echo 'Running containers...'
                sh 'docker compose -f docker-compose-test.yml up -d'
                sh 'sleep 20'
                sh 'docker network create myNetwork'
                sh 'docker network connect myNetwork db'
                sh 'docker network connect myNetwork api'
                sh 'docker network connect myNetwork jenkins'
                sh 'sleep 5'
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
                // Remove network created
                sh 'docker network disconnect myNetwork jenkins'
                sh 'docker network disconnect myNetwork api'
                sh 'docker network disconnect myNetwork db'
                sh 'docker network rm myNetwork'
                // Stop containers
                sh 'docker-compose -f docker-compose-test.yml down'
                // Remove all images
                sh 'docker rmi -f mysql:8.4.0'
                sh 'docker rmi -f coffee-image:latest'
                // Remove volumes
                sh 'docker volume rm -f my-db'
            }
        }
    }
}