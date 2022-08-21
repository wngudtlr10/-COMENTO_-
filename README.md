# -[직무부트캠프]COMENTO_백엔드 실무 학습목표-
코멘토 직무부트캠프를 통해 spring과 spring boot 프레임워크를 사용하여 어떤 차이가 있는지 알아봅니다.  
간단한 API 문서를 작성하여 개발하고 업무 프로세스를 이해합니다.
# -COMENTO_백엔드 실무 1주차 주간보고-

## 1주차 과제: 개발 환경 셋팅

### 1.형상관리
- Github 계정 생성 (완료)
- 저장소 생성 (완료) 

### 2.개발 환경
- **Eclipse, JDK, Tomcat, Spring 환경설정**
  - JDK 1.8 설치(완료)
  - Eclipse 설치(완료)
  - Apache Tomcat 9.0 설치(완료)
  - Spring 설치(완료)
- Hello World 출력(완료)
- mariaDB, mySql WorkBench 설치 및 샘플 DB 구축(완료)
- 스프링, Mariadb, MyBatis 연동, 데이터 조회(완료)

### 3. 기타
- [형식님의 Notion ](https://www.notion.so/1-_-a4dbeab1b75b4a66b6573728a35ac0e1)을 통해 개발 과정 기록 
---
# -COMENTO_백엔드 실무 2주차 주간보고-

## 2주차 과제: HTTP와 REST에 대한 공부 및 간단한 API 문서작성

### 1. HTTP
- HTTP 프로토콜
  - HTTP 프로토콜 개념 (완료)
  - HTTP 프로토콜 동작원리 (완료)
  - HTTP 프로토콜 구조 (완료)
  
### 2. REST, RESTful, REST API
- REST
  - REST란? (완료)
  - RESTful이란? (완료)
  - REST API란? (완료)

### 3. API 문서 작성
- 개요
  - 문서 정보 (완료)
  - 데이터 규칙 (완료)
-API
  -
  
### 4. 기타
- [형식님의 2주차 HTTP & REST 개념 정리](https://www.notion.so/2-_-75dd41cd12b94c018b70f47cba93e9eb)을 통해 정리(완료)
- API 문서작성(완료)

---
# -COMENTO_백엔드 실무 3주차 주간보고-

## 3주차 과제 : 스프링부트 환경 셋팅 & 스프링부트로 간단한 API 개발

### 1. 스프링부트 환경 셋팅(완료)

### 2. 데이터베이스 table 생성(완료)

### 3. 스프링부트, mybatis, mariadb 연동(완료)

### 4. SW활용 현황 통계 API 구축을 위한 SQL 작성(완료)
1. 월별 접속자 수
2. 일자별 접속자 수
3. 평균 하루 로그인 수
4. 휴일을 제외한 로그인 수
5. 부서별 월별 로그인 수

### 5. 기타
- [형식님의 3주차 notion](https://www.notion.so/3-_-c229b3c05c674703be86ebe981ce8f0d)을 통해 정리

---
# -COMENTO_백엔드 실무 4주차 주간보고-

## 4주차 과제 : 3주차 SQL문을 활용하여 API 개발 

### 1. SW활용 현황 통계 API 구축을 위한 SQL 개발(완료)
---
1. 년별 접속자 수  
![image](https://user-images.githubusercontent.com/50539146/185772716-13bcdca2-9809-444a-b509-52f5c2d7d8e9.png)  
'''sql
   <!-- sql 년별 로그인 수 -->
    <select id="selectYearLogin" parameterType="string" resultType="hashMap">
        select count(*) as totCnt
        from statistic.requestinfo ri
        where left(ri.createDate, 2) = #{year};
    </select>
---    
2. 월별 접속자 수  
![image](https://user-images.githubusercontent.com/50539146/185772738-3f391f8d-d568-447a-9b00-33558f827412.png)  
'''sql
  <!-- sql 월별 로그인 수 -->
    <select id="selectMonthLogin" parameterType="string" resultType="hashMap">
      SELECT SUBSTR(createDate, 1, 4) AS monthly, COUNT(distinct(userID)) AS userCnt 
      FROM statistic.requestinfo 
      WHERE substring(createDate, 1, 4) = #{month}
      group by monthly
      order by monthly;
    </select>
---    
3. 일별 접속자 수  
![image](https://user-images.githubusercontent.com/50539146/185772739-84de013d-15ed-42ae-86f9-7f913c9431be.png)  
'''sql
  <!-- sql 일별 로그인 수 -->
    <select id="selectDailyLogin" parameterType="string" resultType="hashMap">
      SELECT SUBSTR(createDate, 1, 6) AS daily, COUNT(distinct(userID)) AS userCnt 
      FROM statistic.requestinfo 
      WHERE substring(createDate, 1, 6) = #{daily}
      group by daily
      order by daily;
    </select>
---
4. 평균 하루 로그인 수  
![image](https://user-images.githubusercontent.com/50539146/185772741-c173f3c2-6ba4-4668-99a4-1d663863bcbf.png)  
'''sql
  <!-- sql 일별 평균 로그인 수 -->
    <select id="selectDailyAvgLogin" parameterType="string" resultType="hashMap">
     SELECT SUBSTR(loginDay , 1, 6) AS daily, AVG(loginCnt) AS userCnt
      FROM (
            SELECT SUBSTR(createDate, 1, 6) AS loginDay, COUNT(distinct(userID)) AS loginCnt
             FROM statistic.requestinfo
           WHERE  substring(createDate, 1, 6) = #{dailyAvg}
           GROUP BY loginDay
           ORDER BY loginDay 
       ) AS tmp
      GROUP BY daily
      ORDER BY daily;
    </select>
---
5. 휴일을 제외한 로그인 수  
![image](https://user-images.githubusercontent.com/50539146/185772746-e4466ec3-d5e7-4ea2-934c-3776f3063417.png)  
'''sql
   <!-- sql 휴일을 제외한 월별 로그인 수 -->
     <select id="selectHollydayLogin" parameterType="string" resultType="hashMap">
      SELECT SUBSTR(createDate, 1, 4) AS monthly, COUNT(*) AS userCnt
       FROM (
      SELECT createDate, dayofweek(SUBSTR(createDate, 1, 8)) AS hollyday
        FROM statistic.requestinfo
        WHERE  SUBSTR(createDate, 1, 4) = #{month}
        ) AS tmp
      WHERE hollyday != 1 AND hollyday != 7
      GROUP BY monthly
      ORDER BY monthly;
    </select>
  ---  
6. 부서별 월별 로그인 수  
![image](https://user-images.githubusercontent.com/50539146/185772751-45bf59d3-2689-4d8d-96e5-485c13cc8008.png)  
'''sql
  <!-- sql 부서별 로그인 수 -->
     <select id="selectDepartmentLogin" parameterType="string" resultType="hashMap">
      SELECT department, SUBSTR(createDate, 1, 4) AS monthly, COUNT(distinct(userID)) AS userCnt
      FROM statistic.requestinfo
      WHERE department = #{department} AND substring(createDate, 1, 4) = #{month}
      group by department
      order by department;
    </select>
### 5. 기타
