package util.connectUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import util.codeGenerate.CodeGenerate;
/**
 * 项目名称：recruitPortal 类名称：CommonDAO 类描述：封装 JDBC 的常用操作
 * 
 * @version 1.0 Copyright (c) 2014 ChongQing University Of Technology
 */
public class CommonDAO {

	// 调用 ParameterMetaData.getParameterType(i + 1) 是否会抛出异常
	protected boolean pmdKnownBroken = false;
	
	public CommonDAO() {

	}

	public CommonDAO(boolean pmdKnownBroken) {
		this.pmdKnownBroken = pmdKnownBroken;
	}

	public Connection getConnetion() {

		Connection conn = null;
		try {
			Class.forName(CodeGenerate.getPath("jdbcDriver"));
			conn = DriverManager.getConnection(CodeGenerate.getPath("jdbc"),
					CodeGenerate.getPath("userName"), CodeGenerate.getPath("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}


	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> excuteQuery(String sql, Object[] o) {
		return (List<Map<String, Object>>) excuteQuery(sql, o,
				new ListMapHander());

	}
	
	public Object excuteQuery(String sql, Object[] o, ResultSetHandler rsh) {

		PreparedStatement pstmt = null;

		Connection con = null;

		ResultSet rs = null;
		try {
			con = this.getConnetion();

			pstmt = con.prepareStatement(sql);

			fillStatement(pstmt, o);

			rs = pstmt.executeQuery();
			System.out.println("SUBMIT TIME : " + new Date() + "\n");
			return rsh.doHander(rs);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			close(con, pstmt, rs);
		}

		return null;

	}

	public int executeUpdate(String sql, Object[] o) {

		PreparedStatement pstmt = null;

		Connection con = null;

		try {

			con = this.getConnetion();

			con.setAutoCommit(false);

			pstmt = con.prepareStatement(sql);

			System.out.println("SUBMIT TIME : " + new Date() + "\n");

			fillStatement(pstmt, o);

			int result = pstmt.executeUpdate();

			con.commit();

			con.setAutoCommit(true);

			return result;
		

		} catch (Exception e) {
			try {
				con.rollback();

				if (!con.getAutoCommit()) {
					con.setAutoCommit(true);
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {

			close(con, pstmt, null);
		}
		return -1;
	}
	
	public int executeUpdate(String sql) {
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		try{
			con = this.getConnetion();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
			System.out.println("SUBMIT TIME : " + new Date() + "\n");
			return count;
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
			this.close(con, pstmt, rs);
		}
		
		return 0;
	}
	
	public int getSqlCount(String sql) throws Exception {
		PreparedStatement pstmt = null;

		Connection con = null;
		
		try{
			con = this.getConnetion();

			con.setAutoCommit(false);

			pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			int count = 0;
			
			while(rs.next()) {
				count++;
			}
			
			return count;

		}catch(Exception ex) {
			throw ex;
		}finally {
			close(con, pstmt, null);
		}
		
	}

	private void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (pstmt != null) {
			try {
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		try {
			if (con != null && !con.isClosed()) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void fillStatement(PreparedStatement pstmt, Object[] o) {

		if (o == null) {
			return;
		}

		try {
			ParameterMetaData pmd = pstmt.getParameterMetaData();

			int count = pmd.getParameterCount();

			int size = o.length;

			if (count != size) {
				return;
			}

			for (int i = 0; i < count; i++) {
				if (o[i] != null) {
					pstmt.setObject(i + 1, o[i]);
				} else {
					int sqlType = Types.VARCHAR;
					sqlType = pmd.getParameterType(i + 1);
					pstmt.setNull(i + 1, sqlType);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/*一个方法,仅仅用来执行一条sql语句*/
	public void executeSql(String sql) throws Exception {
		PreparedStatement pstmt = null;
		Connection con = null;
		try{
			con = this.getConnetion();
			pstmt = con.prepareStatement(sql);	//执行一个语句
			pstmt.executeUpdate();
			System.out.println("SUBMIT TIME : " + new Date() + "\n");
		}catch(Exception e) {
			throw e;
		}
		finally {
			close(con,pstmt,null);
		}
	}

	public int executeUpdateByte(String sql, byte[] b) {

		PreparedStatement pstmt = null;

		Connection con = null;

		try {

			con = this.getConnetion();

			con.setAutoCommit(false);

			pstmt = con.prepareStatement(sql);
			System.out.println(sql + " b:" + b.length);
			pstmt.setBytes(1, b);

			int result = pstmt.executeUpdate();

			con.commit();

			con.setAutoCommit(true);

			return result;

		} catch (Exception e) {
			try {
				con.rollback();

				if (!con.getAutoCommit()) {
					con.setAutoCommit(true);
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {

			close(con, pstmt, null);
		}
		return -1;
	}
	
}
