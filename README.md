# Spring - Mongo Large Data Example
## _Large Data Generator Built In_

Pre-requisites.

- Postman for testing.
- MongoDB installed as docker or as singleton server
- Create a databse in mongo called test and a collection named tag
## Features

- Generating 2,400,000 Records in the tag collection when the spring boot first run.
- Exposing 3 APIs (count, Search By User UUID, Get Tags by ContentId)


## Installation

This example requires the below to be installed:
- Maven
- MongoDB
- Java 8+
- Postman

Clone this repository and build the source code using maven or your favorite IDE.
Make sure you have a running mongoDB contains a database named test and a collection inside the test db named tag.
run your spring boot, it should takes time for the first time since we are generating 2,400,000 record.
after that use the postman collection _Spring-MongoDB.postman_collection.json_ (located inside the project) to test your APIs
