# 테스트 주도 개발 

## 켄트 벡과 저자의 인터뷰

**저자** : 개발을 할때 TDD로 개발하시나요? <br>
**켄트 벡** : TDD로 개발하면 확신이 생깁니다. 물론 모든 코드들을 TDD로 하는 것은 아닙니다. 
TDD로 개발을 하지 않는 부분들은 할 수 없다고 판단했기 때문입니다.

<br>

**저자** : TDD 이외의 확신을 줄 수 있는 방법이 있을까요? <br>
**켄트 벡** : 모릅니다. 다만 짝 프로그래밍으로 대신할 수는 있겠습니다.

<br>

**저자** : 서비스 코드를 TDD로 개발하는 방법론 처럼 데이터베이스도 TDDD 방법론이 있는데요. 어떻게 생각하시나요? <br>
**켄트 벡** : 직접 경험은 안했지만 내가 신뢰하는 사람들이 다 TDDD도 좋은 방법론이고 충분히 가능하다고 합니다.

<br>

**저자** : 모의객체(Mock Object)를 사용하여 테스트 코드를 작성하는 것은 좋다고 생각하나요? <br>
**켄트 벡** : Mock객체를 많이 사용하지 않지만 제가 존경하는 사람들은 Mock객체가 대단하다 말합니다.


## Todo List
1. $5 + 10CHF = $10(환율이 2:1일 경우) <br>
   => 통화가 다른 두 금액을 더해서 주어진 환율에 맞게 변한 금액을 반환
2. ~~$5 * 2 = $10~~ <br>
   ~~=> 주가 * 주식 을 반환해야 한다.~~
3. ~~amount를 private으로 만들기~~
4. ~~Dollar 부작용 (side effect)~~ <br>
   ~~=> Dollar값은 이전 계산 후 변경되지 않아야 한다.~~
5. Money 반올림? 
6. ~~equals~~
7. hashCode(Dollar를 해시 테이블의 키로 사용할때 사용)
8. Equal null
9. Equal object

## [1장] 다중 통화를 지원하는 Money 객체

### 📌 TDD를 진행하는 방법
1. Todo 리스트를 작성한다.(🤔어떤 기능들이 있어야 하나?, 어떤 테스트들이 있어야 하나?)
2. 실패하는 테스트코드를 작성한다. (실패하는 코드 : 컴파일도 되지않는 코드)
3. 성공하는 코드로 수정한다. (성공하는 코드 : 컴파일되고, 테스트 케이스가 녹색불이 뜨는것.) <br>
   => **스텁**을 통한 테스트 코드 통과
4. 리펙토링을 통해 코드수정한다. (성공하는 코드로 만들기위해 만든 냄새나는 부분들 제거한다.)

> Stub이란 메서드 서명부, 반환값만 적어 컴파일이 될 수 있도록 껍데기를 만들어 두는 것

## [2장] 타락한 객체

### 📌 TDD vs ADD
- TDD : Test Driven Development
- ADD : Architecture Driven Development

TDD 개발순서 : 작동하는 코드 -> 깔끔한 코드

ADD 개발 순서 : 깔끔한 코드 -> 작동하는 코드

## [3장] 모두를 위한 평등

### 📌 Value of Pattern
VO 즉 Value Object의 줄임말이죠! <br>
객체를 값처럼 사용한다..? 자바에서는 다양한 Primitive type이 있는데 왜 객체를 값처럼 사용해야할까요??🤔🤔


예를 들어보죠! <br> 
6이란 숫자카드를 나타내기 위해 어떤 Primitive type을 사용해야 할까요? <br>
아마 대부분 int타입을 생각하셨을 겁니다. <br> 
그렇다면 int타입으로 숫자카드를 표현하기 충분할까요? <br>
예 충분하지 않죠! 숫자 카드에는 -1 음수값이 들어갈 수 없죠!? <br>
그럼 누가 6이란 int값을 변경할 수 있지 않을까요? <br>

> 에이~ final을 사용해서 불변하게 만들면 되죠!

하지만 우리의 비즈니스는 이렇게 단순하지 않습니다.
int 자료형만으로는 해당 값이 카드값인지 해당 카드가 스페이스인지, 하트인지 모릅니다..

이렇게 Primitive type 만으로는 우리의 복잡한 도메인을 표현하기엔 부족합니다.

#### Value Object의 특징
VO가 필요한 이유를 앞에서 알아보았습니다!<br>
그럼 VO의 특징을 알아보며 언제 어떻게 사용해야 하는지 알아보죠!<br>

1. Setter 메서드가 존재하지 않습니다. (불변성 보장)
~~~java
final class Card {
    private int number;
    private CardShape shape;
    
