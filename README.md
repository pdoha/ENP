# TIL
> Today I Learned 2024.02.08





---

## 기초세팅
JWT는 heaer, payload, signature로 구성 되어 있다.

> header : signature 를 해싱하기 위한 알고리즘 정보
> 
> 
> payload : 실제로 사용될 데이터 (회원정보)
> 
> signature : 토근의 유효성 검증을 위한 문자열( 토큰이 유효한 토큰인지 검증 )
>
```
aaaaaa(header).bbbbbb(payload).cccccc(signature)
```

## 1️⃣ jwt 의존성 설정 - build.gradle
  
 ```gradle:build.gradle

dependency {
  ...
  
  implementation 'io.jsonwebtoken:jjwt-api:0.12.3'
  implementation 'io.jsonwebtoken:jjwt-impl:0.12.3'
  implementation 'io.jsonwebtoken:jjwt-jackson:0.12.3'
  
  ...
}

```
## 2️⃣ yml 설정 추가 - application.yml


  >secret는 base64 생성기를 이용해서 만든다
  
 ```yml:application.yml

# JSON WebToken 설정
jwt:
  header: Authorization
  secret: YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd6eXoxMjMxMjMxMjMxMjMxMjMxMzEyMzEyMzEzMTIzMTIzMTIzMTMxMjMxMzEzMTMxMjM
  accessTokenValidityInSeconds: 3600 # 60 min
```

- jwt.secret : base64로 인코딩한 값 사용,
  
  > 일정 길이 이상이 되지 않으면 exception이 발생하므로 충분히 길게 설정
  
- access-token-validity-in-seconds : 토큰의 유효기간(초 단위) 예) - 3600 - 60분

---


### 오류
 > ERROR : 연동시 지원되지 않는 문자 집합
> - DB연동시 특정 테이블이 추가되지 않음
> - implementation group: 'com.oracle.ojdbc', name: 'orai18n', version: '19.3.0.0' //의존성 추가로 해결



### 작업소감
 > 깃 마스터 보호 설정으로 3시간동안.. 스스로 고쳤다 😂:
 > DB 연동시 orai18n 추가하라는 거 	 
