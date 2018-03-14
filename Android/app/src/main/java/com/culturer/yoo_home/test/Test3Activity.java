package com.culturer.yoo_home.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.util.Typefaces;
import com.culturer.yoo_home.widget.loading_anim.Titanic;
import com.culturer.yoo_home.widget.loading_anim.TitanicTextView;

public class Test3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        TitanicTextView tv = (TitanicTextView) findViewById(R.id.my_text_view);

        // set fancy typeface
        tv.setTypeface(Typefaces.get(this, "Satisfy-Regular.ttf"));

        // start animation
        new Titanic().start(tv);
    }
}
