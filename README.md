
# Flight Search




## Technologies

**Language:** Java 17

**Framework:** Spring Boot 3.1.2

**Database:** PostgreSQL


**Open API Specification:** Swagger UI


## Run

For Swagger UI

```bash
  http://localhost:8080/swagger-ui/index.html#/
```

  
## API Usage

## Auth API

#### Admin Login

```http
  POST /api/v1/auth/login
```
#### Request:
```http
{
  "username": "admin",
  "password": "123456"
}
```

#### Response: 
```http
  {
  "user": {
    "userId": 1,
    "username": "admin",
    "password": "$2a$10$ayDuK6aM2x24lwaK/dTFo.BRjLMRVAQDYofpUN8gYpZAwDyy.g84.",
    "authorities": [
      {
        "roleId": 1,
        "authority": "ADMIN"
      }
    ],
    "enabled": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true,
    "accountNonExpired": true
  },
  "jwt": "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE2OTI1NDI3MDcsInJvbGVzIjoiQURNSU4ifQ.xLBEHg5y38UK86eDTBVVth6Mx_xaBtOi2HBFpS9Acg9vL14xUaGmj_bnFYFop7fR1cbF1DD6PT4Tpk5Hq5d4sT78lcGLEDYeECUsSl4FveZxH5_RkHaqBZdW2I9NM9uPN1cDhwjqxOmbBajpzGtv8DuAwe8J9sIfnvOR_SCc71BdOvvu3MhJlSso74eD9VYDFECMPgMAe6YNO-LV0PrvVSZsVowXsrBeZ2o5HUge1TLCAPksItVPjhNQaSuU7E5N4VJyeE4Cy7RFSscYUidWjpAcbEKksT_pf0pJ7zcp8mjBlEmbKHkwCUkxpys5IDsW9LF7_jBCsoEbwJ4nowaVVg"
}

```
#### Register 

```http
  POST /api/v1/auth/register
```
#### Request:
```http
{
  "username": "test",
  "password": "123456789"
}
```
#### Response:
```http
{
  "userId": 2,
  "username": "test",
  "password": "$2a$10$DPm9G7NnGnjwjEQupn7l/OG5d4K5Ybaje/0uqu.zsThccLYZNtAGa",
  "authorities": [
    {
      "roleId": 2,
      "authority": "USER"
    }
  ],
  "enabled": true,
  "accountNonLocked": true,
  "credentialsNonExpired": true,
  "accountNonExpired": true
}
```
## Flight API

#### Search Flight

```http
  GET /api/v1/flight/search
```
#### Request:

#### Request:
| Parameter | Type     | Description               |
| :-------- | :------- | :------------------------- |
| `departureAirport*` | `Airport` | Departure Airport Code |
| `arrivalAirport*` | `Airport` | Arrival Airport Code |
| `departureDate*` | `Date` | Departure Date |
| `returnDate` | `Date` | Return Date |

"*" : Required


#### Response (If only departureDate field is full):
```http
[
  {
    "departureFlights": [
      {
        "id": "810d7c6c-925b-439e-87b2-759a0bb261b7",
        "departureDateTime": "2023-08-21T16:31:30.879",
        "arrivalDateTime": "2023-08-21T18:31:30.879",
        "price": 800,
        "departureAirport": {
          "airportCode": "AYT",
          "city": "Antalya"
        },
        "arrivalAirport": {
          "airportCode": "IST",
          "city": "İstanbul"
        }
      }
    ],
    "returnFlights": null
  }
]
```
#### Response (If departureDate field and returnDate is full):
```http
[
  {
    "departureFlights": [
      {
        "id": "810d7c6c-925b-439e-87b2-759a0bb261b7",
        "departureDateTime": "2023-08-21T16:31:30.879",
        "arrivalDateTime": "2023-08-21T18:31:30.879",
        "price": 800,
        "departureAirport": {
          "airportCode": "AYT",
          "city": "Antalya"
        },
        "arrivalAirport": {
          "airportCode": "IST",
          "city": "İstanbul"
        }
      }
    ],
    "returnFlights": [
      {
        "id": "8304f704-eb6b-43f7-b702-47646562d95a",
        "departureDateTime": "2023-08-22T16:31:30.879",
        "arrivalDateTime": "2023-08-22T18:31:30.879",
        "price": 800,
        "departureAirport": {
          "airportCode": "IST",
          "city": "İstanbul"
        },
        "arrivalAirport": {
          "airportCode": "AYT",
          "city": "Antalya"
        }
      }
    ]
  }
]
```

#### Add Flight (Only Admin)

```http
  POST /api/v1/flight
```
#### Request:
```http
{
  "departureDateTime": "2023-08-21T16:31:30.879Z",
  "arrivalDateTime": "2023-08-21T18:31:30.879Z",
  "price": 800,
  "departureAirportCode": "AYT",
  "arrivalAirportCode": "IST"
}
```
#### Response:
```http
{
  "id": "21aa5680-57c0-47e9-bb4f-d6dee099ab07",
  "departureDateTime": "2023-08-21T16:31:30.879",
  "arrivalDateTime": "2023-08-21T18:31:30.879",
  "price": 800,
  "departureAirport": {
    "airportCode": "AYT",
    "city": "Antalya"
  },
  "arrivalAirport": {
    "airportCode": "IST",
    "city": "İstanbul"
  }
}
```
#### Update Flight (Only Admin)

```http
  PUT /api/v1/flight/{flightId}
```
#### Request:
```http
{
  "departureDateTime": "2023-08-21T16:31:30.879Z",
  "arrivalDateTime": "2023-08-21T18:31:30.879Z",
  "price": 800,
  "departureAirportCode": "AYT",
  "arrivalAirportCode": "IST"
}
```
#### Response:
```http
{
  "id": "21aa5680-57c0-47e9-bb4f-d6dee099ab07",
  "departureDateTime": "2023-08-21T16:31:30.879",
  "arrivalDateTime": "2023-08-21T18:31:30.879",
  "price": 800,
  "departureAirport": {
    "airportCode": "AYT",
    "city": "Antalya"
  },
  "arrivalAirport": {
    "airportCode": "IST",
    "city": "İstanbul"
  }
}
```
#### Get Flight By Id (Only Admin)

```http
  GET /api/v1/flight/{flightId}
```

#### Response:
```http
{
  "id": "21aa5680-57c0-47e9-bb4f-d6dee099ab07",
  "departureDateTime": "2023-08-21T16:31:30.879",
  "arrivalDateTime": "2023-08-21T18:31:30.879",
  "price": 800,
  "departureAirport": {
    "airportCode": "AYT",
    "city": "Antalya"
  },
  "arrivalAirport": {
    "airportCode": "IST",
    "city": "İstanbul"
  }
}
```
#### Delete Flight By Id (Only Admin)

```http
  DELETE /api/v1/flight/{flightId}
```

#### Response:
```http
204
```