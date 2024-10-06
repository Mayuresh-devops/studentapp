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
        stage('SCM') {
            steps {
                echo "Cloning the repository from GitHub"
                // Clone the repository (you can set branch to '*/main' or as needed)
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "Building the Maven project..."
                // Run Maven commands to clean and package the project
                sh 'mvn clean package'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    // Use the SonarQube authentication token
                    withCredentials([string(credentialsId: '3941d8f4-cf25-45a1-b576-53d23ecdfa44', variable: 'SONAR_TOKEN')]) {
                        // Run SonarQube analysis using the SonarQube token
                        withSonarQubeEnv('SonarQube') {
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
