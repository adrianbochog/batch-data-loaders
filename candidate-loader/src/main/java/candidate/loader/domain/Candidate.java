package candidate.loader.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Adrian Perez on 4/16/16.
 */
@Entity
public class Candidate {

    @Id
    @Column(name = "CANDIDATE_ID")
    private String candidateId;

    @Column(name = "GIVEN_NAME")
    private String givenName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "POSITION")
    private String position;

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
