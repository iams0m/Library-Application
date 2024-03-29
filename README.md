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
* `@SpringBootApplication` : 스프링을 실행시키기 위해 필요한 다양한 설정들을 자동으로 해주는 어노테이션
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
    * 단일 타입으로 넣을 수도 있지만, request DTO를 생성하여 객체 넣기도 가능 (➡️ 이때 어노테이션 생략) 

#### 2️⃣ POST API
<p>HTTP Body 사용법 연습을 위해 곱셈 기능을 POST로 작성해보자. (원래 POST API는 어떤 값을 저장한다는 의미이기 때문에 적절한 방법은 아니다 😅)</p>
  
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

</details>


<details>
  
**<summary> `Section 3) 역할의 분리와 스프링 컨테이너` </summary>**

</details>


<details>
  
**<summary> `Section 4) 생애 최초 JPA 사용하기` </summary>**

</details>


<details>
  
**<summary> `Section 5) 책 요구사항 구현하기` </summary>**

</details>


<details>
  
**<summary> `Section 6) 생애 최초 배포 준비하기` </summary>**

</details>


<details>
  
**<summary> `Section 7) 생애 최초 배포하기` </summary>**

</details>
