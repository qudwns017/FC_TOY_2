# 토이 프로젝트2 : 여행 여정을 기록과 관리하는 SNS 서비스 2단계

## 설정 방법
- 아래의 SQL 쿼리문을 실행한 뒤, application.yaml 설정을 DB 설정에 맞게 변경
<details>
  <summary>Database Table Structure</summary>
  <div markdown="1">
    -- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: travel
-- ------------------------------------------------------
-- Server version	8.0.33

/_!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT _/;
/_!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS _/;
/_!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION _/;
/_!50503 SET NAMES utf8 _/;
/_!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE _/;
/_!40103 SET TIME_ZONE='+00:00' _/;
/_!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 _/;
/_!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 _/;
/_!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' _/;
/_!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 _/;

--
-- Table structure for table `accommodation`
--

DROP TABLE IF EXISTS `accommodation`;
/_!40101 SET @saved_cs_client = @@character_set_client _/;
/_!50503 SET character_set_client = utf8mb4 _/;
CREATE TABLE `accommodation` (
`id` bigint NOT NULL AUTO*INCREMENT,
`trip_id` bigint NOT NULL,
`name` varchar(50) NOT NULL,
`check_in_datetime` datetime NOT NULL,
`check_out_datetime` datetime NOT NULL,
PRIMARY KEY (`id`),
KEY `fk_accommodation_trip1_idx` (`trip_id`),
CONSTRAINT `fk_accommodation_trip1` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character*set_client = @saved_cs_client */;

--
-- Dumping data for table `accommodation`
--

LOCK TABLES `accommodation` WRITE;
/_!40000 ALTER TABLE `accommodation` DISABLE KEYS _/;
/_!40000 ALTER TABLE `accommodation` ENABLE KEYS _/;
UNLOCK TABLES;

--
-- Table structure for table `itinerary`
--

DROP TABLE IF EXISTS `itinerary`;
/_!40101 SET @saved_cs_client = @@character_set_client _/;
/_!50503 SET character_set_client = utf8mb4 _/;
CREATE TABLE `itinerary` (
`id` bigint NOT NULL AUTO*INCREMENT,
`trip_id` bigint NOT NULL,
`name` varchar(50) NOT NULL,
`type` tinyint NOT NULL,
`start_datetime` datetime NOT NULL,
`end_datetime` datetime NOT NULL,
`comment` text,
PRIMARY KEY (`id`,`trip_id`),
KEY `fk_itinerary_trip_idx` (`trip_id`),
CONSTRAINT `fk_itinerary_trip` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character*set_client = @saved_cs_client */;

--
-- Dumping data for table `itinerary`
--

LOCK TABLES `itinerary` WRITE;
/_!40000 ALTER TABLE `itinerary` DISABLE KEYS _/;
INSERT INTO `itinerary` VALUES (2,2,'점심식사',1,'2024-01-01 15:00:00','2024-01-01 17:00:00','냠냠'),(3,2,'이동',0,'2024-01-01 17:00:00','2024-01-01 19:00:00',NULL);
/_!40000 ALTER TABLE `itinerary` ENABLE KEYS _/;
UNLOCK TABLES;

--
-- Table structure for table `move`
--

DROP TABLE IF EXISTS `move`;
/_!40101 SET @saved_cs_client = @@character_set_client _/;
/_!50503 SET character_set_client = utf8mb4 _/;
CREATE TABLE `move` (
`itinerary_id` bigint NOT NULL,
`transportation` varchar(50) NOT NULL,
`departure_place` varchar(50) NOT NULL,
`arrival_place` varchar(50) NOT NULL,
PRIMARY KEY (`itinerary_id`),
KEY `fk_move_itinerary1_idx` (`itinerary_id`),
CONSTRAINT `move_ibfk_1` FOREIGN KEY (`itinerary_id`) REFERENCES `itinerary` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/_!40101 SET character_set_client = @saved_cs_client _/;

--
-- Dumping data for table `move`
--

LOCK TABLES `move` WRITE;
/_!40000 ALTER TABLE `move` DISABLE KEYS _/;
/_!40000 ALTER TABLE `move` ENABLE KEYS _/;
UNLOCK TABLES;

--
-- Table structure for table `stay`
--

DROP TABLE IF EXISTS `stay`;
/_!40101 SET @saved_cs_client = @@character_set_client _/;
/_!50503 SET character_set_client = utf8mb4 _/;
CREATE TABLE `stay` (
`itinerary_id` bigint NOT NULL,
`place` varchar(50) NOT NULL,
PRIMARY KEY (`itinerary_id`),
KEY `fk_stay_itinerary1_idx` (`itinerary_id`),
CONSTRAINT `stay_ibfk_1` FOREIGN KEY (`itinerary_id`) REFERENCES `itinerary` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/_!40101 SET character_set_client = @saved_cs_client _/;

--
-- Dumping data for table `stay`
--

LOCK TABLES `stay` WRITE;
/_!40000 ALTER TABLE `stay` DISABLE KEYS _/;
/_!40000 ALTER TABLE `stay` ENABLE KEYS _/;
UNLOCK TABLES;

--
-- Table structure for table `trip`
--

DROP TABLE IF EXISTS `trip`;
/_!40101 SET @saved_cs_client = @@character_set_client _/;
/_!50503 SET character_set_client = utf8mb4 _/;
CREATE TABLE `trip` (
`id` bigint NOT NULL AUTO*INCREMENT,
`name` varchar(50) NOT NULL,
`start_date` datetime NOT NULL,
`end_date` datetime NOT NULL,
`is_oversea` tinyint(1) NOT NULL,
`comment` varchar(50) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character*set_client = @saved_cs_client */;

--
-- Dumping data for table `trip`
--

LOCK TABLES `trip` WRITE;
/_!40000 ALTER TABLE `trip` DISABLE KEYS _/;
INSERT INTO `trip` VALUES (1,'일본','2024-01-01 00:00:00','2024-01-05 00:00:00',1,'boobar'),(2,'대만','2024-02-01 00:00:00','2024-02-04 00:00:00',1,'poobar'),(3,'홍콩','2024-02-01 00:00:00','2024-02-04 00:00:00',1,'poobaㄱㄱr'),(4,'홍콩','2024-02-01 00:00:00','2024-02-04 00:00:00',1,'poobaㄱㄱr'),(5,'홍콩','2024-02-01 00:00:00','2024-02-04 00:00:00',1,'poobaㄱㄱr');
/_!40000 ALTER TABLE `trip` ENABLE KEYS _/;
UNLOCK TABLES;
/_!40103 SET TIME_ZONE=@OLD_TIME_ZONE _/;

/_!40101 SET SQL_MODE=@OLD_SQL_MODE _/;
/_!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS _/;
/_!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS _/;
/_!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT _/;
/_!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS _/;
/_!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION _/;
/_!40111 SET SQL_NOTES=@OLD_SQL_NOTES _/;

-- Dump completed on 2024-05-09 17:17:26

  </div>
</details>

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
│              │
│              ├─accommodation
│              │  ├─controller
│              │  │      AccommodationController.java
│              │  │
│              │  ├─db
│              │  │      AccommodationEntity.java
│              │  │      AccommodationMapper.java
│              │  │
│              │  ├─model
│              │  │      AccommodationDto.java
│              │  │      AccommodationRequest.java
│              │  │
│              │  └─service
│              │          AccommodationService.java
│              │
│              ├─global
│              │  ├─error
│              │  │  ├─errorcode
│              │  │  │      ApiSimpleError.java
│              │  │  │      ErrorCode.java
│              │  │  │      TravelError.java
│              │  │  │
│              │  │  └─exception
│              │  │          ControllerAdvice.java
│              │  │          CustomException.java
│              │  │          TravelException.java
│              │  │
│              │  ├─util
│              │  │      ApiResponseUtil.java
│              │  │
│              │  └─validation
│              │          Conditional.java
│              │          ConditionalValidation.java
│              │
│              ├─itinerary
│              │  ├─controller
│              │  │      ItineraryController.java
│              │  │
│              │  ├─db
│              │  │      ItineraryEntity.java
│              │  │      ItineraryMapper.java
│              │  │      ItineraryNameEntity.java
│              │  │      MoveEntity.java
│              │  │      StayEntity.java
│              │  │
│              │  ├─enums
│              │  │      ItineraryType.java
│              │  │
│              │  ├─model
│              │  │      ItineraryDto.java
│              │  │      ItineraryRequest.java
│              │  │
│              │  └─service
│              │          ItineraryService.java
│              │
│              └─trip
│                  ├─controller
│                  │      TripController.java
│                  │
│                  ├─db
│                  │      GetTripByIdEntity.java
│                  │      TripEntity.java
│                  │      TripListEntity.java
│                  │      TripMapper.java
│                  │
│                  ├─enums
│                  │      OverseaType.java
│                  │
│                  ├─model
│                  │      TripDto.java
│                  │      TripRequest.java
│                  │
│                  └─service
│                          TripService.java
│
└─resources
    │  application.yaml
    │
    └─mapper
            Accommodation.xml
            itinerary.xml
            trip.xml

```

## API List

![image](https://github.com/qudwns017/qudwns017/assets/82150958/cfe813f0-eafc-442e-9d92-00742c8aa616)

---

### getTrip

![image](https://github.com/qudwns017/qudwns017/assets/82150958/70680d2c-8302-489c-8cad-20a8338e745a)

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

---

### getTripById

![image](https://github.com/qudwns017/qudwns017/assets/82150958/daa36246-1bd4-4550-96e6-641f47c2057f)

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

---

### createTrip

![image](https://github.com/qudwns017/qudwns017/assets/82150958/265885d4-a45f-41d1-885a-50317e02980a)

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

---

### updateTrip

![image](https://github.com/qudwns017/qudwns017/assets/82150958/e4c90cbd-b221-4d6c-8faa-535b3714af16)

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

---

### deleteTrip

![image](https://github.com/qudwns017/qudwns017/assets/82150958/ae276336-9594-4329-a38d-0555945ff143)

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

---

### createAccommodation

![image](https://github.com/qudwns017/qudwns017/assets/82150958/bd064f45-d2c7-4050-9db7-1d968a669735)

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

---

### deleteAccommodation

![image](https://github.com/qudwns017/qudwns017/assets/82150958/6ad791a8-1437-4934-8340-4963d983e3c5)

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

---

### getItinerary

![image](https://github.com/qudwns017/qudwns017/assets/82150958/259c1787-e072-43b2-86d9-6a21bc7e5846)

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

---

### createItinerary

![image](https://github.com/qudwns017/qudwns017/assets/82150958/3be99e3b-34b2-4242-82ec-bb495615ce5c)

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

---

### updateItinerary

![image](https://github.com/qudwns017/qudwns017/assets/82150958/2984b797-86c7-4ae9-8714-c11300dddc54)

> 여정에 대한 데이터 수정

- Request

```json
{
  "itinerary_name": "string",
  "type": 0,
  "start_datetime": "2024-02-01T14:00:00",
  "end_datetime": "2024-02-04T16:00:00",
  "transportation": "string",
  "departure_place": "string",
  "arrival_place": "string",
  "comment": "string"
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

---

### deleteItinerary

![image](https://github.com/qudwns017/qudwns017/assets/82150958/c67d7df8-25ea-4501-b862-17784a64cfc3)

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
