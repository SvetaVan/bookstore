package bookstore.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("spring.data.mongodb")
public class MongoProps {
    private int port;
    private String database;
    private String uri;

    public int getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getUri() {
        return uri;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
