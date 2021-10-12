# Shake Voice Recorder
기기를 흔들어 음성 녹음을 할 수 있는 안드로이드 어플입니다.<br>
2020년 2학기 모바일 프로그래밍 과목의 기말 과제입니다.<br>
아래 readme 내용은 ***정말로 과제와 함께 제출한 설명 문서***입니다. <br>
해당 프로젝트의 기능을 보여드리기에는 부족함 없는 문서이며 그 때 가지고 있던 생각들을 보존하기 위해 별도의 수정을 거치지 않고 그대로올립니다.<br>

# 0. 본 설명 문서를 읽기 전에

교수님, 제가 고안한 특별한 기능들을 구현하기에는 더 많은 학습과 시간을 요구하기 때문에 미처 구현하지 못 하였습니다. 

초기에 녹음 기능 구현을 굉장히 짧게 계획하였으나 녹음 기능을 구현하는데 굉장히 많은 시간을 소비하였으며 안드로이드 생명 주기에 대하여 조금 더 자세히, 깊게 학습하게 되어 미처 어플의 핵심인 흔들어서 녹음하는 기능은 구현하지 못하게 되었습니다. 

굳이 밝히지 않고 평범한 녹음 어플이라 과제를 소개할 수 있었으나 추후에 조금 더 다듬어 제가 고안한 기능들을 추가한 후 플레이 스토어에 등록하는 것이 이번 수업의 목표이기에 이렇게 서슴없이 밝힙니다. 

교수님께서 설명 문서의 양식은 지정해주지 않아 이렇게 pdf로 제출합니다. 한글 프로그램을 사용하고 싶었으나 현재 개인 pc의 문제로 한글을 사용할 수 없었으며 이 시기에 pc방 등 다른 방법을 사용할 수 없었기에 이렇게 부득이하게 pdf로 제출합니다.

한 학기 정말 수고 많으셨습니다.

혹시나 제가 제출한 파일이 제대로 실행되지 않을 것 같은 염려가 되어
이렇게 제가 촬영한 영상의 주소와 제 작업물의 깃허브 주소를 남깁니다.

