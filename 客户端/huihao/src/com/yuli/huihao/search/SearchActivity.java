package com.yuli.huihao.search;

import java.io.EOFException;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;

import com.yuli.huihao.R;
import com.yuli.huihao.R.id;
import com.yuli.huihao.R.layout;
import com.yuli.huihao.tool.Constant;
import com.yuli.huihao.tool.HttpUploadUtil;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends Activity{
	private AutoCompleteTextView searchTextView;
	private Button searchButton;
	private RadioGroup searchRadioGroup;
	private RadioButton searchYaoMing, searchYouhui;
	private TextView yaoming, leibie, guige, jianjie, kucun;
	private TextView youhui1,youhui2,youhui3;
	private LinearLayout buju1, buju2;
	
	private final String IP = Constant.IP;
	
	
	private Handler hd;
	private String searchInfo;
	private String[] yaopinArray;
	
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
				case Constant.YOUHUI:
					yaopinArray = msgStr.split("\\|");
					youhui1 = (TextView)findViewById(R.id.youhui1);
					youhui2 = (TextView)findViewById(R.id.youhui2);
					youhui3 = (TextView)findViewById(R.id.youhui3);
					try {
						youhui1.setText(yaopinArray[1]);
						youhui2.setText(yaopinArray[2]);
						youhui3.setText(yaopinArray[3]);
						Toast.makeText(SearchActivity.this, "优惠信息显示如下", Toast.LENGTH_SHORT).show();
					}catch(Exception e) {
						e.printStackTrace();
						Toast.makeText(SearchActivity.this, "没有找到您需要的信息，请检查网络状况或是重新输入！", Toast.LENGTH_SHORT).show();
					}
					break;
				case Constant.YAOPIN:
					yaopinArray = msgStr.split("\\|");
					yaoming = (TextView)findViewById(R.id.search_yaoming);
					leibie = (TextView)findViewById(R.id.search_leibie);
					guige = (TextView)findViewById(R.id.search_guige);
					jianjie = (TextView)findViewById(R.id.search_jianjie);
					kucun = (TextView)findViewById(R.id.search_kucun);
					try {
						yaoming.setText(yaopinArray[0]);
						leibie.setText(yaopinArray[1]);
						guige.setText(yaopinArray[2]);
						jianjie.setText(yaopinArray[3]);
						kucun.setText(yaopinArray[4]);
						Toast.makeText(SearchActivity.this, "药品信息显示如下", Toast.LENGTH_SHORT).show();
					}catch(Exception e) {
						e.printStackTrace();
						Toast.makeText(SearchActivity.this, "没有找到您需要的信息，请检查网络状况或是重新输入！", Toast.LENGTH_SHORT).show();
					}
				}
			}
		};
		setContentView(R.layout.modsearch);
		searchTextView = (AutoCompleteTextView)findViewById(R.id.searchTextView);
		searchButton = (Button)findViewById(R.id.searchBtn);
		searchRadioGroup = (RadioGroup)findViewById(R.id.searchRadioGroup);
		searchYaoMing = (RadioButton)findViewById(R.id.searchYaopinInfo);
		searchYouhui = (RadioButton)findViewById(R.id.searchYouhuiInfo);
		buju1 = (LinearLayout)findViewById(R.id.buju1);
		buju2 = (LinearLayout)findViewById(R.id.buju2);
		
		searchButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(searchYaoMing.isChecked() == true) {
					inityaopin();
					buju1.setVisibility(View.VISIBLE);
					buju2.setVisibility(View.GONE);
				}
				else if(searchYouhui.isChecked() == true) {
					inityouhui();
					buju2.setVisibility(View.VISIBLE);
					buju1.setVisibility(View.GONE);
				}
				else {
					Toast.makeText(SearchActivity.this, "请选择要查询的类别！", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	}

	protected void inityouhui() {
		// TODO Auto-generated method stub
		final String url = "http://"+IP+":8080/WebRoot/discount.jsp";
		// TODO Auto-generated method stub
		SharedPreferences sp = getSharedPreferences("actm", Context.MODE_PRIVATE);
		final Map<String,String> params = new HashMap<String, String>();
		params.put("params1", searchTextView.getText().toString());
		
		searchInfo = searchTextView.getText().toString().trim();
		String seachInfo1 = sp.getString(searchInfo, null);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("searchInfo1", searchInfo);
		editor.commit();
		
		new Thread() {
			public void run() {
				String msgStr = HttpUploadUtil.postWithoutFile(url,params);
				
				Bundle b = new Bundle();
				b.putString("msg", msgStr);
				Message msg = new Message();
				msg.setData(b);
				msg.what = Constant.YOUHUI;
				hd.sendMessage(msg);			//发送消息
			}
		}.start();
	}

	private void inityaopin() {
		final String url = "http://"+IP+":8080/WebRoot/medicineInfo.jsp";
		// TODO Auto-generated method stub
		SharedPreferences sp = getSharedPreferences("actm", Context.MODE_PRIVATE);
		final Map<String,String> params = new HashMap<String, String>();
		params.put("params1", searchTextView.getText().toString());
		
		searchInfo = searchTextView.getText().toString().trim();
		String seachInfo1 = sp.getString(searchInfo, null);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("searchInfo1", searchInfo);
		editor.commit();
		
		new Thread() {
			public void run() {
				String msgStr = HttpUploadUtil.postWithoutFile(url,params);
				
				Bundle b = new Bundle();
				b.putString("msg", msgStr);
				Message msg = new Message();
				msg.setData(b);
				msg.what = Constant.YAOPIN;
				hd.sendMessage(msg);			//发送消息
			}
		}.start();
	}
}
