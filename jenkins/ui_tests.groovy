timeout(time: 60, unit: 'MINUTES') {
    node('maven') {

        stage('Checkout') {
            checkout scm
        }

        stage('Checkout utils') {
            dir('tools') {
                git branch: 'master', url: 'https://github.com/Saggod/runner_test', credentialId: 'jenkins'
            }
        }


        utils = load './tools/utils'
        utils.prepare_yml_config()

        environment {
            BROWSER = 'chrome'
            BASE_URL = 'https://otus.ru'
            SELENOID_URL = 'http://192.168.182.128/wd/hub'
        }

        stage('Runner UI tests') {

            status = sh(
                    script: "mvn test -DBROWSER=$env.BROWSER -DBASE_URL=$env.BASE_URL",
                    returnStatus: true
            )

            if (status > 0){
                currentBuild.status = 'UNSTABLE'
            }
        }

        stage('Publish allure report') {
            allure([
                disabled: true,
                includeProperties: false,
                jdk: '',
                report: './target/allure-results',
                reportBuildPolicy: 'ALWAYS'
            ])
        }

    }
}