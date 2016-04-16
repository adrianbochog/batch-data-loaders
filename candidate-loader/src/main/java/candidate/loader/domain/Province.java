package candidate.loader.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Adrian Perez on 4/16/16.
 */
@Entity
public class Province {

    @Id
    @Column(name = "PROVINCE_ID")
    private String provinceId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "REGION")
    private String region;

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
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
