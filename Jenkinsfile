pipeline {
    agent any

    environment {
        SONAR_PROJECT_KEY = 'student'
        SONAR_PROJECT_NAME = 'student'
    }

    tools {
        maven 'Maven 3.8'  // Ensure this matches your Maven installation in Jenkins
    }

    stages {
        stage('Pull') {
            steps {
                echo "We are pulling from GitHub"
                git "https://github.com/Unknown6M/studentapp.git"
            }
        }

        stage('Build') {
            steps {
                echo "Building the Maven project..."
                sh 'mvn clean package'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    // Use credentials for SonarQube token
                    withCredentials([string(credentialsId: '3941d8f4-cf25-45a1-b576-53d23ecdfa44', variable: 'SONAR_TOKEN')]) {
                        // Use the configured SonarQube environment
                        withSonarQubeEnv('SonarQube') {  // Ensure this name matches exactly with your configuration
                            sh """
                            mvn clean verify sonar:sonar \
                            -Dsonar.projectKey=${env.SONAR_PROJECT_KEY} \
                            -Dsonar.projectName=${env.SONAR_PROJECT_NAME} \
                            -Dsonar.login=${SONAR_TOKEN}
                            """
                        }
                    }
                }
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
