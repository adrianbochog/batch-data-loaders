package candidate.loader.web.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Adrian Perez on 4/16/16.
 */
public class CampaignTrailJson {

    @JsonProperty("id")
    private String id;

    @JsonProperty("municipality_id")
    private String municipalityId;

    @JsonProperty("candidate_id")
    private String candidateId;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updateAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(String municipalityId) {
        this.municipalityId = municipalityId;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}
