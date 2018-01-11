import java.sql.*;
public class connectDatabase {
    static Connection conn;
    public static Connection getConnect()
        {
            String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=宾馆管理";
            String userName="sa";
            String userPwd="123456";

            try
            {
                Class.forName(driverName);
                conn=DriverManager.getConnection(dbURL,userName,userPwd);
                System.out.println("连接数据库成功");
            }
            catch(Exception e)
            {
                e.printStackTrace();
                System.out.print("连接失败");
            }
            return conn;
        }

}

