node {
    stage('SCM') {
        git 'https://github.com/Mayuresh-devops/studentapp.git'
    }
    
    stage('SonarQube analysis') {
        withSonarQubeEnv(credentialsId: '3941d8f4-cf25-45a1-b576-53d23ecdfa44') {
            sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.11.0.3922:sonar'
        }
    }
}
