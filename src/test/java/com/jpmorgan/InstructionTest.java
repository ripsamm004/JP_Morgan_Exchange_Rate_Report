package com.jpmorgan;

import com.jpmorgan.model.Instruction;
import com.jpmorgan.model.InstructionCurrency;
import com.jpmorgan.model.InstructionTypeEnum;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Currency;

import static org.junit.Assert.assertEquals;

public class InstructionTest {

    private Instruction instruction;

    @Before
    public void setUp() throws Exception {
        instruction = new Instruction("foo 1", InstructionTypeEnum.BUY, LocalDate.of(2017, 8, 15), LocalDate.of(2017, 8, 18), new InstructionCurrency(0.50, 200, 100.25, Currency.getInstance("AED")));
    }

    @After
    public void tearDown() throws Exception {
        instruction = null;
    }


    @Test
    public void testGetAmount() throws Exception {
        Assert.assertEquals(instruction.getAmount(), 10025.0, 0.0);

    }

    @Test
    public void testGetEntity() throws Exception {
        assertEquals("foo 1", instruction.getEntity());

    }

    @Test
    public void testGetSettlementDate1() throws Exception {
        assertEquals(LocalDate.of(2017, 8, 20), instruction.getSettlementDate());

    }

    @Test
    public void testGetSettlementDate2() throws Exception {
        Instruction temp = new Instruction("foo 1", InstructionTypeEnum.BUY, LocalDate.of(2017, 8, 15), LocalDate.of(2017, 8, 15), new InstructionCurrency(0.50, 200, 100.25, Currency.getInstance("SGD")));
        assertEquals(LocalDate.of(2017, 8, 15), temp.getSettlementDate());

    }

    @Test
    public void testGetSettlementDate3() throws Exception {
        Instruction temp = new Instruction("foo 1", InstructionTypeEnum.BUY, LocalDate.of(2017, 8, 15), LocalDate.of(2017, 8, 20), new InstructionCurrency(0.50, 200, 100.25, Currency.getInstance("SGD")));
        assertEquals(LocalDate.of(2017, 8, 21), temp.getSettlementDate());


    }

}