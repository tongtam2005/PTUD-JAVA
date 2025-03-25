package register.fxml;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RegisterController {
	@FXML
	private Label errorLaber;
	
	@FXML
	private TextField emailTF;
	
	@FXML
	private TextField fullnameTF;
	
	private UserModel userModel = new UserModel(); //du lieu fake
	
	@FXML
	public void oncCickRegisterBtn() {
		//Lay thong tin ng dung nhap vao
		String email = emailTF.getText();
		String fullname = fullnameTF.getText();
		
		System.out.println(email + ":" + fullname);
	
		//Kiem tra email da ton tai chua
		if(userModel.checkEmail(email)) {
			errorLaber.setText("Lỗi email đã tồn tại!");
		}else {//dang ky thanh cong
			//Them user moi vao ds user (CSDL)
			userModel.addUser(new User(email, fullname));
			//Dong man hinh hien tai = cach tim ra cua so cha tu thanh phan UI
			emailTF.getScene().getWindow().hide();
			
			//Mo ra man hinh home
			goHomeScreen();
		}
	}

	private void goHomeScreen() {
		// Thanh phan UI control
		Label userListLabel = new Label(userModel.toString());
		
		//Tao layout va dua UI vao
		HBox root = new HBox(userListLabel);
	
		//Dua layout vao Scene
		Scene scene = new Scene(root, 500, 500);
		
		//Tao moi stage, dua scene vao
		Stage homeStage = new Stage();
		homeStage.setScene(scene);
		
		//Hien thi stage
		homeStage.setTitle("Home Screen");
		homeStage.show();
	}
}

