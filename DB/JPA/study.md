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

# 3장 JPA가 제공하는 기능(영속성 컨텍스트, Entity 생명주기)

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


<br><br><br>

# 4장 JPA와 테이블 매핑 방법

### @Entity
- 기본 생성자가 필수이며, 자바에서는 컴파일 시 생성자가 없는 클래스들은 기본 생성자를 추가해준다.
- JPA를 사용해 테이블을 매핑한다.

### @Table
- Entity와 매핑할 테이블


## 기본키 할당 전략
- Identity 전략
  - 기본키 생성을 DB에 위임한다. (Mysql, postgreSQL)
  - 식별자를 DB에 Insert해야 가져올 수 있으므로 쓰기지연 SQL 저장소에 쿼리를 저장할 수 없다. 즉 persist메서드를 호출 시 바로 DB에 쿼리를 날려 식별자를 얻어온다.

<br>

- Sequence 전략
  - 오라클, H2 DB에서 주로 사용할 수 있다.
  - 유일한 값을 순서대로 생성하는 특별한 DB오브젝트
  - 동작방식
    - persist메서드 호출 시 DB에서 시퀀스 정보를 가져온 뒤 조회한 식별자를 Entity에 할당한 다음 영속성 컨텍스트에 저장한다.
    - `@sequenceGenerator`의 Allocationsize 기본값은 50이다.

<br>  

- Auto 전략
  - 기본이 Auto이며, Mysql, 오라클 등 DB에 따라 알아서 Identity, Sequence로 설정해준다. 
  - 만약 Sequence이면 DB에 별도의 시퀀스나 키 생성용 테이블을 만들어 둬야한다.


<br>

기본키를 설정할때 **자연키**, **대리키**가 있다.<br>
- 자연키는 주민번호, 이메일 등 PK가 될만한 것들을 말한다.
- 대리키는 임의로 만든 키다. 1씩 증가하는 숫자 등.

무조건 자연키보다 대리키로 만드는 것이 추후 유지보수할때도 좋다.


자바에서 기본자료형은 not null일 수 없다. 그러므로 @Column에 nullable 설정을 false로 하여 null일 수 없도록 해야한다.


<br><br><br>

# 5장 객체참조 및 외래키 매핑

연관관계 매핑을 이해하기 위한 핵심 키워드
- 방향(단방향, 양방향)
- 다중성(1:N, N:1, 1:1, N:N)
- 연관관계 주인(객체를 양뱡향으로 만들 시 연관관계 주인 설정)

## 단방향 연관관계
### 객체 관계
- 객체 연관관계는 단방향 연관관계이다. 즉 A 클래스가 B를 참조하고 있으면 A -> B 의 단방향 관계가 성립된다.
- 이를 양방향으로 하려면 B클래스가 A를 참조하면 된다.
  
### DB Table 관계
- 테이블 연관관계에서는 무조건 양방향 관계이다. 외래키를 통해서 join을 사용하여 어느 테이블에서건 조회할 수 있다.
 
| 애노테이션 | 설명 |
|-------------|----------|
`@ManyToOne`  | N : 1관계
`@JoinColumne`| 외래키를 맵핑할때 사용한다. name에는 외래키 이름을 지정한다. name 미지정시 기본전략을 사용한다.(필드명_참조테이블 칼럼PK)

- 연관된 Entity 삭제
  - 기존에 연결되어있던 연관관계를 제거하고 삭제해야한다. 미삭제 시 외래키 제약 조건으로 인해 DB 오류 발생한다.

## 양방향 연관관계

**mappedBy** 속성은 양방향 맵핑일때 사용한다. 연관관계 주인인 필드의 이름으로 값을 주면 된다. 만약 연관관계 주인이 Member이고 `private Team team`이라면 Team 클래스에서 mappedBy속성의 값으로 team을 지정하면 된다.

