# Data Protection APIs
 Spring Boot Application that Provides API to Protect Data using Tokenization

## APIs
* Tokenize : **GET** http://localhost:8082/api/v1/tokenize/{value}
* DeTokenize : **GET** http://localhost:8082/api/v1/deTokenize/{value}
* Postman Collection for APIs is [here](https://github.com/pulkits1998/Data-Protection-APIs/blob/master/Data%20Protection%20APIs.postman_collection.json)

## Local Setup
```
docker build -t data_protection_apis:latest .
docker-compose up
```
