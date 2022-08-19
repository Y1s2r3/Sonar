node {
  stage('Clone') {
  git "https://github.com/RanjanGitHubb/mark2.git"
  }
  stage('Build') {
    bat "mvn -Dmaven.test.failure.ignore=true clean package"
    archiveArtifacts artifacts: 'target/*.jar', onlyIfSuccessful: true
  }
  stage ('analysis') {
    //bat "mvn verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar"
    //junit 'target/surefire-reports/*.xml'
    bat "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar \
    -Dsonar.projectKey=gs-maven1 \
    -Dsonar.host.url=http://localhost:9000 \
    -Dsonar.user=admin \
    -Dsonar.login=sqp_4dfd6313de89be694624cfdee7d60d46cee816b4"
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
      credentialsId: '08b1f4bf-c897-4b47-9d27-82c26e6561a8', 
      groupId: 'mavenforjenkins', 
      nexusUrl: 'localhost:8081', 
      nexusVersion: 'nexus3', 
      protocol: 'http', 
      repository: 'gs-maven', 
      version: '0.0.1-SNAPSHOT'
    
  }
}
