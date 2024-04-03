package trash;


import web.service.booking.client.exceptions.InvalidCardNumberException;

public class PaymentCard {
    private String cardNumber;

    public PaymentCard() {
    }

    public PaymentCard(String cardNumber)  {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) throws InvalidCardNumberException {
        this.cardNumber = cardNumber;
    }
}