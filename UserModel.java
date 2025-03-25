package register.fxml;

import java.util.ArrayList;
import java.util.List;

public class UserModel {
private List<User> userList;
	
	public	UserModel() {
		userList = new ArrayList<>();
		userList.add(new User("lhtung@gmail.com", "Lê Hoàng Tùng"));
		userList.add(new User("lhdat@gmail.com", "Lê Hoàng Đạt"));
		userList.add(new User("ptquyen@gmail.com", "Phạm Thị Quyên"));
	}

	public boolean checkEmail(String email) {
		for(User user: userList) {
			if(email.equals(user.getEmail())) {
				return true; //email da ton tai
			}
		}
	
		return false; //email chua ton tai dk thanh cong
	}
	
	public void addUser(User user) {
		userList.add(user);
	}
	
	@Override
	public String toString() {
		String userListStr = "";
		// TODO Auto-generated method stub
		for(User user: userList) {
			userListStr += user.getEmail() + "-" + user.getFullname() + "\n";
		}
		return userListStr;
	}
}
