package com.jpmorgan.model;

import com.jpmorgan.services.logic.MondayToFridayWeekDayService;
import com.jpmorgan.services.logic.SundayToThursdayWeekDayService;
import com.jpmorgan.services.logic.WeekDayService;
import com.jpmorgan.utils.Common;

import java.time.LocalDate;

public class Instruction {

    private String entity;
    private InstructionTypeEnum instructionType;
    private LocalDate instructionDate;
    private LocalDate instructedSettlementDate;
    private LocalDate settlementDate;
    private InstructionCurrency instructionCurrency;
    private Double amount;

    /**
     * Constructor of the object
     *
     * @param entity                   name of the entity
     * @param instructionType          object model.InstructionTypeEnum
     * @param instructionDate          date of model.Instruction
     * @param instructedSettlementDate date object instructed settlement
     * @param instructionCurrency      model.InstructionCurrency object
     */
    public Instruction(String entity, InstructionTypeEnum instructionType, LocalDate instructionDate, LocalDate instructedSettlementDate, InstructionCurrency instructionCurrency) {
        this.entity = entity;
        this.instructionType = instructionType;
        this.instructionDate = instructionDate;
        this.instructedSettlementDate = instructedSettlementDate;
        settlementDate(instructionCurrency);
        this.instructionCurrency = instructionCurrency;
        this.amount = this.getAmount();
    }

    private void settlementDate(InstructionCurrency instructionCurrency) {

        WeekDayService weekDayService;

        if (Common.doStringsMatch("AED", instructionCurrency.getCurrency().getCurrencyCode()) || Common.doStringsMatch("SAR", instructionCurrency.getCurrency().getCurrencyCode())) {
            weekDayService = SundayToThursdayWeekDayService.getInstance();
        } else {
            weekDayService = MondayToFridayWeekDayService.getInstance();
        }

        this.settlementDate = weekDayService.getNextWorkingDay(this.instructedSettlementDate);
    }

    /**
     * model.Instruction type is return
     *
     * @return model.InstructionTypeEnum  object
     */
    public InstructionTypeEnum getInstructionType() {
        return instructionType;
    }

    /**
     * model.Instruction Type set by model.InstructionTypeEnum object
     *
     * @param instructionType object model.InstructionTypeEnum
     */
    public void setInstructionType(InstructionTypeEnum instructionType) {
        this.instructionType = instructionType;
    }

    /**
     * Get the Name of the entity
     *
     * @return String name of the entity
     */
    public String getEntity() {
        return entity;
    }

    /**
     * Get the name of the the entity
     *
     * @param entity String entity name
     */
    public void setEntity(String entity) {
        this.entity = entity;
    }

    /**
     * The date on which model.Instruction is requested
     *
     * @return date object
     */
    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    /**
     * update the date on which model.Instruction is requested
     *
     * @param instructionDate date of model.Instruction
     */
    public void setInstructionDate(LocalDate instructionDate) {
        this.instructionDate = instructionDate;
    }

    /**
     * The date on which model.Instruction InstructedSettlementDate is requested
     *
     * @return date object
     */
    public LocalDate getInstructedSettlementDate() {
        return instructedSettlementDate;
    }

    /**
     * update on which model.Instruction InstructedSettlementDate is requested
     *
     * @param instructedSettlementDate date object
     */
    public void setInstructedSettlementDate(LocalDate instructedSettlementDate) {
        this.instructedSettlementDate = instructedSettlementDate;
    }

    /**
     * Date on which Settlement is requested
     *
     * @return date object
     */
    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    /**
     * get currency object of model.Instruction
     *
     * @return object of model.InstructionCurrency
     */
    public InstructionCurrency getInstructionCurrency() {
        return instructionCurrency;
    }

    /**
     * model.Instruction Currency  is set
     *
     * @param instructionCurrency model.InstructionCurrency object
     */
    public void setInstructionCurrency(InstructionCurrency instructionCurrency) {
        this.instructionCurrency = instructionCurrency;
    }

    /**
     * getter method of amount
     *
     * @return double value of amount
     */
    public Double getAmount() {
        if (Common.isBlank(this.amount)) {
            {
                if (Common.isBlank(this.instructionCurrency.getPricePerUnit()) || Common.isBlank(this.instructionCurrency.getUnit()) || Common.isBlank(this.instructionCurrency.getFxRate())) {
                    this.amount = 0.0;
                } else {
                    this.amount = (this.instructionCurrency.getPricePerUnit() * this.instructionCurrency.getUnit() * this.instructionCurrency.getFxRate());
                }
            }
        }
        return this.amount;
    }
}
