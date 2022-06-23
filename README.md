# my-retail-restful-service
myRetail RESTful Product Detail Service
myRetail is a rapidly growing company with HQ in Richmond, VA and 
over 200 stores across the east coast. myRetail wants to make its 
internal data available to any number of client devices, from 
myRetail.com to native mobile apps. 

The goal for this exercise is to create an end-to-end Proof-of-Concept 
for a products API, which will aggregate product data from multiple 
sources and return it as JSON to the caller. 

Your goal is to create a RESTful service that can retrieve product and 
price details by ID. The URL structure is up to you to define, but try to 
follow some sort of logical convention.


# Build an application that performs the following actions: 

Responds to an HTTP GET request at /products/{id} and delivers 
product data as JSON (where {id} will be a number. 
Example product IDs: 13860428, 54456119, 13264003, 12954218) 

Example response: {"id":13860428,"name":"The Big Lebowski 
(Blu-ray) (Widescreen)","current_price":{"value": 
13.49,"currency_code":"USD"}}

Performs an HTTP GET to retrieve the product name from an 
external API. (For this exercise the data will come from 
redsky.target.com, but let’s just pretend this is an internal 
resource hosted by myRetail) 

Example: 
https://redsky-uat.perf.target.com/redsky_aggregations/v1/
redsky/case_study_v1?
key=3yUxt7WltYG7MFKPp7uyELi1K40ad2ys&tcin=13860428


Reads pricing information from a NoSQL data store and combines
it with the product id and name from the HTTP request into a 
single response. 

BONUS: Accepts an HTTP PUT request at the same path 
(/products/{id}), containing a JSON request body similar to the 
GET response, and updates the product’s price in the data store. 

# Technology Stack

Coding Language - Java
Framework       - Springboot
Database        - MongoDB Atlas (requires userName and password to be replaced with valid MongoDB Atlas credentials)
Documentation   - Swagger 


# Build Instructions

Clone the repo - https://github.com/chauhan-shobhit/my-retail-restful-service.git
Add credentials for MongoDB in applicaition.properties file (replace userName and password)
Build - 
cd product
./mvnw clean install
./mvnw spring-boot:run

Tomcat port of application : 1000

# Endpoints : 

Swagger Documentation - http://localhost:1000/swagger-ui/index.html
Health check endpoint - http://localhost:1000/actuator/health
Application - http://localhost:1000/



# Swagger Documentation

<img width="1385" alt="image" src="https://user-images.githubusercontent.com/45724184/175400881-7b3a18de-5955-4497-95bd-7bb26a39382d.png">


# Postman 

GET http://localhost:1000/products/13860428

<img width="963" alt="image" src="https://user-images.githubusercontent.com/45724184/175401983-8ddb10ec-9ec5-402a-bf76-4529d11412bd.png">

