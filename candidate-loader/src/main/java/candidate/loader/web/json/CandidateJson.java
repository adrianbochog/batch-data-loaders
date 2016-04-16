package candidate.loader.web.json;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Adrian Perez on 4/16/16.
 */
public class CandidateJson implements Serializable {

    @JsonProperty("can_id")
    private String candidateId;

    @JsonProperty("can_name")
    private String candidateName;

    @JsonProperty("running_position")
    private String position;

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
