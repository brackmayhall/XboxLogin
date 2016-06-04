package dbm.XboxLogin.authentication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Properties {

    @SerializedName("SiteName")
    @Expose
    private String siteName = "user.auth.xboxlive.com";
    
    @SerializedName("RpsTicket")
    @Expose
    private String rpsTicket;
    
    @SerializedName("AuthMethod")
    @Expose
    private String authMethod = "RPS";

    public Properties(String accessToken){
    	this.rpsTicket = accessToken;
    }
    
    /**
     * 
     * @return
     *     The siteName
     */
    public String getSiteName() {
        return siteName;
    }

    /**
     * 
     * @param siteName
     *     The SiteName
     */
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    /**
     * 
     * @return
     *     The rpsTicket
     */
    public String getRpsTicket() {
        return rpsTicket;
    }

    /**
     * 
     * @param rpsTicket
     *     The RpsTicket
     */
    public void setRpsTicket(String rpsTicket) {
        this.rpsTicket = rpsTicket;
    }

    /**
     * 
     * @return
     *     The authMethod
     */
    public String getAuthMethod() {
        return authMethod;
    }

    /**
     * 
     * @param authMethod
     *     The AuthMethod
     */
    public void setAuthMethod(String authMethod) {
        this.authMethod = authMethod;
    }

}
