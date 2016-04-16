package generic.parser;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Adrian Perez on 4/16/16.
 */
@Component
@ConfigurationProperties(prefix = "parser")
public class ParserProperties {

    private String fileDelimiter;
    private String fileName;

    public String getFileDelimiter() {
        return fileDelimiter;
    }

    public void setFileDelimiter(String fileDelimiter) {
        this.fileDelimiter = fileDelimiter;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
