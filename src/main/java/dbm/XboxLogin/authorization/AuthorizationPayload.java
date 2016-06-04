package dbm.XboxLogin.authorization;

import java.util.List;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class AuthorizationPayload {

    @SerializedName("RelyingParty")
    @Expose
    private String relyingParty = "http://xboxlive.com";
    @SerializedName("Properties")
    @Expose
    private Properties properties;
    @SerializedName("TokenType")
    @Expose
    private String tokenType = "JWT";

    public AuthorizationPayload(List<String> userTokens) {
    	this.properties = new Properties(userTokens);
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
