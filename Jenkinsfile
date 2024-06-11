pipeline {

    agent any

    environment {
        EMAIL_DEST='joaohsdelfino@gmail.com'
    }

    stages {

        stage('Setup'){

            steps{
                echo 'Setting up tests...'
                sh 'cd tests/ && npm i'
            }

        }

        stage('Test'){

            parallel {
                stage('Unit Tests') {

                    steps {
                        echo 'Unit tests...'
                        sh './gradlew clean test'
                        sh 'ls'
                        archiveArtifacts './build/reports/tests/test'
                    }

                }
                stage('Integration Tests') {

                    steps {
                        echo 'Integration build...'
                        sh '''
                            cd tests/
                            ./node_modules/.bin/cypress run --spec 'cypress/api/**/' --browser chrome
                           '''
                        archiveArtifacts './tests/cypress/reports/html'
                    }

                }
            }

        }

        stage('Build'){

            steps {
                echo 'Building...'
                sh './gradlew clean build'
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