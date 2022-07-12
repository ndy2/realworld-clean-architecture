# ![RealWorld Example App](logo.png)

## 소개

- `만들면서 배우는 클린 아키텍쳐`를 읽고 육각형 아키텍쳐를 연습하기 위해 진행한 프로젝트
  입니다. [노션 요약 링크](https://ndy-dev.notion.site/d737675bbc324c09ae6d1721754b2dcb)

## TODO

> 예외 처리, 도메인 로직 집중

## 신경써서 개발한 부분

### 네이밍 룰

- 익숙한 계층 아키텍쳐에 비해 더욱 다양한 상황이 등장했고 웹어댑터/컨트롤러, 유스케이스/서비스, 영속성어댑터/리포지토리 계층간의 클래스 네이밍에 일관성을 주기 위해 고민하였습니다.


- 웹어댑터/컨트롤러 DTO
  - `[비즈니스 와 관련된 동사]`  + `[도메인 이름]`   + `[Request/Response]`


- 서비스/유스케이스 DTO
    - `[비즈니스 와 관련된 동사]` + `[도메인 이름]` + `[Command/Result]`
- 서비스/쿼리 DTO
    - `[비즈니스 와 관련된 동사]` + `[도메인 이름]` + `[Query/Result]`


- 영속성 어댑터 Create,Update, Delete DTO
    - `[Insert/Update/DeleteXXX]` + `[도메인 이름]` + `[Command/Result]`
- 영속성 어댑터 Read DTO
    - `[FindXXX]` + `[도메인 이름]` + `[Query/Result]`

### 핵심 비즈니스 로직

- 아티클 조회시
    - 팔로우 중인 사용자의 아티클
    - 태그
    - 작가
    - 사용자의 페이보릿 선정 여부
- 등 다양한 옵션에 따라 필터링을 적용하는 로직이 꽤 복잡해서 `QueryDsl` 을 활용하여 풀어내었습니다.

### 의식적으로 지름길 이용하기

- `만들면서 배우는 클린 아키텍쳐`의 11장 에서 소개하는 지름길 중 `유스케이스 간 모델 공유하기`지름길의 유혹이 가장 크게 들어서 정말 비즈니스 적으로 연관이 있고 변경의 주기가 같다고 생각되는 경우의 쿼리
  응답에 대해서 모델을 공유하였습니다.
    - e.g.) `ArticleResult`, `FindArticleResult` ...

- 책에서 소개한 내용은 아니지만 제가 프로젝트를 진행하며 지름길이라고 느낀 부분은 영속성어댑터의 모델(JpaEntity)간 연관관계 매핑입니다. 최대한 id 를 사용해서 느슨한 결합을 유지하려고 하였지만 Join
  을 통한 처리가 불가피하다고 판단되는 경우에 대해서만 신중히 연관관계를 추가하였습니다.

### 육각형 아키텍처 준수

- 육각형 아키텍쳐의 장점 중 하나는 package-private 접근제어자를 통해 아키텍쳐를 해치는 설계를 컴파일 타임에 막을 수 있다는 점입니다. 사용할 수 있는 위치에서 적절하게 package-private
  접근제어자를 적용하였습니다.
- 육각형 아키텍쳐의 의존성 규칙을 잘 준수하는지 확인 하기 위해 `ArchUnit` 을 활용하였습니다.

### ISP - interface separation principal

- `인터페이스 분리 원칙`을 준수하여 프로그래밍 해보면서 막연하게 알고 있던 ISP의 의의를 직접 느낄 수 있었습니다.

## Getting started

```
./gradlew build
./gradlew bootRun
```

---

> ### SpringBoot codebase containing real world examples (CRUD, auth, advanced patterns, etc) that adheres to the [RealWorld](https://github.com/gothinkster/realworld) spec and API.

### [Demo](https://demo.realworld.io/)&nbsp;&nbsp;&nbsp;&nbsp;[RealWorld](https://github.com/gothinkster/realworld)

This codebase was created to demonstrate a fully fledged fullstack application built with **SpringBoot** including CRUD
operations, authentication, routing, pagination, and more.

We've gone to great lengths to adhere to the **SpringBoot** community style guides & best practices.

For more information on how to this works with other frontends/backends, head over to
the [RealWorld](https://github.com/gothinkster/realworld) repo.