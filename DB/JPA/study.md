# 1장 - JPA란

ORM - Object Relational Mapping

SQL을 직접 다룰 시 발생되는 문제점<br>
1. 진정한 의미의 계층분할이 어렵다.
   * SQL과 JDBC API를 데이터 접근 계층(DAO)로 숨기는데 성공했지만 논리적으로는 강하게 연결되어있다. 그래서 DAO메서드 뿐만 아니라 SQL도 같이 수정해야한다.
2. 엔티티를 신뢰할 수 없다. 
   * 엔티티들은 SQL에 의존함으로 Entity에 필드가 추가되어도 SQL이 바뀌지 않으면 값을 가져올 수 없다.
3. SQL에 의존적인 개발을 피하기 어렵다.
<br>
<br>
<br>

## 객체 vs 관계형 데이터베이스
RDBMS는 데이터 중심으로 구조화가 되어있다.
따라서 상속, 다형성, 추상과 같은 객체지향의 개념이 들어가 있지 않다.
즉 객체와 관계형 데이터베이스의 패러다임이 불일치 한다.

JPA에서는 같은 트랜젝션일 경우 조회된 객체는 동일성 비교로 비교 가능하다. <br>

### *Why?*
~~~~
JPA에서는 엔티티들을 메모리인 1차 캐시에 Map형태로 저장하고있다. 
여기서 key는 엔티티의 고유값(PK)를 말하며, value는 Entity인스턴스이다. 
그러므로 Entity Manager에서 반환하는 엔티티는 키 값으로 검색된 Entity인스턴스 임으로 
주소값이 같은 동일한 인스턴스일 수 밖에 없다. 
~~~~

<br>

> **동일성 비교** : 자바에서 ==비교를 말하며, 객체의 인스턴스의 주소값을 비교할 때 사용한다.<br>
> **동등성 비교** : 자바에서 equals 메서드를 사용하며, 인스턴스 주소값은 다를 수 있지만 객체 내부의 값을 비교할 때 사용한다.

<br><br><br>

# 2장 - EntityManagerFactory, EntityManager 개념정리

> **Grable**, **Maven** 은 빌드관리 도구로서, 프로젝트 빌드 관리 및 라이브러리 관리기능을 한다.

<br>

### EntityManagerFactory
- **EntityManagerFactory**는 JPA를 사용하기위한 공장이라고 생각하면된다.
- 동작방식은 xml 혹은 yml로 설정한 설정값을 토대로 JPA를 동작시키기 위한 기반을 다진다.
즉 JPA 구현체에 따라 필요하다면 DB 커넥션 풀도 생성함으로 초기 로딩하는 비용이 크다.
그래서 보통 프로그램 시작 시 한번만 생성하고 이를 공유해서 사용한다.(프로그램 종료 시 해제해야 한다.)

- EntityManagerFactory는 **Thread safe**하기 때문에 여러 Thread에서 접근하여도 **동시성 문제가 발생하지 않는다.**

<br>

### EntityManager
- **EntityManager**는 JPA의 대부분의 기능들을 제공한다.(CRUD)
- 내부적으로 DB와의 커넥션을 유지하고 있으므로 여러 Thread가 접근하여 사용하면 **동시성 문제가 발생**한다. 즉 **Thread safe하지 않다**.
- 생성한 EntityManager는 반드시 종료해야 한다.

<br>

## JPQL vs SQL
JPQL
- Entity객체를 대상으로 쿼리
- 대소문자를 명확히 구분한다.


SQL 
- DB테이블 대상으로 쿼리
- 관례상 대소문자를 구분하지 않는다.

<br><br><br>

# JPA가 제공하는 기능(영속성 컨텍스트, Entity 생명주기)

JPA는 크게 2가지 기능을 한다. <br>
1. Entity와 table 맵핑하는 설계 부분
2. 맵핑한 Entity를 어떻게 사용하는 부분

이번에는 **맵핑한 Entity를 어떻게 사용하는 부분**에 대해서 알아보려고 한다.
<br>
앞에서 EntityManagerFactory는 Thread safe하며, EntityManger는 DB와의 커넥션을 문제로 인해 Thread safe하지 않다고 했다. <br>
또한 EntityManagerFactory는 요청이 들어오면 매 요청마다 EntityManager를 생성하여 요청에 대응한다.<br>
생성된 EntityManager가 DB와의 Connection을 사용할때에는 **트랜젝션이 시작할 때** 이다. 
<br><br>

## 영속성 컨텍스트
- Entity를 영구 저장하는 환경
- EntityManager의 persist메서드를 사용하여 영속성 컨텍스트에 저장한다.
- 여러 EntityManger에서 하나의 영속성 컨텍스트에 접근 가능하다.

### 영속성 컨텍스트 특징
- 영속된 Entity는 반드시 PK인 식별자를 가지고 있다. Map에 저장되기위해선 Key값이 필요한데 이 키를 PK로 사용한다.
- persist메서드를 호출하면 영속성 컨텍스트에 저장된다고 하는데 그럼 실제 DB에는 언제 저장되는가?
  - 트랜젝션이 종료 시점에 commit메서드를 호출하고 이때 DB에 반영한다. 이를 JPA에서는 Flush라고 한다.
