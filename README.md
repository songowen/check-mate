# 📝 CHECKMATE(체크메이트) - 계약서 분석 및 작성 지원 플랫폼

<p align="center">
  <img src="https://github.com/user-attachments/assets/23d68481-3588-4bce-8d1e-70e88eedf9a0"/>
</p>

---

## 📚 목차
1. [🧭 프로젝트 소개](#프로젝트-소개)
2. [🎯 주요 화면 및 기능 소개](#주요-화면-및-기능-소개)
3. [🔧 개발 환경](#개발-환경)
   - [🖥️ 기술 스택](#기술-스택)
   - [📊 ERD](#erd)
   - [📦 아키텍처](#아키텍처)
   - [🖼️ 와이어프레임](#와이어프레임)
4. [👥 팀원 소개](#팀원-소개)

---

## 🧭 프로젝트 소개

- **서비스 명**: CHECKMATE(체크메이트)
- **소개**: SSAFY 12기 2학기 자율 프로젝트
- **기간**: 2025.04.14 ~ 2025.05.22 (39일)
- **기획 배경**:  
  사회 초년생들이 불공정한 계약을 피할 수 있도록, 계약서 작성 및 분석을 지원하는 서비스 제공


<br/>

# 🎯 주요 화면 및 기능 소개

#### 메인화면
![image](docs/asset/메인페이지.gif)
- 체크메이트 서비스를 설명하는 내용으로 구성
- 페이지의 최하단에는 네이버 뉴스 API를 이용하여 계약 관련된 뉴스를 제공
- 네이버 뉴스를 Redis에 캐싱하여 API 호출

#### 계약서 분석
![분석페이지](https://github.com/user-attachments/assets/623d5ec5-5492-42e9-9bb4-85857129c6c2)
![분석 업로드](https://github.com/user-attachments/assets/ad4cc287-e18a-4f83-b919-2c1fc282fd13)


- 계약서 유형을 선택하면 업로드 할 수 있는 페이지로 전환
- 업로드 페이지에서 HWP, PDF, JPG, PNG 확장자의 계약서를 업로드 할 수 있고, 업로드 되면 ClamAV를 통해 바이러스 검사 진행
- 안전한 파일인게 확인되면 AES-GCM 암호화 하여 DEK를 이용해 2-of-2 방식으로 각기 다른 데이터베이스에 분산 저장장

![image](docs/asset/분석페이지.gif)
- 분석이 완료되면 WebSocket+Stomp를 이용하여 실시간 알림을 제공
- 분석 결과는 LangChain에 OpenAPI를 이용하여 비동기 병렬형태로 진행
- 분석 프롬프트는 RAG를 이용하여 사전 저장된 Qdrant 법률 데이터를 이용하여 벡터 조회를 통해 나온 결과를 프롬프트에 증강하여 신뢰성과 안정성 증가

#### 계약서 작성 가이드
![작성 가이드](https://github.com/user-attachments/assets/545fd415-eb39-454e-b496-2acfa5f37b0a)
- 계약서를 작성할 때, 어떤 순서로 작성을 하면 되는지에 대한 가이드 페이지

#### 계약서 작성 전 주의사항
![작성 전 주의사항](https://github.com/user-attachments/assets/4eebacd6-8498-4815-b4f5-f3ad9995606e)
- 계약서 작성 전 주의사항을 모달창으로 제공

#### 계약서 작성
![계약서 작성](https://github.com/user-attachments/assets/f0023d7a-c8bf-411b-9b8a-dedf1b851a62)
![250526 (1)](https://github.com/user-attachments/assets/fabaf4f7-bca9-4923-b091-2e6efcd8b7ed)

- 카테고리 별 계약서 템플릿을 제공하여 사용자가 필드에 값을 채우면 계약서를 제공
- 자동저장 기능을 통해 이전에 작성한 내용도 쉽게 확인

#### 마이페이지
![image](docs/asset/마이페이지.gif)
![image](docs/asset/법원.gif)
- 마이페이지의 대시보드에는 계약 활동, 알림, 최근 활동 내역을 제공
- 내 계약서 탭으로 이동하면 사용자가 진행했던 계약서 관련 상태를 확인
- d3.js를 통해 지도를 시각화하여 렌더링하는 시간을 감소

#### 계약서 저장
![최종 계약서](https://github.com/user-attachments/assets/d8579639-5d18-49b8-9bad-e82b8e7cd392)

- 저장하기 버튼을 누르면 pdf형태로 사용자에게 제공

#### 전자서명
- DropSignAPI를 이용하여 전자서명 구현


<br/>

# 👻 개발 환경

## 기술스택

### Frontend
<div style="display: flex; gap: 8px;">
  <img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=React&logoColor=white"/>
  <img src="https://img.shields.io/badge/Typescript-3178C6?style=for-the-badge&logo=Typescript&logoColor=white"/>
  <img src="https://img.shields.io/badge/Tailwind%20CSS-06B6D4?style=for-the-badge&logo=Tailwind%20CSS&logoColor=white"/>
</div>

### Backend
<div style="display: flex; gap: 8px;">
  <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"/>
  <img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white"/>
  <img src="https://img.shields.io/badge/ClamAV-0079C1?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/AES--GCM-000000?style=for-the-badge&logo=lock&logoColor=white"/>
</div>

### AI
<div style="display: flex; gap: 8px;">
  <img src="https://img.shields.io/badge/FastAPI-009688?style=for-the-badge&logo=fastapi&logoColor=white"/>
  <img src="https://img.shields.io/badge/LangChain-1C3C3C?style=for-the-badge&logo=LangChain&logoColor=white"/>
  <img src="https://img.shields.io/badge/OpenAI-412991?style=for-the-badge&logo=openai&logoColor=white"/>
</div>

### Database
<div style="display: flex; gap: 8px;">
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
  <img src="https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white"/>
  <img src="https://img.shields.io/badge/Redis-FF4438?style=for-the-badge&logo=redis&logoColor=white"/>
  <img src="https://img.shields.io/badge/Qdrant-000000?style=for-the-badge"/>
</div>

### Infra/DevOps
<div style="display: flex; gap: 8px;">
  <img src="https://img.shields.io/badge/EC2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white"/>
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"/>
  <img src="https://img.shields.io/badge/AWS%20S3-569A31?style=for-the-badge&logo=Amazon%20S3&logoColor=white"/>
  <img src="https://img.shields.io/badge/CloudFront-232F3E?style=for-the-badge&logo=Amazon%20CloudFront&logoColor=white"/>
  <img src="https://img.shields.io/badge/GitLab%20Runner-FC6D26?style=for-the-badge&logo=gitlab&logoColor=white"/>
</div>

### 협업 툴
<div style="display: flex; gap: 8px;">
  <img src="https://img.shields.io/badge/Mattermost-0058CC?style=for-the-badge&logo=mattermost&logoColor=white"/>
  <img src="https://img.shields.io/badge/GitLab-FC6D26?style=for-the-badge&logo=gitlab&logoColor=white"/>
</div>

### 📊 ERD
![image](https://github.com/user-attachments/assets/b8f9cc39-b694-4c36-8ec2-de06ac026c9f)

### 📦 아키텍처
![image](https://github.com/user-attachments/assets/9ccc789a-1986-4459-aec7-d4fdaf42e1b0)

### 🖼️ 와이어프레임(클릭시 이동)
[![image](https://github.com/user-attachments/assets/80667f0d-3b1e-4015-8f8f-47e0cf32d250)](https://www.figma.com/design/CHnjSMdCjRKn3YB7XsvbOO/%EC%9E%90%EC%9C%A8_C103?node-id=0-1&t=VY2dEct9sjRjxOga-1)


<br/>


## 👥 팀 구성

<table>
  <tbody>
    <tr align="center">
      <td><img src="https://avatars.githubusercontent.com/u/29938746?v=4" width="100px;" style="border-radius: 50%;" alt=""/><br /></td>
      <td><img src="https://avatars.githubusercontent.com/u/125804214?v=4" width="100px;" style="border-radius: 50%;" alt=""/><br /></td>
      <td><img src="https://avatars.githubusercontent.com/u/175274988?v=4" width="100px;" style="border-radius: 50%;" alt=""/><br /></td>
      <td><img src="https://avatars.githubusercontent.com/u/175382620?v=4" width="100px;" style="border-radius: 50%;" alt=""/><br /></td>
      <td><img src="https://avatars.githubusercontent.com/u/145769307?v=4" width="100px;" style="border-radius: 50%;" alt=""/><br /></td>
      <td><img src="https://avatars.githubusercontent.com/u/105963431?v=4" width="100px;" style="border-radius: 50%;" alt=""/><br /></td>
    </tr>
    <tr align="center">
      <td width="200"><a href="http://github.com/yj901010">팀장 : 이영재<br/>INFJ</a></td>
      <td width="200"><a href="http://github.com/hjkim2040">팀원 : 김성찬<br/>ISFP</a></td>
      <td width="200"><a href="https://github.com/sonseohy">팀원 : 손서현<br/>ISTP</a></td>
      <td width="200"><a href="https://github.com/tytomko">팀원 : 고태연<br/>ENTJ</a></td>
      <td width="200"><a href="https://github.com/songowen">팀원 : 송창현<br/>ISTP</a></td>
      <td width="200"><a href="https://github.com/newww-a">팀원 : 신승아<br/>ENFP</a></td>
    </tr>
    
  </tbody>
</table>

<br/>
