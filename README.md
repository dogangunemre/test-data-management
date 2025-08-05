# Test Otomasyon Data Projesi

## Tools

* **Java** - Lang
* **Intellij IDE** - IDE
* **Springframework** - Enterprise Applications Tool

## Test Run

1. It can be run based on TestAutomationDataApplication by pressing the green RUN button on the IDE

It needs to be running up:
http://localhost:8040/swagger-ui/index.html#/

## Project Structure

```              
├── src
│   ├── main
│   │   ├── java                      
│   │   │  ├── api
│   │   │  │  ├── controller       
│   │   │  │  │  ├── impl       
│   │   │  │  │  ├── spec       
│   │   │  │  ├── request       
│   │   │  │  ├── response       
│   │   │  ├── config
│   │   │  ├── domain
│   │   │  │  ├── constant  
│   │   │  │  ├── service
│   │   │  │  │  ├── impl   
│   │   │  │  │  ├── input   
│   │   │  │  │  ├── output   
│   │   │  │  │  ├── spec   
│   │   │  ├── entity
│   │   │  ├── mapper
│   │   │  ├── props
│   │   │  ├── repository
│   │   │  ├── util
│   │   │  ├── 
│   │   ├── resources
│   │   │  ├── application.yaml
├── pom.xml
└── README.md
```

## Naming Convention

```
package name = my.package

file name = MyFile

controller file name = MyControllerImpl

request file name = MyRequest

response file name = MyResponse

config file name = MyConfig

service file name = MyService

service implementation file name = MyServiceImpl

entity file name = MyEntity

props file name = MyProps

entity file name = MyEntity

repository file name = MyRepository

class name = MyClass

method name = myMethodName

variable name = myVariable

enum = ALL_CAPITAL

tag name = @my_tag 

yaml key = kebab-case
```

## Environment Variables

* Variable should be added to application.yaml
* It should be called by adding it to the xxProps file

Example:

```
String url = bankClientProps.getHadiBaseUrl() + AppConstants.SQL_QUERY_PATH;
```

---
