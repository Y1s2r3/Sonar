node {
  stage('Clone') {
  git "https://github.com/Y1s2r3/Sonar.git"
  }
  stage('Build') {
    bat "mvn -Dmaven.test.failure.ignore=true clean package"
  }
  stage ('analysis') {
    
    bat "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar \
  -Dsonar.host.url= https://sonarcloud.io \
  -Dsonar.login=92a8e91bb1971fb2decf6baa3e839e815a39b0f0"
  }
  stage('upload to nexus') {
   
      nexusArtifactUploader artifacts: [
      [
        artifactId: 'mavenforjenkins', 
        classifier: '', 
        file: 'target/mavenforjenkins-0.0.1-SNAPSHOT.jar', 
        type: 'jar'
      ]
    ], 
      credentialsId: 'NEXUS_CRED', 
      groupId: 'mavenforjenkins', 
      nexusUrl: 'localhost:8081', 
      nexusVersion: 'nexus3', 
      protocol: 'http', 
      repository: 'Nexus-Test', 
      version: '0.0.1-SNAPSHOT'
    
  }
}
