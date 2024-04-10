AWS FARGATE all steps:

1) First, recreated locally microservice app:
- Created 2 CRUD microservices (PhotoApp/AlbumApp) 
- For each: created 2 configurations: DEV and PROD
- Using Postman: created users + created album. When creating user, gathered Access Token and UserID to use them to get the album details per User (Token in Header and userID in URL GET Http request)

2) ECR (ELASTIC CONTAINER REGISTRY) VS Docker Hub: I used here DockerHub repo instead of ECR.
- Create public Docker repo
- Build jar file of the app
- In root directory: C:\Users\twalciszewski\Downloads\users-microservice>docker build --tag=tomtomdocker2/users-microservice --force-rm=true .   (building Docker image)
- Push in Docker repo: docker push tomtomdocker2/photo-albums-microservice

3) ECS:
- Creating first ECS cluster for the microservices 
- Inside cluster created different services for the microservices
- Creating task definition inside services to run the dockers in ECS/ defining container (one or multiple)/Port/CPU/GPU/Memory
- Deploy Task definition (configuring custom security group for inbound-outbound traffic to ECS Service) + image URI from Docker Hub (tomtomdocker2/users-microservice:latest)
- Creating Security group
- Successfully deployed the microservice docker container on DEV mode (H2 in memory database) on Fargate cluster and was reachable (created a new user) to this URL POST: http://54.86.161.50:8081/users with succesful response. (using Postman)

4) Enable Load balancer: (distribute traffic to different instances of tasks or different services)
- Configuration Application Load Balancer (HTTP and HTTPS) VS (Network load balancer (tcp/tls/udp): creating new task definition 
- Creating new security group for port 80 (with IPv4 and IPv6)
- Testing how it works: with Postman hit the DNS domain to create users: http://users-microservice-lb-1927429127.us-east-1.elb.amazonaws.com/users and successfull JSON response (load balancer reach 1 of 2 created tasks)
- Custom domain name with route 53
Optionnally: follow procedures to request TLS/SSL certificate from Amazon/ Validate domain name ownership/create HTTPs Listener and configure security group to allow port 443/ redirect HTTP traffic to HTTPs

5) ECS Service Auto Scaling:
- Configuration of autoscaling (tasks upragde or downgrade)
- Checking in AWS Cloudwatch the alerts

6) RDS with MySQL:
- Creating databse/configuration
- Creating new ECS Task definition to switch from Springboot app DEV (in memory db) to PROD profile (using MySQL)=>in environment variable of the task definition: add spring.profiles.active= prod
- Config RDS: add environement variable: spring.profiles.active / HOST_NAME/DATABASE_PORT/DATABASE_NAME/DATABASE_USER_NAME/DATABASE_USER_PASSWORD
- Creating new Security group for inbound traffic

7) Centralized Configuration with AWS Parameter Store:
- Creating config parameters for the database
- Updating task definition by creating new revision
- In the task definition: define value to be retrieved by key like this: arn:aws:ssm:us-east-1:702215199750:parameter/prod/users-microservice/mysql/database-port (for each parameter)
- Update ECS service to use last updated task revision (ECS service with load balancer app)
- Add in Roles: AmazonSSMReadOnlyAccess policy to allow access to parameter store

8) Service Discovery with AWS Applciation Load Balancer:(Service discovery + health check + load balancing)  

9) Using Service Connect (instead of IP, communicate logical friendly names between microservices+ load balancing/traffic resilience/health checking/automatic retires/real time network traffic metrics)
- Creating service connect agent and configuration (new container)

Then creating second Microservice Albums(Docker image/create RDS MySQL db/Task definition/creating ECS service/configure security group for both RDS and microservice).

Finally configure Users Service to use local DNS of Albums testing: with Postman creating resources for users and albums (get the json access token + user ID) the GET request to get users (MS 1) with list of albums fecthed from the second MS was succesffull thanks to the Service Discovery & Service Connect between Microservices

===================================================================================
10) AMAZON DEVELOPER TOOLS: CI/CD  
CodeCommit=>CodeBuild=>CodeDeploy=>AWS ECS alongside CodePipeline (automate the pipeline from codecommit to codedeploy through codebuild) for continuous integration, delivery and deployment

Successfully did: 

CodeCommit:
- Git installed
- Create new IAM User (added AdministratorAccess policy)
- Create group with CodeCommitPowerUsers policy and added the user into the group
- clone repository to local desktop + mvn package to get JAR file
- git add /commit /push to AWS CodeCommit repo

CodeBuild:
-configuration
-Creating buildspec.yml (template in AWS CodePipeline documentation but I adjusted it for DockerHub and not AWS ECR)

CodeDeploy:
-Config

CodePipeline:
- Started the automation process: after each changes in my SpringBoot app and pushes into CodeCommit, the CodePipeline automatically started the CodeBuild to generate Docker container from the last commit and pushes it on DockerHub repo, then succesfully deployed it on AWS ECS instance.





