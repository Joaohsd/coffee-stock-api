pipeline {

    agent any

    environment {
        EMAIL_DEST='joaohsdelfino@gmail.com'
    }

    stages {

        stage('Setup'){

            steps{
                echo 'Setting up tests...'
                sh 'cd tests/'
                sh 'npm i'
            }

        }

        stage('Test'){

            steps {
                echo 'Testing...'
                sh 'make run-test'
                archiveArtifacts 'reports/'
            }

        }

        stage('Build'){

            steps {
                echo 'Building...'
                sh 'make run-build'
                archiveArtifacts 'build/dist/'
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