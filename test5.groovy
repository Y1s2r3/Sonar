node {
  stage('SCM') {
    git 'https://github.com/Y1s2r3/Sonar.git'
  }
  stage('SonarQube analysis') {
    withSonarQubeEnv(credentialsId: 'e19f2b234afa50cc33d48e0436d19b3c134e3783', installationName: 'ABC') { 
      bat 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
    }
  }
}
