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
