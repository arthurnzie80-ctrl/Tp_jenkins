pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Tests Unitaires') {
            steps {
                bat 'mvn test'
            }
            post {
                always {
                    junit '**/surefire-reports/*.xml'
                }
            }
        }

        stage('Tests Integration') {
            steps {
                bat 'mvn verify'
            }
            post {
                always {
                    junit '**/failsafe-reports/*.xml'
                }
            }
        }

        stage('Couverture Code') {
            steps {
                jacoco(
                    execPattern: '**/target/jacoco.exec',
                    classPattern: '**/target/classes',
                    sourcePattern: '**/src/main/java'
                )
            }
        }

        stage('Qualite Code') {
            steps {
                bat 'mvn checkstyle:checkstyle pmd:pmd pmd:cpd spotbugs:spotbugs'
            }
            post {
                always {
                    recordIssues tools: [
                        checkStyle(pattern: '**/checkstyle-result.xml'),
                        pmdParser(pattern: '**/pmd.xml'),
                        cpd(pattern: '**/cpd.xml'),
                        spotBugs(pattern: '**/spotbugsXml.xml')
                    ]
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline réussi !'
        }
        failure {
            echo 'Pipeline échoué !'
        }
    }
}