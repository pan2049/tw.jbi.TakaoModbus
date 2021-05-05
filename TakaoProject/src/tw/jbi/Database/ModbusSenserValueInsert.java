package tw.jbi.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import tw.jbi.ModbusSlave.JlibmodbusReadValues;

public class ModbusSenserValueInsert {

	private static String dbName = "org.mariadb.jdbc.Driver";
	private static String dburl = "jdbc:mariadb://localhost:3306/takaodb";
	private static String dbUser = "root";
	private static String dbPassword = "123456";
	
	public static void insertSenserValue() {
		
		// 初始資料庫值
		double dbValue = 0.0;
		// 初始senser陣列
		int[] senValues = null;
		senValues = JlibmodbusReadValues.readValues();
		// 現在時間
		Timestamp timestamp;
		try {

			/*
			 * *** 建立連線
			 * */
			Connection conn = DriverManager.getConnection(dburl, dbUser, dbPassword);
			Statement sm = conn.createStatement();
			
			int i = 0;
			for (int value : senValues){
				
				/*
				 * *** 查詢資料庫資料
				 * */
				String seSql = "SELECT Value FROM"+"`"+ i +"`"+"order by startTime desc LIMIT 1";
				ResultSet rs = sm.executeQuery(seSql);
				while(rs.next()) {
					dbValue = rs.getDouble("value");
				}
				System.out.println("資料庫資料 : "+dbValue);
				
				/*
				 * *** 檢查是否為負值
				 * */
				double reValue = 0.0;
				if(value>32767) {
					Double dValue = Double.valueOf(value);
					reValue = (-65536+dValue)/10;
				}else {
					Double dValue = Double.valueOf(value);
					reValue = dValue/10;
				}

				/*
				 * *** 檢查值是否相同，相同不存
				 * */
				if(dbValue == reValue) {
					System.out.println("資料無異動");
				}else {
					timestamp = new Timestamp(System.currentTimeMillis());
					String sql = "INSERT INTO"+"`"+ i +"`"+"VALUES(?,?)";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setTimestamp(1, timestamp);
					pstmt.setDouble(2, reValue);
					pstmt.executeUpdate();
					pstmt.close();
					System.out.println("資料以更新");
				}
				i++;
				
			}
			
			/*
			 * *** 檢查是否異常
			 * */
			
//			if(rsValue >=50000.0) {
//				System.out.println("異常發生");
//			}else {
//				return;
//			}
			
			sm.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
			
	}	
}
