# spring-security-jwt

An example projects of spring security and jwt

## simple

A simple project with authentication and authorization using jwt and resource server.
[related blog post](https://akazakov.dev/2020/10/14/spring-security-jwt-token/)

## generate-jwk

In this project, we divide the application into an authentication server and a resource server. Keys are generated on startup. For key validation on resource-server are used JWK from the authentication server.
[related blog post](https://akazakov.dev/2020/10/29/spring-security-jwt-with-jwk/)