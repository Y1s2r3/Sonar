node {
  stage('Clone') {
  git "https://github.com/Y1s2r3/Sonar.git"
  }
  stage('Build') {
    bat "mvn -Dmaven.test.failure.ignore=true clean package"
    archiveArtifacts artifacts: 'target/*.jar', onlyIfSuccessful: true
  }
  stage ('analysis') {
    
    bat "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=dd15c01be9f16902157f80e03d3ccb94840cb0f3"
  }
  stage('upload to nexus') {
    //bat "mvn verify sonar:sonar"
    // bat "mvn verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=RanjanGitHubb_mark2"
   
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
