package candidate.loader.web.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Adrian Perez on 4/16/16.
 */
public class ProvinceJson implements Serializable{

    @JsonProperty("pro_id")
    private String id;

    @JsonProperty("pro_name")
    private String name;

    @JsonProperty("region")
    private String region;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
