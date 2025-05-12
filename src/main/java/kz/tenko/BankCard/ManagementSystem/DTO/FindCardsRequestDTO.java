package kz.tenko.BankCard.ManagementSystem.DTO;

public class FindCardsRequestDTO {
    String cardNumber;
    int pageSize = 10;
    int pageNumber = 1;

    public FindCardsRequestDTO() {
    }

    public FindCardsRequestDTO(String cardNumber, int pageSize, int pageNumber) {
        this.cardNumber = cardNumber;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
