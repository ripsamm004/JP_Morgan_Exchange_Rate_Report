package com.jpmorgan.services;

import com.jpmorgan.model.Instruction;
import com.jpmorgan.model.InstructionTypeEnum;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * Print the report in the console
 *
 */

public class ConsolePrinter implements PrinterService {

    private static ConsolePrinter instance = null;

    private ConsolePrinter() {
        // Exists only to defeat instantiation.
    }

    // Trying to make the class as singleton
    public static ConsolePrinter getInstance() {
        if(instance == null) {
            // Will be protect from multi threading not allow to create a new instance
            synchronized (ConsolePrinter.class){
                if (instance == null) {
                    instance = new ConsolePrinter();
                }
            }
        }
        return instance;
    }

    /**
     * Print the total amount in USD of the list of instruction for a given settled date
     *
     * @param date the date of the settled instruction
     * @param amount total amount of all settled instructions of the date
     * @param instructionType InstructionType (buy/sell)
     */
    @Override
    public void printEntityTotalAmountOfTheDay(LocalDate date, Double amount, InstructionTypeEnum instructionType) {
        String flag = instructionType.getFlagValue();
        System.out.println(String.format("\033[1m\nTotal %s settled amount in USD on %s :\033[0m %s", flag, date.toString(), amount));
    }

    /**
     * Print list of instructions with ranking
     * the instruction with highest amount of USD is the ranking 1
     *
     * @param date the date of the settled instruction
     * @param list list of Instruction to print
     * @param instructionType InstructionType (buy/sell)
     */
    @Override
    public void printEntityRankingListOfTheDay(LocalDate date, List<Instruction> list, InstructionTypeEnum instructionType) {
        String flag = instructionType.getFlagValue();
        System.out.println(String.format("\n\t\t\tRanking report of %1$-10s %2$-10s", flag, date.toString()));
        System.out.println(String.format("%1$-10s|%2$-10s|%3$-20s|%4$-20s", "+----------", "----------", "--------------------", "--------------------+"));
        System.out.println(String.format("|%1$-10s|%2$-10s|%3$-20s|%4$-20s|", "Ranking", "Flag", "Entity", "Amount"));
        System.out.println(String.format("%1$-10s|%2$-10s|%3$-20s|%4$-20s", "+----------", "----------", "--------------------", "--------------------+"));
        list.forEach(new Consumer<Instruction>() {
            int i = 1;
            public void accept(Instruction instruction) {
                System.out.println(String.format("|%1$-10s|%2$-10s|%3$-20s|%4$-20s|", i++, flag, instruction.getEntity(), instruction.getAmount()));
                System.out.println(String.format("%1$-10s|%2$-10s|%3$-20s|%4$-20s", "+----------", "----------", "--------------------", "--------------------+"));
            }
        });

    }


}

