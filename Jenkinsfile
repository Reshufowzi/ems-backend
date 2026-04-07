pipeline {
    agent any

    environment {
        DOCKERHUB_REPO = "reshma0209/ems-app"
        DOCKER_CREDENTIALS_ID = "docker-creds"
    }

    stages {

        stage('Build JAR') {
            steps {
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
                    credentialsId: "$docker-creds",
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
            echo "✅ Image pushed with tags: $BUILD_NUMBER and latest"
        }
        failure {
            echo '❌ Pipeline failed!'
        }
    }
}
