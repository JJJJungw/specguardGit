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
```http
GET /api/github/repos/{username}
응답 예시
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

사용자 언어 통계 조회
GET /api/github/languages/{username}
응답 예시
{
  "Java": 38193,
  "Python": 12247,
  "JavaScript": 195124,
  "HTML": 114719,
  "CSS": 364134
}

실행 방법

1. 저장소 클론
git clone https://github.com/your-github-id/github-repo-language-api.git
cd github-repo-language-api

2. 환경 변수 설정
application.yml 또는 application.properties 에 GitHub Personal Access Token을 등록.

3. 서버 실행
📊 확장 아이디어

📈 레포지토리별 최근 커밋 수 / 업데이트 주기 분석

⭐ Fork/Star 기반 인기 레포지토리 정렬 기능

🎨 프론트엔드 대시보드와 연동 (Chart.js, Recharts 등)

🧑‍💻 사용자별 기술 스택 프로필 자동 생성

👩‍💻 Author

Your Name

GitHub: @your-github-id
