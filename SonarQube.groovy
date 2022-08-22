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
   
    nexusArtifactUploader artifacts: [
      [
        artifactId: 'mavenforjenkins1', 
        classifier: '', 
        file: 'target/mavenforjenkins-0.0.1-SNAPSHOT.jar', 
        type: 'jar'
      ]
    ], 
    
    credentialsId: 'Nexus_Repo',
    groupId: 'NexusRepo',
    nexusUrl: 'http://localhost:8081/',
    nexusVersion: 'nexus2',
    protocol: 'http',
    repository: 'Nexus-Repo',
    version: '3.4.2'
  }
}
