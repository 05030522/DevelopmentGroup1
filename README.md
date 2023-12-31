# DevelopmentGroup1



개발하는데,1조하겠습니다_TeamProjectRepository



2023년 08월 07일 시작

2023년 08월 14일 종료



팀장 : 이경원님

팀원 : 권진혁님, 이승현님, 최신혜님





[🎶 팀 노션 페이지](https://www.notion.so/1-44ca40335c5b40f7aaf8c3830955c0c3)





🛠 프로젝트 주제

칸반 보드 기반 서비스로 유명한 Trello와 같은 “프로젝트 협업 사이트”를 만드는 것 입니다.





🛠 프로젝트 설계 단계

주제 선정 -> 요구 사항 확인 -> APT 작성 -> ERD 작성 -> 와이어 프레임 작성 -> 역할 배분 -> 협업 규칙 작성 -> PR할 Repository 생성




🛠 프로젝트 명

DevelopmentGroup1




💡 필수 요구 사항

**사용자 관리 기능**

1. 로그인 / 회원가입 기능

2. 사용자 정보 수정 및 삭제 기능


**보드 관리 기능**

1. 보드 생성

2. 보드 수정 (보드 이름, 배경 색상, 설명)
   
3. 보드 삭제 (생성한 사용자만 삭제를 할 수 있습니다.)
   
4. 보드 초대 (특정 사용자들을 해당 보드에 초대시켜 협업을 할 수 있어야 합니다.)


**컬럼 관리 기능**

1. 컬럼 생성 (보드 내부에 컬럼을 생성할 수 있어야 합니다.)

2. 컬럼 이름 수정

3. 컬럼 삭제

4. 컬럼 순서 이동 (컬럼 순서는 자유롭게 변경될 수 있어야 합니다.)

ex) Backlog, In Progress, Done → Backlog, Done, In Progress

  
**카드 관리 기능**

1. 카드 생성 (컬럼 내부에 카드를 생성할 수 있어야 합니다.)
   
2. 카드 수정 (카드 이름, 카드 설명, 카드 색상, 작업자 할당, 작업자 변경)
   
3. 카드 삭제
   
4. 카드 이동 (같은 컬럼 내에서 카드의 위치를 변경할 수 있어야 합니다. & 카드를 다른 컬럼으로 이동할 수 있어야 합니다.)

   
**카드 상세 기능**

1. 댓글 달기 (협업하는 사람들끼리 카드에 대한 토론이 이루어질 수 있어야 합니다.)
 
2. 날짜 지정 (카드에 마감일을 설정하고 관리할 수 있어야 합니다.)



💡 사용한 기술

<img width="148" alt="기술스택" src="https://github.com/05030522/DevelopmentGroup1/assets/132440453/5af3d483-e79c-4c76-a932-d523ba77e38d">



[💡 와이어 프레임](https://docs.google.com/presentation/d/1AIsqyn0MsqOcSKQ8VMwY3nK1SHSM6JjKOdSBWeQ9ZJQ/edit#slide=id.p)



💡 API

<img width="800" alt="api" src="https://github.com/05030522/DevelopmentGroup1/assets/121823367/8d1ee83e-2ea6-4b14-9bf0-693a209109c7">
<img width="800" alt="api" src="https://github.com/05030522/DevelopmentGroup1/assets/121823367/9e0d5752-a96d-4997-aefe-fb03bab29075">
<img width="800" alt="api" src="https://github.com/05030522/DevelopmentGroup1/assets/121823367/88875d62-2881-475a-afe0-ffa2dd954385">
<img width="800" alt="api" src="https://github.com/05030522/DevelopmentGroup1/assets/121823367/890890ab-8c1d-4918-999e-699857db23be">
<img width="800" alt="api" src="https://github.com/05030522/DevelopmentGroup1/assets/121823367/8d542124-2c5b-4828-b93a-15ce262553a3">



💡 ERD

![ERD](https://github.com/05030522/DevelopmentGroup1/assets/121823367/eaa0ab15-26a9-4ff4-aacb-cc25f85d37b6)



💡 역할 배분

이경원님- 컬럼 관리 기능

- 컬럼 생성 (보드 내부에 컬럼 생성)
  
- 컬럼 이름 수정
  
- 컬럼 삭제
  
- 컬럼 순서 이동

- ERD 작성



권진혁님- 보드 관리 기능

- 보드 생성
  
- 보드 수정 (이름, 색상, 설명)
  
- 보드 삭제 (생성한 사용자만 삭제 가능)
  
- 보드 초대 (협업할 사용자 초대)


  
이승현님- 사용자 관리 기능

- 회원가입
  
- 로그인

- JWT Securiyt 적용

- 사용자 정보 수정


  
최신혜님- 카드 관리 기능, 카드 상세 기능

- 카드 생성 (컬럼 내부에 카드 생성)
  
- 카드 수정 (이름, 설명, 색상, 작업자 할당/변경)
  
- 카드 삭제
  
- 카드 이동 (컬럼 내 위치 변경, 다른 컬럼으로 이동)
  
- 카드 댓글 (협업하는 사람들의 댓글)
  
- 마감일 지정
