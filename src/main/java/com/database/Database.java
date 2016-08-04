package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.BillingType.DataTraffic;
import com.User.User;
import com.User.UserDataTraffic;
import com.generateData.RuleName;
import com.sun.javafx.collections.MappingChange.Map;
import com.utils.PropertiesUtils;

public class Database {
	private static final String databaseName = PropertiesUtils.getPropertyValue("databaseName");
	private static final String tempTableName = PropertiesUtils.getPropertyValue("tempTableName");
//	private static final String resultTableName = PropertiesUtils.getPropertyValue("resultTableName");

	private static final String url = PropertiesUtils.getPropertyValue("url");
	private static final String name = PropertiesUtils.getPropertyValue("name");
	private static final String user = PropertiesUtils.getPropertyValue("user");
	private static final String password = PropertiesUtils.getPropertyValue("password");

	private Connection conn = null;
	private PreparedStatement pst = null;

	// /**
	// * 首先判断数据库是否存在,如果不存在就创建这个数据库
	// */
	// private void createDatabase() {
	// try {
	// Connection con = DriverManager.getConnection(url, user, password);
	// con.createStatement().execute("create database If Not Exists " +
	// databaseName
	// + " DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;");
	// } catch (SQLException e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// }
	// }

	/**
	 * 根据给出的数据库名字创建数据库
	 * 
	 * @param databaseName
	 */
	public void createDatabase(String dbName) {
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			con.createStatement().execute(
					"create database If Not Exists " + dbName + " DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * 根据建表语句建立数据库表
	 * 
	 * @param tableStr
	 */
	public void creatTable(String tableStr) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			con.createStatement().execute(tableStr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// /**
	// * 如果表格不存在,则创建表
	// */
	// private void creatTable() {
	// List<String> ruleName = RuleName.getRuleName();
	// String str = "Create Table If Not Exists " + databaseName + "." +
	// tempTableName
	// + "(phoneNum VarChar(20) Primary key NOT NULL, ";
	// String str2 = "";
	// for (String string : ruleName) {
	// str2 += string + " bigint default '0',";
	// }
	// str2 = str2.substring(0, str2.length() - 1); // 去掉末尾的逗号
	// str += str2 + ");";
	//
	// Connection con = null;
	// try {
	// con = DriverManager.getConnection(url, user, password);
	// con.createStatement().execute(str);
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }finally {
	// try {
	// con.close();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
	// }

	/**
	 * 得到一个连接，这样在多次插入或者查询操作的时候就不用每次都初始化一个连接
	 * 
	 * @return
	 */
	public Connection getOneConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * 关闭制定的数据库连接
	 * 
	 * @param con
	 */
	public void closeConnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 向数据库中插入一条数据或者更新这条数据的最优套餐
	 * 
	 * @param usermessage
	 *            用户数据
	 * @param con
	 *            数据库连接
	 * @param isItself
	 *            判断是用户自己的套餐还是其他套餐，true为自己的
	 */
	public void insertTempRecord(User usermessage, Connection con, boolean isItself) {
		String phoneNum = usermessage.getPhoneNumber();
		UserDataTraffic dataTraffic = usermessage.getUserDataTraffic();
		if (isItself) {
			String myPackageName = dataTraffic.getBandName();
			long myResidueGNFreeDF = dataTraffic.getResidue_gn();
			long myResidueSNFreeDF = dataTraffic.getResidue_sn();
			long myResidueBDFreeDF = dataTraffic.getResidue_bd();

			long myExceededGNDF = dataTraffic.getExceed_gn();
			long myExceededSNDF = dataTraffic.getExceed_sn();
			long myExceededBDDF = dataTraffic.getExceed_bd();

			// 不足1M按1M算
			System.out.println(myExceededSNDF);
			float myDFFee = myExceededGNDF * dataTraffic.getGn_unitPrice() / 1024 + dataTraffic.getGn_unitPrice();
			myDFFee += myExceededSNDF * dataTraffic.getSn_unitPrice() / 1024 + dataTraffic.getGn_unitPrice();
			myDFFee += myExceededBDDF * dataTraffic.getBd_unitPrice() / 1024 + dataTraffic.getGn_unitPrice();

			String str = "insert into " + databaseName + "." + tempTableName + "(phoneNum, myPackageName, "
					+ "myResidueGNFreeDF, myResidueSNFreeDF, myResidueBDFreeDF, myExceededGNDF, "
					+ "myExceededSNDF, myExceededBDDF, myDFFee) value( " + phoneNum + ", '" + myPackageName + "', " + myResidueGNFreeDF + ", "
					+ myResidueSNFreeDF + ", " + myResidueBDFreeDF + ", " + myExceededGNDF + ", " + myExceededSNDF + ", "
					+ myExceededBDDF + ", " + myDFFee + " ) " + "ON DUPLICATE KEY UPDATE ";

			String str1 = "myPackageName='" + myPackageName + "', myResidueGNFreeDF=" + myResidueGNFreeDF
					+ ", myResidueSNFreeDF=" + myResidueSNFreeDF + ", myResidueBDFreeDF=" + myResidueBDFreeDF
					+ ", myExceededGNDF=" + myExceededGNDF + ", myExceededSNDF=" + myExceededSNDF + ", myExceededBDDF="
					+ myExceededBDDF + ", myDFFee=" + myDFFee + ";";

			str += str1;

			System.out.println(str);
			try {
				con.createStatement().execute(str);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// /**
	// * 想temp表格中插入（如果不存在）或者更新（如果已经存在）一条数据
	// * @param usermessage
	// */
	// // 可以再优化一下，争取用同一个连接去插入数据，这样可以节省资源和加快，但是先不管，没时间了。。。。。。。。。。。。。。
	// public void insertTempRecord(User usermessage, Connection con){
	// HashMap<String, Long> hashMap = usermessage.getRuleHashMap();
	// Iterator<String> iter = hashMap.keySet().iterator();
	// List<String> keyList = new ArrayList<String>();
	// List<Long> valueList = new ArrayList<Long>();
	//
	// while (iter.hasNext()){
	// String key = (String) iter.next();
	// long value = hashMap.get(key);
	// keyList.add(key);
	// valueList.add(value);
	// }
	//
	// String str = "insert into " + databaseName + "." + tempTableName + "(";
	// // 构造表项
	// String str1 = "phoneNum,";
	// for (String string : keyList) {
	//// str1 += "'" + string + "',";
	// str1 += string + ",";
	// }
	// str1 = str1.substring(0, str1.length() - 1);
	// str1 += ") ";
	//
	// //构造记录值
	// String str2 = "value('" + usermessage.getPhoneNumber() + "',";
	// for (Long long1 : valueList) {
	// str2 += "'" + long1 + "',";
	// }
	// str2 = str2.substring(0, str2.length() - 1);
	// str2 += ") ON DUPLICATE KEY UPDATE ";
	//
	// String str3 = "";
	// for (int i = 0; i < keyList.size(); i++) {
	// str3 += keyList.get(i) + "=" + valueList.get(i) + ", ";
	// }
	// str3 = str3.substring(0, str3.length() -2);
	//
	// str += str1 + str2 + str3 + ";";
	//
	// System.out.println(str);
	// try {
	// con.createStatement().execute(str);
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public static void main(String[] args) {
		Database database = new Database();
	}

	// 构造函数初始化连接
	public Database() {
		try {
			Class.forName("com.mysql.jdbc.Driver");// 指定连接类型
			conn = DriverManager.getConnection(url, user, password);// 获取连接
		} catch (Exception e) {
			e.printStackTrace();
		}
		// createDatabase();
		// creatTable();
	}

	// 执行查询功能
	public ResultSet query(String sql) {
		PreparedStatement pStatement = null;
		ResultSet ret = null;
		try {
			pStatement = conn.prepareStatement(sql); // 准备执行语句
			ret = pStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	// 关闭数据库
	public void close() {
		try {
			this.conn.close();
			this.pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
