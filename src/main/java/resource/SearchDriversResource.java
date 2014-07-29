package resource;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import msg.DriverSearchResult;
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
    public List<DriverSearchResult> search(@QueryParam("dln") Optional<String> dln) {
        MongoCollection drivers = jongo.getCollection(driversCollection);
        Find searchQuery;
        if (dln.isPresent())
            searchQuery = drivers.find("{currentDriverNumber: #}", dln.get());
        else
            searchQuery = drivers.find().limit(10);

        return Lists.newArrayList(
                searchQuery.as(DriverSearchResult.class).iterator()
        );
    }
}
