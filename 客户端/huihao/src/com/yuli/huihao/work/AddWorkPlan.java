package com.yuli.huihao.work;

import com.yuli.huihao.MainActivity;
import com.yuli.huihao.R;
import com.yuli.huihao.home.HomeActivity;
import com.yuli.huihao.tool.MyDataBaseAdapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class AddWorkPlan extends Activity {
	private final static String[] plan_TAG = {"待访", "拜访", "预购"};
	private int vv = 0, y = 0;
	private TextView workplanBack;
	private TextView workplanSave;
	private TextView workJingxsName;
	private TextView workData;
	private RadioGroup group;
	private RadioButton rb0, rb1, rb2;
	private EditText workYaoMing;
	private EditText workYaopinNum;
	
	private long rowId = 0;
	private MyDataBaseAdapter mMyDataBaseAdapter;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.work_addplan);
		
		//控件初始化
		workplanBack = (TextView)findViewById(R.id.workplan_back);
		workplanSave = (TextView)findViewById(R.id.workplan_save);
		workJingxsName = (TextView)findViewById(R.id.workplan_name);
		workData = (TextView)findViewById(R.id.workplan_data);
		group = (RadioGroup)findViewById(R.id.workplan_group);
		rb0 = (RadioButton)findViewById(R.id.workplan_TAG0);
		rb1 = (RadioButton)findViewById(R.id.workplan_TAG1);
		rb2 = (RadioButton)findViewById(R.id.workplan_TAG2);
		workYaoMing = (EditText)findViewById(R.id.workplan_yaoming);
		workYaopinNum = (EditText)findViewById(R.id.workplan_yaopinnum);
		
		//打开数据库
		mMyDataBaseAdapter = new MyDataBaseAdapter(this);
		mMyDataBaseAdapter.open();
		
		Bundle bundle = this.getIntent().getExtras();
		rowId = bundle.getInt("planId");
		Cursor cur = SearchData(rowId);
		workJingxsName.setText(cur.getString(cur.getColumnIndex("Dealer_name")));
		workData.setText(cur.getString(cur.getColumnIndex("data")));
		workYaoMing.setText(cur.getString(cur.getColumnIndex("yaoming")));
		workYaopinNum.setText(cur.getString(cur.getColumnIndex("yaonum")));
		String temp1 = cur.getString(cur.getColumnIndex("visited"));
		String temp2 = cur.getString(cur.getColumnIndex("yugou"));
		if(Integer.parseInt(temp2) == 1) {
			rb2.setChecked(true);
		}else if(Integer.parseInt(temp2) == 0 && Integer.parseInt(temp1) == 1) {
			rb1.setChecked(true);
		}
		else {
			rb0.setChecked(true);
		}
		//状态选择
		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId == rb2.getId()) {
					y = 1;
				}
				else if(checkedId == rb1.getId()){
					vv = 1;
				}
				else {
					y = 0;
					vv = 0;
				}
			}
		});
		//保存预购信息
		workplanSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean b = update(rowId, 
						workJingxsName.getText().toString(), 
						workData.getText().toString(), 
						vv,
						y,
						workYaoMing.getText().toString(), 
						Integer.parseInt(workYaopinNum.getText().toString())
						);
				if(b == true) {
					Toast.makeText(AddWorkPlan.this, "数据保存成功！", Toast.LENGTH_SHORT).show();
				}else {
					Toast.makeText(AddWorkPlan.this, "数据保存失败！", Toast.LENGTH_SHORT).show();
				}
			}
		});
		//返回到工作计划界面
		workplanBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(AddWorkPlan.this, MainActivity.class);
				startActivity(intent);
				AddWorkPlan.this.finish();
			}
		});
	}
	private Cursor SearchData(long rowId2) {
		// TODO Auto-generated method stub
		return mMyDataBaseAdapter.fetchData(rowId2+1);
	}
	private boolean update(long rowId3, String Dealer_name, String data, int visited, int yugou, String yaoming, int yaonum) {
		return mMyDataBaseAdapter.updateData(rowId3+1, Dealer_name, data, visited, yugou, yaoming, yaonum);
	}
}
