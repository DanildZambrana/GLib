pipeline {
    agent any

    stages {
        stage('install core') {
            tools {
                jdk "jdk-8"
            }

            steps {
                sh 'cd Core'
                sh 'mvn clean install'
            }
        }

        stage('Parallel Buildings') {
            parallel {
                stage('java8') {
                    tools {
                        jdk "jdk-8"
                    }

                    stages {
                        stage('compile') {
                            steps {
                                echo '\n\n==================='
                                sh 'cd Spigot'
                                sh 'mvn compile -Dmaven.test.skip'
                            }
                        }

                        stage('test') {
                            steps {
                                echo '\n\n==================='
                                sh 'cd Spigot'
                                sh 'mvn test'
                            }
                        }

                        stage('package') {
                            steps {
                                echo '\n\n==================='
                                sh 'cd Spigot'
                                sh 'mvn clean package -Dmaven.test.skip'
                            }
                        }
                    }
                }
            }
        }
    }
}