package kz.tenko.BankCard.ManagementSystem.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "currency_rate")
public class CurrencyRate {

    //todo ограничение в 3 года, пока действует карта - сохраняется курс валют + выписка по истечению срока годности карты
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "USD")
    private Double USD;

    @Column(name = "rate_USD_EUR")
    private Double rateEUR;

    @Column(name = "rate_USD_RUB")
    private Double rateRUB;

    @Column(name = "rate_USD_KZT")
    private Double rateKZT;

    @Column(name = "currency_date")
    private LocalDateTime currencyDate;

    public CurrencyRate() {
    }

    public CurrencyRate(Long id, Double USD, Double rateEUR, Double rateRUB, Double rateKZT, LocalDateTime currencyDate) {
        this.id = id;
        this.USD = USD;
        this.rateEUR = rateEUR;
        this.rateRUB = rateRUB;
        this.rateKZT = rateKZT;
        this.currencyDate = currencyDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getUSD() {
        return USD;
    }

    public void setUSD(double USD) {
        this.USD = USD;
    }

    public Double getRateEUR() {
        return rateEUR;
    }

    public void setRateEUR(double rateEUR) {
        this.rateEUR = rateEUR;
    }

    public Double getRateRUB() {
        return rateRUB;
    }

    public void setRateRUB(double rateRUB) {
        this.rateRUB = rateRUB;
    }

    public Double getRateKZT() {
        return rateKZT;
    }

    public void setRateKZT(double rateKZT) {
        this.rateKZT = rateKZT;
    }

    public LocalDateTime getCurrencyDate() {
        return currencyDate;
    }

    public void setCurrencyDate(LocalDateTime currencyDate) {
        this.currencyDate = currencyDate;
    }
}
