package kz.tenko.BankCard.ManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import kz.tenko.BankCard.ManagementSystem.enums.CardStatus;
import kz.tenko.BankCard.ManagementSystem.enums.Currencies;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.LongVarcharJdbcType;

import java.time.LocalDate;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "card_number")
    private String cardNumber;

    //todo установить отображаемый формат mm/yyyy, автоматический расчет даты срока пользования(прим 3 года)
    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "status")
    @JdbcType(LongVarcharJdbcType.class)
    private CardStatus status;

    @Column(name = "balance")
    private Long balance;

    @Column(name = "currency")
    private Currencies currency;

    @Column(name = "card_blocking_request")
    boolean cardBlockingRequest;

    @Column(name = "secret_num")
    int secretNum;

    public Card() {
    }

    public Card(Long id, Long userId, String number, LocalDate expirationDate, CardStatus status, Long balance, boolean cardBlockingRequest, int secretNum) {
        this.id = id;
        this.userId = userId;
        this.cardNumber = number;
        this.expirationDate = expirationDate;
        this.status = status;
        this.balance = balance;
        this.cardBlockingRequest = cardBlockingRequest;
        this.secretNum = secretNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Transient
    @JsonProperty("number")
    public String getMaskedNumber() {
        return "**** **** **** " + getCardNumber().substring(cardNumber.length() - 4);
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Currencies getCurrency() {
        return currency;
    }

    public void setCurrency(Currencies currency) {
        this.currency = currency;
    }

    public boolean isCardBlockingRequest() {
        return cardBlockingRequest;
    }

    public void setCardBlockingRequest(boolean cardBlockingRequest) {
        this.cardBlockingRequest = cardBlockingRequest;
    }

    public int getSecretNum() {
        return secretNum;
    }

    public void setSecretNum(int secretNum) {
        this.secretNum = secretNum;
    }
}