- 연관관계 주인을 왜 설정하고 필요하나?
  - 테이블의 외래키를 가지고 양방향 관계를 가지지만 객체는 참조를 통해 연관관계를 가지기 때문에 객체끼리 서로 참조하게되면 JPA 입장에서는 누구의 필드를 외래키로 사용할 지 알 수 없다. 그러므로 두 객체 중 하나를 정해서 외래키라고 알려줘야 한다. 이를 mappedBy 속성을 사용해서 연관관계 주인을 알려주는 것이다. 

- 연관관계 주인만이 등록, 수정, 삭제 가 가능하며, 아닌쪽은 읽기만 가능하다.
- mappedBy가 있는 쪽이 연관관계 주인이 아니다.
- DB에서 N쪽이 다 외래키를 가진다. 그래서 mappedBy 속성은 @OneToMany쪽에만 있다. 

### 양방향 연관관계 저장
양방향 연관관계는 연관관계 주인이 외래키를 관리한다.
그래서 연관관계 주인만 값을 설정할 수 있다. 하지만 JPA를 사용하지 않고 Test 코드를 작성하거나, 객체관계로만 따지자면 양쪽다 설정하는게 맞다. 그러므로 양쪽다 설정해주자. 

`이때 이전에 설정한 연관관계가 있다면 잊지말고 해제해줘야 한다. ` <br>

또한 양방향 매핑 시 무한루프에 빠질 수 있으므로 주의해야 한다.

<br><br><br>

#  6장 연관관계

## N:1 양방향 연관관계
- 외래키가 있는 쪽이 연관관계 주인이다.
- 양방향 연관관계는 항상 서로를 참조해야 한다. 

## 1:N 연관관계
- 1:N 단방향일 때에는 `@JoinColumn`을 명시해야 한다. 만약 `@JoinColumn`이 없었다면 별도의 연결 table을 만들어 사용한다.
- 1:N 단방향의 단점은 매핑한 객체가 관리하는 외래키가 다른 테이블에 있다는 점이다. 그래서 엔티티 저장과 업데이트를 해당 엔티티에서 처리할 수 없다. 
- 따라서 1:N보다는 N:1을 사용하자.

<br>

> N:1 양방향을 사용하자. 

## 1:1 연관관계

- 두 테이블 중 누구나 외래키를 가질 수 있다.
- 개발자들은 주로 주 테이블에 외래키를 두는 것을 선호한다.
- 주 테이블에 외래키를 두면 단방향, 양방향 다 가능하지만, 대상 테이블에 외래키를 두면 양방향 맵핑으로만 가능하다. 

<br><br><br>

# 7장 고급매핑

DB에서는 상속이란 개념은 없다. 대신 슈퍼타입, 서브타입 관계라는 모델링 기법이 존재한다. 해서 JPA는 해당 모델링 기법을 상속과 매핑하였다.

<br>


- `조인전략` : Entity 각각을 Table로 만들고 부모 테일블의 PK를 자식테이블에서 받아 외래키로 사용하는 전략, 이때 부모 테이블에서는 자식들을 구분지을 **타입칼럼** 추가로 필요하다.
- `단일 테이블 전략` : 테이블 하나에 부모테이블과 자식테이블의 내용을 모두 집어넣어 사용하는 전략. 별도의 Join 쿼리가 불필요하며 속도가 가장 빠르다. 대신 단점으로는 필요 이상으로 테이블이 커질 수 있으며, 자식 테이블의 칼럼들은 모두 Null을 허용해야 한다.
- `구현 클래스마다 테이블 전략` : 자식 테이블 각각 필요한 칼럼을 작성한다. 추천하지 않음. 

<br><br>

## @MappedSuperclass 
DB 테이블과 매핑하는 것이 아니라 단순히 매핑 정보를 상속할 목적으로만 사용한다.
즉 공통의 속성들을 모아둔 클래스를 만들어 이를 각각의 Entity가 상속받아 사용할 수 있도록 한다. 

