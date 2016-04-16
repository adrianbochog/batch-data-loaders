package candidate.loader.domain;

import javax.persistence.*;

/**
 * Created by Adrian Perez on 4/16/16.
 */
@Entity
public class Municipality {

    @Id
    @Column(name = "MUNICIPALITY_ID")
    private String municipalityId;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "PROVINCE_ID")
    private Province province;

    public String getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(String municipalityId) {
        this.municipalityId = municipalityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
