# Restful-Booker API Test Scenarios

Test Scenarios for Restful-Booker API.

Updated in the [restful_booker_test_scenarios.xlsm](https://github.com/mkornaszewska/restful-booker-tests/tree/main/test-scenarios/restful_booker_test_scenarios.xlsm) file.

The python script [export_test_scenarios_to_readme.py](https://github.com/mkornaszewska/restful-booker-tests/tree/main/test-scenarios/export_test_scenarios_to_readme.py) updates this `README.md` file.

## Table of Contents
- [Priority Definitions](#priority-definitions)
- [Test Execution Strategy](#test-execution-strategy)
- [Test Scenarios](#test-scenarios)
    - [Authentication (POST /auth)](#authentication-post-auth)
    - [Read Booking (GET /booking)](#read-booking-get-booking)
    - [Read Booking Id (GET /booking/:id)](#read-booking-id-get-bookingid)
    - [Create Booking (POST /booking)](#create-booking-post-booking)
    - [Update Booking (PUT /booking/:id)](#update-booking-put-bookingid)
    - [Partial Update Booking (PATCH /booking/:id)](#partial-update-booking-patch-bookingid)
    - [Delete Booking (DELETE /booking/:id)](#delete-booking-delete-booking-id)
    - [Health Check (GET /ping)](#health-check-get-ping)

## Priority Definitions

| Priority          | Description                                                                                                                                                  | Impact                                              |
|-------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------|
| **P0 (Critical)** | Core functionality that must work for the API to be considered operational. These tests verify authentication, basic CRUD operations, and security controls. | System unusable if failing. Data integrity at risk. |
| **P1 (High)**     | Important business functionality that affects most users. These tests verify data validation, error handling for common scenarios, and proper authorization. | Major user impact, but workarounds may exist.       |
| **P2 (Medium)**   | Secondary features and edge case handling. These tests verify filtering, optional fields, and specific error messages.                                       | Limited user impact, affects specific features.     |
| **P3 (Low)**      | Edge cases, rare scenarios, and optional functionality. These tests verify handling of unusual inputs and optional features.                                 | Minimal user impact, cosmetic or rare issues.       |

## Test Execution Strategy

- `P0 tests` run on every code change
- `P0-P1` tests run daily
- `P0-P2` tests run weekly
- Complete test suite (`P0-P3`) runs before each release

## Test Scenarios

### Authentication (POST /auth)

##### Priority: P0 (Critical)
<!-- POST__AUTH_P0_TEST_SCENARIOS_START -->

| Test Scenario Id   | Priority   | API endpoint   | Description                                                         |
|:-------------------|:-----------|:---------------|:--------------------------------------------------------------------|
| RB_TS_01           | P0         | POST /auth     | System accepts login attempts with valid credentials                |
| RB_TS_02           | P0         | POST /auth     | System rejects login attempts with invalid credentials              |
| RB_TS_03           | P0         | POST /auth     | System rejects login attempts with empty username                   |
| RB_TS_04           | P0         | POST /auth     | System rejects login attempts with empty password                   |
| RB_TS_05           | P0         | POST /auth     | System rejects login attempts with both username and password empty |

<!-- POST__AUTH_P0_TEST_SCENARIOS_END -->

### Read Booking (GET /booking)

#### Priority: P0 (Critical)
<!-- GET__BOOKING_P0_TEST_SCENARIOS_START -->

| Test Scenario Id   | Priority   | API endpoint   | Description                       |
|:-------------------|:-----------|:---------------|:----------------------------------|
| RB_TS_22           | P0         | GET /booking   | Read all bookings with no filters |

<!-- GET__BOOKING_P0_TEST_SCENARIOS_END -->

#### Priority: P1 (High)
<!-- GET__BOOKING_P1_TEST_SCENARIOS_START -->

N/A - No test scenarios for this priority level

<!-- GET__BOOKING_P1_TEST_SCENARIOS_END -->

#### Priority: P2 (Medium)
<!-- GET__BOOKING_P2_TEST_SCENARIOS_START -->

| Test Scenario Id   | Priority   | API endpoint   | Description                                               |
|:-------------------|:-----------|:---------------|:----------------------------------------------------------|
| RB_TS_06           | P2         | GET /booking   | Filter by existing first name                             |
| RB_TS_07           | P2         | GET /booking   | Filter by existing last name                              |
| RB_TS_08           | P2         | GET /booking   | Filter by first and last names                            |
| RB_TS_09           | P2         | GET /booking   | Filter by existing check in date                          |
| RB_TS_10           | P2         | GET /booking   | Filter by existing check out date                         |
| RB_TS_11           | P2         | GET /booking   | Filter by existing check in and check out dates           |
| RB_TS_12           | P2         | GET /booking   | Filter by existing first name and check in date           |
| RB_TS_13           | P2         | GET /booking   | Filter by existing first name and check out date          |
| RB_TS_14           | P2         | GET /booking   | Filter by existing last name and check in date            |
| RB_TS_15           | P2         | GET /booking   | Filter by existing last name and check out date           |
| RB_TS_16           | P2         | GET /booking   | Filter by invalid first name                              |
| RB_TS_17           | P2         | GET /booking   | Filter by invalid last name                               |
| RB_TS_18           | P2         | GET /booking   | Filter by invalid check in date (string instead of date)  |
| RB_TS_19           | P2         | GET /booking   | Filter by invalid check out date (string instead of date) |
| RB_TS_20           | P2         | GET /booking   | Filter by check in date with invalid date format          |
| RB_TS_21           | P2         | GET /booking   | Filter by check out date with invalid date format         |

<!-- GET__BOOKING_P2_TEST_SCENARIOS_END -->

#### Priority: P3 (Low)
<!-- GET__BOOKING_P3_TEST_SCENARIOS_START -->

N/A - No test scenarios for this priority level

<!-- GET__BOOKING_P3_TEST_SCENARIOS_END -->

### Read Booking Id (GET /booking/:id)

#### Priority: P0 (Critical)
<!-- GET__BOOKING__ID_P0_TEST_SCENARIOS_START -->

| Test Scenario Id   | Priority   | API endpoint     | Description                          |
|:-------------------|:-----------|:-----------------|:-------------------------------------|
| RB_TS_23           | P0         | GET /booking/:id | Read existing bookingId (JSON / XML) |

<!-- GET__BOOKING__ID_P0_TEST_SCENARIOS_END -->

#### Priority: P1 (High)
<!-- GET__BOOKING__ID_P1_TEST_SCENARIOS_START -->

| Test Scenario Id   | Priority   | API endpoint     | Description                                 |
|:-------------------|:-----------|:-----------------|:--------------------------------------------|
| RB_TS_24           | P1         | GET /booking/:id | Error for non-existing bookingId (JSON/XML) |
| RB_TS_25           | P1         | GET /booking/:id | Error for no bookingId (JSON/XML)           |

<!-- GET__BOOKING__ID_P1_TEST_SCENARIOS_END -->

#### Priority: P2 (Medium)
<!-- GET__BOOKING__ID_P2_TEST_SCENARIOS_START -->

N/A - No test scenarios for this priority level

<!-- GET__BOOKING__ID_P2_TEST_SCENARIOS_END -->

#### Priority: P3 (Low)
<!-- GET__BOOKING__ID_P3_TEST_SCENARIOS_START -->

N/A - No test scenarios for this priority level

<!-- GET__BOOKING__ID_P3_TEST_SCENARIOS_END -->

### Create Booking (POST /booking)

#### Priority: P0 (Critical)
<!-- POST__BOOKING_P0_TEST_SCENARIOS_START -->

| Test Scenario Id   | Priority   | API endpoint   | Description                                           |
|:-------------------|:-----------|:---------------|:------------------------------------------------------|
| RB_TS_26           | P0         | POST /booking  | Create valid booking (JSON / XML)                     |
| RB_TS_27           | P0         | POST /booking  | Error for booking without first name (JSON / XML)     |
| RB_TS_28           | P0         | POST /booking  | Error for booking without last name (JSON / XML)      |
| RB_TS_31           | P0         | POST /booking  | Error for booking without check in date (JSON / XML)  |
| RB_TS_32           | P0         | POST /booking  | Error for booking without check out date (JSON / XML) |

<!-- POST__BOOKING_P0_TEST_SCENARIOS_END -->

#### Priority: P1 (High)
<!-- POST__BOOKING_P1_TEST_SCENARIOS_START -->

| Test Scenario Id   | Priority   | API endpoint   | Description                                                               |
|:-------------------|:-----------|:---------------|:--------------------------------------------------------------------------|
| RB_TS_30           | P1         | POST /booking  | Error for booking without deposit paid (JSON / XML)                       |
| RB_TS_34           | P1         | POST /booking  | Error for invalid first name (data type other than string) (JSON / XML)   |
| RB_TS_35           | P1         | POST /booking  | Error for invalid last name (data type other than string) (JSON / XML)    |
| RB_TS_38           | P1         | POST /booking  | Error for invalid check in date (data type other than date) (JSON / XML)  |
| RB_TS_39           | P1         | POST /booking  | Error for invalid check out date (data type other than date) (JSON / XML) |
| RB_TS_41           | P1         | POST /booking  | Total price is a decimal (JSON / XML)                                     |
| RB_TS_42           | P1         | POST /booking  | Error for invalid date format for check in date (JSON / XML)              |
| RB_TS_43           | P1         | POST /booking  | Error for invalid date format check out date (JSON / XML)                 |

<!-- POST__BOOKING_P1_TEST_SCENARIOS_END -->

#### Priority: P2 (Medium)
<!-- POST__BOOKING_P2_TEST_SCENARIOS_START -->

| Test Scenario Id   | Priority   | API endpoint   | Description                                                                |
|:-------------------|:-----------|:---------------|:---------------------------------------------------------------------------|
| RB_TS_29           | P2         | POST /booking  | Error for booking without total price (JSON / XML)                         |
| RB_TS_36           | P2         | POST /booking  | Error for invalid total price (negative integer) (JSON / XML)              |
| RB_TS_37           | P2         | POST /booking  | Error for invalid deposit paid (data type other than boolean) (JSON / XML) |

<!-- POST__BOOKING_P2_TEST_SCENARIOS_END -->

#### Priority: P3 (Low)
<!-- POST__BOOKING_P3_TEST_SCENARIOS_START -->

| Test Scenario Id   | Priority   | API endpoint   | Description                                                                  |
|:-------------------|:-----------|:---------------|:-----------------------------------------------------------------------------|
| RB_TS_33           | P3         | POST /booking  | Error for booking without additional needs (JSON / XML)                      |
| RB_TS_40           | P3         | POST /booking  | Error for invalid addtional needs (data type other than string) (JSON / XML) |

<!-- POST__BOOKING_P3_TEST_SCENARIOS_END -->

### Update Booking (PUT /booking/:id)

#### Priority: P0 (Critical)
<!-- PUT__BOOKING__ID_P0_TEST_SCENARIOS_START -->

| Test Scenario Id   | Priority   | API endpoint     | Description                                                            |
|:-------------------|:-----------|:-----------------|:-----------------------------------------------------------------------|
| RB_TS_44           | P0         | PUT /booking/:id | Update booking with valid cookie (JSON / XML)                          |
| RB_TS_45           | P0         | PUT /booking/:id | Update booking with valid authorization token (JSON / XML)             |
| RB_TS_46           | P0         | PUT /booking/:id | Error for update booking with invalid cookie (JSON / XML)              |
| RB_TS_47           | P0         | PUT /booking/:id | Error for update booking with invalid authorization token (JSON / XML) |
| RB_TS_48           | P0         | PUT /booking/:id | Error for update booking with no cookie (JSON / XML)                   |
| RB_TS_49           | P0         | PUT /booking/:id | Error for update booking with no authorization token (JSON / XML)      |

<!-- PUT__BOOKING__ID_P0_TEST_SCENARIOS_END -->

#### Priority: P1 (High)
<!-- PUT__BOOKING__ID_P1_TEST_SCENARIOS_START -->

| Test Scenario Id   | Priority   | API endpoint     | Description                                                                                   |
|:-------------------|:-----------|:-----------------|:----------------------------------------------------------------------------------------------|
| RB_TS_51           | P1         | PUT /booking/:id | Error for update booking without first name (JSON / XML)                                      |
| RB_TS_52           | P1         | PUT /booking/:id | Error for update booking without last name (JSON / XML)                                       |
| RB_TS_55           | P1         | PUT /booking/:id | Error for update booking without check in date (JSON / XML)                                   |
| RB_TS_56           | P1         | PUT /booking/:id | Error for update booking without check out date (JSON / XML)                                  |
| RB_TS_60           | P1         | PUT /booking/:id | Error for update booking with invalid total price (negative integer) (JSON / XML)             |
| RB_TS_62           | P1         | PUT /booking/:id | Error for update booking with invalid check in date (data type other than date) (JSON / XML)  |
| RB_TS_63           | P1         | PUT /booking/:id | Error for update booking with invalid check out date (data type other than date) (JSON / XML) |
| RB_TS_65           | P1         | PUT /booking/:id | Error for update booking with invalid date format for check in date (JSON / XML)              |
| RB_TS_66           | P1         | PUT /booking/:id | Error for update booking with invalid date format for check out date (JSON / XML)             |
| RB_TS_68           | P1         | PUT /booking/:id | Error for update booking with no bookingId (JSON / XML)                                       |

<!-- PUT__BOOKING__ID_P1_TEST_SCENARIOS_END -->

#### Priority: P2 (Medium)
<!-- PUT__BOOKING__ID_P2_TEST_SCENARIOS_START -->

| Test Scenario Id   | Priority   | API endpoint     | Description                                                                                    |
|:-------------------|:-----------|:-----------------|:-----------------------------------------------------------------------------------------------|
| RB_TS_50           | P2         | PUT /booking/:id | Error for update booking for non-existing bookingId (JSON / XML)                               |
| RB_TS_53           | P2         | PUT /booking/:id | Error for update booking without total price (JSON / XML)                                      |
| RB_TS_54           | P2         | PUT /booking/:id | Error for update booking without deposit paid (JSON / XML)                                     |
| RB_TS_58           | P2         | PUT /booking/:id | Error for update booking with invalid first name (data type other than string) (JSON / XML)    |
| RB_TS_59           | P2         | PUT /booking/:id | Error for update booking with invalid last name (data type other than string) (JSON / XML)     |
| RB_TS_61           | P2         | PUT /booking/:id | Error for update booking with invalid deposit paid (data type other than boolean) (JSON / XML) |
| RB_TS_67           | P2         | PUT /booking/:id | Update booking when total price is a decimal (JSON / XML)                                      |

<!-- PUT__BOOKING__ID_P2_TEST_SCENARIOS_END -->

#### Priority: P3 (Low)
<!-- PUT__BOOKING__ID_P3_TEST_SCENARIOS_START -->

| Test Scenario Id   | Priority   | API endpoint     | Description                                                                                       |
|:-------------------|:-----------|:-----------------|:--------------------------------------------------------------------------------------------------|
| RB_TS_57           | P3         | PUT /booking/:id | Error for update booking without additional needs (JSON / XML)                                    |
| RB_TS_64           | P3         | PUT /booking/:id | Error for update booking with invalid additional needs (data type other than string) (JSON / XML) |

<!-- PUT__BOOKING__ID_P3_TEST_SCENARIOS_END -->

### Partial Update Booking (PATCH /booking/:id)

#### Priority: P0 (Critical)
<!-- PATCH__BOOKING__ID_P0_TEST_SCENARIOS_START -->

| Test Scenario Id   | Priority   | API endpoint       | Description                                                                    |
|:-------------------|:-----------|:-------------------|:-------------------------------------------------------------------------------|
| RB_TS_69           | P0         | PATCH /booking/:id | Partial update booking with valid cookie (JSON / XML)                          |
| RB_TS_70           | P0         | PATCH /booking/:id | Partial update booking with valid authorization token (JSON / XML)             |
| RB_TS_71           | P0         | PATCH /booking/:id | Error for partial update booking with invalid cookie (JSON / XML)              |
| RB_TS_72           | P0         | PATCH /booking/:id | Error for partial update booking with invalid authorization token (JSON / XML) |
| RB_TS_73           | P0         | PATCH /booking/:id | Error for partial update booking with no cookie (JSON / XML)                   |
| RB_TS_74           | P0         | PATCH /booking/:id | Error for partial update booking with no authorization token (JSON / XML)      |

<!-- PATCH__BOOKING__ID_P0_TEST_SCENARIOS_END -->

#### Priority: P1 (High)
<!-- PATCH__BOOKING__ID_P1_TEST_SCENARIOS_START -->

| Test Scenario Id   | Priority   | API endpoint       | Description                                                                                                  |
|:-------------------|:-----------|:-------------------|:-------------------------------------------------------------------------------------------------------------|
| RB_TS_76           | P1         | PATCH /booking/:id | Valid partial update for first name (JSON / XML)                                                             |
| RB_TS_77           | P1         | PATCH /booking/:id | Valid partial update for last name (JSON / XML)                                                              |
| RB_TS_78           | P1         | PATCH /booking/:id | Valid partial update for total price (JSON / XML)                                                            |
| RB_TS_79           | P1         | PATCH /booking/:id | Valid partial update for deposit paid (JSON / XML)                                                           |
| RB_TS_80           | P1         | PATCH /booking/:id | Valid partial update for check in date (JSON / XML)                                                          |
| RB_TS_81           | P1         | PATCH /booking/:id | Valid partial update for check out date (JSON / XML)                                                         |
| RB_TS_87           | P1         | PATCH /booking/:id | Error for partial update partial booking with invalid check in date (data type other than date) (JSON / XML) |
| RB_TS_88           | P1         | PATCH /booking/:id | Error for partial update booking with invalid check out date (data type other than date) (JSON / XML)        |
| RB_TS_90           | P1         | PATCH /booking/:id | Error for partial update booking with invalid date format for check in date (JSON / XML)                     |
| RB_TS_91           | P1         | PATCH /booking/:id | Error for partial update booking with invalid date format for check out date (JSON / XML)                    |
| RB_TS_93           | P1         | PATCH /booking/:id | Error for partial update booking for non-existing bookingId (JSON / XML)                                     |
| RB_TS_94           | P1         | PATCH /booking/:id | Error for partial update booking with no bookingId (JSON / XML)                                              |

<!-- PATCH__BOOKING__ID_P1_TEST_SCENARIOS_END -->

#### Priority: P2 (Medium)
<!-- PATCH__BOOKING__ID_P2_TEST_SCENARIOS_START -->

| Test Scenario Id   | Priority   | API endpoint       | Description                                                                                            |
|:-------------------|:-----------|:-------------------|:-------------------------------------------------------------------------------------------------------|
| RB_TS_75           | P2         | PATCH /booking/:id | Error for partial update booking for non-existing bookingId (JSON / XML)                               |
| RB_TS_83           | P2         | PATCH /booking/:id | Error for partial update booking with invalid first name (data type other than string) (JSON / XML)    |
| RB_TS_84           | P2         | PATCH /booking/:id | Error for partial update booking with invalid last name (data type other than string) (JSON / XML)     |
| RB_TS_85           | P2         | PATCH /booking/:id | Error for partial update booking with invalid total price (negative integer) (JSON / XML)              |
| RB_TS_86           | P2         | PATCH /booking/:id | Error for partial update booking with invalid deposit paid (data type other than boolean) (JSON / XML) |
| RB_TS_92           | P2         | PATCH /booking/:id | Valid partial update booking when total price is a decimal (JSON / XML)                                |

<!-- PATCH__BOOKING__ID_P2_TEST_SCENARIOS_END -->

#### Priority: P3 (Low)
<!-- PATCH__BOOKING__ID_P3_TEST_SCENARIOS_START -->

| Test Scenario Id   | Priority   | API endpoint       | Description                                                                                               |
|:-------------------|:-----------|:-------------------|:----------------------------------------------------------------------------------------------------------|
| RB_TS_82           | P3         | PATCH /booking/:id | Valid partial update for additional needs (JSON / XML)                                                    |
| RB_TS_89           | P3         | PATCH /booking/:id | Error for partial update booking with invalid additional needs (data type other than string) (JSON / XML) |

<!-- PATCH__BOOKING__ID_P3_TEST_SCENARIOS_END -->

## Delete booking (DELETE /booking/:id)

#### Priority: P0 (Critical)
<!-- DELETE__BOOKING__ID_P0_TEST_SCENARIOS_START -->

| Test Scenario Id   | Priority   | API endpoint        | Description                                                            |
|:-------------------|:-----------|:--------------------|:-----------------------------------------------------------------------|
| RB_TS_95           | P0         | DELETE /booking/:id | Delete booking with valid cookie (JSON / XML)                          |
| RB_TS_96           | P0         | DELETE /booking/:id | Delete booking with valid authorization token (JSON / XML)             |
| RB_TS_97           | P0         | DELETE /booking/:id | Error for delete booking with invalid cookie (JSON / XML)              |
| RB_TS_98           | P0         | DELETE /booking/:id | Error for delete booking with invalid authorization token (JSON / XML) |
| RB_TS_99           | P0         | DELETE /booking/:id | Error for delete booking with no cookie (JSON / XML)                   |
| RB_TS_100          | P0         | DELETE /booking/:id | Error for delete booking with no authorization token (JSON / XML)      |

<!-- DELETE__BOOKING__ID_P0_TEST_SCENARIOS_END -->

#### Priority: P1 (High)
<!-- DELETE__BOOKING__ID_P1_TEST_SCENARIOS_START -->

| Test Scenario Id   | Priority   | API endpoint        | Description                                                      |
|:-------------------|:-----------|:--------------------|:-----------------------------------------------------------------|
| RB_TS_101          | P1         | DELETE /booking/:id | Error for delete booking for non-existing bookingId (JSON / XML) |
| RB_TS_102          | P1         | DELETE /booking/:id | Error for delete booking with no bookingId (JSON / XML)          |

<!-- DELETE__BOOKING__ID_P1_TEST_SCENARIOS_END -->

#### Priority: P2 (Medium)
<!-- DELETE__BOOKING__ID_P2_TEST_SCENARIOS_START -->

N/A - No test scenarios for this priority level

<!-- DELETE__BOOKING__ID_P2_TEST_SCENARIOS_END -->

#### Priority: P3 (Low)
<!-- DELETE__BOOKING__ID_P3_TEST_SCENARIOS_START -->

N/A - No test scenarios for this priority level

<!-- DELETE__BOOKING__ID_P3_TEST_SCENARIOS_END -->

## Health Check (GET /ping)

#### Priority: P0 (Critical)
<!-- GET__PING_P0_TEST_SCENARIOS_START -->

| Test Scenario Id   | Priority   | API endpoint   | Description       |
|:-------------------|:-----------|:---------------|:------------------|
| RB_TS_103          | P0         | GET /ping      | Read health check |

<!-- GET__PING_P0_TEST_SCENARIOS_END -->

#### Priority: P1 (High)
<!-- GET__PING_P1_TEST_SCENARIOS_START -->

N/A - No test scenarios for this priority level

<!-- GET__PING_P1_TEST_SCENARIOS_END -->

#### Priority: P2 (Medium)
<!-- GET__PING_P2_TEST_SCENARIOS_START -->

N/A - No test scenarios for this priority level

<!-- GET__PING_P2_TEST_SCENARIOS_END -->

#### Priority: P3 (Low)
<!-- GET__PING_P3_TEST_SCENARIOS_START -->

N/A - No test scenarios for this priority level

<!-- GET__PING_P3_TEST_SCENARIOS_END -->
