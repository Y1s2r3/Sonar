node {
  stage('SCM') {
    git 'cd initial && https://github.com/foo/bar.git'
  }
  stage('SonarQube analysis') {
    withSonarQubeEnv(credentialsId: 'a128e9c8ce931305e324e8df042c58ce23077b3f', installationName: 'ABC') { 
      sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
    }
  }
}
