Two projects are here:

1. Frontend React Hello App: vinod-react-app
1. Backend Spring boot REST service: vin-hello-rest-service

# Important

`Dockerfile` for the frontend/React app

```sh
FROM python
WORKDIR /app
COPY build/. /app/
CMD ["python", "-m", "http.server", "4000"]
EXPOSE 4000
```

Remember these points:

1. The value 4000 represents the port number being used by the HTTP server within the docker container
2. And this value should be exposed
3. The same value is exposed via port 80 in the `docker run` command in the `Jenkinsfile` as shown below:

```sh
stage ('Docker Run') {
    steps {
        sh "docker build ./ -t ${FINAL_NAME} && docker run -d -p 80:4000 --name ${FINAL_NAME}-container ${FINAL_NAME}"
    }
}
```

## So, best is to keep the file unchanged!

---

`Jenkinsfile` for the frontend application

```sh
pipeline {
    agent any

    tools {
        nodejs "node10"
    }

    environment {
        CI = 'true' 
        FINAL_NAME = 'vinod-react-app'
    }
    
    stages {
    	stage ('Clean') {
		    steps {
            	sh "rm -rf build"
            }
		}        
        stage('Install') { 
            steps {
                sh "npm i"
            }
        }
        stage ('Test') {
		    steps {
                sh "npm test"
            }
		}
        stage('Sonar Analysis') {
		    steps {
                withSonarQubeEnv('SonarServer') {
                    sh "npm run quality-check"
                }
            }
        }
        stage('Quality Gate') {
		    steps {
                timeout(time: 2, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage ('Build') {
		    steps {
            	sh "npm run build"
            }
		}
        stage ('Docker Image') {
        	steps {
        		sh "(docker stop ${FINAL_NAME}-container || true) && (docker rm ${FINAL_NAME}-container || true) && (docker rmi ${FINAL_NAME} || true)"
        	}
        }
        stage ('Docker Run') {
        	steps {
            	sh "docker build ./ -t ${FINAL_NAME} && docker run -d -p 80:4000 --name ${FINAL_NAME}-container ${FINAL_NAME}"
        	}
        }
    }
}
```

The only value you may want to consider changing in the above file is `FINAL_NAME` environment variable, as this is used as docker image and container names.

```sh
    environment {
        CI = 'true' 
        FINAL_NAME = 'vinod-react-app'
    }
```


`Dockerfile` for the backend/REST service

```sh
FROM openjdk:8
MAINTAINER Vinod <vinod@vinod.co>
ADD target/hello-rest-service.jar hello-rest-service.jar
ENTRYPOINT ["java", "-jar", "-Dserver.port=4000", "/hello-rest-service.jar"]
EXPOSE 4000
```
