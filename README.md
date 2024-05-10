# 토이 프로젝트2 : 여행 여정을 기록과 관리하는 SNS 서비스 2단계

## Develop Tool

Java 17, Spring Boot, MyBatis, Postman, MySQL, Notion, Discord

## ERD

![image](https://github.com/qudwns017/qudwns017/assets/82150958/37db0e37-602f-45bd-a806-125f713cf3aa)

## Directory Structure

```
┌ java
│  └─org
│      └─example
│          └─kdtbe8_toyproject2
│              │  Kdtbe8ToyProject2Application.java
│              ├─accommodation
│              │  ├─controller
│              │  │      AccommodationController.java
│              │  ├─db
│              │  │      AccommodationEntity.java
│              │  │      AccommodationMapper.java
│              │  ├─model
│              │  │      AccommodationDto.java
│              │  │      AccomodationRequest.java
│              │  └─service
│              │          AccommodationService.java
│              ├─global
│              │  ├─error
│              │  │  ├─errorcode
│              │  │  │      AccommodationError.java
│              │  │  │      ApiSimpleError.java
│              │  │  │      ErrorCode.java
│              │  │  │      ItineraryError.java
│              │  │  │      TimeError.java
│              │  │  │      TravelError.java
│              │  │  │      TripError.java
│              │  │  └─exception
│              │  │          AccommodationException.java
│              │  │          ControllerAdvice.java
│              │  │          CustomException.java
│              │  │          ItineraryException.java
│              │  │          TimeException.java
│              │  │          TravelException.java
│              │  │          TripException.java
│              │  ├─util
│              │  │      ApiResponse.java
│              │  └─validation
│              │          Conditional.java
│              │          ConditionalValidation.java
│              ├─itinerary
│              │  ├─controller
│              │  │      ItineraryController.java
│              │  ├─db
│              │  │      ItineraryEntity.java
│              │  │      ItineraryMapper.java
│              │  │      ItineraryNameEntity.java
│              │  │      MoveEntity.java
│              │  │      StayEntity.java
│              │  ├─enums
│              │  │      ItineraryType.java
│              │  ├─model
│              │  │      ItineraryDto.java
│              │  │      ItineraryRequest.java
│              │  └─service
│              │          ItineraryService.java
│              └─trip
│                  ├─controller
│                  │      TripController.java
│                  ├─db
│                  │      GetTripByIdEntity.java
│                  │      TripEntity.java
│                  │      TripListEntity.java
│                  │      TripMapper.java
│                  ├─model
│                  │      TripDto.java
│                  │      TripRequest.java
│                  └─service
│                          TripService.java
└─resources
    │  application.yaml
    └─mapper
            Accommodation.xml
            itinerary.xml
            trip.xml
```

## API List

![image](https://github.com/qudwns017/qudwns017/assets/82150958/cfe813f0-eafc-442e-9d92-00742c8aa616)

### getTrip

> 여행과 각 여행에 대한 여정의 id와 name List를 반환

- Request

```

```

- Response

```json
{
  "status": 200,
  "name": "OK",
  "data": [
    {
      "tripId": 1,
      "tripName": "string",
      "itineraries": [
        {
          "itineraryId": 4,
          "itineraryName": "string"
        }
      ]
    },
    {
      "tripId": 2,
      "tripName": "string",
      "itineraries": [
        {
          "itineraryId": 2,
          "itineraryName": "string"
        },
        {
          "itineraryId": 3,
          "itineraryName": "string"
        }
      ]
    },
    {
      "tripId": 3,
      "tripName": "string",
      "itineraries": []
    }
  ],
  "timestamp": "2024-05-10T02:59:00.494203600Z"
}
```

### getTripById

> 특정 여행의 정보, 숙박 정보, 여정 정보 호출

- Request

```json

```

- Response

```json
{
  "status": 200,
  "name": "OK",
  "data": {
    "trip_id": 1,
    "trip_name": "string",
    "start_date": "2024-01-01T00:00:00",
    "end_date": "2024-01-05T00:00:00",
    "is_oversea": true,
    "comment": "string",
    "accommodation": [
      {
        "id": 8,
        "trip_id": 1,
        "name": "string",
        "check_in_datetime": "2024-02-01T14:00:00",
        "check_out_datetime": "2024-02-01T16:00:00"
      }
    ],
    "itinerary": [
      {
        "id": 4,
        "trip_id": 1,
        "name": "string",
        "type": 0,
        "start_datetime": "2024-04-10T14:00:00",
        "end_datetime": "2024-04-12T16:00:00",
        "comment": "string"
      }
    ]
  },
  "timestamp": "2024-05-10T03:04:00.372780100Z"
}
```

### createTrip

> 여행 정보 생성

- Request

```json
{
  "trip_name": "string",
  "start_date": "2024-02-01",
  "end_date": "2024-02-04",
  "is_oversea": 1,
  "comment": "string"
}
```

- Response
```json
{
  "status": 200,
  "name": "OK",
  "data": {
    "tripId": 8,
    "tripName": "string",
    "startDate": "2024-01-01",
    "endDate": "2024-01-04",
    "isOversea": 1,
    "comment": "string"
  },
  "timestamp": "2024-05-10T03:06:16.481407800Z"
}
```

### updateTrip

> 등록되어있는 여행 정보 수정

- Request

```json
{
  "trip_name": "string",
  "start_date": "2024-01-01",
  "end_date": "2024-01-09",
  "is_oversea": 1,
  "comment": "string"
}
```

- Response

```json
{
    "status": 200,
    "name": "OK",
    "data": {
        "tripId": 1,
        "tripName": "string",
        "startDate": "2024-01-01",
        "endDate": "2024-01-09",
        "isOversea": 1,
        "comment": "string"
    },
    "timestamp": "2024-05-10T03:07:17.851693800Z"
}
```

### deleteTrip

> 등록되어있는 여행 삭제

- Request

```json

```

- Response

```json
{
    "status": 202,
    "name": "ACCEPTED",
    "timestamp": "2024-05-10T03:09:35.183109700Z"
}
```

### createAccommodation

> 여행에 대한 숙박 정보 생성

- Request

```json
{
  "trip_id": 1,
  "name": "string",
  "check_in_datetime": "2024-02-01 14:00",
  "check_out_datetime": "2024-02-01 16:00"
}
```

- Response

```json
{
    "status": 200,
    "name": "OK",
    "data": {
        "id": 10,
        "trip_id": 2,
        "name": "string",
        "check_in_datetime": "2024-02-01T14:00:00",
        "check_out_datetime": "2024-02-01T16:00:00"
    },
    "timestamp": "2024-05-10T03:10:41.852795400Z"
}
```

### deleteAccommodation

> 등록되어있는 숙박 정보 삭제

- Request

```json

```

- Response

```json
{
    "status": 202,
    "name": "ACCEPTED",
    "timestamp": "2024-05-10T03:11:22.738746600Z"
}
```

### getItinerary

> 등록되어있는 여행에 대한 여정 리스트 호출

- Request

```json

```

- Response

```json
{
    "status": 200,
    "name": "OK",
    "data": [
        {
            "id": 2,
            "trip_id": 2,
            "name": "string",
            "type": 1,
            "start_datetime": "2024-01-01T15:00:00",
            "end_datetime": "2024-01-01T17:00:00",
            "comment": "string"
        },
        {
            "id": 3,
            "trip_id": 2,
            "name": "string",
            "type": 0,
            "start_datetime": "2024-01-01T17:00:00",
            "end_datetime": "2024-01-01T19:00:00",
            "comment": "string"
        }
    ],
    "timestamp": "2024-05-10T03:13:03.412360300Z"
}
```

### createItinerary

> 여정 생성

- Request

```json
// 이동
{
    "itinerary_name" : "string",
    "type" : 0,
    "start_datetime" : "2024-02-01T14:00:00",
    "end_datetime" : "2024-02-04T16:00:00",
    "transportation" : "string",
    "departure_place" : "string",
    "arrival_place" : "string",
    "comment" : "string"
}
// 체류
{
    "itinerary_name" : "string",
    "type" : 1,
    "start_datetime" : "2024-02-01T14:00:00",
    "end_datetime" : "2024-02-04T16:00:00",
    "place" : "string",
    "comment" : "string"
}
```

- Response

```json
// 이동
{
    "status": 200,
    "name": "OK",
    "data": {
        "id": 9,
        "trip_id": 3,
        "name": "string",
        "type": 0,
        "start_datetime": "2024-02-01T14:00:00",
        "end_datetime": "2024-02-04T16:00:00",
        "comment": "string",
        "transportation": "string",
        "departure_place": "string",
        "arrival_place": "string"
    },
    "timestamp": "2024-05-10T03:20:32.862459600Z"
}

//체류
{
    "status": 200,
    "name": "OK",
    "data": {
        "id": 8,
        "trip_id": 3,
        "name": "string",
        "type": 1,
        "start_datetime": "2024-02-01T14:00:00",
        "end_datetime": "2024-02-04T16:00:00",
        "comment": "string",
        "place": "string"
    },
    "timestamp": "2024-05-10T03:19:17.108917500Z"
}
```

### updateItinerary

> 여정에 대한 데이터 수정

- Request
```json
{
    "itinerary_name" : "string",
    "type" : 0,
    "start_datetime" : "2024-02-01T14:00:00",
    "end_datetime" : "2024-02-04T16:00:00",
    "transportation" : "string",
    "departure_place" : "string",
    "arrival_place" : "string",
    "comment" : "string"
}

```

- Response

```json
{
    "status": 202,
    "name": "ACCEPTED",
    "data": {
        "id": 9,
        "trip_id": 3,
        "name": "string",
        "type": 0,
        "start_datetime": "2024-02-01T14:00:00",
        "end_datetime": "2024-02-04T16:00:00",
        "comment": "string",
        "transportation": "string",
        "departure_place": "string",
        "arrival_place": "string"
    },
    "timestamp": "2024-05-10T03:21:20.917032400Z"
}
```

### deleteItinerary

> 여정과 여정 ID를 외래키로 갖고 있는 Move나 Stay도 삭제

- Request
```json
```

- Response

```json
{
    "status": 202,
    "name": "ACCEPTED",
    "timestamp": "2024-05-10T03:23:17.656083100Z"
}
```


## 개발 중점

- 클라이언트와의 상호작용을 고려한 API 설계
- 클린 코드
- 각 레이어에 대한 역할을 분명하게 하기 위해 결합도를 최대한 낮추어 코드를 작성

## 프로젝트 고찰 및 문제 해결 사례

- 여정과 숙박 정보의 분리
  - 숙박에 대한 정보(체크인, 체크아웃 등)를 입력받는 시점과 사용하는 시점에 대한 모호함 발생
    - ex) 체크인을 할 때 정보를 입력할 것인지, 체크인을 할 때만 정보를 입력하고 이후에 숙소에 들리는 여정은 여정으로 취급할지, 숙소에 갈 때마다 체크인, 체크아웃에 대한 정보를 출력할 것인지 등
  - 숙박은 여정이 아닌, 여행에 대한 정보라고 판단
  - 여행의 ID를 외래키로 갖는 참조 테이블로 포함
- 여정의 type에 대한 설정
  - 중복되는 값들을 최소화하기 위해 move(이동)과 stay(체류) 테이블로 여정을 분리
  - itinerary_id를 외래키로 갖는 참조 테이블을 생성하여 여정의 type에 맞는 정보를 포함
