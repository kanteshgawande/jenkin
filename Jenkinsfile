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

        stage('Build Docker Image') {
            steps {
                script {
                    def imageTag = "kantesh11/tictactoe-app:${env.BUILD_NUMBER}"
                    bat "docker build -t ${imageTag} ."
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    def imageTag = "kantesh11/tictactoe-app:${env.BUILD_NUMBER}"

                    withCredentials([usernamePassword(
                        credentialsId: 'docker-hub-creds',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )]) {

                        bat """
                        docker login -u %DOCKER_USER% -p %DOCKER_PASS%
                        docker push ${imageTag}
                        """
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    def imageTag = "kantesh11/tictactoe-app:${env.BUILD_NUMBER}"
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