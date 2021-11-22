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

<br><br>

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