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
  -Dsonar.login=9cd8c838bb0c984756c002fcc24c821c94d653f5"
  }
  stage('upload to nexus') {
   
    nexusArtifactUploader artifacts: [
      [
        artifactId: 'mavenforjenkins', 
        classifier: '', 
        file: 'target/mavenforjenkins-0.0.2-SNAPSHOT.jar', 
        type: 'jar'
      ]
    ], 
    
    credentialsId: 'Nexus-user',
    groupId: 'mavenforjenkins',
    nexusUrl: 'localhost:8081',
    nexusVersion: 'nexus3',
    protocol: 'http',
    repository: 'Nexus1',
    version: '4.0.0'
  }
}
