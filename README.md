# Study-Oauth2
SSO 서비스를 위한 Oauth2 구축

## SSO 란?
Single Sign-On(SSO)의 약어로 **일회성 사용자 인증**으로 다수의 애플리케이션 및 웹사이트에 대한 사용자 로그인을 허용하는 인증 솔루션 이다.
SSO는 한번 자격 증명이 검증된 사용자 에게는 반복되는 로그인 없이 모든 암호 보호 리소스에 대해 액세스하도록 하여 보안과 사용자 경험을 모두 충족할 수 있습니다.

### SSO 유형
+ SAML
    + Security Assertion Markup Launguage 의 약어
    + SAML은 브라우저 친화적 마크업 언어인 XML을 사용하여 사용자 식별 데이터를 교환
+ OAuth
    + Open Authorization 의 약어
    + 애플리케이션은 사용자 암호를 요청하는 대신 OAuth를 사용하여 보호된 데이터에 엑세스 할 수 있는 사용자 권한을 얻음
    + AccessToken과 RefreshToken 으로 구성되며, JWT 토큰이 주로 사용 됨
+ OIDC
    + OpenID 의 약어
    + 서비스 공급자가 사용자 보안 인증 정보를 인증하는 역할을 수행
+ Kerberos
    + 둘 이상의 당사자가 네트워크에서 신원을 서로 검증할 수 있는 티켓 기반 인증 시스템
    + 보안 암호화를 사용하여 서버, 클아이언트 및 키 배포 센터 간에 전송되는 식별정보에 대한 무단 액세스를 방지


## Oauth 서버


### Oauth2의 4가지 역할
+ 인증 서버(Authorization Server)
    + 관리대상에 포함된 고객인지 확인해 주는 역할을 한다.
    + ID/PW를 통해 인증을 하고 유효할 경우 AccessToken을 발급하여 리턴 한다.
+ 리소스 소유자(Resource Owner)
    + 리소스 서버가 제공하는 서비스를 이용하는 사용자를 뜻한다.
+ 클라이언트(Client, front-end)
    + 서드파티 앱을 뜻한다.
    + 사용자가 사용하는 aos, ios, webapp 등이 이에 해당 한다.
+ 리소스 서버(Resource Server, back-end)
    + 사용자에게 제공할 API가 있는 서버를 말한다.
    + AcessToken 을 통해 API 자원을 활용 할 수 있다.
    
`※ 2019년 11월 24일 Spring Security 블로그에서 Authorization Server의 지원을 중지를 발표 했다고 한다. 2019년에는 사용 가능한 상용 및 오픈소스 인증서버가 많다는 이유에서다.`
[사이트참조](https://lemontia.tistory.com/927)

### 토큰 발급
토큰 발급 방식(grant_type)은 총 4가지가 존재하며,   
`기본적으로 헤더에 authorization 값(clientid:secretkey base64 인코딩한 값) 을 넣어서 요청` 해야 한다.
#### 토큰 발급 방식 (grant_type)
grant_type은 OAuth 2.0 인증프로세스의 중요한 부분 입니다.   
클라이언트가 리소스 서버에서 엑세스 토큰을 요청하는 방법을 결정 합니다.
+ **authorization_code**
    + 웹에서 사용되는 가장 보편적인 인증 형태
    + 시나리오
        + step1.클라이언트 -> 사용자 에게 권한 요청
        + step2.사용자 동의를 받은 후 엑세스토큰을 요청하는데 사용
        ```
        - URL : http://localhost:8080/oauth/token
        - Parameter : grant_type=code, authorization_code=<발급받은코드>, redirect_uri=<코드 전달받은 callback URL>
        ```
+ **password**
    + `id/pw 인증 후 accessToekn` 발행
    + 시나리오
        + step1. `클라이언트가 사용자의 자격증명(ID/PW)을 사용하여 엑세스 토큰을 요청`하는데 사용
        ```
        - URL : http://localhost:8080/oauth/token
        - Parameter : grant_type=password, username=<아이디>, password=<패스워드>, scope=read
        ```
+ **client_credentials**
    + `인증에 대한 clientId, secretkey 만으로 바로 AccessToken 발행. 신뢰도 높은 클라이언트에게만 해당 방식 허용`
    + 시나리오
        + `클라이언트 -> 리소스 서버에 대한 엑세스 권한을 요청`하는데 사용
        ```
        - URL : http://localhost:8080/oauth/token
        - Parameter : grant_type=client_credentials, scope=read
        ```
+ **refresh_token**
    + 만료된 엑세스 토큰을 새로 고치기 위해 `클라이언트 -> 리소스 서버에 엑세스 토큰을 요청`하는데 사용
    + 시나리오
        + ㅇㄻㄴㄹ
        ```
        - URL : http://localhost:8080/oauth/token
        - Parameter : grant_type=refresh_token, refresh_token=<이전에 발급받은 refresh token>, scope=read
        ```


#### 토큰 발급 시나리오
+ 엑세스토큰 신규 발급
+
+ 엑세스토큰 갱신


### 사이트 참조
+ [SSO란 무엇인가요](https://aws.amazon.com/ko/what-is/sso/)
+ [5분안에 구축하는 OAuth 서버 - 01](https://co-de.tistory.com/29)
+ [SpringBoot - OAuth2 Client 만들기]
