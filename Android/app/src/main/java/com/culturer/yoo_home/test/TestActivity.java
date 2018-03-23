package com.culturer.yoo_home.test;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.Button;

import com.armour8.yooplus.yooplus.R;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.http.VolleyError;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;

import java.io.File;
import java.util.ArrayList;

import static com.culturer.yoo_home.config.Urls.FILES_URL;

public class TestActivity extends AppCompatActivity{

    private static final String TAG = "TestActivity";
    private View contentView;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = getLayoutInflater().inflate(R.layout.activity_test,null);
        setContentView(contentView);
        init();
    }

    private void init(){
        btn = contentView.findViewById(R.id.test);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: test --- true");
                Album.image(TestActivity.this) // 选择图片。
                        .multipleChoice()
                        .requestCode(0)
                        .camera(true)
                        .columnCount(3)
                        .selectCount(3)
                        .checkedList(new ArrayList<AlbumFile>())
//                        .filterSize()
//                        .filterMimeType()
//                        .afterFilterVisibility() // 显示被过滤掉的文件，但它们是不可用的。
                        .onResult(new Action<ArrayList<AlbumFile>>() {
                            @Override
                            public void onAction(int requestCode, @NonNull ArrayList<AlbumFile> result) {
                                HttpParams params = new HttpParams();
                                params.put("file",new File(result.get(0).getPath()));
                                Log.i(TAG, "onAction: file"+result.get(0).getPath());
                                params.put("path","/test");
                                params.putHeaders("token","token");
                                HttpCallback callback = new HttpCallback() {
                                    @Override
                                    public void onSuccess(String t) {
                                        Log.i(TAG, "test_acca: onSuccess "+t);
                                    }

                                    @Override
                                    public void onFailure(VolleyError error) {
                                        Log.i(TAG, "test_acca: onSuccess "+error.getMessage());
                                    }
                                };
                                new RxVolley.Builder()
                                        .url("http://192.168.43.146:3333/upload")
                                        .httpMethod(RxVolley.Method.POST)
                                        .contentType(RxVolley.ContentType.FORM)
                                        .params(params)
                                        .cacheTime(0)
                                        .shouldCache(false)
                                        .callback(callback)
                                        .encoding("UTF-8")
                                        .doTask();
                            }
                        })
                        .onCancel(new Action<String>() {
                            @Override
                            public void onAction(int requestCode, @NonNull String result) {

                            }
                        })
                        .start();
            }
        });
    }

}
