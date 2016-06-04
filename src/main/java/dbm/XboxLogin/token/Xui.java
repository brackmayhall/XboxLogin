package dbm.XboxLogin.token;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Xui {

    @SerializedName("uhs")
    @Expose
    private String uhs;

    /**
     * 
     * @return
     *     The uhs
     */
    public String getUhs() {
        return uhs;
    }

    /**
     * 
     * @param uhs
     *     The uhs
     */
    public void setUhs(String uhs) {
        this.uhs = uhs;
    }

}
