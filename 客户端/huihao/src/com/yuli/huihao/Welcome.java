package com.yuli.huihao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class Welcome extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);
		new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                    Intent intent = new Intent(Welcome.this, Userlogin.class);
                    startActivity(intent);
                    Welcome.this.finish();
            }
    }, 2000);
	}
}
