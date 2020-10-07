# An example project of spring-security and jwt 

Standalone application with spring security adn jjwt library

## Generate public and private keys

For pivate key generation you can use the following commands:

````
openssl genpkey -out jwt.pem -algorithm RSA -pkeyopt rsa_keygen_bits:2048
````

For public key generation use the below commands.

````
openssl rsa -in jwt.pem -pubout
````
