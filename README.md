# 대소고 대나무숲

### application.yml
```yaml
server:
  port: 8080

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/{DB 이름}?serverTimezone=UTC&characterEncoding=UTF-8
    username: {DB 유저네임}
    password: {DB 패스워드}

  jpa:
    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect
    open-in-view: false
    show-sql: false
    hibernate:
      format_sql: true
      ddl-auto: update
```