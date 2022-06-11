# Spring-Boot Lambda

### Build artifactory
```shell
./gradlew build -x test
```

### Create AWS Lambda
With AWS CLI
```shell
aws lambda create-function \
--function-name uppercase \
--role arn:aws:iam::[USERID]:role/service-role/[ROLE] \
--zip-file fileb://build/libs/spring-lambda.jar \
--handler org.springframework.cloud.function.adapter.aws.FunctionInvoker::handleRequest \
--runtime java11 --region ap-southeast-1 \
--timeout 30 --memory-size 1024 --publish
```

With AWS Console
1. Go to AWS Console and then choose Lambda services. Click Create function.
   ![image1](images/image1.png)
2. Create function by select Author from scratch and enter function name and select runtime to Java 11.
   ![image2](images/image2.png)
3. Expand Change default execution role and Advanced settings, then choose config
   following image below. And click Create function.
   ![image3](images/image3.png)
4. When create function success. Click upload from.
   ![image4](images/image4.png)
5. Choose .zip or .jar file.
   ![image5](images/image5.png)
6. Click upload file and choose file from project-directory/build/libs/spring-lambda.jar, then click save.
   ![image6](images/image6.png)
7. After upload file success. Click Edit at Runtime panel.
   ![image7](images/image7.png)
8. At Handler field, enter value `org.springframework.cloud.function.adapter.aws.FunctionInvoker::handleRequest`
   and click save.
   ![image8](images/image8.png)
9. Copy function URL from function overview page.
   ![image9](images/image9.png)
10. Open Postman and paste URL from (9). Use request body like image below. Then send Post request,
   You will see result from function.
   ![image10](images/image10.png)


### Test aws lambda function
```shell
aws lambda invoke \
--cli-binary-format raw-in-base64-out \
--function-name uppercase \
--payload file://request.txt \
response.json && cat response.json
```

Document reference
- https://docs.spring.io/spring-cloud-function/docs/current/reference/html/aws.html
- https://github.com/spring-cloud/spring-cloud-function
