node {
  stage('SCM') {
    git 'https://github.com/Y1s2r3/Sonar.git'
  }
  stage('SonarQube analysis') {
   bat "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar \
  -Dsonar.projectKey=wertfgf \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=8eca862fd93a92c3e6ae9a1b0b1fcbe627febe73"
    
  }
}
