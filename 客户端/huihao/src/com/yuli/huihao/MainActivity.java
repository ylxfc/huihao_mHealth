package com.yuli.huihao;




import com.yuli.huihao.home.HomeActivity;
import com.yuli.huihao.person.PersonActivity;
import com.yuli.huihao.search.SearchActivity;
import com.yuli.huihao.work.WorkActivity;

import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

public class MainActivity extends TabActivity implements OnCheckedChangeListener {
	private RadioGroup mainTab;
	private TabHost tabhost;
	private Intent iHome;
	private Intent iPerson;
	private Intent iWork;
	private Intent iSearch;
	private Intent iMore;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		
		//自高容僕
//		init();
		
		mainTab=(RadioGroup)findViewById(R.id.main_tab);
        mainTab.setOnCheckedChangeListener(this);
        tabhost = getTabHost();
        
        iHome = new Intent(this, HomeActivity.class);
        tabhost.addTab(tabhost.newTabSpec("iHome")
        		.setIndicator(getResources().getString(R.string.main_home), getResources().getDrawable(R.drawable.ico_zy01))
        		.setContent(iHome));
        
        iPerson = new Intent(this, PersonActivity.class);
        tabhost.addTab(tabhost.newTabSpec("iPerson")
        		.setIndicator(getResources().getString(R.string.main_person), getResources().getDrawable(R.drawable.ico_dp01))
        		.setContent(iPerson));
        
        iWork = new Intent(this, WorkActivity.class);
		tabhost.addTab(tabhost.newTabSpec("iWork")
	        	.setIndicator(getResources().getString(R.string.main_work), getResources().getDrawable(R.drawable.icon_2_c))
	        	.setContent(iWork));
		
		iSearch = new Intent(this,SearchActivity.class);
		tabhost.addTab(tabhost.newTabSpec("iSearch")
	        	.setIndicator(getResources().getString(R.string.main_search), getResources().getDrawable(R.drawable.icon_3_c))
	        	.setContent(iSearch));
		
		iMore = new Intent(this, MoreActivity.class);
		tabhost.addTab(tabhost.newTabSpec("iMore")
	        	.setIndicator(getResources().getString(R.string.main_more), getResources().getDrawable(R.drawable.ico_gd01))
	        	.setContent(iMore));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch(checkedId){
		case R.id.radio_button0:
			this.tabhost.setCurrentTabByTag("iHome");
			break;
		case R.id.radio_button1:
			this.tabhost.setCurrentTabByTag("iPerson");
			break;
		case R.id.radio_button2:
			this.tabhost.setCurrentTabByTag("iWork");
			break;
		case R.id.radio_button3:
			this.tabhost.setCurrentTabByTag("iSearch");
			break;
		case R.id.radio_button4:
			this.tabhost.setCurrentTabByTag("iMore");
			break;
		}
		
	}
//	private void init(){
//		JPushInterface.init(getApplicationContext());
//	}

}
