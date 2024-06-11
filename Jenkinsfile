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

        stage('Test'){

            parallel {
                stage('Unit Tests') {

                    steps {
                        echo 'Unit tests...'
                        sh 'make run-unit-tests'
                        archiveArtifacts 'build/reports/tests/test/'
                    }

                }
                stage('Integration Tests') {

                    steps {
                        echo 'Integration build...'
                        sh 'make run-integration-tests'
                        archiveArtifacts 'tests/cypress/reports/html/'
                    }

                }
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

    }
}