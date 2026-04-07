pipeline {

    agent {
        docker {
            image 'maven:3.9.9-eclipse-temurin-17'
            args '-v /var/run/docker.sock:/var/run/docker.sock'
        }
    }

    environment {
        DOCKERHUB_REPO = "reshma0209/ems-app"
        DOCKER_CREDENTIALS_ID = "docker-creds"
    }

    stages {

        stage('Build JAR') {
            steps {
                sh 'java -version'
                sh 'mvn -version'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                docker build -t $DOCKERHUB_REPO:$BUILD_NUMBER .
                docker tag $DOCKERHUB_REPO:$BUILD_NUMBER $DOCKERHUB_REPO:latest
                '''
            }
        }

        stage('Login to Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: DOCKER_CREDENTIALS_ID,
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                }
            }
        }

        stage('Push Image to Docker Hub') {
            steps {
                sh '''
                docker push $DOCKERHUB_REPO:$BUILD_NUMBER
                docker push $DOCKERHUB_REPO:latest
                '''
            }
        }
    }

    post {
        success {
            echo "✅ Image pushed successfully: $DOCKERHUB_REPO:$BUILD_NUMBER & latest"
        }
        failure {
            echo "❌ Pipeline failed!"
        }
    }
}
