package tw.jbi.Database;

public class DatabaseCheckClass {

	private static String dbName = "org.mariadb.jdbc.Driver";
	private static String dburl = "jdbc:mariadb://localhost:3306/modbus";
	private static String dbUser = "root";
	private static String dbPassword = "123456";
	
	public static void dbCheck() {
		try {
			Class.forName(dbName);
			System.out.println("Database連線成功 \n");
		}catch(ClassNotFoundException ce){
			System.out.println("JDBC連線失敗" + ce.getMessage());
			return;
		}
	}
}
