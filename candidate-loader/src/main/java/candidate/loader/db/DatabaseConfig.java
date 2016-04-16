package candidate.loader.db;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
    public static final String VALIDATION_QUERY = "select 1 from dual";

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean
    public javax.sql.DataSource logDataSource() {
        org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
        DataSource appDataSource = dataSourceProperties.getLogDataSource();
        ds.setDriverClassName(appDataSource.getDriverClassName());
        ds.setUrl(appDataSource.getUrl());
        ds.setUsername(appDataSource.getUsername());
        ds.setPassword(appDataSource.getPassword());
        ds.setValidationQuery(VALIDATION_QUERY);
        ds.setTestOnBorrow(true);
        ds.setTestOnReturn(true);
        ds.setTestWhileIdle(true);
        return ds;
    }
}
