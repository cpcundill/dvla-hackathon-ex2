package health;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.Mongo;

public class AppHealthCheck extends HealthCheck {

    private Mongo mongo;

    public AppHealthCheck(Mongo mongo) {
        this.mongo = mongo;
    }

    @Override
    protected Result check() throws Exception {
        mongo.getDatabaseNames();
        return Result.healthy("What could possibly go wrong?!");
    }
}
