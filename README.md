# kopring-grpc
play kotlin,springboot with grpc

### generate proto file
```
➜ ./gradlew clean :proto:generateProto
```

### grpc test
```
# user-service
➜ grpcurl --plaintext localhost:6565 list
➜ grpcurl --plaintext -d '{"login_id": "morgan"}' localhost:6565 UserService/getUserGenre
{
  "login_id": "morgan",
  "name": "mkim",
  "genre": "ACTION"
}

# movie-service
➜ grpcurl --plaintext -d '{"genre": "ACTION"}' localhost:7575 MovieService/getMovies
{
  "movie": [
    {
      "title": "Oldboy",
      "year": 2003,
      "rating": 8.4
    }
  ]
}
```
### aggregator test sample
[aggregator-requests.http](./aggregator-service/src/main/resources/aggregator-requests.http)

### Reference
- https://vins-udemy.s3.amazonaws.com/grpc-java/slides/gRPC-slides.pdf