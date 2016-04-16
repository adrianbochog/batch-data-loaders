package candidate.loader.web.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Adrian Perez on 4/16/16.
 */
public class IssueJson implements Serializable {

    @JsonProperty("id")
    private String Id;

    @JsonProperty("issue")
    private String issue;

    @JsonProperty("shortname")
    private String shortname;

    @JsonProperty("created_at")
    private String creationDate;

    @JsonProperty("updated_at")
    private String updateDate;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
