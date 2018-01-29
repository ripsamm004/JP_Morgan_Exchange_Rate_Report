
package com.jpmorgan.services.logic;

import java.time.LocalDate;

/**
 *
 */
public abstract class WeekDayService {
    /**
     * Resolve weekend and return next working day
     *
     * @return date object
     */
    public abstract LocalDate getNextWorkingDay(LocalDate date);

}
