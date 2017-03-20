package com.hyunseok.android.rxbasic3_network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    TextView textView_slow, textView_fast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_slow = (TextView) findViewById(R.id.textView_slow);
        textView_fast = (TextView) findViewById(R.id.textView_fast);

        Observable<String> naverObservable =
                Observable.create(emitter -> {
                            emitter.onNext(Remote.getUrlByGet("naver.com"));
                        }
                );
        Observable<String> cnetObservable =
                Observable.create(emitter -> {
                            emitter.onNext(Remote.getUrlByGet("www.cnet.co.kr/"));
                        }
                );

        cnetObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (result) -> { textView_slow.setText(result); }
                );

        naverObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (result) -> { textView_fast.setText(result); }
                );

    }
}
