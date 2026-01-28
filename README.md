# BackEndArena 🚀

BackEndArena is a scalable **coding-judge backend system** built using **Java + Spring Boot**, designed to support real-world competitive programming / interview-style problem execution.

It follows a **microservice-inspired architecture**(future) where the backend submits code to an isolated **Execution Engine service**, receives results, evaluates verdicts, and persists submission history.

---

## ✨ Features

✅ **JWT Authentication & Role-based Access**  
✅ **Problem Attempt + Submission Tracking**  
✅ **Remote Code Execution via Execution Engine (separate service/container)**  
✅ **Runs multiple test cases per submission**  
✅ **Verdict Evaluation (AC / WA / TLE / RTE)**  
✅ **Stores verdict + runtime + results in PostgreSQL**  
✅ **Docker Compose setup for local demo**

---

## 🏗️ Architecture

This repository is a **monorepo** containing 3 major modules:

### 1️⃣ BackendArena Backend (`/bea`)
- Receives submission requests
- Fetches test cases (planned)
- Calls execution engine
- Computes final verdict
- Stores submission + verdict in DB

### 2️⃣ Execution Contract (`/execution-contract`)
Shared contract module containing common DTOs like:
- `ExecutionRequest`
- `ExecutionResult`
- `TestCase`
- `TestCaseExecutionResult`

Used by both backend + execution engine.

### 3️⃣ Execution Engine (`/execution-engine`)
- Compiles and runs code in a temporary sandbox folder
- Executes code per test case
- Handles timeout (TLE)
- Returns structured `ExecutionResult`

---

## ✅ Verdict Rules

| Condition | Verdict |
|----------|---------|
| Compilation fails | Compilation Error |
| Exit code = -1 | TLE |
| Exit code != 0 | RTE |
| Output mismatch | WA |
| All testcases passed | AC |

---

## ⚙️ Tech Stack

**Backend**
- Java 17
- Spring Boot
- Spring Security + JWT
- REST APIs
- Hibernate / JPA
- PostgreSQL
- Maven

**Execution Engine**
- Java ProcessBuilder (javac + java)
- Temp directory sandbox execution
- Timeout handling (per test case)

**DevOps**
- Docker & Docker Compose
- GitHub

---

## 🧪 API Demo Flow

### ✅ Submit Code
`POST /api/user/submissions`

Example body:
```json
{
  "attemptId": 5,
  "language": "JAVA",
  "code": "public class Main { public static void main(String[] args){ System.out.println(\"Hello\"); } }"
}

Backend:

Saves submission in DB

Calls execution engine

Receives execution result

Evaluates verdict

Updates submission status + verdict

Get Submission Result

GET /api/user/submissions/{submissionId}

Example response:

{
  "submissionId": 25,
  "status": "COMPLETED",
  "verdict": "WA",
  "executionTimeMs": 195
}

🛠️ Current Status / Roadmap

✅ Backend ↔ Execution Engine integration completed
✅ Verdict evaluation working
✅ Submission tracking API working

🚧 Coming next:

Add problem_test_cases table

Map DB testcases → contract TestCase at runtime

Store per-testcase output details

Add better sandbox security (limits / memory isolation)

👨‍💻 Author

Built by Nishit Gajbhiye
(Java Backend Developer)

If you're a recruiter and want a walkthrough/demo, feel free to reach out 🙂

