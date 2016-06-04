package dbm.XboxLogin.authentication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AuthPayload {

    @SerializedName("RelyingParty")
    @Expose
    private String relyingParty = "http://auth.xboxlive.com";
    @SerializedName("Properties")
    @Expose
    private Properties properties;
    @SerializedName("TokenType")
    @Expose
    private String tokenType = "JWT";

    public AuthPayload(String accessToken) {
    	this.properties = new Properties(accessToken);
    }
    
    /**
     * 
     * @return
     *     The relyingParty
     */
    public String getRelyingParty() {
        return relyingParty;
    }

    /**
     * 
     * @param relyingParty
     *     The RelyingParty
     */
    public void setRelyingParty(String relyingParty) {
        this.relyingParty = relyingParty;
    }

    /**
     * 
     * @return
     *     The properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * 
     * @param properties
     *     The Properties
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * 
     * @return
     *     The tokenType
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * 
     * @param tokenType
     *     The TokenType
     */
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

}
