# 🚀 GitHub Repo & Language API  

> Spring Boot 기반으로 구현한 REST API 서버로, GitHub API를 활용하여 특정 사용자의 **레포지토리 목록**과 **언어 사용 현황**을 조회합니다.  

---

## 📌 주요 기능
- 특정 GitHub 사용자의 **공개 레포지토리 목록 조회**  
- 각 레포지토리별 **언어(Language) 사용 비율 조회**  
- 결과를 JSON 포맷으로 반환  

---

## 🛠 기술 스택
- **Backend**: Spring Boot  
- **Build Tool**: Gradle  
- **Language**: Java 21  
- **HTTP Client**: RestTemplate  
- **External API**: GitHub REST API v3  

---

## 📂 API 명세

### 1. 사용자 레포지토리 조회
`GET /api/github/repos/{username}`  

**응답 예시**
```json
[
  {
    "name": "my-project",
    "url": "https://github.com/example/my-project",
    "stars": 10,
    "forks": 2,
    "language": "Java"
  },
  {
    "name": "data-analysis",
    "url": "https://github.com/example/data-analysis",
    "stars": 5,
    "forks": 1,
    "language": "Python"
  }
]
