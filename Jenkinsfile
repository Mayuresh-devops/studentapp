pipeline {
    agent {
        label 'test'  // The label of the node where the job will run
    }

    environment {
        SONAR_PROJECT_KEY = 'student'
        SONAR_PROJECT_NAME = 'student'
    }

    tools {
        maven 'Maven 3.8'  // Ensure this matches the name of the Maven installation in Jenkins
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
                withMaven() {
                    sh 'mvn clean package'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    withCredentials([string(credentialsId: '3941d8f4-cf25-45a1-b576-53d23ecdfa44', variable: 'SONAR_TOKEN')]) {
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
