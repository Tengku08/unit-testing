pipeline {
    agent any

 
    stages {
        stage('Build') {
            steps {
                deleteDir()
                git branch:"main",url:'https://github.com/Tengku08/unit-testing.git'
            }
        }
         stage('Scanner') {
            steps {
                withMaven(maven: 'maven3:10'){
                    withSonarQubeEnv(installationName: 'Sonar') {
                    sh 'java -version'
                    sh "mvn clean -e -U install sonar:sonar -Dsonar.projectKey=java -Dsonar.projectName='java' 
-Dsonar.host.url=http://43.207.96.137/"
                    }
                }
            }
        }
    }
}

