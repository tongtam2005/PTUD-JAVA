package register.fxml;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	public static List<User> listALLUser() {
        List<User> listUsers = new ArrayList<>();
        String url = "jdbc:ucanaccess://lib/QLNS.accdb";
        String username = "";
        String pass = "";

        String sql = "SELECT * FROM tbluser";

            try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				Connection con = DriverManager.getConnection(url, username, pass);
				Statement sta = con.createStatement();
				ResultSet rs = sta.executeQuery(sql);
				
				while (rs.next()) {
				    String email = rs.getString("email");
				    String fullname = rs.getString("fullname");
				    listUsers.add(new User(email, fullname));
				}
				rs.close();
				sta.close();
				con.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	return listUsers;
	}
	
	public static User checkUser(String email) {
   
        String url = "jdbc:ucanaccess://lib/QLNS.accdb";
        String username = "";
        String pass = "";

        String sql = "SELECT * FROM tbluser WHERE email=?";

            try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				Connection con = DriverManager.getConnection(url, username, pass);
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, email);// đưa mail vào vị trí dâu ? 1
				ResultSet rs = pst.executeQuery();
				
				if(rs.next()) {
				   //có tồn tại user mà có email cần kiểm tra
					 String fullname = rs.getString("fullname");
					 return new User(email, fullname);
				}
				rs.close();
				pst.close();
				con.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	return null;
	}
	public static boolean addUser(User user) {
		boolean resultInserted=false;
		
        String url = "jdbc:ucanaccess://lib/QLNS.accdb";
        String username = "";
        String pass = "";

        String sql = "INSERT INTO tbluser (email,fullname) VALUES(?,?)";

            try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				Connection con = DriverManager.getConnection(url, username, pass);
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, user.getEmail());
				pst.setString(2, user.getFullname());
				resultInserted = pst.executeUpdate()>0;
				
				pst.close();
				con.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	return resultInserted;
	}


	public static void main(String[] args) {
	
		
		User user = UserDAO.checkUser("DTHoang@vnua.edu.vn");
        if (user != null) {
            System.out.println("\nUser tìm thấy: " + user.getFullname());
        } else {
            System.out.println("\nKhông tìm thấy user!");
        }
        
       User newUser=new User("TVHoc@gmail.com","Trần Văn Học");
       boolean resultInsert =UserDAO.addUser(newUser);
       if (resultInsert) {
    	   System.out.println("Thêm mới thành công! ");
       }
   	List<User> listUser=UserDAO.listALLUser();
	for(User u:listUser) {
		System.out.println(u.getEmail()+"-"+u.getFullname());
	}
	}
}
