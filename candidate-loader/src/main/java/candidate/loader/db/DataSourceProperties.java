package candidate.loader.db;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "database")
public class DataSourceProperties {

    private DataSource logDataSource;

    public DataSource getLogDataSource() {
        return logDataSource;
    }

    public void setLogDataSource(DataSource logDataSource) {
        this.logDataSource = logDataSource;
    }
}
