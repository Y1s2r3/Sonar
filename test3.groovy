node {
  stage('Clone') {
  git "https://github.com/RanjanGitHubb/mark2.git"
  }
  stage('Build') {
    bat "mvn -Dmaven.test.failure.ignore=true clean package"
    archiveArtifacts artifacts: 'target/*.jar', onlyIfSuccessful: true
  }
  stage('upload to nexus') {
    //bat "mvn verify sonar:sonar"
    //bat "mvn verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=RanjanGitHubb_mark2"
   def mavenPom = readMavenPom file: 'pom.xml'
   def nexusRepoName = mavenPom.version.endsWith("SNAPSHOT") ? "gs-maven" : "mavenforjenkins-release"
      nexusArtifactUploader artifacts: [
      [
        artifactId: 'mavenforjenkins', 
        classifier: '', 
        file: 'target/mavenforjenkins-${mavenPom.version}.jar', 
        type: 'jar'
      ]
    ], 
      credentialsId: '08b1f4bf-c897-4b47-9d27-82c26e6561a8', 
      groupId: 'mavenforjenkins', 
      nexusUrl: 'localhost:8081', 
      nexusVersion: 'nexus3', 
      protocol: 'http', 
      repository: 'nexusRepoName', 
      version: '${mavenPom.version}'
    
  }
}
