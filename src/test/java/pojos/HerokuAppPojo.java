package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HerokuAppPojo {

    //1) Tum keyler icin private veriable'lar olusturulur
    private Integer bookingid;
    private BookingPojo booking;

    //2) Tum parametrelerle ve parametresiz constructor'lar olusturulur
    public HerokuAppPojo(Integer bookingid, BookingPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public HerokuAppPojo() {
    }

    //3) Getters ve Setters'lar olusturulur
    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingPojo booking) {
        this.booking = booking;
    }

    //4) toString methodu olusturulur
    @Override
    public String toString() {
        return "HerokuAppPojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
