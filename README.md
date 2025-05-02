# 간단한 게시판 프로젝트

## Docker로 MySQL 8.0.26 서버를 띄우기 위한 설정
```yaml
version: "3"
services:
  db:
    image: mysql:8.0.26
    restart: always
    command:
      - --lower_case_table_names=1
      - --character-set-server=utf8mb4
      - --collation-server=utf8mb4_unicode_ci
    container_name: mysql
    ports:
      - "3306:3306"
    environment:
      - MYSQL_DATABASE=${MYSQL_DATABASE}
      - MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD}
      - TZ=${TZ}
    volumes:
      - ${MYSQL_VOLUME_PATH}:/var/lib/mysql
```

## EER Diagram
![Image](https://github.com/user-attachments/assets/9616b9c4-8ada-4b29-b6dc-a43846b68022)

## REST API

### 게시글(Post) 기준 CRUD

게시글 기준으로 CREATE REST API를 실습하기 위해, 게시판(Board)를 미리 생성해둔다.
- CREATE
```
METHOD POST
URL http://localhost:8080/api/post
BODY
{
  "board_id" : 1,
  "user_name" : "최명수",
  "password" : "1111",
  "email" : "audtn@naver.com",
  "title" : "문의드립니다.",
  "content" : "사이즈가 맞지 않아 교환하고 싶습니다."
}
```
```json
{
    "id": 1,
    "board_id": 1,
    "user_name": "최명수",
    "password": "1111",
    "email": "audtn@naver.com",
    "status": "REGISTERED",
    "title": "문의드립니다.",
    "content": "사이즈가 맞지 않아 교환하고 싶습니다.",
    "posted_at": "2025-05-02T13:11:51.9239442",
    "reply_list": [
    ]
}

```

게시글 기준으로 READ REST API를 실습하기 위해, 게시글 답변(Reply)를 미리 생성해둔다. 
- READ
```
METHOD GET
URL http://localhost:8080/api/post/id/1
```
```json
{
    "id": 1,
    "board_id": 1,
    "user_name": "최명수",
    "password": "1111",
    "email": "audtn@naver.com",
    "status": "REGISTERED",
    "title": "문의드립니다.",
    "content": "사이즈가 맞지 않아 교환하고 싶습니다.",
    "posted_at": "2025-05-02T13:11:52",
    "reply_list": [
        {
            "id": 1,
            "post_id": 1,
            "user_name": "관리자",
            "password": "1111",
            "status": "REGISTERED",
            "title": "답변드립니다.",
            "content": "사이즈 변심으로 인한 교환 시 ~ ",
            "replied_at": "2025-05-02T13:15:15"
        }
    ]
}
```

전체 게시글을 조회하기 위해 게시글을 추가로 미리 생성해둔다.
- READ ALL
```
METHOD GET
URL http://localhost:8080/api/post/all
```
```json
{
    "body": [
        {
            "id": 2,
            "board_id": 1,
            "user_name": "김지원",
            "password": "1111",
            "email": "wldnjs@naver.com",
            "status": "REGISTERED",
            "title": "문의드립니다.",
            "content": "배송 완료라는데 배송이 아직 오지 않았어요.",
            "posted_at": "2025-05-02T13:18:37",
            "reply_list": [
            ]
        },
        {
            "id": 1,
            "board_id": 1,
            "user_name": "최명수",
            "password": "1111",
            "email": "audtn@naver.com",
            "status": "REGISTERED",
            "title": "문의드립니다.",
            "content": "사이즈가 맞지 않아 교환하고 싶습니다.",
            "posted_at": "2025-05-02T13:11:52",
            "reply_list": [
                {
                    "id": 1,
                    "post_id": 1,
                    "user_name": "관리자",
                    "password": "1111",
                    "status": "REGISTERED",
                    "title": "답변드립니다.",
                    "content": "사이즈 변심으로 인한 교환 시 ~ ",
                    "replied_at": "2025-05-02T13:15:15"
                }
            ]
        }
    ],
    "pagination": {
        "page": 0,
        "size": 10,
        "currentElement": 2,
        "totalPages": 1,
        "totalElements": 2
    }
}

```

- UPDATE
```
METHOD PUT
URL http://localhost:8080/api/post/id/2
BODY
{
  "board_id" : 1,
  "user_name" : "김지원",
  "password" : "1111",
  "email" : "wldnjs@naver.com",
  "title" : "문의드립니다.",
  "content" : "죄송합니다, 배송이 왔는데 확인을 못했습니다."
}
```
```json
{
    "id": 2,
    "board_id": 1,
    "user_name": "김지원",
    "password": "1111",
    "email": "wldnjs@naver.com",
    "status": "REGISTERED",
    "title": "문의드립니다.",
    "content": "죄송합니다, 배송이 왔는데 확인을 못했습니다.",
    "posted_at": "2025-05-02T14:06:27.3846297",
    "reply_list": [
    ]
}

```

- DELETE
```
METHOD DELETE
URL http://localhost:8080/api/post/id/2
```