# 크롬 DevTools에서 JS사용할 때 undefined가 뜨는 이유가 궁금하여 공부하게되었다.

```javascript
console.log("왜 undefined가 뜨는걸까 궁금");
```


<img src="https://github.com/leejk0924/study/blob/main/etc/picture/console_undefined_in_js.png?raw=true"/>

console.log()를 사용하면 결과를 출력하고 undefined가 출력된다. 뭔지 거슬리기도 하고 도대체 왜 뜨는지 몰랐다. 
이것을 이해하기 위해서 몇가지 알아야하는 배경지식이 있다. 

## 값
식(expression)이 평가(evaluate)되어 생성된 결과
값으로 평가될 수 있는 문

## 변수
하나의 값을 저장하기 위해 확보한 메모리 공간 자체 또는 그 메모리 공간을 식별하기 위해 붙인 이름
```javascript
var sum = 10 + 20;
```
변수 `sum`에는 `10 + 20`이 아니라 `10 + 20`이 평가된 값 30이 할당된다. 

## 리터럴
사람이 이해할 수 있는 문자 또는 약속된 기호를 사용해 값을 생성하는 표기법


## 문 
프로그램을 구성하는 기본 단위이자 실행 단위


## 표현식
값으로 평가될 수 있는 문 (즉, 표현식이 평가되면 새로운 값을 생성하거나 기존 값을 참조)
```javascript
var score = 100;
```
리터럴은 자바스크립트 엔진에 의해 평가되어 값을 생성하므로 리터럴 자체도 표현식이다. 

```javascript
var score = 50 + 50;
```
리터럴과 연산자로 이뤄져있지만, `50 + 50`이 평가되어 숫자 값 100을 생성하므로 표현식이다.

```javascript
vscore; // -> 100
```
변수 식별자를 참조하면 변수 값으로 평가된다. 식별자 참조는 값을 생성하지 않지만 값으로 평가되므로 표현식이다. 


reference

https://velog.io/@ingdol2/JS-console.log%EB%A7%8C-%EC%B0%8D%EC%96%B4%EB%8F%84-undefined-%EB%82%98%EC%98%A4%EB%8A%94-%EC%9D%B4%EC%9C%A0


모던자바스크립트 딥다이브

