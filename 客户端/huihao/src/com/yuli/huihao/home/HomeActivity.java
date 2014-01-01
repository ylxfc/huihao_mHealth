package com.yuli.huihao.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.yuli.huihao.R;
import com.yuli.huihao.R.id;
import com.yuli.huihao.R.layout;
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
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity{
	
	private List<List<String>> info1 = new ArrayList<List<String>>();
	private String[][] info = new String[][] {{"回答是uhioehfislhfdioshdfsifhkhihio", "7-3 19:46"},
			{"dsuhgfusdhilhaiosdhklsahdoihawkldhniasdhiadw","7-5 16:00"},
			{"sahiadhkiwokshaifhewflisahiofdwdfksdshiiwojo","7-7 12:00"}};
	
	private ListView datalist = null;
	private List<Map<String, String>> list = new ArrayList<Map<String,String>>();	//定义显示的内容包装
	private   SimpleAdapter simpleAdapter = null;
	
	private String username;
	private Handler hd;
	private TextView refreshBtn;
	private int count;
	private String infoId;
	private String[] postInfoArray;
	
	private final String IP = Constant.IP;
	private final String url = "http://"+IP+":8080/WebRoot/postInfo.jsp";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		hd = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				Bundle b;
				b = msg.getData();
				String msgStr = b.getString("msg");
				b = msg.getData();
				msgStr = b.getString("msg");
				
				System.out.println("于立\n"+msgStr);
				
				try{
					postInfoArray = msgStr.split("\\|");
					int m = postInfoArray.length/2;
//					List<String> temp = new ArrayList<String>();
//					for(int i = 0;i<m;i++) {
//						temp.add(info[i][0]);
//						temp.add(info[i][1]);
//						info1.add(temp);
//						temp.clear();
//					}
					for(int i = 0;i<m;i++) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("inform", postInfoArray[2*i]);
						map.put("data", postInfoArray[2*i+1]);
						list.add(map);
					}
					
					simpleAdapter = new SimpleAdapter(HomeActivity.this, list, R.layout.list_item, new String[] {"inform","data"}, new int[] {R.id.listitem,R.id.listdata});
					datalist.setAdapter(simpleAdapter);
					Toast.makeText(HomeActivity.this, "公司通知更新成功！", Toast.LENGTH_SHORT).show();
				}catch(Exception e) {
					e.printStackTrace();
					Toast.makeText(HomeActivity.this, "更新失败，请检查网络连接！", Toast.LENGTH_SHORT).show();
				}
				
			}
		};
		setContentView(R.layout.modhome);
		
		refreshBtn = (TextView)findViewById(R.id.home_refresh);
		datalist = (ListView)findViewById(R.id.datalist);
		
		refreshBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					info1.clear();
					init();
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		datalist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Map<String, String> map2 = (Map<String, String>) HomeActivity.this.simpleAdapter
						.getItem(position);
				Intent intent = new Intent();
				intent.setClass(HomeActivity.this, HomeInfo.class);
				Bundle bundle = new Bundle();
				bundle.putString("infos", map2.get("inform"));
				intent.putExtras(bundle);
				startActivity(intent); 
			}
		});
		
	}
	private void init() {
		count = info.length;
		SharedPreferences sp = getSharedPreferences("actm", Context.MODE_PRIVATE);
		username = sp.getString("uname", null);
		
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("uname", username);
		editor.commit();
		
		final Map<String,String> params = new HashMap<String, String>();
		params.put("params2", Integer.toString(count));
		params.put("params1", username);
//		List<String> temp1 = new ArrayList<String>();
//		for(int j = 0;j<3;j++) {
//			temp1.add(info[j][0]);
//			temp1.add(info[j][1]);
//			info1.add(temp1);
//			temp1.clear();
//		}
		
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
