# restful-booker-tests
Postman and Rest Assured API tests for Restful-Booker.

Restful-Booker quick links:
- [Restful-Booker](https://restful-booker.herokuapp.com/)
- [Restful-Booker API Docs](https://restful-booker.herokuapp.com/apidoc/index.html)

Restful-Booker is a Web API playground created by Mark Winteringham that forms a Bed and Breakfast booking service. It offers authentication and CRUD operations. This Web API was implemented with some bugs present as well. The bugs I have found are listed in the [Issues section](Issues)

## Postman
Postman environment and collection prepared for Restful-Booker API tests. Both can be found in the [postman folder](https://github.com/mkornaszewska/restful-booker-tests/tree/main/postman).

## Rest Assured
![](https://img.shields.io/badge/Code-Java-informational?style=flat&&color=1E96FC) ![](https://img.shields.io/badge/Framework-Junit%205-informational?style=flat&&color=1E96FC) ![](https://img.shields.io/badge/Build%20tool-Maven-informational?style=flat&&color=1E96FC)

![](https://img.shields.io/badge/Library-REST%20Assured-informational?style=flat&&color=1E96FC) ![](https://img.shields.io/badge/Library-Lombok-informational?style=flat&&color=1E96FC) ![](https://img.shields.io/badge/Library-JSON%20Schema%20Validator-informational?style=flat&&color=1E96FC) ![](https://img.shields.io/badge/Library-JavaFaker-informational?style=flat&&color=1E96FC) ![](https://img.shields.io/badge/Library-SLF4J-informational?style=flat&&color=1E96FC) ![](https://img.shields.io/badge/Library-Allure-informational?style=flat&&color=1E96FC)


### Project structure

```
src
  + main
    + java
        + com
            + mkornaszewska
                + config                Configuration reader for the properties file
                + dto                   DTOs for createBooking and partialUpdatebooking
                + helpers               Helpers to create fake data for requests
    + resources                         Properties for RestfulBooker (baseURL, credentials, etc.)
  + test
    + java
        + com
            + mkornaszewska
                + requests               Classes for BaseRequest, booking and token requests
                + schemas                JSON schemas for requests
                + tests                  Tests for Restful-Booker endpoints
                + reports                Sample allure report
```

### Installation and test execution
#### Prerequisites
- Java 19
#### Installation
1. Clone this git repository
2. Open it with an IDE of your choice (IntelliJ, Eclipse)
#### Test Execution
Either "run test" option in the IDE of your choice or execute the following command in the terminal:
```
$ mvn clean test
```
#### Allure reports
To generate an Allure report for this project, execute the following command:
```
$ mvn allure:serve
```
The generated report will open in a new browser window.

Sample report can be found here: [AllureSampleReport.pdf](https://github.com/mkornaszewska/restful-booker-tests/blob/main/restAssured/restful-booker/src/test/java/com/kornaszewska/reports/AllureSampleReport.pdf)

## Issues
| # | Method and Endpoint | Issue |
| - |---------------------| ------ |
| 1.| POST /auth | When incorrect credentials are provided, status code is 200. It should be 401 Unauthorized instead. |
| 2.| GET /booking?checkin={{checkin}} <br><br> GET /booking?checkout={{checkout}}  |   Filtering by checkin/checkout dates doesn't return bookingids with checkin/checkout date greater than or equal to the set checkin/checkout date. Example: query param checkout is set to 2025-04-09, bookingid returned with checkout is 2019-01-01.|
| 3.| POST /booking|   Valid request should return 201 Created instead of 200 OK. |
| 4.| POST /booking|   Sending a request with one property missing (for example: totalprice) in the request body results in 500 Internal Server Error. The code should be changed to 400 Bad Request.  |
| 5.| PUT /booking<br> PATCH /booking <br> DELETE /booking|  If no Cookie or Authorization headers are provided, the response is 403 Forbidden. In documentation, aforementioned headers are marked as optional. |
| 6.| PUT /booking<br> PATCH /booking <br> DELETE /booking  |  If Authorization header is provided, the response is 403 Forbidden. Expected response is 200 OK. |
| 7.| POST /booking<br> PUT /booking <br> PATCH /booking  |  totalprice provided can be a float (example: 325.85), but in the request it is always an integer rounded down (example: 325). |
| 8.| DELETE /booking  |  Status code is 201 Created for the response. It should be a different code, for example 204 No Content.  |
| 9.| DELETE /booking  |  Deleting an nonexisting bookingid results in the response having status code 405 Method Not Allowed. It should be changed to 404 Not Found instead.  |
| 10.| GET /ping  |  The status code in the response should be changed from 201 Created to 200 OK.  |
