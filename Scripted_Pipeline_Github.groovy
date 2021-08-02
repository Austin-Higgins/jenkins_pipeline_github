pipeline {
    agent any
    stages {
        stage('Git-Checkout') {
            steps {
                    echo "Checking out from Git Repo";
                    git 'https://github.com/Austin-Higgins/Pipeline_Script.git';    
            }
        }
        stage('Build') {
            steps {
                    echo "Building the checked-out project!";    
                    sh 'Build.sh'
            }
        }
        stage('JUnit') {
            steps {
                    echo "Running JUnit Tests";
                    sh 'Unit.sh'    
            }
        }
        stage('Quality-Gate') {
            steps {
                    echo "Verifying Quality Gates";
                    sh 'Quality.sh'   
            }
        }
        stage('Deploy') {
            steps {
                    echo "Pass!";
                    sh 'Deploy.sh'    
            }
        }
    }
    post {
        always {
            echo 'This will always run'
        }
        success {
            echo 'This will run only if successful'
        }
        failure {
            echo 'This will run only if failed'
        }
        unstable {
            echo 'This will run only if the run was marked as unstable'
        }
        changed {
            echo 'This will run only if the state of the Pipeline has changed'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }
    }
}  