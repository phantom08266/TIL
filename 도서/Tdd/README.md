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
10. ~~5CHF * 2 = 10CHF~~
11. Dollar, Franc 중복
12. ~~공용 equals~~
13. 공용 times
14. Franc와 Dollar 비교하기

## 책 내용정리

### 📌 [(1장) 다중 통화를 지원하는 Money 객체](https://github.com/phantom08266/TIL/wiki/%5B1%EC%9E%A5%5D-%EB%8B%A4%EC%A4%91-%ED%86%B5%ED%99%94%EB%A5%BC-%EC%A7%80%EC%9B%90%ED%95%98%EB%8A%94-Money-%EA%B0%9D%EC%B2%B4)
- TDD를 진행하는 방법
### 📌 [(2장) 타락한 객체](https://github.com/phantom08266/TIL/wiki/%5B2%EC%9E%A5%5D-%ED%83%80%EB%9D%BD%ED%95%9C-%EA%B0%9D%EC%B2%B4)
-  TDD vs ADD
### 📌 [(3장) 모두를 위한 평등](https://github.com/phantom08266/TIL/wiki/%5B3%EC%9E%A5%5D-%EB%AA%A8%EB%91%90%EB%A5%BC-%EC%9C%84%ED%95%9C-%ED%8F%89%EB%93%B1)
- Value of Pattern
- 동일성 vs 동등성
### 📌 [(4장) 프라이버시](https://github.com/phantom08266/TIL/wiki/%5B4%EC%9E%A5%5D-%ED%94%84%EB%9D%BC%EC%9D%B4%EB%B2%84%EC%8B%9C)
- 테스트 코드작성 시 주의사항
### 📌 [(5장) 솔직히 말하자면](https://github.com/phantom08266/TIL/wiki/%5B5%EC%9E%A5%5D-%EC%86%94%EC%A7%81%ED%9E%88-%EB%A7%90%ED%95%98%EC%9E%90%EB%A9%B4)
- 테스트 코드의 중요성 및 복붙시 주의사항
