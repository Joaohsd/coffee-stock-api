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
//                         mail to: "$EMAIL_DEST",
//                              subject: "Tests: ${currentBuild.fullDisplayName}",
//                              body: "Successfully running unit tests with ${env.BUILD_URL}"
                    }

                }
                stage('Build Notification') {

                    steps {
                        echo 'Notifying build...'
//                         mail to: "$EMAIL_DEST",
//                              subject: "Build: ${currentBuild.fullDisplayName}",
//                              body: "Successfully building with ${env.BUILD_URL}"
                    }

                }
            }
        }

        stage('Docker Build and Run') {

            steps{
                echo 'Building and running docker containers...'
                sh 'make run-docker-build-run'
            }

        }

        stage('API Tests') {

            steps {
                echo 'API Integration tests...'
                sh 'make run-integration-test'
                archiveArtifacts 'tests/cypress/reports/html/'
            }

        }

        stage('Notification') {

            steps {
                echo 'Notifying api tests...'
//                 mail to: "$EMAIL_DEST",
//                      subject: "Build: ${currentBuild.fullDisplayName}",
//                      body: "Successfully running api integration tests with ${env.BUILD_URL}"
            }

        }

        stage('Docker Push to DockerHub') {

            steps {
                echo 'Pushing Docker image to DockerHub...'
                sh 'make run-docker-push'
            }

        }

    }

    post {
        always {
            script {
                echo 'Cleaning up...'
                sh 'make run-docker-clean'
            }
        }
    }
}