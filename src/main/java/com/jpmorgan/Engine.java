package com.jpmorgan;

import com.jpmorgan.model.Instruction;
import com.jpmorgan.model.InstructionTypeEnum;
import com.jpmorgan.services.Report;
import com.jpmorgan.services.TestInstructionData;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by shiful on 26/01/2018.
 */
public class Engine {

    private final static Logger LOG = Logger.getLogger(Engine.class.getName());

    public static void main(String[] args) {

        LOG.log(Level.INFO, "J.P Morgan report engine started  .. .. ..");

        // Generate Instructions
        List<Instruction> list = TestInstructionData.invoke();

        // Create report object and pass the list of instructions to generate report
        Report report = new Report(list);

        // Date is today, date can be change to any date to display the report
        LocalDate date = LocalDate.now();

        // Display report of total USD amount of outgoing instructions for a given date
        report.printTotalAmountReportByDateAndFlag(date, InstructionTypeEnum.BUY);

        // Display the ranking report from all outgoing instructions for a given date
        report.printRankingReportByDateAndFlag(date, InstructionTypeEnum.BUY);

        // Display total USD amount of incoming instructions for a given date
        report.printTotalAmountReportByDateAndFlag(date, InstructionTypeEnum.SELL);

        // Display the ranking report from all incoming instructions for a given date
        report.printRankingReportByDateAndFlag(date, InstructionTypeEnum.SELL);

    }
}
