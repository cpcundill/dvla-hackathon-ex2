package msg;

import java.util.List;

public class DriverSearchResult {

    private List<Driver> results;

    public DriverSearchResult() {}

    public DriverSearchResult(List<Driver> results) {
        this.results = results;
    }

    public List<Driver> getResults() {
        return results;
    }

    public void setResults(List<Driver> results) {
        this.results = results;
    }
}
