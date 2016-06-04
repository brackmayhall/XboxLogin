package dbm.XboxLogin.finalAuth;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class DisplayClaims {

    @SerializedName("xui")
    @Expose
    private List<Xui> xui = new ArrayList<Xui>();

    /**
     * 
     * @return
     *     The xui
     */
    public List<Xui> getXui() {
        return xui;
    }

    /**
     * 
     * @param xui
     *     The xui
     */
    public void setXui(List<Xui> xui) {
        this.xui = xui;
    }

}
