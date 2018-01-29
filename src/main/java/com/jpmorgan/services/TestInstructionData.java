
package com.jpmorgan.services;

import com.jpmorgan.model.Instruction;
import com.jpmorgan.model.InstructionCurrency;
import com.jpmorgan.model.InstructionTypeEnum;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class TestInstructionData {
    public static List<Instruction> invoke() {
        List<Instruction> list = new ArrayList<>();
        list.add(new Instruction("foo 1", InstructionTypeEnum.BUY, LocalDate.now(), LocalDate.now(), new InstructionCurrency(0.50, 200, 100.25, Currency.getInstance("SGD"))));
        list.add(new Instruction("foo 2", InstructionTypeEnum.BUY, LocalDate.now(), LocalDate.now(), new InstructionCurrency(0.50, 200, 200.25, Currency.getInstance("SGD"))));
        list.add(new Instruction("foo 3", InstructionTypeEnum.BUY, LocalDate.now(), LocalDate.now(), new InstructionCurrency(0.50, 200, 400.25, Currency.getInstance("SGD"))));
        list.add(new Instruction("foo 4", InstructionTypeEnum.BUY, LocalDate.now(), LocalDate.now(), new InstructionCurrency(0.50, 200, 140.25, Currency.getInstance("SGD"))));
        list.add(new Instruction("foo 5", InstructionTypeEnum.BUY, LocalDate.now(), LocalDate.now(), new InstructionCurrency(0.50, 200, 110.25, Currency.getInstance("SGD"))));


        list.add(new Instruction("bar 0.1", InstructionTypeEnum.SELL, LocalDate.now(), LocalDate.now(), new InstructionCurrency(0.2, 40, 60.5, Currency.getInstance("GBP"))));
        list.add(new Instruction("bar 0.2", InstructionTypeEnum.SELL, LocalDate.now(), LocalDate.now(), new InstructionCurrency(0.2, 50, 50.5, Currency.getInstance("GBP"))));
        list.add(new Instruction("bar 0.3", InstructionTypeEnum.SELL, LocalDate.now(), LocalDate.now(), new InstructionCurrency(0.2, 90, 40.5, Currency.getInstance("GBP"))));

        list.add(new Instruction("bar 1", InstructionTypeEnum.SELL, LocalDate.now(), LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY)), new InstructionCurrency(0.2, 450, 450.5, Currency.getInstance("GBP"))));
        list.add(new Instruction("bar 2", InstructionTypeEnum.SELL, LocalDate.now(), LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY)), new InstructionCurrency(0.2, 450, 650.5, Currency.getInstance("GBP"))));
        list.add(new Instruction("bar 3", InstructionTypeEnum.SELL, LocalDate.now(), LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY)), new InstructionCurrency(0.2, 450, 50.5, Currency.getInstance("GBP"))));
        list.add(new Instruction("bar 4", InstructionTypeEnum.SELL, LocalDate.now(), LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)), new InstructionCurrency(0.2, 450, 150.5, Currency.getInstance("GBP"))));

        list.add(new Instruction("bar 5", InstructionTypeEnum.SELL, LocalDate.now(), LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY)), new InstructionCurrency(0.22, 450, 450.5, Currency.getInstance("AED"))));
        list.add(new Instruction("bar 6", InstructionTypeEnum.SELL, LocalDate.now(), LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.THURSDAY)), new InstructionCurrency(0.22, 450, 650.5, Currency.getInstance("AED"))));
        list.add(new Instruction("bar 7", InstructionTypeEnum.SELL, LocalDate.now(), LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY)), new InstructionCurrency(0.22, 450, 150.5, Currency.getInstance("AED"))));
        list.add(new Instruction("bar 8", InstructionTypeEnum.SELL, LocalDate.now(), LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY)), new InstructionCurrency(0.22, 450, 50.5, Currency.getInstance("AED"))));
        list.add(new Instruction("bar 9", InstructionTypeEnum.SELL, LocalDate.now(), LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY)), new InstructionCurrency(0.22, 450, 150.5, Currency.getInstance("AED"))));

        list.add(new Instruction("bar 10", InstructionTypeEnum.SELL, LocalDate.now(), LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY)), new InstructionCurrency(0.32, 450, 510.5, Currency.getInstance("SAR"))));
        list.add(new Instruction("bar 11", InstructionTypeEnum.SELL, LocalDate.now(), LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.THURSDAY)), new InstructionCurrency(0.32, 450, 530.5, Currency.getInstance("SAR"))));
        list.add(new Instruction("bar 12", InstructionTypeEnum.SELL, LocalDate.now(), LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY)), new InstructionCurrency(0.32, 450, 540.5, Currency.getInstance("SAR"))));
        list.add(new Instruction("bar 13", InstructionTypeEnum.SELL, LocalDate.now(), LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY)), new InstructionCurrency(0.32, 450, 550.5, Currency.getInstance("SAR"))));
        list.add(new Instruction("bar 14", InstructionTypeEnum.SELL, LocalDate.now(), LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY)), new InstructionCurrency(0.32, 450, 570.5, Currency.getInstance("SAR"))));
        return list;
    }
}