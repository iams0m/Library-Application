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

</details>


<details>
  
**<summary> `Section 2) 생애 최초 Database 조작하기` </summary>**

</details>


<details>
  
**<summary> `Section 3) 역할의 분리와 스프링 컨테이너` </summary>**

</details>


<details>
  
**<summary> `Section 4) 생애 최초 JPA 사용하기` </summary>**

</details>


<details>
  
**<summary> `Section 5) 책 요구사항 구현하기` </summary>**
### 주요 기능
* 👤 `사용자`
  *  도서관 사용자를 등록할 수 있습니다.
  *  등록된 사용자 목록을 조회할 수 있습니다.
  *  사용자의 이름을 변경할 수 있습니다.
  *  등록된 사용자를 삭제할 수 있습니다.
 
* 📖 `도서`
  *  도서관에 책을 등록할 수 있습니다.
  *  사용자는 등록된 도서를 대출/반납할 수 있습니다.
</details>


<details>
  
**<summary> `Section 6) 생애 최초 배포 준비하기` </summary>**

</details>


<details>
  
**<summary> `Section 7) 생애 최초 배포하기` </summary>**

</details>
