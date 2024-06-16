pipeline {

    agent any

    environment {
        EMAIL_DEST='joaohsdelfino@gmail.com'
        DOCKERHUB_REPO='joaohsd/coffee-stock'
        DOCKERHUB_CREDS=credentials('bf1a5149-d813-447a-b842-8f7d320dafff')
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

        stage('Docker Build and Run') {

            steps{
                echo 'Building docker image...'
                sh 'docker build -t coffee-image -f Dockerfile-API .'
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

        stage('API Tests') {

            steps {
                echo 'API Integration tests...'
                sh 'make run-integration-test'
                archiveArtifacts 'tests/cypress/reports/html/'
            }

        }

        stage('Docker Push to DockerHub') {
            steps {
                echo 'Pushing Docker image to DockerHub...'
                sh "echo $DOCKERHUB_CREDS_PSW | docker login -u $DOCKERHUB_CREDS_USR --password-stdin"
                sh "docker tag coffee-image:latest $DOCKERHUB_REPO:latest"
                sh "docker push $DOCKERHUB_REPO:latest"
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
                sh 'docker stop api'
                sh 'docker stop db'
                sh 'docker rm api'
                sh 'docker rm db'
                // Remove all images
                sh 'docker rmi -f mysql:8.4.0'
                sh 'docker rmi -f coffee-image:latest'
                sh 'docker rmi -f joaohsd/coffee-stock:latest'
                // Remove volumes
                sh 'docker volume rm -f my-db'
            }
        }
    }
}