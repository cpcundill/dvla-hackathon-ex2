package config;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class MongoConfig {

    @NotEmpty
    private String host;
    @Min(1) @Max(65535)
    private int port;
    @NotEmpty
    private String db;
    @NotEmpty
    private String driversCollection;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getDriversCollection() {
        return driversCollection;
    }

    public void setDriversCollection(String driversCollection) {
        this.driversCollection = driversCollection;
    }
}
