pipeline {
    agent any

    stages {
        stage('install core') {
            tools {
                jdk "jdk-8"
            }

            stage('core') {
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
                            echo '\n\n==================='
                            sh 'cd Spigot'
                            sh 'mvn test'
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

                stage('java16') {
                    tools {
                        jdk "jdk-16"
                    }

                    stage('compile') {
                        steps {
                            echo '\n\n==================='
                            sh 'mvn compile -Dmaven.test.skip'
                        }
                    }

                    stage('test') {
                        echo '\n\n==================='
                        sh 'mvn test'
                    }

                    stage('package') {
                        steps {
                            echo '\n\n==================='
                            sh 'mvn clean package -Dmaven.test.skip'
                        }
                    }
                }
            }
        }
    }
}