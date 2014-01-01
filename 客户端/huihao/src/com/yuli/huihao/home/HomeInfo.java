package com.yuli.huihao.home;


import java.util.HashMap;
import java.util.Map;

import com.yuli.huihao.R;
import com.yuli.huihao.R.id;
import com.yuli.huihao.R.layout;
import com.yuli.huihao.tool.Constant;
import com.yuli.huihao.tool.HttpUploadUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeInfo extends Activity {

	private TextView viewInfo = null;
	private EditText returnInfo = null;
	private TextView homereturn = null;
	private Button sendBtn = null;
	
	private Handler hd;
	private final String IP = Constant.IP;
	private final String url = "http://"+IP+":8080/WebRoot/back.jsp";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		hd = new Handler() {
			public void handleMessage(Message msg) {
				Bundle b;
				b = msg.getData();
				String msgStr = b.getString("msg");
				b = msg.getData();
				msgStr = b.getString("msg");
				int m = Integer.parseInt(msgStr);
				if(m == 1) {
					Toast.makeText(HomeInfo.this, "数据发送成功！", Toast.LENGTH_SHORT).show();
				}else {
					Toast.makeText(HomeInfo.this, "数据发送失败，请检查网络！", Toast.LENGTH_SHORT).show();
				}
			}
		};
		super.setContentView(R.layout.act_set_trafficstat);
		
		viewInfo = (TextView)findViewById(R.id.home_viewInfo);
		returnInfo = (EditText)findViewById(R.id.home_returnInfo);
		sendBtn = (Button)findViewById(R.id.home_postbtn);
		homereturn = (TextView)findViewById(R.id.home_return);
		
		
		Bundle bundle = this.getIntent().getExtras();
		System.out.println("ylxfc\n"+bundle.getString("infos"));
		viewInfo.setText(bundle.getString("infos"));
		
		sendBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				init();
			}
		});
		
		homereturn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(HomeInfo.this, HomeActivity.class);
				HomeInfo.this.finish();
			}
		});
	}
	protected void init() {
		// TODO Auto-generated method stub
		String s = returnInfo.getText().toString();
		final Map<String,String> params = new HashMap<String, String>();
		params.put("params1", s);
		
		new Thread() {
			public void run() {
				String msgStr = HttpUploadUtil.postWithoutFile(url,params);
				System.out.println("于立\n"+msgStr);
				Bundle b = new Bundle();
				b.putString("msg", msgStr);
				Message msg = new Message();
				msg.setData(b);
				hd.sendMessage(msg);	
			}
		}.start();
	}

	
}
