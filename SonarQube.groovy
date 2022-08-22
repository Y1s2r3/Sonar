node {
  stage('Clone') {
  git "https://github.com/Y1s2r3/Sonar.git"
  }
  stage('Build') {
    bat "mvn -Dmaven.test.failure.ignore=true clean package"
  }
  stage ('analysis') {
     bat "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=f1b02859e6a66c45d03e242f3bfe2ac79572f30f"
  }
  stage('upload to nexus') {
   
     nexusArtifactUploader credentialsId: 'Nexus_Repo', groupId: 'Nexus-Repo', nexusUrl: 'localhost:8081/', nexusVersion: 'nexus3', protocol: 'http', repository: 'Nexus-Repo', version: '4.3.4' 
}
