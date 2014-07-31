package msg;

public class Driver {

    private String currentDriverNumber;
    private BirthDetails birthDetails;
    private Address address;

    public String getCurrentDriverNumber() {
        return currentDriverNumber;
    }

    public void setCurrentDriverNumber(String currentDriverNumber) {
        this.currentDriverNumber = currentDriverNumber;
    }

    public BirthDetails getBirthDetails() {
        return birthDetails;
    }

    public void setBirthDetails(BirthDetails birthDetails) {
        this.birthDetails = birthDetails;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
