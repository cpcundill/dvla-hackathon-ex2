package msg;

public class Driver {

    private String currentDriverNumber;
    private Address address;

    public String getCurrentDriverNumber() {
        return currentDriverNumber;
    }

    public void setCurrentDriverNumber(String currentDriverNumber) {
        this.currentDriverNumber = currentDriverNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
