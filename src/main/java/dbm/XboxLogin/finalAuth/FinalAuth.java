package dbm.XboxLogin.finalAuth;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class FinalAuth {

    @SerializedName("IssueInstant")
    @Expose
    private String issueInstant;
    @SerializedName("NotAfter")
    @Expose
    private String notAfter;
    @SerializedName("Token")
    @Expose
    private String token;
    @SerializedName("DisplayClaims")
    @Expose
    private DisplayClaims displayClaims;

    /**
     * 
     * @return
     *     The issueInstant
     */
    public String getIssueInstant() {
        return issueInstant;
    }

    /**
     * 
     * @param issueInstant
     *     The IssueInstant
     */
    public void setIssueInstant(String issueInstant) {
        this.issueInstant = issueInstant;
    }

    /**
     * 
     * @return
     *     The notAfter
     */
    public String getNotAfter() {
        return notAfter;
    }

    /**
     * 
     * @param notAfter
     *     The NotAfter
     */
    public void setNotAfter(String notAfter) {
        this.notAfter = notAfter;
    }

    /**
     * 
     * @return
     *     The token
     */
    public String getToken() {
        return token;
    }

    /**
     * 
     * @param token
     *     The Token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 
     * @return
     *     The displayClaims
     */
    public DisplayClaims getDisplayClaims() {
        return displayClaims;
    }

    /**
     * 
     * @param displayClaims
     *     The DisplayClaims
     */
    public void setDisplayClaims(DisplayClaims displayClaims) {
        this.displayClaims = displayClaims;
    }

}
