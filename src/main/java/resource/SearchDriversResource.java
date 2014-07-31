package resource;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import msg.Driver;
import msg.DriverSearchResult;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.jongo.Find;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/driver") @Produces(MediaType.APPLICATION_JSON)
public class SearchDriversResource {

    private Jongo jongo;
    private String driversCollection;

    public SearchDriversResource(Jongo jongo, String driversCollection) {
        this.jongo = jongo;
        this.driversCollection = driversCollection;
    }

    @GET @Timed
    public DriverSearchResult search(@QueryParam("dln") Optional<String> dln, @QueryParam("ageRange") Optional<String> ageRange) {
        MongoCollection drivers = jongo.getCollection(driversCollection);
        Find searchQuery;
        if (dln.isPresent())
            searchQuery = drivers.find("{currentDriverNumber: #}", dln.get());
        else if (ageRange.isPresent())
            searchQuery = createAgeRangeQuery(drivers, ageRange);
        else
            searchQuery = drivers.find().limit(100);

        return new DriverSearchResult(
                Lists.newArrayList(searchQuery.as(Driver.class).iterator())
        );
    }

    private Find createAgeRangeQuery(MongoCollection drivers, Optional<String> ageRange) {
        String[] ages = ageRange.get().split("-");
        DateTime upperBoundary = DateTime.now().minusYears(Integer.parseInt(ages[0]));
        DateTime lowerBoundary = DateTime.now().minusYears(Integer.parseInt(ages[1]));
        return drivers.find("{birthDetails.date: {$gte: #, $lte: #}}", lowerBoundary.toDate(), upperBoundary.toDate());
    }
}
