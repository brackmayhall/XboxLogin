package dbm.XboxLogin.authorization;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Properties {

    @SerializedName("UserTokens")
    @Expose
    private List<String> userTokens = new ArrayList<String>();
    
    @SerializedName("SandboxId")
    @Expose
    private String sandboxId = "RETAIL";

    public Properties(List<String> userTokens){
    	this.userTokens = userTokens;
    }
    
    /**
     * 
     * @return
     *     The userTokens
     */
    public List<String> getUserTokens() {
        return userTokens;
    }

    /**
     * 
     * @param userTokens
     *     The UserTokens
     */
    public void setUserTokens(List<String> userTokens) {
        this.userTokens = userTokens;
    }

    /**
     * 
     * @return
     *     The sandboxId
     */
    public String getSandboxId() {
        return sandboxId;
    }

    /**
     * 
     * @param sandboxId
     *     The SandboxId
     */
    public void setSandboxId(String sandboxId) {
        this.sandboxId = sandboxId;
    }

}
