# 🏢 충북청년미래센터 시설물 예약 시스템

충북청년미래센터 근로자들을 위한 **회의실 및 시설물 예약 웹 서비스**입니다.  
사용자는 간편하게 시설을 예약하고, 중복 예약을 방지하며, 캘린더를 통해 실시간 예약 현황을 확인할 수 있습니다.

## 🚀 배포 주소 (Live Demo)
> **서비스 접속하기:** [https://booking-cbhope.onrender.com](https://booking-cbhope.onrender.com)
>
> *(Cloud: Render.com / DB: PostgreSQL)*

<br>

## ✨ 주요 기능

* **🔒 사용자 인증**
    * 회원가입 및 로그인 (Spring Security + BCrypt 암호화)
    * 로그인 여부에 따른 메뉴 접근 제어

* **📅 예약 시스템**
    * 시설물(상담실 1~3) 선택 및 날짜/시간 지정 예약
    * **중복 예약 방지 로직** (동일 시간대 예약 불가 처리)
    * 내 예약 내역 조회 (최신순 정렬)

* **📊 예약 현황판**
    * FullCalendar 라이브러리를 활용한 주간/월간 예약 현황 시각화
    * 시설물별 색상 구분으로 직관적인 확인 가능

<br>

## 🛠 기술 스택 (Tech Stack)

* **Backend:** Java 17, Spring Boot 3.x, Spring Security, Spring Data JPA
* **Database:** PostgreSQL
* **Frontend:** Thymeleaf, Bootstrap 5, FullCalendar.js
* **Infra & Deploy:** Docker, Render.com

<br>

## 📂 프로젝트 구조

```text
src
├── controller  # 웹/API 요청 처리
├── service     # 비즈니스 로직 (중복 검사 등)
├── domain      # 엔티티 (User, Facility, Reservation)
├── repository  # DB 접근 (JPA)
├── config      # 보안(Security) 및 초기 데이터 설정
└── dto         # 데이터 전송 객체