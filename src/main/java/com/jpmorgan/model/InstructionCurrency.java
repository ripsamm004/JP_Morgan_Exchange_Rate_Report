package com.jpmorgan.model;

import java.util.Currency;

/**
 * Class which hold the model.Instruction Currency
 */
public class InstructionCurrency {


    private Double fxRate;
    private Integer unit;
    private Double pricePerUnit;
    private Currency currency;


    public InstructionCurrency(Double fxRate, Integer unit, Double pricePerUnit, Currency currency) {
        this.fxRate = fxRate;
        this.unit = unit;
        this.pricePerUnit = pricePerUnit;
        this.currency = currency;

    }

    /**
     * getter method of fx rate
     *
     * @return FX rate in double
     */
    public Double getFxRate() {
        return fxRate;
    }

    /**
     * set value of Fx rate
     *
     * @param fxRate fx rate
     */
    public void setFxRate(Double fxRate) {
        this.fxRate = fxRate;
    }

    /**
     * getter method of unit
     *
     * @return integer value
     */
    public Integer getUnit() {
        return unit;
    }

    /**
     * setting the unit value
     *
     * @param unit Integer unit value
     */
    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    /**
     * getter method of unit perice
     *
     * @return double value
     */

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    /**
     * set unit price
     *
     * @param pricePerUnit set the unit price
     */
    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    /**
     * getting currency of instruction
     *
     * @return Currency object
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * set Currency of object
     *
     * @param currency currency object
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }


}
