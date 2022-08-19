node {
	stage('Clone') {
        	git 'https://github.com/RanjanGitHubb/mark2.git'
    	}
    	stage('Complile') {
	    bat "mvn -Dmaven.test.failure.ignore=true clean compile"
	}
	stage('Build') {
	    bat "mvn -Dmaven.test.failure.ignore=true clean install"
	}
	stage('Test') {
	    bat "mvn -Dmaven.test.failure.ignore=true clean test"
	}
	
}
