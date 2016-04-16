package candidate.loader.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(mappedBy = "municipalityVisited")
    private List<Candidate> visitors;

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

    public List<Candidate> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Candidate> visitors) {
        this.visitors = visitors;
    }

    public void addVisitor(Candidate candidate){
        if(visitors == null){
            visitors = new ArrayList<>();
            visitors.add(candidate);
        } else {
            visitors.add(candidate);
        }
    }
}
