

![Static Badge](https://img.shields.io/badge/java-21-brightgreen?style=flat&logoColor=red) ![Static Badge](https://img.shields.io/badge/springBoot-3.4.4.-brightgreen?style=flat&logoColor=red) ![Static Badge](https://img.shields.io/badge/spring.framework.boot-spring.boot.starter.data.jpa-brightgreen?style=flat&logoColor=red) ![Static Badge](https://img.shields.io/badge/spring.framework.boot-spring.boot.starter.web-brightgreen?style=flat&logoColor=red) ![Static Badge](https://img.shields.io/badge/org.postgresql-postgresql-brightgreen?style=flat&logoColor=red)
![Static Badge](https://img.shields.io/badge/org.projectlombok-lombok-brightgreen?style=flat&logoColor=red) ![Static Badge](https://img.shields.io/badge/org.springframework.boot-spring.boot.starter.test-brightgreen?style=flat&logoColor=red) ![Static Badge](https://img.shields.io/badge/maven-blue?style=flat&logoColor=red) ![Static Badge](https://img.shields.io/badge/postgresql-17-blue?style=flat&logoColor=red)





## Description 
A SWIFT code, also known as a Bank Identifier Code (BIC), is a unique identifier of a bank's 
branch or headquarter. It ensures that international wire transfers are directed to the correct 
bank and branch, acting as a bank's unique address within the global financial network.

## Task is to create an application that will: 
### 1. Parse SWIFT codes Data:
    ● Code Identification: 
      ○ Codes ending with “XXX” represent a bank's headquarters, otherwise 
      branch. 
      ○ Branch codes are associated with a headquarters if their first 8 characters 
      match. 
      ○ Codes can represent both the branch and the headquarter of the bank. 
    ● Formatting: 
      ○ Country codes and names must always be stored and returned as 
      uppercase strings. 
      ○ Redundant columns in the file may be omitted.

### 2. Store the Data:
   Use postgresql
### 3. Expose a REST API: 
The application must provide access to the SWIFT codes database through RESTful 
endpoints. 
#### Endpoint 1: Retrieve details of a single SWIFT code whether for a headquarters or branches. 
    GET: /v1/swift-codes/{swift-code}: 
    Response Structure for headquarter swift code: 
    { 
    } 
    "address": string, 
    "countryISO2": string, 
    "countryName": string, 
    "isHeadquarter": bool, 
    "swiftCode": string 
    “branches”: [ 
    { 
    "address": string, 
    "countryISO2": string, 
    "isHeadquarter": bool, 
    "swiftCode": string 
    }, 
    { 
    "address": string, 
    "countryISO2": string, 
    "isHeadquarter": bool, 
    "swiftCode": string 
    }, . . . 
    ] 
 
    Response Structure for branch swift code:  
     
    { 
        "address": string, 
        "countryISO2": string, 
        "countryName": string, 
        "isHeadquarter": bool, 
        "swiftCode": string 
    } 
 
 
 
#### Endpoint 2: Return all SWIFT codes with details for a specific country (both headquarters and branches). 
    GET:  /v1/swift-codes/country/{countryISO2code}: 
    Response Structure : 
     
    { 
        "countryISO2": string, 
        "countryName": string, 
        "swiftCodes": [ 
            { 
                "address": string, 
           "countryISO2": string, 
           "isHeadquarter": bool, 
           "swiftCode": string 
            }, 
            { 
                "address": string, 
           "countryISO2": string, 
           "isHeadquarter": bool, 
           "swiftCode": string 
            }, . . . 
        ] 
    } 
     
 
 
 
  
 #### Endpoint 3: Adds new SWIFT code entries to the database for a specific country. 
     POST:  /v1/swift-codes: 
     Request Structure : 
     
     { 
        "address": string, 
        "countryISO2": string, 
        "countryName": string, 
        “isHeadquarter”: bool, 
        "swiftCode": string, 
    } 
     
    Response Structure:  
     
    { 
        "message": string, 
    } 
 
##### Endpoint 4: Deletes swift-code data if swiftCode matches the one in the database. 
    DELETE:  /v1/swift-codes/{swift-code} 
    Response Structure:  
     
    { 
        "message": string, 
    }
##### Endpoint 5: Return all rows in table
     GET:  /v1/swift-codes/all: 
        Response Structure : 
         
        { 
            "result": [
            { 
        "address": string, 
        "countryISO2": string, 
        "countryName": string, 
        “isHeadquarter”: bool, 
        "swiftCode": string, 
    } , 
    ...
            ]
        } 
##### Endpoint 6: Edit swiftCode row 
     PUT:  /v1/swift-codes/{swift-code}:
         Request Structure : 
         
         { 
            "address": string, 
            "countryISO2": string, 
            "countryName": string, 
            “isHeadquarter”: bool, 
            "swiftCode": string, 
        }
        
         Response Structure:  
     
            { 
                "message": string, 
            }
     
## Key Expectations:
    Verify that all endpoints and responses align with the structure outlined in the exercise description. 
    
    Handle all edge cases gracefully, with clear and informative error messages. (in progress)
    
    Provide thorough unit and integration tests to ensure reliability.
    
    Containerize the application and database, ensuring the endpoints are accessible at localhost:8080.
    
## How to Install and Run 
Downolad project and unpack.
Use Spring Boot to run application. You can use Visual Studio Code with Spring Boot Dashboard for VS Code extension or navigate to the root of the project via command line and execute the command mvn spring-boot:run.I presume you have maven installed and correctly added maven to your environment variable. Make sure that to set application port to run(default 8080), database password, username and url in src/main/resources/applicatiion.properties. Tables will be created automatically.