- 🎬 시연 영상 : [https://youtu.be/nC-77QgWh8Q](https://youtu.be/nC-77QgWh8Q)
- 💻 GitHub : [https://github.com/paperfrog1228/Shake-Voice-Recorder](https://github.com/paperfrog1228/Shake-Voice-Recorder)

# 목차

- [1. 어플 소개](#1-어플-소개)
- [2. 과제 구현 내용](#2-과제-구현-내용)
- [3. 어플 테스트 하는 방법](#3-어플-테스트-하는-방법)
- [4. 프로젝트를 마치며](#4-프로젝트를-마치며)

# 1. 어플 소개

## 1. Shake Voice Recorder(SVR)?

Shake Voice Recorder 이하 쉐보레(SVR)는 모바일 기기를 흔들어서 녹음을 실행하는 어플이다.

이러한 어플을 고안한 이유는 다음과 같다.

1. 메뉴로 들어가 실행하는 것보다 빠르게  실행할 수 있다.
2. 내가 녹음을 한다는 것을 들키지 않고 녹음 할 수 있다.

본인은 과거 야간 편의점 아르바이트를 한 적이 있었는데 그 과정에서 잦은 분쟁으로 인해 녹음 어플을 자주 사용하였다. 

그러나 메뉴를 통해 어플을 찾는 것은 격앙된 감정으로 인하여 쉽지 않았으며 어플을 실행하고 녹음을 시작하는 과정에서 손님에게 질책을 당했었다. 

그로 인하여 위와 같은 동기들을 생각하였고 마침 이번 학기 수업인 '모바일 프로그래밍'의 기말 과제를 통해 SVR을 만들기로 하였다.

## 2. 기능 소개

이번 프로젝트에서는 bottom navigation 인터페이스를 사용하였으며 아래와 같은 4가지의 기능을 메뉴로 가지고 있다.

- 녹음 목록 메뉴 (초기 화면)
- 즐겨찾기 목록 메뉴
- 녹음 메뉴
- 설정 메뉴

이러한 인터페이스를 고른 이유는 요 근래의 어플들이 다 bottom navigation을 채택하고 있기에 한 번 사용해보고 싶어서 사용하였으며 별 다른 기능이 없는 이 어플에 적합하고 수업시간에 열심히 배운 fragment를 적극 사용 할 수 있을 것 같아서 채택하였다.

### 1) 녹음 기능

하드웨어의 기능을 통해 음성을 녹음한다. 에뮬레이터로 동작 시 테스트가 불가능하다.

- 대기 상태

![image](https://user-images.githubusercontent.com/11247319/136947785-2be95f3f-e6b6-4e6c-a195-6c90152d72f8.png)

1. 파일 이름 설정 : 저장 될 녹음 파일의 이름을 설정할 수 있습니다. 기본 설정은 현재 날짜와 시간이다.
2. 녹음 시간 타이머 : 녹음 시 녹음 시간을 측정한다. (시 : 분 : 초)
3. 즐겨찾기 : 저장 시 즐겨찾기 유무를 설정 한다. 추후 녹음 파일 목록에서 수정이 가능하다.
4. 녹음 버튼 : 녹음의 시작과 일시 정지 기능을 제공한다.
5. 정지 버튼 : 녹음을 종료하고 파일을 저장한다. 녹음 시작 전 까지는 비활성화 상태이다.

- 녹음 상태

![image](https://user-images.githubusercontent.com/11247319/136947915-9e512f08-8625-4020-92d2-59b429643088.png)

1. 파일 이름 설정 : 녹음이 시작 되면 파일 이름의 변경은 불가능하다.
2. 버튼 : 녹음 시 시작 버튼은 일시 정지 버튼으로 변경되고 정지 버튼이 활성화된다.
3. 알림 : 녹음 중/일시정지 중에 다른 메뉴로 선택 시  뜨는 알림 메세지이다.
녹음 중과 일시정지 중에는 다른 메뉴를 선택 할 수 없다.

### 2) 녹음 파일 목록

본 어플을 통해 녹음한 파일을 조회, 재생 할 수 있다.

- 녹음 파일 목록

![image](https://user-images.githubusercontent.com/11247319/136948036-ecdccdf5-b344-46cf-b75d-d2921855040a.png)

1. 검색 기능 : 파일의 이름을 통해 검색이 가능합니다. 현재는 파일의 이름으로만 검색이 가능하다.
2. 즐겨찾기 설정/해제 : 버튼을 통해 쉽게 즐겨찾기 설정, 해제가 가능하다.
3. 파일 정보 : 파일의 이름, 녹음 날짜와 시간, 녹음된 파일의 길이를 보여준다.
4. 녹음 파일 삭제 : 녹음된 파일을 삭제한다.

- 녹음 파일 재생


![image](https://user-images.githubusercontent.com/11247319/136948165-aa8fa7bd-e274-49e9-aa97-a580d5c20634.png)

1. 파일 이름 : 선택한 파일의 이름을 보여준다
2. 재생/일시정지 버튼 : 파일을 재생, 일시 정지한다.
3. 현재 재생 시간 : 재생 중인 파일의 현재 재생 시간이다.
4. 파일의 길이 : 파일의 길이이지만 내부적인 처리 실패로 목록에서 보여주는 길이와 다르다..
또한 저 재생 시간을 보여주는 바는 실제로 시간에 맞추어 움직이지만 저 바를 조정하여 원하는 시간에서의 재생은 구현하지 못하였다.

### 3) 즐겨찾기 파일 조회

녹음 파일 조회와 모든 기능이 유사하며 즐겨찾기 처리를 한 녹음 파일 들만 모아서 볼 수 있다.

이 메뉴에서도 동일하게 녹음 파일을 재생 할 수 있다.

![image](https://user-images.githubusercontent.com/11247319/136948204-827551d3-5a2d-46bd-b785-681776a29298.png)


이 메뉴에서 파일 이름으로 검색 시 즐겨찾기한 파일만 검색되도록 하였으며 파일의 삭제 시 파일 목록의 메뉴에서도 파일이 삭제된다.

### 4) 설정

![image](https://user-images.githubusercontent.com/11247319/136948325-e41660bb-c53f-467e-a5cd-ad4f51b04f84.png)

녹음 파일 포맷(저장 파일 확장자)와 샘플링 레이트를 선택 할 수있다. 

# 2. 과제 구현 내용

## 1. 데이터베이스 설계

초기 계획에서는 녹음 파일 목록에서 보여줄 테이블과 즐겨찾기 파일 목록에서 보여줄 테이블, 이렇게 2개의 테이블로 계획했었다. 

그러나 이번 프로젝트에서는 최대한 구조를 간단하게 하기 위해서 즐겨찾기를 테이블이 아닌 속성으로 설정하여 record란 하나의 테이블로 구성하였다.

### record 테이블
![image](https://user-images.githubusercontent.com/11247319/136948460-ac172d54-493d-4d54-bb31-00158fd1b040.png)


bookmark의 경우 BOOLEAN 타입으로 저장하고 싶었으나 SQLite에서는 BOOLEAN 데이터 타입을 지원하지 않아서 결국 TEXT 타입으로 저장하였고 '0'과 '1'의 값으로 표현하였다.

데이터베이스 내에서 기본키를 _id로 설정하였으나 본인은 별도로 item에 _id를 저장하는 것이 구조적으로 복잡하다고 판단하여 date를 기본키로 활용하였다.

## 2. 데이터베이스 입력, 수정, 삭제

### 1) 입력

이번 프로젝트에서 데이터베이스 입력(insert) 과정은 오직 녹음 파일을 저장하는 동작에서만 일어난다. 

![image](https://user-images.githubusercontent.com/11247319/136948521-155af63b-888b-4087-bc65-b5f7390579ae.png)

### 2) 수정

이번 프로젝트에서 데이터베이스 수정(update)은 오직 즐겨찾기를 설정/해제 하는 동작에서만 일어난다.

![image](https://user-images.githubusercontent.com/11247319/136948581-cab6842c-0ad8-4148-bf3c-212fb77cfe08.png)


```java
public void bookmarkRecord(String date) {
        int bookmark = 0;
				//date를 기본키로 사용함.
        String sql = "select * from record where date ='" + date + "'";
        Cursor cursor = RecordDB.getInstance().DB.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int tmp = cursor.getInt(cursor.getColumnIndex("bookmark"););
            if (tmp == 1)//기존이 1이었으면 0으로 바꾸고 
                bookmark = 0;
            else//1이 아니었으면(0) 1로 바꾸어준다.
                bookmark = 1;
            DB.execSQL("update record set bookmark ="+bookmark+" where date ='" + date + "';");
        }
        cursor.close();
    }
```

### 3) 삭제

이번 프로젝트에서 데이터베이스 삭제(delete)는 녹음 파일 목록에서 삭제 시에만 일어난다.

![image](https://user-images.githubusercontent.com/11247319/136948656-932ecbda-122e-46ea-a3c3-ae82d0be00fc.png)


## 3. 조회 및 검색 기능

### 1) 조회

위의 기능 소개에서 소개한 녹음 파일 목록 메뉴와 즐겨찾기 메뉴에서 사용하였다. 

```sql
select * from record //녹음 파일 목록 조회 SQL
select * from record where bookmark = 1 //즐겨찾기 파일 목록 조회 SQL
```

### 2) 검색

위의 기능 소개에서 소개한  녹음 파일 목록 메뉴와 즐겨찾기 메뉴 검색에서 사용하였따.

간단하게 search view + sql select를 통하여 구현하였다.

```sql
//newText 검색한 텍스트
select * from record where name like %newText% //녹음 파일 검색 SQL
select * from record where bookmark = 1 and name like %newText% //즐겨찾기 검색 SQL
```

# 3. 어플 테스트 하는 방법

## 1. 각종 설정 파일 확인해주세요!

### 1) AndroidManifest.xml

녹음과 저장 때문에 아래와 같은 권한을 요구합니다.

```xml
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.RECORD_AUDIO"/>
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
```

### 2) build.gradle

```java
implementation 'androidx.appcompat:appcompat:1.2.0'
implementation 'com.google.android.material:material:1.2.1'
implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
implementation 'com.android.support:design:28.0.0' //이 부분을 추가하였습니다.
testImplementation 'junit:junit:4.+'
androidTestImplementation 'androidx.test.ext:junit:1.1.2'
androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'
```

## 2. 앱 처음 시작 시 요청하는 권한을 수락해주세요!

![image](https://user-images.githubusercontent.com/11247319/136948834-3dfac372-6715-4297-b4eb-873612da462d.png)

만약 여기서 거절 시 기기의 설정에서 별도로 설정해야합니다.

## 4. 구동 환경에 따라 다른 퍼포먼스를 보여줍니다..

제가 테스트한 구동 환경으로는 다음과 같으며 모두 정상적으로 작동하였습니다.

- Pixel 3a (API 26) - 안드로이드 스튜디오 에뮬레이터
- LG G6 (API ??) - 친구의 핸드폰

외부 저장 장치가 존재하지 않을 시 등 여러 예외 처리를 하지 못해서 그러한 것 같습니다.

또한 에뮬레이터로는 녹음 기능을 테스트 하실 수 없습니다.

## 5. 그래도 안 된다면?

그래서 제가 영상을 촬영해놓았습니다! 다음 유튜브 영상을 참고해주십시오!

시연 영상 : 🎬 [https://youtu.be/nC-77QgWh8Q](https://youtu.be/nC-77QgWh8Q)

# 4. 프로젝트를 마치며

## 1. 향후 추가할 점&고칠 점

1. 시간이 부족해 구현하지 못한 흔들어 녹음하는 기능을 추가
2. 예약 시간에 맞춰 녹음을 하는 기능 추가
3. 타이머의 시간과 실제 녹음 파일의 시간이 일치하지 않는데 쓰레드를 깊게 이해하지 못하여 못 고치겠음
4. ad를 붙여 플레이스토어에 출시 해보고 싶음

## 2. 모바일 프로그래밍을 마치며

우선 여름 방학 때 공모전으로 인하여 생전 처음으로 안드로이드 어플을 개발하게 되었는데 그 때도 시간이 부족해 코틀린을 사용해 안드로이드 개발을 맛만 보았다.

개인적으로 자료도 java가 더 풍부하였고 컴퓨터 공학도라면 적어도 java로 안드로이드를 개발한 경험이 있어야한다고 생각한 찰나에 '모바일 프로그래밍'이라는 수업을 들을 수 있어서 다행인 것 같다.

평소에 절대 찾아서 공부하지 않을 쓰레드나 애니메이션, 네트워크 연결 등 잘 짜여진 커리큘럼의 수업이었기에 재밌게 수강한 것 같다.

이 쪽  개발 스택은 취향이 아니라 최대한 피하기 바빴는데 이번 수업을 통해서 조금은 안드로이드 개발에 대한 관심이 생겼다.

이러한 훌륭한 수업을 진행해주신 교수님께 감사를 표한다.
