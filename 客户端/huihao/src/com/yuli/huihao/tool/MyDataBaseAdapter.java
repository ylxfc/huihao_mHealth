package com.yuli.huihao.tool;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBaseAdapter {
	//调试log
	private static final String TAG = "MyDataBaseAdapter";
	//一条记录的属性
	//序号
	private static final String KEY_ID = "_id";
	//经销商名称
	public static final String KEY_NAME = "Dealer_name";
	//拜访日期
	public static final String KEY_DATA = "data";
	//是否拜访
	private static final String KEY_VISITED = "visited";
	//是否预购
	private static final String KEY_YUGOU = "yugou";
	//预购药名
	private static final String KEY_YAOMING = "yaoming";
	//药品数量
	private static final String KEY_YAOPINNUM = "yaonum";
	
	//数据库名称
	private static final String DB_NAME = "huihao.db";
	//数据库表
	private static final String DB_TABLE = "worktable";
	//数据库版本
	private static final int DB_VERSON = 1;
	//本地context对象
	private Context mContext = null;
	
	//建表
	private static final String DB_CREATE = "CREATE TABLE " + DB_TABLE + " ("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY,"
			+ KEY_NAME
			+ " TEXT,"
			+ KEY_DATA
			+ " TEXT,"
			+ KEY_VISITED
			+ " INTEGER,"
			+ KEY_YUGOU
			+ " INTEGER,"
			+ KEY_YAOMING
			+ " TEXT,"
			+ KEY_YAOPINNUM
			+ " INTEGER)";
	//执行open()打开数据库时，保存返回的数据对象
	private SQLiteDatabase mSQLiteDatabase = null;
	//由SQLiteopenHelper继承过来
	private DatabaseHelper mDatabaseHelper = null;
	private static class DatabaseHelper extends SQLiteOpenHelper {

		//构造函数，建立数据库
		DatabaseHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSON);
		}
		//创建一个表
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			//数据库没有表时
			db.execSQL(DB_CREATE);
		}
		//升级数据库
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS notes");
			onCreate(db);
		}
	}
	//构造函数，取得Context
	public MyDataBaseAdapter(Context context) {
		mContext = context;
	}
	//打开数据库，返回数据库对象
	public void open() throws SQLException {
		mDatabaseHelper = new DatabaseHelper(mContext);
		mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
	}
	//关闭数据库
	public void close()
	{
		mDatabaseHelper.close();
	}
	
	/* 插入一条数据 */
	public long insertData(String Dealer_name, String data, int visited, int yugou, String yaoming, int yaonum)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, Dealer_name);
		initialValues.put(KEY_DATA, data);
		initialValues.put(KEY_VISITED, visited);
		initialValues.put(KEY_YUGOU, yugou);
		initialValues.put(KEY_YAOMING, yaoming);
		initialValues.put(KEY_YAOPINNUM, yaonum);
		return mSQLiteDatabase.insert(DB_TABLE, KEY_ID, initialValues);
	}
	/* 删除一条数据 */
	public boolean deleteData(long rowId)
	{
		return mSQLiteDatabase.delete(DB_TABLE, KEY_ID + "=" + rowId, null) > 0;
	}
	//通过Cursor查询所有数据
	public Cursor fetchAllData() {
		return mSQLiteDatabase.query(DB_TABLE, new String[] {KEY_ID, KEY_NAME, KEY_DATA, KEY_VISITED, 
				KEY_YUGOU, KEY_YAOMING, KEY_YAOPINNUM}, null, null, null, null, null);
	}
	//查询指定数据
	public Cursor fetchData(long rowId) throws SQLException {
		Cursor mCursor = mSQLiteDatabase.query(true, DB_TABLE, new String[] {KEY_ID, KEY_NAME, KEY_DATA, KEY_VISITED, KEY_YUGOU, KEY_YAOMING, KEY_YAOPINNUM}, KEY_ID + "=" +rowId, 
						null, null, null, null, null);
		if(mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	//更新一条数据
	public boolean updateData(long rowId, String Dealer_name, String data, int visited, int yugou, String yaoming, int yaonum)
	{
		ContentValues args = new ContentValues();
		args.put(KEY_NAME, Dealer_name);
		args.put(KEY_DATA, data);
		args.put(KEY_VISITED, visited);
		args.put(KEY_YUGOU, yugou);
		args.put(KEY_YAOMING, yaoming);
		args.put(KEY_YAOPINNUM, yaonum);
		return mSQLiteDatabase.update(DB_TABLE, args, KEY_ID + "=" + rowId, null) > 0;
	}
}