- 영속성 컨텍스트를 사용하는 이유
  - 1차 캐시기능
    - Entity를 조회 시 Map 형태로 영속성 컨텍스트에 저장되어 있다. 그래서 영속화된 Entity라면 메모리에서 바로 찾아와 리턴해줌으로 성능상 빠르다.
  - 동일성 보장
    - Map의 value값을 리턴함으로 동일성(==)을 보장한다.
    - `em.find() == em.find()`
  - 트랜잭션을 지원하는 쓰기지연(transactional write behind)
    - persist메서드를 호출하면 영속성 컨텍스트에서 관리하는 1차캐시에 Entity정보가 저장된다고 했다.
    - 이때 저장되는 부분이 하나 더 있다. 바로 **쓰기 지연 SQL 저장소** 이다.
    - 즉 persist, remove를 호출하면 쓰기 지연 SQL 저장소에 insert, update, delete 메서드가 적재된다.
    - 이후 적재된 쿼리들은 트래잭션 종료 시점에 commit메서드가 호출되어 DB로 Flush된다. 
    - 이 기능을 통해 여러 쿼리를 한번에 묶어서 DB에 전달하여 성능향상을 도모할 수 있다.
  - 변경 감지(Dirty Checking)
    - JPA에는 Update라는 메서드는 따로 존재하지 않는다.
    - 다만 영속화된 Entity를 수정하기만 하면 이를 감지하고 쓰기 지연 SQL 저장소에 update쿼리를 적재한다.
    - 변경되었다는 것을 알려면 기준이 필요하다. JPA는 이를 **스냅샷**이라는 용어를 사용하며, 스냅샷은 Entity의 최소상태를 저장해 둔다. 해서 스냅샷과 현재상태를 비교하여 처리하는 것.
    - 호출순서를 정리하자면 
      1. 트랜잭션 commit 호출
      2. 영속성 컨텍스트 내부에서 Flush메서드 호출
      3. 스냅샷과 Entity를 비교하여 변경된 Entity 찾기
      4. 변경된 Entity가 있으면 쓰기지연 SQL 저장소에 update쿼리 적재
      5. 쓰기지연 SQL 저장소의 모든 내용을 DB로 전송
      6. DB에 트랜잭션 commit
    - JPA는 업데이트시 **모들 칼럼을 update**한다. 그래서 @DynamicUpdate, @DynamicInsert를 통해 해당 칼럼만 update할 수 있다.
  - 지연 로딩
<br><br>

## Entity 생명주기

- 비영속성(new / transient) : 순수 객체 상태, 아직 persist메서드 호출 전, DB와 관련없는 상태
- 영속(managed) : 영속성 컨텍스트가 관리하는 Entity를 말한다. persist메서드 호출 이후
- 준영속(detached) : 관리된 영속성 컨텍스트에서 빠져나온 상태( em.detached(), em.clear(), em.close() )
- 삭제(removed) : Entity를 DB와 영속성 컨텍스트에서 삭제한다.(em.remove() )
<br><br>

## Flush
영속성 컨텍스트의 모든 변경내용을 DB에 반영하는 메서드이다.
<br>

### Flush 호출방법
1. EntityManager의 flush()메서드를 직접 호출한다.
2. 개발자의 실수를 방지하기위해 트랜잭션 커밋 시 JPA가 자동으로 호출한다.
3. JPQL 쿼리 실행 시 자동으로 호출한다.

<br><br>

## 준영속
영속화된 Entity를 준영속으로 만든다.

### 준영속으로 만드는 방법
1. `em.detach(Entity)` : 특정 Entity만 준영속 상태로 설정
2. `em.clear()` : 영속성 컨텍스트 초기화
3. `em.close()` : 영속성 컨텍스트 종료

준영속화가 되면 1차캐시뿐 아니라 쓰기지연 SQL 저장소의 내용도 삭제된다.

### 준영속 특징
- 당연히 영속성 컨텍스트가 제공하는 모든 기능이 동작안된다.
- 비영속이 아니라 준영속임으로 준영속에는 **PK 값이 있다**.

<br><br>

## 병합

```java
em.merge(Entity)
```
**병합 순서** <br>
1. 준영속성은 PK가 있으므로 해당 PK를 기준으로 1차캐시에서 찾는다.
2. 1차캐시에 없으면 DB에서 찾아 1차캐시에 적재 후 반환한다(이때 만약 변경된 값이 있다면 변경 후 적재한다).
3. 트랜잭션 commit호출 시 내부적으로 Flush가 호출되고 이때 스냅샷과 비교하여 update 쿼리를 날린다. 
4. merge의 파라미터로 준영속이 아닌 비영속 Entity가 올 수 있다. 이때는 PK가 없으므로 새로 만들어서 영속화시킨다. 즉 merge메서드는 Save to Update 처리를 한다. 
