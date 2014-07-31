package msg;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BirthDetails {

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="UTC")
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
