agent any

stages {
    stage('java8') {
        tools {
            jdk "jdk-8"
        }

        stages {
            stage('compile') {
                steps {
                    "java -version"
                }
            }
        }
    }

    stage('java16') {
        tools {
            jdk "jdk-16"
        }

        stages {
            stage('compile') {
                steps {
                    "java -version"
                }
            }
        }
    }
}