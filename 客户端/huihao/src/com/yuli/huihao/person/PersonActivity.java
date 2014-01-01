package com.yuli.huihao.person;

import java.util.HashMap;
import java.util.Map;

import com.yuli.huihao.R;
import com.yuli.huihao.R.id;
import com.yuli.huihao.R.layout;
import com.yuli.huihao.tool.Constant;
import com.yuli.huihao.tool.HttpUploadUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PersonActivity extends Activity{
	private TextView personName;
	private TextView personNum;
	private TextView personSex;
	private TextView personAge;
	private TextView personPhone;
	private TextView personYaopin;
	private TextView personSignednum;
	
	
	private TextView personRefresh;
	private TextView personEdit;
	
	private String username;
	private Handler hd;
	private String[] userInfoArray;
	
	private final String IP = Constant.IP;
	private final String url = "http://"+IP+":8080/WebRoot/UserInfo.jsp";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		hd = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				Bundle b;
				b = msg.getData();
				String msgStr = b.getString("msg");
				switch(msg.what) {
				case Constant.NEWPASSWORD:
					b = msg.getData();
					msgStr = b.getString("msg");
					try{
						if(Integer.parseInt(msgStr) == 1) {
							Toast.makeText(PersonActivity.this, "密码修改成功！", Toast.LENGTH_SHORT).show();
						}else {
							Toast.makeText(PersonActivity.this, "密码修改失败！", Toast.LENGTH_SHORT).show();
						}
					}catch(Exception e) {
						e.printStackTrace();
						Toast.makeText(PersonActivity.this, "密码修改失败！", Toast.LENGTH_SHORT).show();
					}
					break;
				case Constant.USERINFO:
					b = msg.getData();
					msgStr = b.getString("msg");
					userInfoArray = msgStr.split("\\|");
					System.out.print(userInfoArray[0]);
					personName = (TextView)findViewById(R.id.person_name);
					personSex = (TextView)findViewById(R.id.person_sex);
					personAge = (TextView)findViewById(R.id.person_age);
					personPhone = (TextView)findViewById(R.id.person_phone);
					personYaopin = (TextView)findViewById(R.id.person_yaopin);
					personSignednum = (TextView)findViewById(R.id.person_signednum);
					try{
						personName.setText(userInfoArray[1]);
						personSex.setText(userInfoArray[2]);
						personAge.setText(userInfoArray[3]);
						personPhone.setText(userInfoArray[4]);
						personYaopin.setText(userInfoArray[5]);
						personSignednum.setText(userInfoArray[6]);
						Toast.makeText(PersonActivity.this, "更新成功！", Toast.LENGTH_SHORT).show();
					}catch(Exception e) {
						e.printStackTrace();
						Toast.makeText(PersonActivity.this, "更新失败，请检查网络状况！", Toast.LENGTH_SHORT).show();
					}
					break;
				}
				
			}
		};
		setContentView(R.layout.modperson);
		init();
		
		personRefresh = (TextView)findViewById(R.id.person_refresh);
		
		personRefresh.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				init();
			}
		});
		
		personEdit = (TextView)findViewById(R.id.person_edit);
		personEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LayoutInflater factory = LayoutInflater.from(PersonActivity.this);
				final View DialogView = factory.inflate(R.layout.dialog, null);
				AlertDialog dlg = new AlertDialog.Builder(PersonActivity.this)
				.setTitle("密码修改")
				.setView(DialogView)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						EditText newpwd = null;
						EditText againpwd = null;
						final String url = "http://"+IP+":8080/WebRoot/newpw.jsp";
						String s1 = "", s2 = "";
						newpwd = (EditText)DialogView.findViewById(R.id.newpassword);
						againpwd = (EditText)DialogView.findViewById(R.id.againpassword);
						System.out.println(newpwd.getText().toString());
						s1 = newpwd.getText().toString();
						s2 = againpwd.getText().toString();
						if(s1.equals(s2)) {
							String str1;
							SharedPreferences sp = getSharedPreferences("actm", Context.MODE_PRIVATE);
							str1 = sp.getString("uname", null);
							
							SharedPreferences.Editor editor = sp.edit();
							editor.putString("uname1", str1);
							editor.commit();
							final Map<String,String> params = new HashMap<String, String>();
//							params.put("params2", "0");
							params.put("params1", str1);
							params.put("params2", s1);
							
							new Thread() {
								public void run() {
									String msgStr = HttpUploadUtil.postWithoutFile(url,params);
									Bundle b = new Bundle();
									b.putString("msg", msgStr);
									Message msg = new Message();
									msg.setData(b);
									msg.what = Constant.NEWPASSWORD;
									hd.sendMessage(msg);
								}
							}.start();
						}else {
							Toast.makeText(PersonActivity.this, "两次密码输入不同，请重新输入！", Toast.LENGTH_SHORT).show();
							newpwd.setText("");
							againpwd.setText("");
						}
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				}).create();
				dlg.show();
				
			}
		});
	}


	private void init() {
		// TODO Auto-generated method stub
		final String url = "http://"+IP+":8080/WebRoot/UserInfo.jsp";
		SharedPreferences sp = getSharedPreferences("actm", Context.MODE_PRIVATE);
		username = sp.getString("uname", null);
		
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("uname", username);
		editor.commit();
		personNum = (TextView)findViewById(R.id.person_num);
		personNum.setText(username);
		final Map<String,String> params = new HashMap<String, String>();
		params.put("params1", personNum.getText().toString());
//		params.put("params2", "1");
		new Thread() {
			public void run() {
				String msgStr = HttpUploadUtil.postWithoutFile(url,params);
				Bundle b = new Bundle();
				b.putString("msg", msgStr);
				Message msg = new Message();
				msg.setData(b);
				msg.what = Constant.USERINFO;	//设置消息的what值
				hd.sendMessage(msg);	
			}
		}.start();
	}
	
	
}
