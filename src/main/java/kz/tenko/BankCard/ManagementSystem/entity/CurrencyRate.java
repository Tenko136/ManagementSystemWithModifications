package kz.tenko.BankCard.ManagementSystem.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "currency_rate")
public class CurrencyRate {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "USD")
    private CurrencyRate USD;

    @Column(name = "EUR")
    private CurrencyRate EUR;

    @Column(name = "RUB")
    private CurrencyRate RUB;

    @Column(name = "KZT")
    private CurrencyRate KZT;

    @Column(name = "currency_date")
    private LocalDateTime currencyDate;

    public CurrencyRate() {
    }

    public CurrencyRate(CurrencyRate USD, CurrencyRate EUR, CurrencyRate RUB, CurrencyRate KZT, LocalDateTime currencyDate) {
        this.USD = USD;
        this.EUR = EUR;
        this.RUB = RUB;
        this.KZT = KZT;
        this.currencyDate = currencyDate;
    }

    public CurrencyRate getUSD() {
        return USD;
    }

    public void setUSD(CurrencyRate USD) {
        this.USD = USD;
    }

    public CurrencyRate getEUR() {
        return EUR;
    }

    public void setEUR(CurrencyRate EUR) {
        this.EUR = EUR;
    }

    public CurrencyRate getRUB() {
        return RUB;
    }

    public void setRUB(CurrencyRate RUB) {
        this.RUB = RUB;
    }

    public CurrencyRate getKZT() {
        return KZT;
    }

    public void setKZT(CurrencyRate KZT) {
        this.KZT = KZT;
    }

    public LocalDateTime getCurrencyDate() {
        return currencyDate;
    }

    public void setCurrencyDate(LocalDateTime currencyDate) {
        this.currencyDate = currencyDate;
    }
}
