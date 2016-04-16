package candidate.loader.web.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Adrian Perez on 4/16/16.
 */
public class MunicipalityJson {

    @JsonProperty("mun_id")
    private String mId;

    @JsonProperty("pro_id")
    private String pId;

    @JsonProperty("mun_name")
    private String name;

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
