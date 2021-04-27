package com.mrhi2020.ex61broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // Android Application 의 4대 구성요소[Component] - AndroidManifest.xml의 <application>태그안에 반드시 등록해야만 사용할 수 있는 주요 클래스들 ]
    // 1. Activity  - 화면 담당 클래스
    // 2. BroadcastReceiver - 디바이스의 특정 상태[문자수신, 배터리부족, 부팅완료, gps 상태정보 등등..]를 OS 에서 방송해주면 이를 수신할 때 사용
    // 3. Service - 빽그라운드(앱은 실행 중- 화면에서 안보이는)에서 코드를 동작하게 하고 싶을 때 사용. [ex. 뮤직플레이어 앱]
    // 4. Contents Provider - 다른앱에게 나의 DB 정보를 제공할 때 사용 - 여러분들이 거의 사용할 일이 없음.

    //이 예제는 브로드캐스트 리스버의 수신 연습을 위해서..
    //원래는 OS[운영체제:android]가 방송(broadcast)를 해야 하지만..
    //이 앱에서 버튼을 눌러서 방송(broadcast) 해보고...이를 수신해보기..
    //다음 예제에서 실제 OS의 방송[부팅완료]을 수신해보기..

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBtn(View view) {
        //방송 보내기.. [4대 컴포넌트 중 Activity, Service, Broadcast receiver 는 모두 Intent 로 실행]
        //원래는 OS가 방송하는 것임... 연습으로 ...여기서 강제로 방송 실행

        //1. 명시적 인텐트 [class명을 직접작성] - 같은 앱안에 있는 리시버만 듣는 방송 Intent
        // 4대 구성요소는 반드시 Manifest.xml 에 등록되어 있어야만 함.
//        Intent intent= new Intent(this, MyReceiver.class);
//        sendBroadcast(intent);

        //2. 암시적 인텐트 [action값으로 식별]  - 디바이스안에 설치된 모든 앱안에 있는 리시버가 듣는 방송 Intent
        // Oreo(api 26)버전부터 암시적 인텐트를 시트템액션만 가능함.[OS만 방송할 수 있는 Intent]
        // 그럼에도 사용하고 싶다면.. 암시적 인텐트를 듣는 리스버를 동적으로 등록해야만 함. - Manifest.xml에 리시버를 등록하지 않고 Java 로 등록하는 방법

        //"aaa"라는 이름의 방송 송출!!
        Intent intent= new Intent("bbb");
        //intent.setAction("aaa");

        sendBroadcast(intent);
    }

    //멤버변수
    MyReceiver receiver;

    //앱이 화면에 보여질때 리시버 등록
    @Override
    protected void onResume() {
        super.onResume();
        //"aaa"라는 암시적 인텐트 방송을 수신하는 리시버 동적 등록
        receiver= new MyReceiver();

        IntentFilter filter= new IntentFilter();
        filter.addAction("bbb");

        registerReceiver(receiver, filter);
    }

    //화면에서 안보이기 시작할 때 발동하는 메소드 [onResume 의 반대메소드]
    @Override
    protected void onPause() {
        super.onPause();

        //리시버 제거
        unregisterReceiver(receiver);
    }
}//MainActivity class...