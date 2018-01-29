package com.jpmorgan.services;

import com.jpmorgan.model.Instruction;
import com.jpmorgan.model.InstructionTypeEnum;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Generate report based on daily or any given date instructions data
 */
public class Report {

    private List<Instruction> list;
    private PrinterService printerService;

    /**
     * Constructor take list to process
     *
     * @param list list of instructions
     */
    public Report(List<Instruction> list) {
        this.list = list;
        this.printerService = ConsolePrinter.getInstance();
    }

    /**
     * Filter the list of instruction based on instruction type [ incoming | outgoing ]
     * Sort only if required the result of the instruction list as descending order on the amount
     * [ amount = USD amount of instructions currency]
     *
     * @param date the date of the settled instruction
     * @param instructionType InstructionType (buy/sell)
     * @param isSort only true if need to sort the instruction list as descending order
     * @return list of instruction
     */
    public List<Instruction> getInstructionsByDateAndFlag(LocalDate date, InstructionTypeEnum instructionType, boolean isSort) {
        List<Instruction> ratingList = list.stream().filter(curr -> curr.getSettlementDate().equals(date)
                && curr.getInstructionType().is(instructionType)).collect(Collectors.toList());

        // sort the list in revered order
        if(isSort) ratingList.sort(Comparator.comparing(Instruction::getAmount).reversed());
        return ratingList;
    }

    /**
     * From the list of settled instructions calculate total USD amount
     *
     * @param date the date of the settled instruction
     * @param instructionType InstructionType (buy/sell)
     * @return double the total amount of USD from the list of instructions
     */
    public Double getInstructionAmountByDateAndFlag(LocalDate date, InstructionTypeEnum instructionType) {
        Double total = 0.0;
        for (Instruction curr : getInstructionsByDateAndFlag(date, instructionType, false)) {
            if (curr.getSettlementDate().equals(date) && curr.getInstructionType().is(instructionType)) {
                total += curr.getAmount();
            }
        }
        return total;
    }

    /**
     * Display the total USD amount of settled instruction list
     * invoke printer service to display the amount
     *
     * @param date the date of the settled instruction
     * @param instructionType InstructionType (buy/sell)
     */
    public void printTotalAmountReportByDateAndFlag(LocalDate date, InstructionTypeEnum instructionType) {
        Double totalAmount = getInstructionAmountByDateAndFlag(date, instructionType);
        printerService.printEntityTotalAmountOfTheDay(date, totalAmount, instructionType);
    }

    /**
     * Display the ranking list of instructions based on USD amount from settled instruction list
     * invoke printer service to display the raking list
     *
     * @param date  the date of the settled instruction
     * @param instructionType InstructionType (buy/sell)
     */
    public void printRankingReportByDateAndFlag(LocalDate date, InstructionTypeEnum instructionType) {
        List<Instruction> instructions = getInstructionsByDateAndFlag(date, instructionType, true);
        printerService.printEntityRankingListOfTheDay(date, instructions, instructionType);
    }

}