> 만약 부모로부터 받은 매핑정보를 변경하려면 @AttributeOvveride나 @AttributeOvverides를 사용한다.

<br>
@MappedSuperclass로 지정한 클래스는 Entity가 아닌 단순 클래스 임으로 JPQL이나 em.find로 사용할 수 없다. 즉 영속화 되어있지 않는 클래스 이다. 

<br>
Entity는 Entity나 @MappedSuperclass로 설정된 클래스만 상속받을 수 있다.

<br><br>

## 조인테이블
DB 테이블의 연관관계 설계 방법에는 크게 2가지가 있다. 
1. `@JoinColumn`을 사용하는 방법
2. `@JoinTable`을 사용하는 방법

### @JoinColumn
- name의 값으로 외래키 이름을 설정한다.
- 외래키에 null 값이 들어갈 수 있다.
- 그래서 내부조인이 아닌 외부조인을 사용해야 한다.
- 내부조인을 사용하면 null일때에는 조회되지 않는다.

### @JoinTable
- 조인 테이블이라는 별도의 테이블을 새로 생성한다. 
- 조인 테이블에 외래키들을 추가하고 관리한다. 
- 별도의 테이블을 새로 만드는 것 임으로 관리포인트가 하나 더 늘어난 다는 단점이 있다.

<br><br><br>

# 8장 프록시

프록시가 왜 필요한지 알기 위해서는 지연로딩, 즉시로딩 개념에 대해서 알고 있어야 한다. 

- 지연로딩 : 실제 사용될때 쿼리문을 DB로 전달하여 가져옴.
- 즉시로딩 : 애플리케이션 실행 시 DB로 전달하여 가져옴.

그렇다면 지연로딩은 어떻게 제공하나? 이때 나온 개념이 바로 **프록시** 이다. 

<br>

> 물론 프록시로만 지연로딩을 설정할 수 있는 것은 아니다. 바이트코드를 수정하는 방법도 있지만 이는 복잡함으로 프록시만 알고있어도 충분할 듯.

<br>

## 프록시란

프록시는 실제 클래스를 상속받아서 구현되어있다. 그래서 겉 모양은 실제 클래스와 비슷하다. 상속받은 프록시 객체는 내부적으로 실제객체를 참조해서 target변수로 저장하여 실제 요청이 들어올 시, 즉 프록시 객체의 메소드를 실행 시 실제객체를 호출하여 실행한다. 

<br>

### 프록시 동작방식

1. 프록시 메서드 호출
2. 한번도 실제 엔티티가 생성되지 않았따면 영속성 컨텍스트에 실제 엔티티 요청(**초기화**)
3. 영속성 컨텍스트는 실제 DB 조회
4. 프록시 객체가 참조하는 target 변수에 저장
5. 이후 요청은 target에 저장한 실제 entity객체를 통해서 한다.

<br>

### 프록시 특징
- 프록시 객체는 처음 사용할 때 초기화한다.
- 프록시 객체가 초기화되면 실제 Entity에 접근가능
- 초기화는 영속성 컨텍스트의 도움을 받아야 하기 때문에 준영속성인 상태에서는 초기화할 수 없다.(만약 준영속상태에서 호출 시 LazyInitializationException 발생)

<br>

## 즉시로딩(EAGER)

- 조회 시 연관된 Entity도 함께 조회된다.
- JPA는 즉시로딩을 최적화 하기위해 Join을 사용한다.
  - 별도의 설정이 없으면 기본적으로 외부조인(outer join)을 사용한다.
  - 외부 조인을 사용하는 이유는 A가 B를 참조하고 있을 경우 B가 Null일때 내부조인 시 B뿐만 아니라 A도 검색이 안된다. 
- `@OneToOne`, `@ManyToOne`에서 JPA는 기본전략은 **EAGER**이다.
  
### 외부조인
JPA는 기본적으로 조인할때 Outer Join을 사용한다. 이는 Null이 포함될 수 있기 때문에 그렇다. 그래서 이를 사전에 방지하면 Inner Join으로 Join을 처리하도록 설정할 수 있다.