    public Card(int number, CardShape shape) {
        this.number = number;
        this.shape = shape;
    }
}
~~~
호오~ 값의 변경 걱정을 안해도 되니 동일성비교를 사용해도 큰 문제가 없겠습니다!?

그렇다면 불변한 객체를 어떻게 사용해야 할까요? <br>
VO를 조작하기 위해서는 4가지 방법이 있습니다.
- 생성자 또는 정적 메서드를 사용한 인스턴스 만들기
- 인스턴스 필드들을 사용하여 다른 타입으로 반환
- 객체를 새로 생성하기
- 정적 팩토리 메서드 사용

~~~java
final class Card {
   private int number;
   private CardShape shape;

   // 정적 팩토리 메서드를 사용하기 위해 private으로 변경
   private Card(int number, CardShape shape) {
      this.number = number;
      this.shape = shape;
   }
   // 정적 메서드
   public static Card joker() {
       return new Card(0, CardShape.Joker);
   }
   // 정적 팩토리 메서드 사용
   public static Card of(int number, CardShape shape) {
       return new Card(number, shape);
   }
   // 객체를 새로 생성하기
   public Card changeShape(CardShape changeShape) {
       return new Card(this.number, changeShape);
   }
   //인스턴스 필드 사용하여 다른 타입으로 반환
   @Override
   public String toString() {
       return String.format("Card Number : %d, Shape : %s", number, shape);
   }
}
~~~

2. 동등성 비교
~~~java
final class Card {
    private int number;
    private CardShape shape;
    
    @Override
    public boolean isSame(Card card) {
        return this.equals(card);
    }
}
~~~

3. 유효성 검사 로직
~~~java
final class Card {
    private int number;
    private CardShape shape;
    
    public Card(int number, CardShape shape) {
        this.number = number;
        this.shape = shape;
        validCardNumber();
    }
    
    private void validCardNumber() {
        if (this.number < 0) {
            throw new IllegalArgumentException("카드값은 0보다 작을 수 없습니다.");
        }
    }
}
~~~
> 📍 Tip : 검증을 할때 값을 반영 후 검증을 했는데 이렇게 하면 Multi Thread에 안전하다고 합니다. 이를 다른말로 방어적 복사 기법이라 합니다.
#### Value Object와 일급 컬렉션의 상관관계!?
VO는 결국 복잡한 도메인 로직을 포함한 불변의 값 객체라는 건데... 어디서 많이 들어보지 않았나요? <br>
TDD 뽀개기 스터디에서 학습한 일급 컬렉션이 떠올랐습니다. <br>
일급 컬렉션이란 간단하게 말하면 객체안에 하나의 컬렉션만 존재하며 이를 불변으로 관리한다는 특징이 있습니다.<br>

> 혹시 일급컬렉션에 대해 궁금하신 분들은 다음에 저희 TDD 뽀개기 스터디 회고록을 참고해주세요! 

아무튼 그래서 좀 찾아보니 일급 컬렉션도 VO의 일종으로 본다고 합니다.!

<br>

### 📌 동일성 vs 동등성
자바의 최상위 클래스인 Object는 equals메서드를 기본으로 가지고 있습니다. <br>
따라서 자바에서 클래스를 만들면 기본적으로 equals메서드를 가지고 있죠!

![image](https://user-images.githubusercontent.com/39672033/170819232-e874b23d-1165-4a38-91b7-acbe22de2740.png)

책에서는 동일성에 대해서 이야기를 하고 있습니다. 
그런데 조금 검색을 해보니 동등성이란 키워드가 나와 같이 정리해보았습니다.

- 동일성(identity) : **식별자를 기반으로 객체가 같은지 판단**할 수 있는 성질
- 동등성(equality) : **상태를 이용해 두 값이 같은지 판단**할 수 있는 성질

그렇다면 위에서 본 == 연산자는 동일성일까요? 동등성일까요? 🤔<br>
. <br> . <br> . <br> . <br> . <br> . <br>
네 맞습니다. 동일성 입니다.👍

그렇다면 동등성은 언제 왜 사용하는 걸까요? <br>

자바는 무슨 언어일까요? 네! 객체지향 언어이죠!?.<br>
객체지향 언어는 객체들이 서로 상호작용 하면서 동작하도록 하는 프로그래밍 언어이죠 <br>
그렇다면 그 상호작용할 객체들을 비교를 할땐 동일성보단 동등성으로 비교를 하는것이 맞지 않을까요? <br>

> A가 가진 6이라는 숫자 카드와 B가 가지고 있는 6이란 숫자카드를 서로 Swap했을 경우 달라진게 있을까요? [NO]

따라서 객체들간의 비교는 동등성을 이용하여 비교하는 것이 옳은 방법이며, 
동일성 비교는 Primitive type 을 사용하는 것이 맞다 생각합니다. (물론 예외도 있겠지요?) <br>

