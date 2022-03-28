pipeline {
    agent any

    stages {
        stage('Parallel Buildings') {
            parallel {
                stage('java8') {
                    tools {
                        jdk "jdk-8"
                    }

                    agent {
                        label "Java 8"
                    }

                    stages {
                        stage('compile') {
                            steps {
                                sh "java -version"
                            }
                        }
                    }
                }

                stage('java16') {
                    tools {
                        jdk "jdk-16"
                    }

                    agent {
                        label "Java 16"
                    }

                    stages {
                        stage('compile') {
                            steps {
                                sh "java -version"
                            }
                        }
                    }
                }
            }
        }
    }
}