package dbm.XboxLogin.token;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
