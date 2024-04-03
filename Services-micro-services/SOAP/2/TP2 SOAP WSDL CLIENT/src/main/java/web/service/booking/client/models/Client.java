package web.service.booking.client.models;

public class Client {
    private Integer id;
    private String lastName;
    private String firstName;

    private String cardNumber;

    public Client() {
    }

    public Client(String lastName, String firstName, String cardNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.cardNumber = cardNumber;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;

        if (getLastName() != null ? !getLastName().equals(client.getLastName()) : client.getLastName() != null)
            return false;
        if (getFirstName() != null ? !getFirstName().equals(client.getFirstName()) : client.getFirstName() != null)
            return false;
        return getCardNumber() != null ? getCardNumber().equals(client.getCardNumber()) : client.getCardNumber() == null;
    }

    @Override
    public int hashCode() {
        int result = getLastName() != null ? getLastName().hashCode() : 0;
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getCardNumber() != null ? getCardNumber().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
