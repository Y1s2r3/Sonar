node {
  stage('Clone') {
  git "https://github.com/Y1s2r3/Sonar.git"
  }
  stage('Build') {
    bat "mvn -Dmaven.test.failure.ignore=true clean package"
    archiveArtifacts artifacts: 'target/*.jar', onlyIfSuccessful: true
  }
  stage ('analysis') {
    //bat "mvn verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar"
    //junit 'target/surefire-reports/*.xml'
    bat "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar \
  -Dsonar.projectKey=wertfg \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=b28333f90083aa7ab0b59d11dc1c9ba3dc5c1696"
  }
}
