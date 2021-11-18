pipeline {
    agent any
    tools {
        maven "maven3.8.3"
        jdk "jdk17-nuevo"
    }
    stages {
        stage("Env Variables") {
            steps {
                bat "printenv"
            }
        }
        stage('Build') {
            steps {
                bat 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        stage('Site') {
            steps {
                bat 'mvn site'
            }
        }
        stage('Sonar'){
            steps {
                 bat 'mvn verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=m5-spring-jenkins -Dsonar.login=c5f73456fdc62705650e6c57648ffa62ca6f5737 -Dsonar.host.url=https://sonarcloud.io -Dsonar.organization=jorgeapariciolara'
            }
        }
    }
}