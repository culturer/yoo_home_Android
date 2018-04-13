package com.culturer.yoo_home.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import com.armour8.yooplus.yooplus.R;

import io.github.rockerhieu.emojicon.EmojiconEditText;
import io.github.rockerhieu.emojicon.EmojiconGridFragment;
import io.github.rockerhieu.emojicon.EmojiconTextView;
import io.github.rockerhieu.emojicon.EmojiconsFragment;
import io.github.rockerhieu.emojicon.emoji.Emojicon;

public class TestEmojiActivity extends AppCompatActivity  implements EmojiconGridFragment.OnEmojiconClickedListener, EmojiconsFragment.OnEmojiconBackspaceClickedListener  {
	EmojiconEditText mEditEmojicon;
	EmojiconTextView mTxtEmojicon;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_emoji);
		mEditEmojicon = (EmojiconEditText) findViewById(R.id.editEmojicon);
		mTxtEmojicon = (EmojiconTextView) findViewById(R.id.txtEmojicon);
		mEditEmojicon.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
			
			}
			
			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				mTxtEmojicon.setText(charSequence);
			}
			
			@Override
			public void afterTextChanged(Editable editable) {
			
			}
		});
		setEmojiconFragment(false);
		
	}
	
	private void setEmojiconFragment(boolean useSystemDefault) {
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.emojicons, EmojiconsFragment.newInstance(useSystemDefault))
				.commit();
	}
	
	@Override
	public void onEmojiconBackspaceClicked(View v) {
		EmojiconsFragment.backspace(mEditEmojicon);
	}
	
	@Override
	public void onEmojiconClicked(Emojicon emojicon) {
		EmojiconsFragment.input(mEditEmojicon, emojicon);
	}
}
