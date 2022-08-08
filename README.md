# ProductService

The attributes of the product resource is distributed across different datasources.
This service aggregates the product data from two different sources and combines
the response from two data sources to a single one.

1. RedSky Api (Name and id attributes are fetched)
2. NoSql Database (Price information is fetched)

We will have to move to OpenApi/Swagger later to document the REST endpoints 
but for now, the two endpoints are documented here for easeness.

# Different API Endpoints

* GET /product/{id} - Responds with the unified product object with the price information
if available.

Sample response:
```yaml
{
    "id": 13860428,
    "name": "The Big Lebowski",
    "current_price": {
        "value": 50,
        "currency_code": "USD"
    }
} 
```
* PUT /product/{id} - Updates the price information to the NoSql DB. Name and 
if fields are not updated into database as source of truth for them is the RedSky API.

Sample request and response 
```yaml
{
    "id": 13860428,
    "name": "The Big Lebowski",
    "current_price": {
        "value": 50,
        "currency_code": "USD"
    }
} 
```

# Running the Application

### 1. Run the MongoDB locally first 
Run the mongodb docker using following command. More information can be found at https://hub.docker.com/_/mongo/
```
docker run -d -p 27017:27017 mongo
```
This will run the mongodb locally for testing purpose

### 2. Building and packaging the application
Ue the following command in the project root to build the application.
```
./gradlew clean build
```

This will produce a build artifact under {PROJECT_ROOT}/build/lib/productservice-0.0.1-SNAPSHOT 

### 3. Running the SpringBoot Application
There are a couple of options on how to run the program.
#### Option 1: Run packaged jar directly 

First set the necessary environment variables
```
export APP_REDSKY_URL=https://redsky-uat.perf.target.com
export APP_REDSKY_KEY=GIVEN_API_KEY
```

Use following command to run the application built in last step
```
java -jar {PROJECT_ROOT}/productservice/build/libs/productservice-0.0.1-SNAPSHOT.jar
```

#### Option 2: Run as a docker container
The project has Dockerfile in the root directly. We can use this to run the 
application easy.

First build the docker image

```
 docker build -t productservice:latest .
```

Run the docker container
```
docker run --network host -e APP_REDSKY_URL='https://redsky-uat.perf.target.com' -e APP_REDSKY_KEY='API Key' productservice
```

### 3. Testing the Application

The application has unit tests written and covers some of the basic scenarios. Run the following 
command to execute the unit tests.
```
./gradlew clean build test
```

#### Using Postman to test the Application

The repository contains postman collection in {PROJECT_ROOT}/postman directory which can be used to 
test the endpoints once the application is up and running.

Use the following endpoint to check if the application is up and running.
```
GET http://localhost:8080/heartbeat
```