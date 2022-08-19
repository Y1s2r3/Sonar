pipeline {
    agent any

    tools {
        
        maven "MAVEN_HOME"
    }

    stages {
        stage('Build') {
            steps {
                
                //git 'https://github.com/RanjanGitHubb/mark2.git'
				
                bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
			
			   success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
