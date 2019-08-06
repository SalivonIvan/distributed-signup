# distributed-signup

This application is a [solution](Distributed_Signup.pdf).

This application are a microservices architecture.

This application is configured for Service Discovery and Configuration with Consul. On launch, it will refuse to start if it is not able to connect to Consul at [http://localhost:8500](http://localhost:8500).

This application includes two maven modules:

 - [persistence-microservice](persistence-microservice)  
 - [signup-microservice](signup-microservice)
 
 For more information, refer to the **[persistence-microservice](persistence-microservice/README.md)**,  **[signup-microservice](signup-microservice/README.md)**
## Build
  
     mvn clean install
  

## Using Docker to simplify development

###  Prerequisites

You have to install [Docker](https://docs.docker.com/) and [Docker Compose](https://docs.docker.com/compose/install/):

You can use Docker. A number of docker-compose configuration are available in the [docker-compose](docker-compose) folder to launch required third party services.

For example, to start a mongodb database in a docker container, run:

    docker-compose -f docker-compose/mongodb.yml up -d

To stop it and remove the container, run:

    docker-compose -f docker-compose/mongodb.yml down

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

    1. cd persistence-microservice
       ./mvnw -Pprod verify jib:dockerBuild
    2. cd ..
       cd signup-microservice
       ./mvnw -Pprod verify jib:dockerBuild
       cd ..

Then run:

    docker-compose -f docker-compose/app.yml up -d