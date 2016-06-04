package dbm.XboxLogin.loginpayload;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payload {

@SerializedName("login")
@Expose
private String login;
@SerializedName("passwd")
@Expose
private String passwd;
@SerializedName("PPFT")
@Expose
private String PPFT;
@SerializedName("PPSX")
@Expose
private String PPSX = "Passpor";
@SerializedName("SI")
@Expose
private String SI = "Sign in";
@SerializedName("type")
@Expose
private String type = "11";
@SerializedName("NewUser")
@Expose
private String NewUser = "1";
@SerializedName("LoginOptions")
@Expose
private String LoginOptions = "1";
@SerializedName("i3")
@Expose
private String i3 = "36728";
@SerializedName("m1")
@Expose
private String m1 = "768";
@SerializedName("m2")
@Expose
private String m2 = "1184";
@SerializedName("m3")
@Expose
private String m3 = "0";
@SerializedName("i12")
@Expose
private String i12 = "1";
@SerializedName("i17")
@Expose
private String i17 = "0";
@SerializedName("i18")
@Expose
private String i18 = "__Login_Host|1";

/**
* 
* @return
* The login
*/
public String getLogin() {
return login;
}

/**
* 
* @param login
* The login
*/
public void setLogin(String login) {
this.login = login;
}

/**
* 
* @return
* The passwd
*/
public String getPasswd() {
return passwd;
}

/**
* 
* @param passwd
* The passwd
*/
public void setPasswd(String passwd) {
this.passwd = passwd;
}

/**
* 
* @return
* The PPFT
*/
public String getPPFT() {
return PPFT;
}

/**
* 
* @param PPFT
* The PPFT
*/
public void setPPFT(String PPFT) {
this.PPFT = PPFT;
}

/**
* 
* @return
* The PPSX
*/
public String getPPSX() {
return PPSX;
}

/**
* 
* @param PPSX
* The PPSX
*/
public void setPPSX(String PPSX) {
this.PPSX = PPSX;
}

/**
* 
* @return
* The SI
*/
public String getSI() {
return SI;
}

/**
* 
* @param SI
* The SI
*/
public void setSI(String SI) {
this.SI = SI;
}

/**
* 
* @return
* The type
*/
public String getType() {
return type;
}

/**
* 
* @param type
* The type
*/
public void setType(String type) {
this.type = type;
}

/**
* 
* @return
* The NewUser
*/
public String getNewUser() {
return NewUser;
}

/**
* 
* @param NewUser
* The NewUser
*/
public void setNewUser(String NewUser) {
this.NewUser = NewUser;
}

/**
* 
* @return
* The LoginOptions
*/
public String getLoginOptions() {
return LoginOptions;
}

/**
* 
* @param LoginOptions
* The LoginOptions
*/
public void setLoginOptions(String LoginOptions) {
this.LoginOptions = LoginOptions;
}

/**
* 
* @return
* The i3
*/
public String getI3() {
return i3;
}

/**
* 
* @param i3
* The i3
*/
public void setI3(String i3) {
this.i3 = i3;
}

/**
* 
* @return
* The m1
*/
public String getM1() {
return m1;
}

/**
* 
* @param m1
* The m1
*/
public void setM1(String m1) {
this.m1 = m1;
}

/**
* 
* @return
* The m2
*/
public String getM2() {
return m2;
}

/**
* 
* @param m2
* The m2
*/
public void setM2(String m2) {
this.m2 = m2;
}

/**
* 
* @return
* The m3
*/
public String getM3() {
return m3;
}

/**
* 
* @param m3
* The m3
*/
public void setM3(String m3) {
this.m3 = m3;
}

/**
* 
* @return
* The i12
*/
public String getI12() {
return i12;
}

/**
* 
* @param i12
* The i12
*/
public void setI12(String i12) {
this.i12 = i12;
}

/**
* 
* @return
* The i17
*/
public String getI17() {
return i17;
}

/**
* 
* @param i17
* The i17
*/
public void setI17(String i17) {
this.i17 = i17;
}

/**
* 
* @return
* The i18
*/
public String getI18() {
return i18;
}

/**
* 
* @param i18
* The i18
*/
public void setI18(String i18) {
this.i18 = i18;
}

}