- `@JoinColumn`에 nullable 값을 false로 설정한다.
- `@ManyToOne`에 optional값을 false로 설정한다.

둘중 한가지 방법을 사용하면 외부조인이었던 것을 내부조인으로 설정할 수 있다.
외부 조인보다 내부조인이 성능상 이점이 있기 때문에 상황에 따라 내부조인으로 설정하는 것이 좋다. 

<br>

### 지연로딩(Lazy)

```java
Member member = em.find(Member.class, 1L);
Team team = member.getTeam(); // 프록시 객체를 반환한다. 
team.getTeam(); // 실제 Team 객체를 사용하여 DB에서 조회해서 초기화한다.
```

지연로딩으로 되어있으면 실제 호출되지 않는 한 select시 프록시가 들어가 있으므로 쿼리를 보면 포함되어있지 않다.

컬렉션은 로딩 시 비용이 많이 들기 때문에 `@OneToMany`, `@ManyToMany` 시 기본전략이 Lazy이다. 

컬렉션은 즉시로딩하는 것은 권하지는 않지만, 필요하다면 즉시로딩 시 항상 외부조인을 사용하자.
 
<br><br>

## 영속성 전이(CASCADE)

JPA에서는 Entity를 저장할때 연관된 모든 Entity는 영속상태여야 한다. 그래서 Entity를 영속상태로 만들 때 연관된 모든 Entity도 영속상태로 만들기 위해 사용한다.

<br>

부모를 영속화 시 자식도 영속화 된다.

<br>

```java
@Entity
class Parent{

  @OneToMany(mappedBy="parent", cascade = CascadeType.PERSIST)
  private List<Child> children = new ArrayList<child>();

}
```

> 영속성 전이는 연관관계 매핑과는 **관련이 없다**. 단지 부모를 영속화시 연결된 자식도 영속화 해주는 기능만 있다. 

<br>

- CascadeType.REMOVE 속성으로 부모 Entity 삭제 시 연결된 자식 Entity도 삭제되도록 설정할 수 있다. 즉 부모가 자식의 **삭제 생명주기를 관리**한다. 
- 삭제 순서는 자식 -> 부모 순이다.
- CascadeType.PERSIST 와  CascadeType.REMOVE를 같이 사용하면  CascadeType.ALL과 동일하다.
- CascadeType.PERSIST, CascadeType.REMOVE는 플러시를 호출할때 영속성 전이가 발생한다.

<br><br>

## REMOVE 시 주의사항 

```java
Member member1 = new Member();
Member member2 = new Member();
Team team = new Team();

team.addMember(member1);
team.addMember(member2);

teamRepository.save(team);

team.getMembers().remove(0);

List<Team> teams = teamRepository.findAll();
List<Team> members = memberRepository.findAll();

assertThat(teams).hasSize(1);
assertThat(members).hasSize(2);
```
> CascadeType.REMOVE는 부모와 자식의 관계가 끊어져도 자식을 삭제하지 않는다. 
> 즉 Delete 쿼리가 나가지 않는다. 

<br><br>

## 고아객체

```java
@OneToMaby(mappedBy="parent", orphanRemoval=true)
```

- 고아객체는 부모와 연결이 끊어져도 남아있는 자식 Entity를 말한다.
- 자식 Entity의 참조만 제거시 자식 Entity가 자동으로 삭제된다.(CascadeType의 Remove속성의 주의사항 부분을 해결해준다.)
- CascadeType.Remove 처럼 부모 Entity제거 시 자식 Entity도 제거해준다.

<br>

### 고아객체 제거 시 주의사항
참조가 제거된 Entity는 다른 곳에서도 참조되지 않아야 한다.

<br>

### 영속성 전이 + 고아객체 제거 둘다 사용 시
CascadeType.ALL + orphanRemoval = true 시 자식의 Entity 생명주기를 부모 Entity가 관리한다는 의미이다.

