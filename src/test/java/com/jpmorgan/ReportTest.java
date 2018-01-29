package com.jpmorgan;

import com.jpmorgan.model.Instruction;
import com.jpmorgan.model.InstructionCurrency;
import com.jpmorgan.model.InstructionTypeEnum;
import com.jpmorgan.services.Report;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class ReportTest {

    private List<Instruction> instructions;
    private Report report;
    private LocalDate instructionDate;
    private Double totalUSDfromInstructions = 0d;
    @Before
    public void setUp() throws Exception {
        instructions = new ArrayList<>();
        instructionDate = LocalDate.of(2017, 8, 15);

        LocalDate instructedSettlementDate1 = instructionDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        Instruction instruction1 = new Instruction("foo 1", InstructionTypeEnum.BUY, instructionDate, instructedSettlementDate1, new InstructionCurrency(0.50, 200, 100.25, Currency.getInstance("AED")));
        instructions.add(instruction1);
        totalUSDfromInstructions +=instruction1.getAmount();

        LocalDate instructedSettlementDate2 = instructionDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        Instruction instruction2 = new Instruction("foo 2", InstructionTypeEnum.BUY, instructionDate, instructedSettlementDate2, new InstructionCurrency(0.90, 200, 100.25, Currency.getInstance("SAR")));
        instructions.add(instruction2);
        totalUSDfromInstructions +=instruction2.getAmount();

        LocalDate instructedSettlementDate3 = instructionDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        Instruction instruction3 = new Instruction("foo 3", InstructionTypeEnum.BUY, instructionDate, instructedSettlementDate3, new InstructionCurrency(1.20, 100, 200.00, Currency.getInstance("GBP")));
        instructions.add(instruction3);

        report = new Report(instructions);

    }

    @After
    public void tearDown() throws Exception {
        instructions.clear();
        report=null;
    }


    @Test
    public void testSettlementDateShouldSetNewSettlementWorkingDate() throws Exception {
        LocalDate instructedSettlementDate = instructionDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        LocalDate newInstructedSettlementWorkingDate = instructedSettlementDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        Assert.assertEquals(newInstructedSettlementWorkingDate, instructions.get(0).getSettlementDate());


        LocalDate instructedSettlementDate2 = instructionDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        LocalDate newInstructedSettlementWorkingDate2 = instructedSettlementDate2.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        Assert.assertEquals(newInstructedSettlementWorkingDate2, instructions.get(1).getSettlementDate());

    }

    @Test
    public void testWorkWeekOfSettlementDateShouldReturnNoInstructions() throws Exception {
        LocalDate instructedSettlementDate = instructionDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        List<Instruction> ratingList = report.getInstructionsByDateAndFlag(instructedSettlementDate, InstructionTypeEnum.BUY, true);
        Assert.assertEquals(0, ratingList.size());

    }

    @Test
    public void testSundayToThursdaySettlementDateShouldReturnInstructions() throws Exception {
        LocalDate instructedSettlementDate = instructionDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        List<Instruction> ratingList = report.getInstructionsByDateAndFlag(instructedSettlementDate, InstructionTypeEnum.BUY, true);
        Assert.assertEquals(2, ratingList.size());
    }

    @Test
    public void testRankingOfEntity() throws Exception {
        LocalDate instructedSettlementDate = instructionDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        List<Instruction> ratingList = report.getInstructionsByDateAndFlag(instructedSettlementDate, InstructionTypeEnum.BUY, true);
        Assert.assertEquals(2, ratingList.size());
        Assert.assertEquals("foo 2", ratingList.get(0).getEntity());
    }

    @Test
    public void testMondayToFridaySettlementDateShouldReturnInstructions() throws Exception {
        LocalDate instructedSettlementDate = instructionDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        List<Instruction> ratingList = report.getInstructionsByDateAndFlag(instructedSettlementDate, InstructionTypeEnum.BUY, true);
        Assert.assertEquals(1, ratingList.size());
        Assert.assertEquals(Currency.getInstance("GBP"), ratingList.get(0).getInstructionCurrency().getCurrency());

    }

    /**
     * USD amount calculation = Price per unit * Units * Agreed Fx
     * @throws Exception
     */
    @Test
    public void testTotalAmountOfInstructions() throws Exception {
        LocalDate instructedSettlementDate = instructionDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        Double totalUSDAmountFromReport = report.getInstructionAmountByDateAndFlag(instructedSettlementDate, InstructionTypeEnum.BUY);
        Assert.assertEquals(totalUSDfromInstructions, totalUSDAmountFromReport);
    }

}