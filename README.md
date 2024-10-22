### Overview

Runs a Java | Springboot | Graphql based application that utilizes the free API from CAT Fact API: https://alexwohlbruck.github.io/cat-facts/docs/endpoints/facts.html

### To build
`./gradlew build`

### To run application via Gradle
`./gradlew bootRun`

### To build and run as container

```
docker build -t animal-facts .
docker run -p 8088:8088 animal-facts
```

### To pull docker image and run the container
```
docker run -p 8088:8088 karthik20/animal-facts:latest
```

### View the GraphQL APIs via Graphiql playground

Go to: http://localhost:8088/graphiql


> The code uses Spring's `RestClient` which is a blocking fluent API that's replacement for soon to be deprecated `RestTemplate`.

`WebClient` vs `RestClient`:

#### Webclient 
- `WebClient` uses a non-blocking and asynchronous way of invoking HTTP API calls
- Fluent API style
- Suitable for high performance and to take advantages of multi-core architecture
- Needs `spring-webflux` dependency that includes `netty`

#### RestClient
- `RestClient` is a blocking and synchronous way of invoking HTTP API calls
- FLuent API style
- Supports interceptors and others similar to `WebClient`
- Doesn't need `spring-webflux` dependency as its blocking IO and uses servlet APIs.