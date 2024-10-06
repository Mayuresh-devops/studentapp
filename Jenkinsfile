pipeline {
    agent {
        label 'test'  // Define the label of the node on which this pipeline will run
    }

    environment {
        // Define environment variables for SonarQube project properties
        SONAR_PROJECT_KEY = 'student'
        SONAR_PROJECT_NAME = 'student'
    }

    stages {
        stage('Pull') {
            steps {
                echo "We are pulling from GitHub"
                // Pulling the code from the GitHub repository
                git url: 'https://github.com/Unknown6M/studentapp.git', branch: 'master'
            }
        }

        stage('Install Maven') {
            steps {
                echo "Installing Maven..."
                // Install Maven (assumes Ubuntu/Debian-based system)
                sh '''
                sudo apt update
                sudo apt install -y maven
                '''
            }
        }

        stage('Build') {
            steps {
                echo "Building the Maven project..."
                // Build the project using Maven
                sh 'mvn clean package'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    // Run SonarQube analysis using Maven
                    withSonarQubeEnv() {
                        sh """
                        mvn clean verify sonar:sonar \
                        -Dsonar.projectKey=${env.SONAR_PROJECT_KEY} \
                        -Dsonar.projectName=${env.SONAR_PROJECT_NAME}
                        """
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
