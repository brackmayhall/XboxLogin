package dbm.XboxLogin;

public class LoginApp {

	public static void main(String[] args) throws Exception {
		String un = "GAMERTAG_HERE";
		String pw = "PASSWORD_HERE";		  
		XboxClient xboxLogin = new XboxClient(un,pw);
		xboxLogin.login();
		System.out.println("if everything worked you will be logged in at this point.");
		System.out.println("This needs to be extended to do anything with the logged in account.");
	}
}
