# 🏢 충북청년미래센터 시설물 예약 시스템

충북청년미래센터 근로자들을 위한 **회의실 및 시설물 예약 웹 서비스**입니다.  
사용자는 직관적인 UI를 통해 시설을 예약하고, 관리자는 전체 예약 현황을 효율적으로 관리할 수 있습니다.

## 🚀 배포 주소 (Live Demo)
> **서비스 접속하기:** [https://booking-cbhope.onrender.com](https://booking-cbhope.onrender.com)
>
> * **Cloud:** Render.com (Docker Container)
> * **DB:** Neon (Serverless PostgreSQL)
> * **Monitoring:** UptimeRobot (Cold Start 방지)

<br>

## ✨ 주요 기능

### 1. 🔒 사용자 권한 관리 (Security)
* **회원가입 및 로그인:** BCrypt 암호화를 통한 안전한 비밀번호 저장.
* **권한 분리 (Role-based):**
  * `USER`: 일반 예약 및 내 예약 조회.
  * `ADMIN`: 관리자 전용 페이지 접근 권한.

### 2. 📅 스마트 예약 시스템 (UX 개선)
* **시설물 예약:** '상담실 3' 시설물에 대한 예약 신청.
* **직관적인 시간 선택:**
  * **Flatpickr** 라이브러리를 도입하여 편리한 날짜 선택 (지난 날짜 방지).
  * **30분 단위**의 세밀한 예약 시간 설정 (시작 시간~종료 시간).
  * **동적 유효성 검사:** 이미 예약된 시간대는 선택할 수 없도록 자동 필터링.
* **중복 방지 로직:** 서버단에서 2차 검증을 통해 중복 예약을 원천 차단.

### 3. 👮 관리자(Admin) 기능
* **전체 예약 통합 관리:** 모든 사용자의 예약 내역을 최신순으로 조회.
* **예약 강제 취소:** 불필요하거나 잘못된 예약을 관리자가 직접 삭제 가능.

### 4. 📊 시각화 및 조회
* **예약 현황판:** FullCalendar.js를 활용하여 주간/월간 예약 현황을 한눈에 파악.
* **내 예약 관리:** 본인의 예약 내역을 확인하고 관리.

<br>

## 🛠 기술 스택 (Tech Stack)

### Backend
* **Java 17 / Spring Boot 3.x**
* **Spring Security:** 인증(Authentication) 및 인가(Authorization) 구현
* **Spring Data JPA:** ORM 기반 데이터 접근 및 객체 지향 쿼리 처리
* **Validation:** 데이터 유효성 검증

### Database
* **PostgreSQL (Neon Tech):** Serverless 클라우드 데이터베이스 연동

### Frontend
* **Thymeleaf:** 서버 사이드 렌더링
* **Bootstrap 5:** 반응형 UI 구성
* **JavaScript (ES6+):** 비동기 통신(Fetch API) 및 DOM 조작
* **Libraries:** FullCalendar, Flatpickr

### Infra & DevOps
* **Docker:** 컨테이너 기반 애플리케이션 패키징
* **Render:** 클라우드 플랫폼 배포 (CI/CD 자동화)

<br>

## 📂 프로젝트 구조

```text
src
├── controller  # 웹 페이지 및 API 요청 처리 (Admin, Booking, Main)
├── service     # 비즈니스 로직 (예약 중복 검사, 권한 변환 등)
├── domain      # JPA 엔티티 (User, Facility, Reservation, Role)
├── repository  # DB 접근 계층 (Repository Interface)
├── config      # 설정 (SecurityConfig, DataInit, PasswordEncoder)
└── dto         # 데이터 전송 객체 (BookingFormDto, TimeSlotDto)