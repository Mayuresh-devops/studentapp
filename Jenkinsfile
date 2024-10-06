pipeline {
    agent {
        label 'test'  // The label of the node where the job will run
    }

    environment {
        // SonarQube environment variables
        SONAR_PROJECT_KEY = 'student'
        SONAR_PROJECT_NAME = 'student'
    }

    stages {
        stage('Pull') {
            steps {
                echo 'We are pulling from GitHub'
                git 'https://github.com/Unknown6M/studentapp.git'
            }
        }
        stage('SonarQube Analysis') {
            def scannerHome = tool 'SonarScanner';
            withSonarQubeEnv() {
            sh "${scannerHome}/bin/sonar-scanner"
            }
        }
        stage('Build') {
            steps {
                echo 'Building the Maven project...'

                // Install Maven (preferably, do this outside the pipeline if possible)
                sh 'sudo apt-get update && sudo apt-get install -y maven'

                // Build the project
                sh 'mvn clean package'
            }
        }

    }

    post {
        always {
            echo 'This will run after all stages, regardless of success or failure.'
        }

        success {
            echo 'Pipeline executed successfully!'
        }

        failure {
            echo 'Pipeline failed. Please check the logs for errors.'
        }
    }
}
