# 작업 규칙
1. 각 작업자는 main 브랜치에 머지 / 푸시 하지 않는다.
2. 각 작업자는 [project](https://github.com/orgs/spartawhatisgoingon/projects/1/views/1)) 의 `issue` 의 일감 번호를 이용해 `branch` 를 생성한다.
3. 각 작업자는 본인의 작업 완료된 branch 를 remote 에 push 후 dev 브랜치에 PR 를 올린다.
4. 최소 한 명 이상의 approve 가 있으면 dev 로 머지한다.
5. 하루에 한 번 main 브랜치로 dev 를 머지한다.

# 브랜치 생성 및 커밋 규칙
1. [project](https://github.com/orgs/spartawhatisgoingon/projects/1/views/1)) issue 의 일감 번호를 이용해 브랜치를 생성해야함
2. `[#issue-number] '작업 타입': '작업 내용'`
3. 한글로 작업 내용 작성

# 커밋 규칙

작업 타입	작업내용
✨ update:  	해당 파일에 새로운 기능이 생김
🎉 add:	없던 파일을 생성함, 초기 세팅
🐛 bugfix:	버그 수정
♻️ refactor:	코드 리팩토링
🩹 fix:	코드 수정
🚚 move:	파일 옮김/정리
🔥 del:	기능/파일을 삭제
🍻 test:	테스트 코드를 작성
💄 style:	css
🙈 gitfix:	gitignore 수정
🔨script:	package.json 변경(npm 설치 등)