<br><br><br>

# 9장 값 타입

JPA에서는 타입이 크게 2가지가 있다. 
- Entity 타입
- 값 타입(int, integer, string 등)

이중에서 값 타입에는 3가지 종류가 있다.
- 기본 값 타입
- 임베디드 타입
- 컬렉션 타입

<br><br>

## 임베디드 타입

- 해당 엔티티가 더욱 의미있고 가독성 있도록 보이게 해준다.
- 새로 만든 클래스에 `@Embededable`을 붙이고 사용하는 곳 에다가 `@Embedded`애노테이션을 붙인다.
- 임베디드 타입은 기본 생성자가 필수이다.
- 하이버네이트는 임베디드 타입을 컴포넌트라 한다.
- 임베디드 타입은 클래스 임으로 여러 Entity에서 공유해서 사용하면 의도치않는 값 변경이 발생할 수 있다. 그러므로 값만 복사하는 별도의 clone메서드를 만들어서 사용해야 한다.
- 값 타입은 위처럼 공유 참조 문제가 발생하지 않도록 불변객체로 만드는 것이 좋다. 즉 생성자로만 파라미터를 받아 수정하고 setter메서드를 제공하지 않는것이 좋다. 
- 임베디드 타입은 값 비교시 객체는 인스턴스가 달라도 값이 같으면 같은 것으로 봐야 한다. 그래서 동등성 비교를 하기위해 equals메서드를 재정의 해야하며, 이때 hashcode도 같이 재정의 해야한다. 안그럼 컬렉션 사용 시 문제가 발생한다.

<br><br>

### @AttributeOverride 속성 재정의

임베디드 타입을 하나의 Entity에서 2개 이상 사용함에 따라 컬럼명 중복이 발생한다. 이런 문제를 해결하기 위해서 `@AttributeOverride`를 사용할 수 있다.

<br>

```java
@Embededable
public class Address{
  private String city;
  private String street;
  private String zipcode;
}

@Entity
public class Member{
  ...
  @Embedded Address homeAddress;

  @Embedded 
  @AttributeOVerride({
    @AttributeOverride(name="city", column=@Column(name= "COMPANY_CITY")),
    @AttributeOverride(name="street", column=@Column(name= "COMPANY_STREET")),
    @AttributeOverride(name="zipcode", column=@Column(name= "COMPANY_ZIPCODE"))
    })
  Address companyAddress;
}
```
<br><br>

### 값 타입 컬렉션

RDB에는 컬렉션을 칼럼에 포함할수 없으므로 별도의 table을 만들어 매핑해야 한다.<br>
`@CollctionTable`을 생략하면 기본값을 사용한다. 여기서 기본값이란 `{Entity이름}_{커렉션 속성 이름}`을 말한다.<br>



```java
@Entity
public class Member{
  ...
  @ElementCollection
  @CollctionTable(name="FAVORITE_FOODS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
  @Column(name="FOOD_NAME")
  private Set<String> favoriteFoods = new HashSet<String>();
  
  @ElementCollection
  @CollctionTable(name="FAVORITE_FOODS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
  @Column(name="FOOD_NAME")
  private List<Address> favoriteFoods = new ArrayList<Address>();
}
```


> 값 타입의 컬렉션은 기본적으로 **영속성 전이 + 고아객체 제거** 기능을 가지고 있다. @ElementCollection에 FetchType설정이 가능하다. 

||Entity타입| 값 타입|
|-|-----|--------|
|식별자|있다| 없다|
|생명주기|있다.(생성, 연속, 소멸)| 없다.(Entity에 의존)|
|공유|있다.(회원 Entity를 다른 Entity에서 얼마든지 참조 가능)| 공유하지 않는게 좋다.(대신 값을 복사하는 메서드를 만들어 사용하거나 불변 객체를 만들어서 사용하는 것이 좋다.)|

