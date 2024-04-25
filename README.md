# 📚 도서 관리 애플리케이션 만들어보기
#### 📣 [인프런] 최태현 님의 [「자바와 스프링 부트로 생애 최초 서버 만들기, 누구나 쉽게 개발부터 배포까지!」](https://inf.run/Hywa) 강의 실습 코드입니다.

## 개발 환경
* `Java 17`
* `SpringBoot v3.2.3`
* `Dependencies` : Spring Web, Spring Data JPA, Lombok
* `IDE` : IntelliJ IDEA Ultimate 2023.2
* `Build` : IntelliJ IDEA

## 학습 내용 정리

<details>
  
**<summary> `Section 1) 생애 최초 API 만들기` </summary>**
### ✔️ 스프링 프로젝트 시작하기
#### 스프링 프로젝트를 시작하는 첫 번째 방법
* 이미 만들어져 있는 스프링 프로젝트를 다운받아 IntelliJ를 통해 열기
* 다운로드가 완료되면, LibraryAppApplication 클래스를 찾아 실행 (경로 : src/main/java/패키지명/LibraryAppApplication.java)


#### 스프링 프로젝트를 시작하는 두 번째 방법
* [spring initializr](https://start.spring.io/) 이용하기
  ##### 1️⃣ 빌드 툴 설정
  ##### 2️⃣ 언어 선택
  ##### 3️⃣ 스프링 부트 버전 선택
  * 옆에 알파벳이 붙지 않은 가장 최신 버전 선택하기 ➡️ 알파벳이 붙어있다는 의미는 아직 개발 중이거나 테스트 중인 오픈베타버전으로 안정성이 떨어질 수 있음
  ##### 4️⃣ 프로젝트 메타 데이터 작성
  * Group : 프로젝트 그룹
  * Artifact : 최종 결과물의 이름
  * Name : 프로젝트 이름
  * Description : 프로젝트 설명
  * Package name : 패키지 이름
  * Packaging : 패키징 방법 (➡️ Spring Boot는 톰캣이 내장되어 있어 Jar 선택)
  * Java : 자바 버전
  ##### 5️⃣ 의존성 설정
  * 프로젝트에서 사용하는 라이브러리/프레임워크 설정
    * 📚 라이브러리 : 프로그래밍을 개발할 때 미리 만들어져 있는 기능을 가져다 사용하는 것
    * 📚 프레임워크 : 프로그래밍을 개발할 때 미리 만들어져 있는 구조에 코드를 가져다 끼워 넣는 것

  이렇게 설정을 모두 마쳤으면, Generate를 눌러 프로젝트를 만들어주자. 다운로드 된 압축 파일을 해제하고 적절한 위치로 옮겨 IntelliJ로 열어주면 된다 !

### ✔️ @SpringBootApplication과 서버
LibraryAppApplication 클래스를 살펴보자.

```java
@SpringBootApplication
public class LibraryAppApplication {
  public static void main(String[] args) {
    SpringApplication.run(LibraryAppApplication.class, args);
  }
}
```
* `@SpringBootApplication` : 스프링을 실행시키기 위해 필요한 다양한 설정들을 자동으로 해주는 애노테이션
* `class` : Java에서 모든 코드는 class 안에 있어야 하고, static 메서드인 main 메서드가 이 안에 존재
* `SpringApplication.run(LibraryAppApplication.class, args);` : 서버를 실행하는 코드

#### 🙋🏻 서버란 무엇일까?
* 어떠한 기능을 제공하는 프로그램
* 클라이언트로부터 요청을 받아 결과 반환

### ✔️ 클라이언트가 컴퓨터를 통해 서버에 요청하는 과정 알아보기
#### 🙋🏻 네트워크란 무엇일까? 
<p>네트워크를 이해하기 위해 A 부족과 B 부족이 존재하는 이세계를 생각해보자. </p>
<p>이세계는 주소 체계와 택배 시스템이 잘 발달되어 있어 주소를 통해 택배를 보낼 수 있다고 하자.</p>

```text
B부족 감자동 곰로 13번길 2에 사는 둘째  
```

<p>하지만 이렇게 복잡한 주소 체계는 외우기 어렵다 💦 '파란집에 사는 둘째'라고 더 간단하게 부를 수 있다.</p>

<p>이세계 뿐만 아니라 현실 세계도 마찬가지다 ! 현실 세계의 컴퓨터는 각각 고유 주소 (IP)를 가지고 있다. 그리고 택배 시스템처럼 인터넷이 잘 발달되어 있어 우리는 인터넷을 통해 데이터를 주고 받을 수 있다.</p>

```text
IP 244.66.51.9, port : 3000 
```
<p>여기서 port 번호는 '파란집에 사는 둘째'이고, IP는 자세한 주소를 나타낸다. </p>
<p>BUT, 우리는 인터넷에 접속할 때 일반적으로 IP 주소와 port 번호를 입력하지 않는다. 아래와 같이 도메인 이름을 입력하여 접속할 것이다. (➡️ DNS : Domain Name System)</p>

```text
spring.com:3000 
```

### ✔️ HTTP와 API란 무엇인가?!
#### 🙋🏻 HTTP는 무엇일까?
<p>우리는 택배를 보내려면 운송장이라는 표준이 있어야 한다.</p>

```text
내놓아라 파란집 둘째, 포션 빨강색 2개
```

* `내놓아라` : 운송장을 받는 사람에게 요청하는 **행위**
* `파란집` : 운송장이 가는 집
* `둘째` : 운송장을 받는 사람
* `포션` : 운송장을 받는 사람에게 원하는 **자원**
* `빨강색 2개` : 자원의 세부 조건

여기서 행위와 자원은 빨간집에 운송장을 보내기 전에 **약속**해야 한다.

<p>현실 세계에도 데이터를 받는 표준인 HTTP(HyperText Transfer Protocol)가 존재한다. </p>

#### 예시 1️⃣

  ```text
  GET /portion?color=red&count=2
  Host: spring.com:3000
  ```
  
  * `GET` : HTTP 요청을 받는 컴퓨터에게 요청하는 행위 (**HTTP Method**)
  * `/portion` : HTTP 요청을 받는 컴퓨터에게 원하는 자원 (**Path**)
  * `?`, `&` : 구분 기호
  * `color=red`, `count=2` : 자원의 세부 조건 (**Query String**)
  * `Host: spring.com:3000` : HTTP 요청을 받는 컴퓨터와 프로그램 정보

#### 예시 2️⃣

  ```text
  POST /oak/leather
  Host: spring.com:3000

  오크가죽정보
  ```
  
  * `POST` : HTTP 요청을 받는 컴퓨터에게 요청하는 행위 (**HTTP Method**)
  * `/oak/leather` : HTTP 요청을 받는 컴퓨터에게 원하는 자원 (**Path**)
  * `오크가죽정보` : 실제 저장할 오크 가죽 정보 (**Body**)
  * `Host: spring.com:3000` : HTTP 요청을 받는 컴퓨터와 프로그램 정보

  현실 세계에서도 마찬가지로 행위와 자원은 HTTP 요청을 보내기 전에 약속해야 한다.

#### 📚 정리
* 정보를 보내는 방법 2가지 존재 (➡️ Query String & Body)
  * `GET`(데이터 요청), `DELETE`(데이터 삭제) : Query String
  * `POST`(데이터 저장), `PUT`(데이터 수정) : Body

#### 🙋🏻 API(Application Programming Interface)는 무엇일까?
<p>클라이언트와 서버는 HTTP를 주고 받으며 기능을 수행하는데, 이때 정해진 규칙을 의미하는 API</p>

##### HTTP 요청 문법
* 첫째줄 : HTTP Method와 Path, (Query)를 작성하고 필요하다면 HTTP Version도 작성
* 둘째줄 : Header 영역으로, 어디로 보낼지 도메인 + 포트 번호로 Host 작성 (여러 줄 가능)
* Body가 있을 경우, 한 줄 띄우고 작성 (여러 줄 가능)

##### URL (Uniform Resource Locator)

```text
프로토콜://도메인(혹은 IP주소):포트번호/자원경로?쿼리(추가정보)
```

##### HTTP 응답
<p>우리는 HTTP 요청 방법에 대해 살펴보았다.</p>

#### 🙋🏻 그럼 들어온 요청에 대한 응답은 어떻게 하는 걸까?
* `서버` : 요청에 대한 응답을 제공하는 컴퓨터
  * ✨ **상태 코드**를 통해 응답
  * 응답시, Body에 추가 정보 담을 수 있음
  * HTTP 요청과 동일한 구조
    * 첫째줄 : 상태 코드
    * 둘째줄 : Header 영역 (여러 줄 가능)
    * Body가 있을 경우, 한 줄 띄우고 작성 (여러 줄 가능)   

* `클라이언트` : 요청을 한 컴퓨터

### ✔️ API 개발하고 테스트하기

<p>API를 개발하기 전에는 API 스펙을 살펴봐야 한다. </p>
<p>HTTP Method와 path를 결정하고, 데이터를 전달하기 위해 쿼리를 사용할 것인지 아니면 바디를 사용할 것인지, 결과는 어떤 형태로 줄 것인지를 고민해서 결정해야 한다.</p>

#### 1️⃣ GET API
  #### ➕ 덧셈 API
  * HTTP Method : `GET`
  * HTTP Path : `/add`
  * 쿼리 사용
    * `int number1`
    * `int number2`
  * 결과 반환
    * `int 쿼리로 들어온 두 숫자의 합`   

  #### 📍 Controller 
  * `@RestController` : 주어진 class를 Controller로 등록 (Controller : API의 진입 지점)
  * `@GetMapping(“/add”)` : 아래 함수를 HTTP Method가 `GET` 이고, HTTP path가 `/add`인 API로 지정
  * `@RequestParam` : 주어진 쿼리를 함수 파라미터로 넣음
    * 단일 타입으로 넣을 수도 있지만, request DTO를 생성하여 객체 넣기도 가능 (➡️ 이때 애노테이션 생략) 

#### 2️⃣  API
<p>HTTP Body 사용법 연습을 위해 곱셈 기능을 로 작성해보자. (원래  API는 어떤 값을 저장한다는 의미이기 때문에 적절한 방법은 아니다 😅)</p>
  
  #### ➕ 곱셈 API
  * HTTP Method : `POST`
  * HTTP Path : `/multiply`
  * HTTP Body (JSON)
    
    ```text
    {
      “number1”: 숫자,
      “number2”: 숫자
    }
    ```

    * JSON (**J**ava**S**cript **O**bject **N**otation)
      * 중괄호를 사용하여 `"key": "value"`로 표기하는 객체 표기법
      * 쉼표로 속성 구분
      * Java의 Map<Object, Object>와 유사
  * 결과 반환
    * `Body로 들어온 두 숫자의 곱`   

  #### 📍 Controller 
  * `@PostMapping(“/multiply”)` : 아래 함수를 HTTP Method가 `POST` 이고, HTTP path가 `/multiply`인 API로 지정
  * `@RequestBody` : HTTP Body로 들어오는 JSON을 파라미터로 넘긴 객체(DTO)로 변경
    * DTO에는 JSON의 key값이 명시되어야 하며, 각 속성은 key값과 동일하게, 타입도 value의 타입에 따라 작성 

### ✔️ 도서 관리 어플리케이션 API 개발
#### 주요 기능
* 👤 `사용자`
  * 도서관 사용자를 등록할 수 있습니다.
  * 등록된 사용자 목록을 조회할 수 있습니다.
  * 사용자의 이름을 변경할 수 있습니다.
  * 등록된 사용자를 삭제할 수 있습니다.
 
* 📖 `도서`
  * 도서관에 책을 등록 및 삭제할 수 있습니다.
  * 사용자는 등록된 도서를 대출/반납할 수 있습니다.
    * 다른 사용자가 대여 중인 책은 빌릴 수 없습니다.  

#### 📍 도서관 사용자 등록 
  #### 👤 유저 생성 API
  * HTTP Method : `POST`
  * HTTP Path : `/user`
  * HTTP Body (JSON)
  
    ```text
      {
        “name”: String (null X),
        “age”: Integer
      }
    ```
    * String : null 허용 ➡️ 검증 로직 필요
    * Integer : null 허용 (int : null 허용 X)
    
  * 결과 반환 X
    * `200 OK` 상태 코드

   
  #### 로직
   ##### 1️⃣ API 호출
   ##### 2️⃣ User 클래스의 인스턴스 생성
   ##### 3️⃣ 생성한 데이터 List에 저장

   ```java
      @RestController
      public class UserController {
        private final List<User> users = new ArrayList<>();

        @PostMapping("/user")
        public void saveUser(@RequestBody UserCreateRequest request) {
          User newUser = new User(request.getName(), request.getAge());
          users.add(newUser);
        }
      }
   ```

#### 📍 사용자 목록 조회
  #### 👤 유저 조회 API
  * HTTP Method : `GET`
  * HTTP Path : `/user`
  * 쿼리 : X (➡️ API 호출시, 전체 User 데이터를 줄 것이므로)
  * 결과 반환

    ```text
      [{
        “id”: Long,
        “name”: String (null X),
        “age”: Integer
      }, ...]
    ```
  ##### 🤔 어떻게 결과를 JSON으로 반환할 수 있을까?
  <p>파라미터로 넘기는 객체(DTO)에 getter가 있다면, Controller에서 객체를 반환시 JSON으로 응답이 가능하다. </p>
  
  ➡️ `@RestController`를 클래스에 붙여준 덕분에 가능한 일 !
  
  ##### 🤔 Id는 무엇일까?
  <p>Id란 데이터 별로 겹치지 않는 유일한 번호를 의미한다. API 스펙에 Id가 있다는 것은 User 별로 고유한 번호를 API 응답 결과로 반환하기 위함이다.</p>
  <p>여기서는 List에 담겨 있는 User의 순서를 Id로 설정하자.</p>

  #### 로직
   ##### 1️⃣ API 호출
   ##### 2️⃣ List로 저장된 유저 정보 반환

   ```java
        @GetMapping("/user")
        public List<UserResponse> getUsers() {
          List<UserResponse> responses = new ArrayList<>();
          for (int i = 0; i < users.size(); i++) {
            responses.add(new UserResponse(i + 1, users.get(i)));
          }
          return responses;
        }
   ```


### 📚 Section 1 학습 내용
#### 1️⃣ 스프링 프로젝트를 시작하고 실행할 수 있다.
#### 2️⃣ 네트워크, IP, 도메인, 포트, HTTP 요청과 응답 구조, 클라이언트 - 서버 구조, API와 같은 기반 지식을 배울 수 있었다.
#### 3️⃣ Spring Boot에서 GET API와 POST API를 만드는 방법에 대해 학습하였다.

#### ⚠️ 우리가 개발한 API의 문제점
<p>User 정보는 메모리에서만 유지되고 있기 때문에 서버를 재시작하면, User 정보가 날라간다 !</p>
<p>따라서 데이터가 날라가는 문제점을 해결하기 위해 DB를 사용해보자.</p>
 
</details>


<details>
  
**<summary> `Section 2) 생애 최초 Database 조작하기` </summary>**
### ✔️ Database와 MySQL
<p>지금까지 우리는 User 정보를 RAM (임시 기억 장치)에 저장했다. 그래서 서버가 종료되면, RAM에 있는 모든 정보가 사라져 User 정보가 날라갔다.</p>
<p>데이터가 날라가지 않도록 하기 위해 DISK (장기 기억 장치)에 정보를 저장할 수 있도록 해보자. 이럴 때 사용하는 것이 Database이다 !</p>

#### 📍 Database
<p>Database란, 데이터를 구조화 시켜 저장하는 장치이다. 대표적으로 RDB의 MySQL이 있다.</p>

  * RDB (Relational Database) : 데이터를 표처럼 구조화 시켜 저장 (예 : MySQL)
  * SQL (Structured Query Language) : 표처럼 구조화된 데이터를 조회하는 언어

<p>MySQL을 사용해 데이터에 접근해보자.</p>

### ✔️ MySQL에서 테이블 만들기
#### 📍 DDL (Data Definition Language)
  * 데이터베이스 만들기
  ```sql
  $> create database [데이터베이스 이름];
  ```

  * 데이터베이스 목록 보기
  ```sql
  $> show databases;
  ```

  * 데이터베이스 지우기
  ```sql
  $> drop databases [데이터베이스 이름];
  ```

  * 데이터베이스 접속하기
  ```sql
  $> use [데이터베이스 이름];
  ```

  * 테이블 목록 보기
  ```sql
  $> show tables;
  ```

  * 테이블 만들기
  ```sql
  $> create table [테이블 이름] ( 
  [필드1 이름] [타입] [부가조건], 
  [필드2 이름] [타입] [부가조건], 
  ...
  primary key ([필드이름]) 
  );
  ``` 

  * 테이블 제거하기
  ```sql
  $> drop table [테이블 이름];
  ```

#### 📍 MySQL 타입 살펴보기
  ##### 정수 타입
  * tinyint : 1byte
  * int : 4byte
  * bigint : 8byte
  
  ##### 실수 타입
  * double : 8byte
  * decimal(A, B) : 소수점을 B개 가지고 있는 전체 A자리 실수

  ##### 문자열 타입
  * char(A) : A글자가 들어갈 수 있는 고정된 문자열
  * varchar(A) : 최대 A글자가 들어갈 수 있는 문자열

  ##### 날짜, 시간 타입
  * date : `yyyy-MM-dd`
  * time : `HH:mm:ss`
  * datetime : `yyyy-MM-dd HH:mm:ss`

### ✔️ 테이블 데이터 조작하기
#### 📍 DML (Data Manipulation Language)
  * 데이터 삽입
  ```sql
  $> INSERT INTO [테이블 이름] (필드1이름, 필드2이름, ...) VALUES (값1, 값2, ...)
  ```

  * 데이터 조회
  ```sql
  $> SELECT * FROM [테이블 이름]; # * 대신 필드 이름 작성 가능
  ```

  ```sql
  # 특정 조건을 걸어 조회
  $> SELECT * FROM [테이블 이름] WHERE [조건];
  ```

  * 데이터 업데이트
  ```sql
  $> UPDATE [테이블 이름]
  SET 필드1이름=값, 필드2이름=값, ... WHERE [조건];
  # [조건]이 없으면, 모든 데이터 업데이트
  ```

  * 데이터 삭제
  ```sql
  $> DELETE FROM [테이블 이름] WHERE [조건];
  # [조건]이 없으면, 모든 데이터 삭제
  ```

### ✔️ Spring에서 Database 사용하기
#### 📍`application.yml` 파일 만들기
* `application.properties`에 DB 설정 정보 기입도 가능
  ```yml
  spring:
    datasource:
      url: "jdbc:mysql://localhost/library"
      username: "root"
      password: "1234"
      driver-class-name: com.mysql.cj.jdbc.Driver
  ```
  * url : 연결할 데이터베이스 주소
    * `jdbc:mysql://` : jdbc를 이용해 mysql 접근
    * `localhost` : 접근하려는 mysql은 localhost에 있음
    * `/library` : 접근하려는 데이터베이스는 library
  * username : mysql에 접근하기 위한 계정명
  * password : mysqp에 접근하기 위한 비밀번호
  * driver-class-name : 데이터베이스 접근시 사용할 프로그램 

#### 📍 유저 생성 API 리팩토링
  ##### 1️⃣ User 테이블 생성
  ##### 2️⃣ JdbcTemplate을 이용해 sql 날리기
  * JdbcTemplate을 final 변수를 만들고 생성자를 만들어두면, 스프링이 알아서 JdbcTemplate을 넣어줌
  ##### 3️⃣ sql을 문자열로 입력 후, 값이 들어갈 부분에 ? 넣기
  * ? 사용시, 유동적으로 값 변경 가능
  * 이 문자열을 JdbcTemplate의 update 메서드에 담음 (➡️ JdbcTemplate의 update 메서드는 insert, update, delete 쿼리에 적용 가능)  

#### 📍 유저 조회 API 리팩토링
  ```java
  jdbcTemplate.query(sql, RowMapper 구현 익명클래스)
  ```
  * query를 사용하면, select 쿼리를 날릴 수 있음
  * 구현 익명클래스 안에는 ResultSet에 getType("필드이름")을 사용해 실제 값을 가져올 수 있음
    * 익명클래스는 람다식을 이용하면 더 간단하게 표현 가능 !

### ✔️ 유저 업데이트 API, 삭제 API 개발과 테스트
#### 📍 도서관 사용자 이름 변경 
  #### 👤 유저 업데이트 API
  * HTTP Method : `PUT`
  * HTTP Path : `/user`
  * HTTP Body (JSON)
  
    ```text
      {
        "id": Long,
        "name": String
      }
    ```
    
  * 결과 반환 X
    * `200 OK` 상태 코드
   
  #### 로직
   ##### 1️⃣ API 호출
   ##### 2️⃣ UPDATE 쿼리를 통해 jdbcTemplate의 update 메서드에 넘겨줌

   ```java
      @RestController
      public class UserController {
        @PutMapping("/user")
        public void updateUser(@RequestBody UserUpdateRequest request) {
          String sql = "UPDATE user SET name = ? WHERE id = ?";
          jdbcTemplate.update(sql, request.getName(), request.getId());
      }
   ```

#### 📍 도서관 사용자 삭제 
  #### 👤 유저 삭제 API
  * HTTP Method : `DELETE`
  * HTTP Path : `/user`
  * 쿼리
    * 문자열 name (삭제할 사용자 이름) 
  * 결과 반환 X
    * `200 OK` 상태 코드
   
  #### 로직
   ##### 1️⃣ API 호출
   ##### 2️⃣ DELETE 쿼리를 통해 jdbcTemplate의 update 메서드에 넘겨줌

   ```java
      @RestController
      public class UserController {
        @DeleteMapping("/user")
        public void deleteUser(@RequestParam String name) {
          String sql = "DELETE FROM user WHERE name = ?";
          jdbcTemplate.update(sql, name);
      }
   ```

### ✔️ 유저 업데이트 API, 삭제 API 예외 처리하기
<p>존재하지 않는 유저를 업데이트나 삭제하려고 할 때, 예외가 발생하도록 수정해보자.</p>
  
   ```java
     String readSql = "SELECT * FROM user WHERE id = ?";
     boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, request.getId()).isEmpty();
     if (isUserNotExist) {
       throw new IllegalArgumentException();
     }
   ```

  #### 로직
   ##### 1️⃣ id를 기준으로 유저가 존재하는지 확인하는 sql을 날려 DB 데이터 유무 체크 
   ##### 2️⃣ 있으면 다음 로직 수행, 없으면 예외 발생
   
### 📚 Section 2 학습 내용
#### 1️⃣ 데이터베이스를 통해 서버 재시작하면 데이터가 사라지는 문제를 해결할 수 있었다.
#### 2️⃣ SQL을 이용하여 MySQL 데이터베이스를 조작할 수 있다.
#### 3️⃣ 스프링 서버를 이용해 데이터베이스에 접근하고 데이터를 저장, 조회, 업데이트, 삭제할 수 있다.
#### 4️⃣ API 예외 상황을 이해하고 예외를 처리할 수 있다.

#### ⚠️ 우리가 개발한 API의 문제점
<p>한 클래스인 Controller가 많은 역할을 담당하며, 여러 비즈니스 로직이 통합되어 있다.</p>
<p>추가로 구현해야 할 요구사항이 늘어날수록 코드 수정은 복잡해질 것이다. 따라서 이런 문제를 어떻게 해결할 수 있을지 고민해보자.</p>
</details>


<details>
  
**<summary> `Section 3) 역할의 분리와 스프링 컨테이너` </summary>**
### ✔️ Clean Code의 중요성
<p>코드란, 요구사항을 표현하는 언어이다. 개발자의 중요한 업무 중 하나는 요구사항 구현을 위해 코드를 읽고 작성한다는 것이다.</p>
<p>여기서 핵심은 읽는다는 것이다 ! 다른 사람이 작성한 코드를 읽는 경우가 많고, 내가 오래 전에 작성한 기억나지 않는 코드를 읽을 때도 있다.</p>
<p>🌟 따라서 누구나 쉽게 코드를 읽고 이해할 수 있도록 클린 코드 작성은 중요하다 🌟</p>

#### 📍 Clean Code
<p>클린 코드 가이드 라인을 살펴보자.</p>

* 함수는 최대한 작게 만들고 한 가지 일만 수행하는 것이 좋다.
* 클래스는 작아야 하며 하나의 책임만을 가져야 한다.

<p>지금까지 작성한 Controller 클래스는 3가지 역할을 담당하고 있다.</p>

    1. API 진입 지점
    2. 예외 처리
    3. DB 통신

<p>♻️ Controller를 3단 분리 해보자</p>

### ✔️ Controller 3단 분리하기
#### 📍 Layered Architecture
<p>Controller, Service, Repository 각 클래스가 각자의 역할을 가지고 겹겹이 쌓인 구조</p>
  
  ##### Controller
  * 클라이언트의 요청 및 응답 처리
  * Service에게 요청에 대한 처리 전담
  
  ##### Service
  * 사용자 요구사항 처리
  * DB 정보 필요시, Repository에게 전담
  * Controller와 Repository 사이를 연결하는 역할
  
  ##### Repository
  * DB 관리 (연결, 해제, 자원 관리)
  * DB CRUD (Create, Read, Update, Delete) 작업 처리

### ✔️ UserController와 스프링 컨테이너
#### 📍 UserController
이전에 작성한 `UserController`를 살펴보면, 의아한 부분이 존재한다.

   ```java
    @RestController
    public class UserController {

     private final UserService userService;

     public UserController(JdbcTemplate jdbcTemplate) {
       this.userService = new UserService(jdbcTemplate);
     }

    }
   ```

  <p> 🙋🏻 현재 UserController에 존재하는 메서드를 API의 진입 지점으로 사용하고 있다. 상식적으로 클래스 안에 있는 함수를 사용하기 위해서 객체화 (인스턴스화)가 필요하다 !</p> 
  
    🤔 질문 1. UserController를 현재 객체화 하지 않고 있는데, 어떻게 API의 진입 지점으로 사용하는 것일까?

  
  <p> 🙋🏻 UserController의 생성자는 JdbcTemplate을 의존하고 있다. 하지만 우리는 JdbcTemplate에 대해 처리한 적이 없다 !</p>
  
     🤔 질문 2. UserController는 어떻게 JdbcTemplate을 가져오는 걸까?

#### 📍 `@RestController`  
<p>이 애노테이션은 UserController 클래스를 API의 진입 지점으로 만들어 줄 뿐만 아니라, 스프링 빈으로 등록 시켜준다. </p>

#### 🫛 스프링 빈
<p>우리가 스프링 부트로 만든 프로젝트를 실행하면, 서버가 동작하게 된다. 그러면 서버 내부에 거대한 컨테이너를 만들어준다. 그리고 컨테이너 안에는 클래스가 들어가게 되는데, 이 클래스를 스프링 빈이라고 부른다 !</p> 

<p>클래스가 들어갈 때, 이 빈을 식별할 수 있는 이름이나 타입과 함께 다양한 정보들을 함께 저장한다. 이때 인스턴스화도 함께 이루어지게 된다.</p>

<p>JdbcTemplate 역시 스프링 빈으로 등록되어 있기 때문에 스프링 컨테이너 내부에 존재하게 된다.</p>

#### 따라서 스프링 컨테이너는 `UserController`를 인스턴스화 할 때, `JdbcTemplate`을 컨테이너 내부에서 찾아서 가져올 수 있었던 것이다 ! 

     🤔 그럼 누가 JdbcTemplate을 스프링 빈으로 등록해준걸까?

<p>바로 build.gradle의 spring-boot-starter-data-jpa 의존성이 JdbcTemplate을 스프링 빈으로 미리 등록해준 것이다. </p>

   ```gradle
    dependencies {
      implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    }
   ```

<p>위의 내용을 정리해보자 ! 우리가 서버를 시작하게 되면 다음과 같은 일이 순차적으로 일어난다.</p>

    1. 스프링 컨테이너 시작
    2. 기본적으로 많은 스프링 빈이 컨테이너에 등록됨
    3. 개발자가 직접 설정해준 스프링 빈 등록
    4. 필요한 의존성 자동 설정

#### 🫛 Repository와 Service 스프링 빈 등록하기
<p>Repository와 Service를 스프링 빈으로 등록하는 방법은 아주 간단하다 !</p>

##### Repository 클래스는 `@Repository` 애노테이션을 클래스 위에 붙여주고, Service 클래스는 `@Service` 애노테이션을 클래스 위에 붙여주기만 하면 빈으로 등록이 된다. 

##### 그럼 이제 `Controller` 입장에서도 `Service`가 스프링 빈이니 굳이 직접 `new` 연산자를 통해 인스턴스화 해줄 필요가 없다 ! 컨테이너가 알아서 처리해줄 것이다.

##### 또한 `Repository`가 `JdbcTemplate`을 직접 가지고 있기 때문에 `Controller`는 `JdbcTemplate`를 가지고 있을 필요가 없어지게 된다. 그럼 `Controller`는 아래와 같이 변경될 것이다.

#### 📍 UserController
   ```java
    @RestController
    public class UserController {

     private final UserService userService;

     public UserController(UserService userService) {
       this.userService = userService;
     }

    }
   ```

#### `Controller` - `Service` - `Repository` 클래스가 스프링 빈으로 등록되는 과정을 정리해보자.
<p>스프링 서버가 시작되면,</p>

    1. 의존성에 의해 빈으로 등록된 JdbcTemplate이 스프링 컨테이너로 들어간다.
    2. JdbcTemplate의 의존성을 가진 UserRepository가 빈으로 등록된다.
    3. UserRepository를 의존하는 UserService가 빈으로 등록된다.
    4. UserService를 의존하는 UserController가 빈으로 등록된다.

#### 🤔 코드가 깔끔해진 것 같긴 한데 ... 스프링 컨테이너를 사용하는 이유에 대해 자세히 알아보자 !

### ✔️ 스프링 컨테이너
<p>아래와 같은 요구사항이 있다고 하자.</p>

> 책 이름을 메모리에 저장하는 API를 구현하라. Controller만 스프링 빈으로 등록하고 Service와 Repository는 스프링 빈이 아니어야 한다.

<p>우리는 먼저 Book 객체를 만들고 BookController, BookService, BookMemoryRepository를 만들 것이다. 그리고 BookMemoryRepository를 의존하는 BookService는 아래와 같이 객체를 생성할 것이다.</p>

#### 📍 BookService
   ```java
    public class BookService {
      private final BookMemoryRepository bookRepository = new BookMemoryRepository();
    }
   ```

<p>이제 Memory가 아닌 MySQL과 같은 DB를 사용하기로 요구사항이 변경되었다고 가정하자. 그리고 JdbcTemplate은 Repository가 바로 설정할 수 있다고 하자. 그럼 아래와 같은 일이 일어날 것이다.</p>

    1. BookMemoryRepository 대신 BookMySqlRepository가 새로 생길 것이다.
    2. Repository가 변경됨에 따라 BookService도 변경될 것이다.

#### 🤔 Repository의 역할만 변경하고 싶은데 Service 변경을 최소화할 수 있는 방법은 없을까?
  
  #### 📍 Java의 인터페이스
  <p>인터페이스를 도입하게 되면 코드는 아래와 같이 변경된다.</p>

  ##### 📍 BookService
  ```java
    public class BookService {
      private final BookRepository bookRepository = new BookMemoryRepository();
    }
   ```

  ##### 📍 BookRepository
  ```java
    public interface BookRepository {
      public void save(String bookName);
    }
   ```

  ##### 📍 BookMemoryRepository
  ```java
    public class BookMemoryRepository implements BookRepository {
      private final List<String> books = new ArrayList();

      @Override
      public void save(String bookName) {
        books.add(bookName);
      }

    }
   ```  

  ##### 📍 BookMySqlRepository
  ```java
    public class BookMySqlRepository implements BookRepository {
      private final List<String> books = new ArrayList();

      @Override
      public void save(String bookName) {
        // jdbcTemplate.....
      }

    }
   ```

<p>인터페이스의 도입으로 Service 코드 변경을 최소화 하였다 !</p>

##### 만약 Repository를 쓰는 Service 코드가 수백 개 클래스에 있다면, Repository 변경시 Service 코드를 하나하나 수정하는 것은 여전히 어려울 것이다 😢

#### 🤔 그렇다면 Repository를 변경하더라도 Service를 완전히 변경하지 않는 방법은 없을까?
<p>➡️ 이 고민에 대한 해결책이 바로 스프링 컨테이너이다 !</p>

#### 📍 제어의 역전(IoC, Inversion of Control)

> 스프링 컨테이너가 Service 대신 Repository를 인스턴스화 해주고, 그때 그때 알아서 어떤 Repository 클래스를 사용할지 결정해주는 방식

#### 📍 의존성 주입(DI, Dependency Injection)

> * 스프링 컨테이너가 Repository 클래스를 선택해서 Service에 넣어주는 과정
> * `@Primary` 애노테이션을 이용해 우선권 제어 가능

### ✔️ 스프링 컨테이너를 다루는 방법
<p>스프링 빈으로 등록하는 또다른 방법과 스프링 빈을 주입받는 방법을 살펴보자.</p>

#### 🫛 스프링 빈으로 등록하기
  ##### 📍`@Configuration`, `@Bean`
  > * `@Configuration` : 클래스에 붙여주는 애노테이션,  `@Bean`과 함께 사용
  > * `@Bean` : 메서드에 붙여주는 애노테이션, 해당 메서드에서 반환되는 객체를 스프링 빈으로 등록, 스프링 컨테이너가 필요한 의존성 주입 가능

  #### 🤔 우리는 앞서 `@Service`, `@Repository` 애노테이션을 붙여 스프링 빈으로 등록했는데 이 애노테이션은 언제 사용하는 걸까? `@Configuration` + `@Bean` 애노테이션은 또 언제 사용하는 걸까?
  
  <p>정리하면 아래와 같다.</p>
  
  > * `@Service`, `@Repository` : 개발자가 직접 만든 클래스를 스프링 빈으로 등록할 때
  > * `@Configuration` + `@Bean` : 외부 라이브러리나 프레임워크에 만들어져 있는 클래스를 스프링 빈으로 등록할 때
  
  ##### 📍 `@Component`
  > * 주어진 클래스를 컴포넌트로 간주
  > * 스프링 서버 시작할 때 컴포넌트 자동 감지
  > * Controller, Service, Repository가 모두 아니고 개발자가 직접 작성한 클래스를 스프링 빈으로 등록할 때 사용 
  > * `@RestController` / `@Service` / `@Repository` / `@Configuration` 모두 `@Component` 애노테이션을 가지고 있어 지금까지 스프링 서버 실행시 애노테이션이 자동 감지 되었던 것이다 😲

#### 🫛 스프링 빈 주입 받기
  <p>빈을 주입받는 방법은 3가지가 존재한다.</p>
  
  ##### 1️⃣ 생성자를 이용한 주입 방법 (권장)
  ##### 2️⃣ setter와 `@Autowired`
  ```java
    private JdbcTemplate jdbcTemplate;

    @Autowired // 스프링 컨테이너에 있는 스프링 빈을 찾아 setter에 넣어주는 애노테이션
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
      this.jdbcTemplate = jdbcTemplate;
    }
  ```
  
  ##### 3️⃣ 필드에 직접 `@Autowired` 사용
  ```java
    @Autowired
    private JdbcTemplate jdbcTemplate;
  ```

  ##### 📍 `@Qualifier`
  > * `@Primary` 애노테이션이 없는 상황에서 주입 받는 쪽이 특정 스프링 빈을 선택할 수 있게 해주는 애노테이션
  > * 스프링 빈을 사용하는 쪽과 스프링 빈을 등록하는 쪽 모두 사용 가능 ➡️ `@Qualifier` 애노테이션에 적어준 값이 같은 것끼리 연결

  #### 🤔 그렇다면 `@Primary`와 `@Qualifier` 애노테이션 중 누가 우선순위가 높을까?
  > 스프링 빈을 사용하는 쪽에서 직접 적어준 `@Qualifier`가 이긴다 !

### 📚 Section 3 학습 내용
#### 1️⃣ 클린코드의 중요성에 대해 이해하고, 기존 Controller 코드를 Layered Architecture로 분리했다.
#### 2️⃣ 스프링 컨테이너와 스프링 빈이 무엇인지 알아보고, 어떤 애노테이션을 통해 주입 받고 빈으로 등록할 수 있는지 학습하였다.
</details>


<details>
  
**<summary> `Section 4) 생애 최초 JPA 사용하기` </summary>**
### ✔️ 문자열 SQL 직접 사용의 한계
  <p>지금까지 우리는 데이터베이스에 접근하기 위해 Repository에 직접 SQL을 작성하였다. 하지만 이렇게 문자열로 작성하면 다음과 같은 문제가 발생할 수 있다.</p>

  ##### 1️⃣ 문자열을 직접 작성하기 때문에 오타가 날 수 있으며, 실수를 인지하는 시점이 느리다.
  ##### 2️⃣ 특정 데이터베이스에 종속적이기 때문에 데이터베이스 변경이 어려울 수 있다.
  ##### 3️⃣ 보통 테이블당 기본적으로 CRUD 쿼리를 작성하게 되는데, 단순 반복 작업이 많아지게 된다.
  ##### 4️⃣ 데이터베이스의 테이블과 객체는 패러다임이 다르다. (예 : 연관관계, 상속)

  <p>이런 어려움 속에서 등장한 것이 바로 JPA이다 !</p>

  #### 📍 JPA (Java Persistence API)
  > * 자바 진영의 ORM (Object-Relational Mapping) 기술 표준
  > * 객체와 관계형 데이터베이스의 테이블을 짝지어 데이터를 영구적으로 저장할 수 있도록 정한 Java 진영의 규칙
  > * Hibernate : JPA를 구현한 구현체, 내부적으로 JDBC 사용

### ✔️ 테이블에 대응되는 Entity Class 만들기
  <p>이제 User 테이블과 User 클래스를 매핑시켜보자 !</p>
  
  ##### 1️⃣ User 객체에 `@Entity` 애노테이션 붙이기
  > 🙋🏻 Entity란?
  > * 저장되고 관리되어야 하는 데이터
  > * 서버가 동작할 때 User 객체와 User 테이블을 같은 것으로 간주

  ##### 2️⃣ User 테이블에만 존재하는 id를 User 객체에 추가하기
  <p>현재 User 테이블은 id가 primary key이고, auto_increment가 적용되어 있다. 이 부분을 자바 코드에 적용하기위해 아래와 같은 애노테이션을 User 객체에 추가하자.</p> 
  
  > `@Id` : 이 필드를 primary key로 간주
  
  > `@GeneratedValue`
  > * primary key는 데이터베이스에서 자동으로 생성해주기 때문에 필요한 애노테이션
  > * 데이터베이스마다 다른 자동 생성 전략 (MySQL의 auto_increment는 IDENTITY 전략과 매칭 

  ##### ⚠️ JPA에 의해 테이블과 매핑된 객체는 기본 생성자 반드시 필요

  ##### 3️⃣ `@Column` 애노테이션 추가
  >* nullable : 필드에 null이 들어갈 수 있는지 여부
  >* length : 길이 제한
  >* name : 데이터베이스에서의 Column 이름 설정 (➡️ 필드 이름과 동일할 경우 생략 가능)
  
  #### 📍 User 객체
  ```java
    @Entity
    public class User {
        
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id = null;

      @Column(nullable = false, length = 20, name = "name")
      private String name;
      private Integer age;

      protected User() {}
      ...
    }
   ```  

  ##### 4️⃣ `application.yml`에 JPA 설정하기
  ```yml
    spring:
     jpa:
      hibernate:
        ddl-auto: none
      properties:
        hibernate:
          show_sql: true
          format_sql: true
          dialect: org.hibernate.dialect.MySQLDialect
   ```  
    
  * **`ddl-auto`** : 스프링이 시작할 때 DB에 있는 테이블을 어떻게 처리할 것인지에 대한 옵션
    * create : 기존 테이블이 있다면, 삭제 후 다시 생성
    * create-drop : 스프링이 종료될 때 테이블 삭제
    * update : 객체와 테이블이 다른 부분만 변경
    * validate : 객체와 테이블이 동일한지 확인
    * none : 아무런 조치 X

  * **`show_sql`** : JPA를 사용해 DB에 SQL을 날릴 때, SQL을 보여줄지 결정
  * **`format_sql`** : JPA를 사용해 DB에 SQL을 날릴 때, SQL 포맷팅 여부 결정
  * **`dialect`** : JPA가 알아서 DB끼리 다른 SQL 수정

### ✔️ Spring Data JPA를 이용해 자동으로 쿼리 날리기
  <p>이제 직접 SQL을 작성하지 않고 JPA를 이용하여 유저 생성/조회/업데이트 기능을 리팩토링해보자 !</p> 

  ##### 1️⃣ domain 계층에 JpaRepository를 상속 받는 UserRepository 인터페이스 생성
  ```java
    public interface UserRepository extends JpaRepository<User, Long> {
    }
   ``` 

  ##### 2️⃣ 서비스 코드에서 해당 UserRepository로 의존성 주입
  ##### 📍 UserService (유저 생성)
  ```java
    public void saveUser(UserCreateRequest request) {
      userRepository.save(new User(request.getName(), request.getAge()));
    }
  ``` 

  > * JpaRepository를 상속 받은 UserRepository에 내장되어 있는 save 메서드 사용
  > * User 객체 생성 후 실제 DB에 저장

  ##### 📍 UserService (유저 조회)
  ```java
    public List<UserResponse> getUsers() {
      return userRepository.findAll().stream()
              .map(user -> new UserResponse(user.getId(), user.getName(), user.getAge()))
              .collect(Collectors.toList());
    }
  ``` 

  > * JpaRepository를 상속 받은 UserRepository에 내장되어 있는 findAll 메서드 사용
  > * 모든 유저 데이터를 조회하는 SQL이 날라가고 List<User> 반환
  > * List<User>를 UserResponse로 변경하여 전달

  ##### 📍 UserService (유저 업데이트)
  ```java
    public void updateUser(UserUpdateRequest request) {
      User user = userRepository.findById(request.getId()).orElseThrow(IllegalArgumentException::new);
      user.updateName(request.getName());
    
      userRepository.save(user);
    }
  ``` 

  > * JpaRepository를 상속 받은 UserRepository에 내장되어 있는 findById 기능 사용
  > * id로 1개의 User 데이터를 가져오는 SQL이 날라가고 User 반환
  > * orElseThrow를 사용하여 User가 비어있는 경우 예외 발생
  > * User가 있다면 updateName이라는 메서드가 실행되고, save를 통해 업데이트 내용 저장

  #### 🤔 그럼 이렇게 메서드를 통해 쿼리 작성 없이 쿼리가 날라갈 수 있는 이유는 무엇일까?
  #### ➡️ JPA를 이용하는 Spring Data JPA가 자동으로 처리해준 것 !
  > Spring Data JPA : 복잡한 JPA 코드를 스프링과 함께 쉽게 사용할 수 있도록 도와주는 라이브러리

  <p>즉, 전체적인 구조를 살펴보면 Spring Data JPA가 JPA라는 규칙을 사용하는데 이 규칙은 Hibernate가 구현했고, Hibernate는 구현할 때 JDBC를 사용한다 !</p>

### ✔️ Spring Data JPA를 이용해 다양한 쿼리 작성하기
  <p>이제 삭제 기능을 Spring Data JPA로 변경해보자. 삭제는 요청으로 들어온 유저의 이름이 존재하는지 확인하고, 유저가 존재한다면 delete 쿼리를 날리고 없으면 예외를 날리게 된다.</p>
  
  ##### 📍 UserService (유저 삭제)
  ```java
    public void deleteUser(String name) {
      User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
    
      userRepository.delete(user);
    }
  ```

  ##### 📍 UserRepository
  ```java
    public interface UserRepository extends JpaRepository<User, Long> {

      Optional<User> findByName(String name);
    }
  ```  

  > findByName
  > * 기본으로 제공하는 메서드가 아닌 직접 인터페이스에 정의한 메서드 (함수 이름만 작성하면, 알아서 SQL 조립)
  > * 이름을 기준으로 User 데이터를 조회하여 User 객체 반환 (User 정보가 없으면, null 반환)

  > delete
  > * 기본으로 제공해주는 메서드

  #### 📍 Spring Data JPA의 추가적인 쿼리 작성법
  <p>findByName처럼 우리가 일정한 규칙에 맞게 인터페이스에 정의하면 쿼리들을 제공해준다. 그 규칙들을 살펴보자. By 앞과 뒤에 어떤 단어가 들어가는지에 따라 쿼리를 마음껏 만들어낼 수 있다.</p>
  <p>By 앞에는 다음과 같은 구절이 들어갈 수 있다.</p>
  
  > * find : 1건을 가져옴, 객체나 Optional<타입> 반환
  > * findAll : 쿼리의 결과물이 N개인 경우 사용, List<타입> 반환
  > * exists : 쿼리 결과가 존재하는지 확인, boolean 타입 반환
  > * count : SQL의 결과 개수를 셈, long 타입 반환 

  <p>By 뒤에는 필드 이름이 들어간다. 조건이 여러개일 경우 And 혹은 Or로 조합될 수 있다.</p>
  
  > * GreaterThan : 초과
  > * GreaterThanEqual : 이상
  > * LessThan : 미만
  > * LessThanEqual : 이하
  > * Between : 사이에
  > * StartsWith : ~로 시작하는
  > * EndsWith : ~로 끝나는

### ✔️ 트랜잭션
  #### 🤔 트랜잭션이란 무엇일까? 한가지 예시를 통해 살펴보자.
  <p>인터넷 쇼핑몰 사이트를 운영한다고 가정하자. 물건을 주문하면 Service 계층에서는 다음과 같은 일을 처리한다.</p>
  
  > 결제가 완료되면,
  > 1. 주문 기록을 저장한다.
  > 2. 포인트를 올려준다.  
  > 3. 구매 기록을 저장한다.

  <p>물건을 주문하는 과정에서 주문 기록 저장과 포인트 저장까지는 성공했는데, 구매 기록을 저장하는 과정에서 에러가 발생해 저장이 성공적으로 이루어지지 않았다면 어떻게 될까?</p>
  <p>이런 문제를 해결하기 위해 '트랜잭션'이라는 개념이 등장한다 !</p>

  > 트랜잭션
  > * 여러 SQL을 사용해야 할 때 한 번에 성공시키거나, 하나라도 실패하면 모두 실패시키는 기능 
  > * 쪼갤 수 없는 업무의 최소 단위
  > * 모든 과정이 정상적으로 수행되었다면 `commit`, 하나라도 실패했다면 `rollback`

### ✔️ 트랜잭션 적용과 영속성 컨텍스트
  <p>Spring Data JPA에서 트랜잭션 적용은 대상 메서드에 @Transactional 애노테이션을 붙여주면 된다. 데이터 변경 없이 조회 기능만 있을 때 readOnly 옵션을 줄 수 있다.</p>
  
  > `readOnly` : 데이터 변경을 위한 불필요한 기능이 빠지게 되어 약간의 성능적 이점이 있음
  
  #### ⚠️ `@Transactional` 애노테이션은 Unchecked Exception에 대해서만 rollback이 일어난다 !

  #### 📍 영속성 컨텍스트
  > * 테이블과 매핑된 Entity 객체를 관리 및 보관
  > * 스프링에서는 트랜잭션을 사용하면 영속성 컨텍스트가 생겨나고, 트랜잭션이 종료되면 영속성 컨텍스트가 종료됨

  #### 📍 영속성 컨텍스트의 특징
  ##### 1️⃣ 변경 감지 (Dirty Check)
  > 영속성 컨텍스트 안에서 불러와진 Entity는 명시적으로 save를 해주지 않더라도 **알아서 변경을 감지**하여 저장할 수 있게 해줌
  
  ##### 2️⃣ 쓰기 지연
  > 트랜잭션이 commit 되는 시점에 SQL을 모아서 한 번에 처리하여 통신 횟수가 줄어듦
  
  ##### 3️⃣ 1차 캐싱
  > * Id를 기준으로 Entity를 기억
  > * 동일한 쿼리를 여러 번 실행할 때 최초 1회만 쿼리가 나가고 이후 영속성 컨텍스트가 보관하고 있는 데이터 활용
  > * 캐싱된 객체는 완전 동일함 

### 📚 Section 4 학습 내용
  #### 1️⃣ 문자열 SQL 직접 사용의 한계를 이해하고 해결책인 JPA, Hibernate, Spring Data JPA가 무엇인지 알게 되었다.
  
  #### 2️⃣ Spring Data JPA를 이용해 데이터를 생성, 조회, 수정, 삭제할 수 있다.

  #### 3️⃣ 트랜잭션의 필요성에 대해 이해하고, 스프링에서 트랜잭션을 제어하는 방법을 학습하였다.

  #### 4️⃣ 영속성 컨텍스트와 트랜잭션의 관계를 이해하고, 영속성 컨텍스트의 특징을 알아 보았다.
</details>

<details>
  
**<summary> `Section 5) 책 요구사항 구현하기` </summary>**
<p>지금까지 학습한 내용을 바탕으로 책을 등록하고 대출, 반납할 수 있는 기능을 만들어보자.</p>

### ✔️ 책 생성 API 개발하기
  #### 📍 API 스펙
  * HTTP Method : `POST`
  * HTTP Path : `/book`
  * HTTP Body (JSON)
      
      ```text
      {
        “name": String // 책 이름
      }
      ```
  * 결과 반환 X
    * `200 OK` 상태 코드

  #### 📍 개발하기
  ##### 1️⃣ 테이블 설계

  ```sql
  create table book(
    id bigint auto_increment,
    name varchar(255),
    primary key (id)
  );
```

  > **id**
  > * 모든 테이블에는 자동 증가하는 아이디 필요
  
  > **책 이름 최대 255자**
  > * JPA 사용시 `@Column` 애노테이션을 쓰게 되는데, 길이 제한 기본값이 255자 ➡️ `@Column` 생략 가능
  > * 문자열 필드는 최적화를 해야 하는 경우가 아니라면, 여유롭게 설정하는 것이 좋음 ➡️ 테이블의 스키마(DDL)를 바꾸는 일이 생각보다 어려울 수 있음
  
  ##### 2️⃣ JPA 객체 생성
  > book 테이블을 기반으로 Book 객체 만들기 (➡️ domain 객체)
  
  ##### 3️⃣ BookRepository / DTO / Controller / Service 생성

### ✔️ 책 대출 API 개발하기
  #### 📍 요구사항
  * 사용자는 책을 빌릴 수 있다.
    * 다른 사람이 대여 중인 책은 빌릴 수 없다.   

  #### 📍 API 스펙
  * HTTP Method : `POST`
  * HTTP Path : `/book/loan`
  * HTTP Body (JSON)
      
      ```text
      {
        “username": String
        “bookname": String      
      }
      ```
  * 결과 반환 X
    * `200 OK` 상태 코드

  #### 📍 개발하기
  ##### 1️⃣ 테이블 설계
  <p>어떤 유저가 어떤 책을 빌리고 반납했는지 확인할 수 있는 테이블 추가적으로 설계하기</p>

  ```sql
  create table user_loan_history(
    id bigint auto_increment,
    user_id bigint,
    book_name varchar(255),
    is_return tinyint(1),
    primary key (id)
  );
  ```

  > **id**
  > * 모든 테이블에는 자동 증가하는 아이디 필요

  > **user_id**
  > * 어떤 유저가 빌렸는지 알 수 있도록 유저 id 필드 생성

  > **book_name**
  > * 유저가 어떤 책을 빌렸는지 알 수 있도록 책 이름 필드 생성

  > **is_return**
  > * 유저가 빌린 책이 대출 중인지, 반납 완료했는지 확인하는 필드
  > * 값이 0이면 대출 중, 값이 1이면 반납 완료
  
  
  ##### 2️⃣ JPA 객체 생성
  > * user_loan_history 테이블을 기반으로 UserLoanHistory 객체 만들기 (➡️ domain 객체)
  > * tinyint형을 쓰고 있는 is_return 필드를 boolean에 매핑하게 되면, DB에 0이 들어갈 경우 false, 1이 들어갈 경우 true로 생각함
  
  ##### 3️⃣ UserLoanHistoryRepository / DTO / Controller / Service 생성
    1. Controler에서 API를 받아 HTTP 파싱 후, POST /book/loan으로 연결
    2. HTTP Body에 들어있는 JSON을 BookLoanRequest로 변경해주고, 그 정보를 BookService로 넘겨줌
    3. Service 계층에서는 Transactional을 관리해주고, repository 호출
      
      a. 책 정보 가져오기
      b. 대출 기록 정보를 확인해서 대출 중인지 확인
      c. 대출 중이라면 예외를 발생시키고, 그렇지 않다면 유저 정보 가져오기
      d. 유저 정보와 책 정보를 기반으로 대출 기록 생성

### ✔️ 책 반납 API 개발하기
  #### 📍 요구사항
  * 사용자는 책을 반납할 수 있다.

  #### 📍 API 스펙
  * HTTP Method : ` PUT`
  * HTTP Path : `/book/return`
  * HTTP Body (JSON)
      
      ```text
      {
        “username": String
        “bookname": String      
      }
      ```
  * 결과 반환 X
    * `200 OK` 상태 코드

  #### 📍 개발하기
  ##### 1️⃣ JPA 객체 생성
  > 대출 기능과 스펙이 동일하더라도 DTO가 다르면, 둘 중 하나의 기능에 변화가 생겼을 때 한 부분에만 코드 추가가 가능하면서 다른 한 쪽에 영향을 미치지 않음 ➡️ 스펙이 동일하더라도 다른 DTO 객체를 만들자 !
  
  ##### 2️⃣ Controller 생성
  
  ##### 3️⃣ Service 생성
    1. Controler에서 API를 받아 HTTP 파싱 후, POST /book/return으로 연결
    2. HTTP Body에 들어있는 JSON을 BookReturnRequest로 변경해주고, 그 정보를 BookService로 넘겨줌
    3. Service 계층에서는 Transactional을 관리해주고, repository 호출
      
      a. userName을 가지고 유저를 찾아서 userId를 가져옴
      b. userId와 bookName을 통해 대출 기록 확인
      c. 대출 기록 반납 처리 (➡️ isReturn을 true로 변경)

### ✔️ 객체지향적으로 개발하기
  #### 📍 대출 및 반납 기능
  > **현재**
  > * 객체끼리 직접 협업하는 것이 아닌 중간에 Service를 거치는 절차지향적인 방법으로 구성되어 있음

  #### 📍 개선하기
  <p>기존 코드를 JPA 연관관계를 활용하여 객체지향적인 방식으로 변경해보자.</p>

  > **같은 도메인 내에 있는 객체끼리 협업하도록 만들어보자.**
  > * 대출 : User만 가져와서 바로 대출 처리
  > * 반납 : User만 가져와서 바로 반납 처리

  <p>🙋🏻 코드 개선에 앞서 객체지향적 설계의 핵심 개념인 JPA 연관관계에 대해 살펴보자 !</p>

### ✔️ JPA 연관관계
  > * 두 도메인 (객체, 테이블)이 서로 논리적인 의미를 갖고 양쪽을 참조하는 관계
  > * 객체 지향 프로그램에서 객체들이 서로 연관관계를 맺는 방법과 RDB에서 테이블들이 연관관계를 맺는 방법은 다름 ➡️ 이 간극을 채워주기 위한 기술이 ORM이며, ORM 기술의 Java 표준 명세가 JPA !

  #### ⚠️ 연관관계 매핑 시 고려사항
  #### 1. 방향
  > * 객체는 참조용 필드를 가지고 있는 객체만 연관된 객체를 조회할 수 있으므로 방향이 존재한다.
  > * 양방향 : 두 객체가 서로 참조하는 관계
  > * 단방향 : 한 객체에서 다른 객체만 참조하는 관계  
  
  #### 2. 다중성
  > 연관관계는 다음과 같은 다중성이 존재한다.
  > * 일대일(1:1, OneToOne)
  > * 일대다(1:N, OneToMany)
  > * 다대일(N:1, ManyToOne)
  > * 다대다(N:M, ManyToMany) : 구조가 복잡하고, 테이블이 직관적으로 매핑되지 않아 사용 권장 ✖️

  > 예) 일대다 혹은 다대일의 경우, N쪽에 @ManyToOne 애노테이션을 붙여주고 관계를 맺는 객체를 선언해준다. 1쪽도 마찬가지로 @OneToMany 애노테이션을 붙여주고 관계를 맺는 객체를 선언해준다.
  
  #### 3. 연관관계의 주인
  > * 연관관계 관리 포인트는 외래 키인데, 양방향 관계를 맺으면 객체 서로가 외래 키를 가질 수 있게 된다. 따라서 두 객체 중 하나를 외래 키로 관리해야 한다. 여기서 **외래 키를 관리하는 객체**를 연관관계의 주인이라고 한다.  
  > * 연관관계 주인만이 데이터를 등록, 변경할 수 있으며 주인이 아닌 객체는 읽기만 가능하다.
  > * 연관관계의 주인이 아닌 객체는 mappedBy 속성을 통해 주인에게 매여있음을 표시해주어야 한다.
  
  #### 📍 cascade 옵션 & orphanRemoval 옵션
  ##### ☑️ cascade
  * 한 객체가 저장되거나 삭제될 때, 연결되어 있는 객체도 함께 저장되거나 삭제되는 기능

  ##### ☑️ orphanRemoval
  * 연관관계가 끊어진 데이터 자동 제거

### ✔️ 책 대출/반납 기능 리팩토링과 지연 로딩 
  <p>JPA에 있는 연관관계 옵션을 사용하여 최대한 도메인들끼리 직접 협력할 수 있도록 리팩토링해보자.</p>
  
  #### 📍 도메인 계층에 비즈니스 로직이 들어갔다
  * **책 대출** : `UserLoanHistory`를 `Service`에서 만들어 저장하는 것이 아닌 `User` 객체에서 책 대출 함수를 호출하고, 그 함수 안에서 List 형태의 `UserLoanHistory` 생성
  * **책 반납** : `User` 객체에서 책 이름을 받는 함수를 호출하고, List 형태의 `UserLoanHistory`에서 반납이 들어온 책 기록을 찾아 반납 처리

  ##### 😎 여기서 영속성 컨텍스트의 또다른 능력이 등장한다 !
  
  #### 📍 지연 로딩 (Lazy Loading)
  * 연결되어 있는 객체를 처음에 한번에 로딩하지 않고, 꼭 필요한 순간에 로딩
  * `@OneToMany`의 fetch 옵션 기본값인 LAZY (⬅️➡️ EAGER : 처음 데이터 로딩시 바로 가져옴)

  #### 🤔 연관관계를 사용하면 무엇이 좋을까?
  > 1. 각자의 역할에 집중 가능
  > 2. 코드 가독성 향상
  > 3. 테스트 코드 작성 용이  

  <p>지나친 연관관계 사용은</p>
  
  > 1. 성능상의 문제
  > 2. 도메인 간 복잡한 연결로 인해 시스템 파악의 어려움
  > 3. 하나의 수정이 다른 곳까지 영향을 주게 됨 

  #### 따라서 비즈니스 및 기술적 요구사항, 도메인 아키텍처 등 여러 부분을 고민해보고 사용하자 !

### 📚 Section 5 학습 내용
#### 1️⃣ 객체지향적 설계를 위해 연관관계를 이해하고, 연관관계의 다양한 옵션을 살펴보았다.
#### 2️⃣ JPA에서 연관관계를 매핑하는 방법에 대해 학습하고, 연관관계 사용 유무에 따른 차이점을 비교해보았다.
</details>


<details>
  
**<summary> `Section 6) 생애 최초 배포 준비하기` </summary>**

</details>


<details>
  
**<summary> `Section 7) 생애 최초 배포하기` </summary>**

</details>
