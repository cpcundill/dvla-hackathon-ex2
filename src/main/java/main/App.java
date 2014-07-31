package main;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import config.DriverSearchConfig;
import config.MongoConfig;
import health.AppHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jongo.Jongo;
import resource.SearchDriversResource;

import java.text.DateFormat;

public class App extends Application<DriverSearchConfig> {

    @Override
    public String getName() {
        return "driver-search-service";
    }

    @Override
    public void initialize(Bootstrap<DriverSearchConfig> bootstrap) {}

    @Override
    public void run(DriverSearchConfig config, Environment environment) throws Exception {
        MongoConfig mongoConfig = config.getMongo();

        Mongo mongo = new MongoClient(mongoConfig.getHost(), mongoConfig.getPort());
        environment.lifecycle().manage(new MongoManaged(mongo));
        environment.healthChecks().register("app", new AppHealthCheck(mongo));

        Jongo jongo = new Jongo(mongo.getDB(mongoConfig.getDb()));
        environment.jersey().register(new SearchDriversResource(jongo, mongoConfig.getDriversCollection()));

    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }


}
