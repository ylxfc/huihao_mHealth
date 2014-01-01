package com.yuli.huihao;

import java.util.HashMap;
import java.util.Map;

import com.yuli.huihao.tool.Constant;
import com.yuli.huihao.tool.HttpUploadUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Userlogin extends Activity {
	private final String IP = Constant.IP;
	private final String url = "http://"+IP+":8080/WebRoot/receive.jsp"; 
	private EditText username;
	private EditText password;
	private Button lbutton;
	private Button qbutton;
	private Handler hd;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		hd = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				Bundle b;
				b = msg.getData();
				String msgStr = b.getString("msg");
				
				if(msgStr.equals("登录成功")) {
					Toast.makeText(Userlogin.this, "登陆成功！", Toast.LENGTH_SHORT).show();
					gotoMainView();
				}
				else if (msgStr.equals("登录失败")){
					Toast.makeText(Userlogin.this, "登陆失败！", Toast.LENGTH_SHORT).show();
				}
				else if(msgStr.equals("用户不存在，请重新输入")) {
					Toast.makeText(Userlogin.this, "用户不存在，请重新输入!", Toast.LENGTH_SHORT).show();
				}
				else {
					Toast.makeText(Userlogin.this, "未知错误！", Toast.LENGTH_SHORT).show();
					System.out.println(msgStr);
				}
			}
		};
		
		setContentView(R.layout.login);
		
		username = (EditText)findViewById(R.id.login_edit_account);
		password = (EditText)findViewById(R.id.login_edit_pwd);
		lbutton = (Button)findViewById(R.id.login_btn_login);
		qbutton = (Button)findViewById(R.id.login_btn_exit);
		
		lbutton.setOnClickListener(
				new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						final Map<String,String> params = new HashMap<String, String>();
						params.put("params1", username.getText().toString());
						params.put("params2", password.getText().toString());
						
						String user = username.getText().toString();
						SharedPreferences sp = Userlogin.this.getSharedPreferences("actm", Context.MODE_PRIVATE);
						String uname = sp.getString(user, null);
						SharedPreferences.Editor editor = sp.edit();
						editor.putString("uname", user);
						editor.commit();
						//发送数据
						new Thread() {
							public void run() {
								try{
								String msgStr = HttpUploadUtil.postWithoutFile(url,params);
								
								Bundle b = new Bundle();
								b.putString("msg", msgStr);
								Message msg = new Message();
								msg.setData(b);
	//							msg.what = Constant.LOGINVIEW;	//设置消息的what值
								hd.sendMessage(msg);			//发送消息
								}catch(Exception e) {
									e.printStackTrace();
								}
							}
						}.start();
					}
				});
		qbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Userlogin.this.finish();
			}
		});
	}
	private void gotoMainView() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Userlogin.this, MainActivity.class);
        startActivity(intent);
        Userlogin.this.finish();
	}
}
