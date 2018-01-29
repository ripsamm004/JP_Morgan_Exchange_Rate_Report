package com.jpmorgan.services;

import com.jpmorgan.model.Instruction;
import com.jpmorgan.model.InstructionTypeEnum;

import java.time.LocalDate;
import java.util.List;

/**
 * The interface of the base report printer service
 *
 */
public interface PrinterService {
    void printEntityTotalAmountOfTheDay(LocalDate date, Double amount, InstructionTypeEnum instructionType);
    void printEntityRankingListOfTheDay(LocalDate date, List<Instruction> instructions, InstructionTypeEnum instructionType);
}
