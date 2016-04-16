package candidate.loader.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Adrian Perez on 4/16/16.
 */
@Entity
public class Issue {

    @Id
    @Column(name = "ISSUE_ID")
    private String issueId;

    @Column
    private String issue;

    @Column
    private String shortname;

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
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
}
