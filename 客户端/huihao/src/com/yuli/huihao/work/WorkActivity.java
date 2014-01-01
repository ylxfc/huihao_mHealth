package com.yuli.huihao.work;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.yuli.huihao.MoreActivity;
import com.yuli.huihao.R;
import com.yuli.huihao.Userlogin;
import com.yuli.huihao.R.id;
import com.yuli.huihao.R.layout;
import com.yuli.huihao.person.PersonActivity;
import com.yuli.huihao.tool.Constant;
import com.yuli.huihao.tool.HttpUploadUtil;
import com.yuli.huihao.tool.MyDataBaseAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class WorkActivity extends Activity{
	private MyDataBaseAdapter mMyDataBaseAdapter;
	private TextView workAddBtn;
	private TextView workSendBtn;
	EditText JingxiaoshangName = null;
	EditText VisitedData = null;
	
	private static int miCount = 0;
//	private TextView test123;
	private ListView test1;
	private SimpleCursorAdapter mSimpleCursorAdapter;
	
	private String sendMsg = "";
	private final String IP = Constant.IP;
	final String url = "http://"+IP+":8080/WebRoot/getWorkPlan.jsp";
	Handler hd;
	
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
				b = msg.getData();
				msgStr = b.getString("msg");
				try{
					if(Integer.parseInt(msgStr) == 1) {
						Toast.makeText(WorkActivity.this, "工作计划发送成功！", Toast.LENGTH_SHORT).show();
					}else {
						Toast.makeText(WorkActivity.this,"工作计划发送失败！", Toast.LENGTH_SHORT).show();
					}
				}catch(Exception e) {
					e.printStackTrace();
					Toast.makeText(WorkActivity.this, "工作计划发送失败！", Toast.LENGTH_SHORT).show();
				}
			}
		};
		
		setContentView(R.layout.modwork);
		
		test1 = (ListView)findViewById(R.id.worklist);
		workAddBtn = (TextView)findViewById(R.id.work_add);
		workSendBtn = (TextView)findViewById(R.id.work_send);
		
		mMyDataBaseAdapter = new MyDataBaseAdapter(this);
		mMyDataBaseAdapter.open();
		UpdataAdapter();
		//添加工作计划
		workAddBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  	//获取当前时间
//				final String date=sdf.format(new java.util.Date());  
				
				LayoutInflater factory = LayoutInflater.from(WorkActivity.this);
				final View DialogView = factory.inflate(R.layout.addplandialog, null);
				AlertDialog dlg = new AlertDialog.Builder(WorkActivity.this)
				.setTitle("添加工作计划")
				.setView(DialogView)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						String s1 = "", s2 = "";
						JingxiaoshangName = (EditText)DialogView.findViewById(R.id.dealer_name);
						VisitedData = (EditText)DialogView.findViewById(R.id.dealer_data);
						
//						System.out.println(date);
//						VisitedData.setText(date);
//						test123 = (TextView)findViewById(R.id.test);
						s1 = JingxiaoshangName.getText().toString();
						s2 = VisitedData.getText().toString();
	
						AddData(s1, s2, 0, 0, null, 0);
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
		//发送工作计划
		workSendBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sendMsg = "";
				Cursor cur2 = getAllInfo();
				if(cur2 != null) {
					while(cur2.moveToNext()) {
						sendMsg = sendMsg + "|" + cur2.getString(cur2.getColumnIndex("Dealer_name")) +",";
						sendMsg = sendMsg + cur2.getString(cur2.getColumnIndex("data")) +",";
						sendMsg = sendMsg + cur2.getString(cur2.getColumnIndex("visited")) +",";
						sendMsg = sendMsg + cur2.getString(cur2.getColumnIndex("yugou")) +",";
						sendMsg = sendMsg + cur2.getString(cur2.getColumnIndex("yaoming")) +",";
						sendMsg = sendMsg + cur2.getString(cur2.getColumnIndex("yaonum")) ;
					}
				}
				System.out.println(sendMsg);
				init();
			}
		});
		//进入计划编辑界面
		test1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
//				String[] strtemp = new String[2];
//				strtemp[0] = 
				Intent intent = new Intent();
				intent.setClass(WorkActivity.this, AddWorkPlan.class);
				Bundle bundle = new Bundle();
//				bundle.putStringArray("planId", new String{});
				bundle.putInt("planId", position);
				intent.putExtras(bundle);
				startActivity(intent);
				WorkActivity.this.finish();
			}
		});
		test1.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View arg1,
					 int arg2, long arg3) {
				// TODO Auto-generated method stub
				final int s = arg2;
				Dialog dlg = new AlertDialog.Builder(WorkActivity.this)
				.setTitle("提示")
				.setMessage("您确定要删除这条工作计划吗！")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						boolean boo = DelData(s);
						if(boo == true) {
							Toast.makeText(WorkActivity.this, "工作计划删除成功！", Toast.LENGTH_SHORT).show();
						}else {
							Toast.makeText(WorkActivity.this, "工作计划删除失败！", Toast.LENGTH_SHORT).show();
						}
					}
				}).setNeutralButton("取消", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				}).create();
				dlg.show();
				
				
				return false;
			}
		});
	}
	//添加计划
	private void AddData(String Dealer_name, String data, int visited, int yugou, String yaoming, int yaonum) {
		mMyDataBaseAdapter.insertData(Dealer_name, data, visited, yugou, yaoming, yaonum);
		UpdataAdapter();
	}
	//删除计划
	private boolean DelData(long roeId22) {
		boolean s  = mMyDataBaseAdapter.deleteData(roeId22+1);
		UpdataAdapter();
		Cursor cur3 = mMyDataBaseAdapter.fetchAllData();
		cur3.moveToFirst();
		return s;
	}
	private Cursor getAllInfo() {
		return mMyDataBaseAdapter.fetchAllData();
	}
	private void UpdataAdapter() {
		// TODO Auto-generated method stub
		Cursor cur = mMyDataBaseAdapter.fetchAllData();
		cur.getCount();
		if(cur != null && cur.getCount()>0) {
			mSimpleCursorAdapter = new SimpleCursorAdapter(WorkActivity.this, R.layout.work_item, cur, 
			new String[] {MyDataBaseAdapter.KEY_NAME, MyDataBaseAdapter.KEY_DATA}, 
			new int[] {R.id.jingxsName, R.id.visitedData});
			test1.setAdapter(mSimpleCursorAdapter);
		}
	}
	private void init() {
		SharedPreferences sp = getSharedPreferences("actm", Context.MODE_PRIVATE);
		String username = sp.getString("uname", null);
		
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("uname", username);
		editor.commit();
		final Map<String,String> params = new HashMap<String, String>();
//		sendMsg = "|123,2013-07-25,0,0,0,0";
		username = username + sendMsg;
		
		System.out.println(username);
		params.put("params1", username);
		
		new Thread() {
			public void run() {
				String msgStr = HttpUploadUtil.postWithoutFile(url,params);
				Bundle b = new Bundle();
				b.putString("msg", msgStr);
				Message msg = new Message();
				msg.setData(b);
				hd.sendMessage(msg);	
			}
		}.start();
	}
}
