# application.yml 설정

```text
src/main/resources/application.yml
```

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/스키마명?serverTimezone=Asia/Seoul
    username: id
    password: pw
spring:
  mvc:
    view:
      prefix: /
      suffix: .jsp
spring:
  servlet:
    multipart:
      max-file-size: 50MB
      max-request-size: 100MB
      location: 파일 저장 경로
```
