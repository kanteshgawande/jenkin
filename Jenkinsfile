pipeline {
    agent any

    stages {

        stage('Clean Workspace') {
            steps {
                deleteDir()
            }
        }

        stage('Checkout Code') {
            steps {
                git branch: 'dev', url: 'https://github.com/kanteshgawande/jenkin.git'
            }
        }
        
        stage('Build JAR') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        

      stage('Deploy') {
            steps {
                script {
                def imageTag = "tictactoe-app:${env.BUILD_NUMBER}"
                bat """
                set KUBECONFIG=C:\\ProgramData\\Jenkins\\.kube\\config

                kubectl apply -f k8s.yaml

                kubectl set image deployment/tictactoe tictactoe=${imageTag}
                """
            }
        }
    }
    }

    options {
        timeout(time: 10, unit: 'MINUTES')
    }

    post {
        always {
            cleanWs()
        }
    }
}
