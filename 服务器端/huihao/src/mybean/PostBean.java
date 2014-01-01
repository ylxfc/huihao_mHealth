package mybean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import myutils.SqlUtils;

import com.sun.rowset.CachedRowSetImpl;

public class PostBean {

	private CachedRowSetImpl row;
	private int pageSize;
	private int pageCount;
	private int rowCount;
	private int currentPage;
	public PostBean(){
		pageSize= 2;
		currentPage = 1;
		String sql = "select * from postinfo ";
		List<String> parameters = new ArrayList<String>();
		row = SqlUtils.querySql(sql, parameters);
		try {
			row.last();
			rowCount = row.getRow();
			pageCount = (rowCount%pageSize==0)?(rowCount/pageSize):(rowCount/pageSize+1);
			
		} catch (SQLException e) {
			e.printStackTrace();
			rowCount = 0;
			pageCount = 0;
		}
	}
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		pageCount = (rowCount%pageSize==0)?(rowCount/pageSize):(rowCount/pageSize+1);
		
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public CachedRowSetImpl getRow() {
		return row;
	}

	public int getPageCount() {
		return pageCount;
	}

	public int getRowCount() {
		return rowCount;
	}
}

