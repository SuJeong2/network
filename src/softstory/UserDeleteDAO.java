package softstory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class UserDeleteDAO {
	public static void main(String[] args) {
		 
	}
	public static boolean create(UserDTO Udto) throws Exception {

		boolean flag = false;
		Connection con = null;
		Statement stmt = null;// 데이터를 전송하는 객체
		PreparedStatement pstmt = null; //statement확장판
		String id = Udto.getId();		
		
		
		//String userDelete_sql = "INSERT INTO currentUser(id) VALUES";
		String userDelete_sql = "DELETE FROM currentUser where id='";

		try {
			//한글처리. mysql상에서 안돼서 여기서 처리함.
			userDelete_sql += new String(id.getBytes(), "ISO-8859-1") + "'";
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/softstory", "root", "12345");
			pstmt = (PreparedStatement)con.prepareStatement(userDelete_sql);
			int result = pstmt.executeUpdate();
			flag = true;
			
			if(result > 0){
				System.out.println("update success");
			}
			else{
				System.out.println("update fail");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return flag;
	}
}
