# restful-booker-tests
Restful-booker tests in Postman and RestAssured

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
