## Labseq-Service
This project is a REST service implemented in Quarkus, a Java framework 

The service calculates the value of the Labseq sequence, which is defined as follows:

```
n = 0 => l(0) = 0
n = 1 => l(1) = 1
n = 2 => l(2) = 0
n = 3 => l(3) = 1
n > 3 => l(n) = l(n-4) + l(n-3)
```

The endpoint created is in the form `<baseurl>/labseq/{n}`, where {n} represents the index of the sequence's (single) value to return. 
The index may be any non-negative integer number.

The implemented service uses a caching mechanism to take advantage of previous calculations to speed up future calculations. This caching mechanism is used in the algorithm's intermediate calculations and not only in the endpoint's invocations.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

##  Building and running the application
The application can be packaged using:

```shell script
./mvnw package
```

It will produce the `quarkus-run.jar` file in the `target/quarkus-app/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Usage
To retrieve the value of the Labseq sequence at a specific index, make a GET request to the endpoint `<baseurl>/labseq/{n}`, where {n} is the index of the sequence's value to return.

### Example
Assuming the application is running locally on port 8080, you can retrieve the value of the sequence at index 10 using the following URL:

```
http://localhost:8080/labseq/10
```

The expected response should be:

```
3
```

### Documentation
**Check out the [Swagger](https://labseq-service.herokuapp.com/swagger)**

**Check out the [API Documentation](https://s3.amazonaws.com/fabriciosouza.me/assets/files/Labseq-EN.pdf)**

### Website
You can check the website [here](https://labseq-front.herokuapp.com/)