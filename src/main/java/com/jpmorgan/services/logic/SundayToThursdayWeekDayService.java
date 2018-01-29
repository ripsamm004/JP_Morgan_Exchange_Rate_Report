
package com.jpmorgan.services.logic;

import java.time.LocalDate;

/**
 *  Impalement the WeekDayService of getNextWorkingDay as of Sunday to Thursday working days
 *  when the Currency is AED or SAR the working days should be Sunday to Thursday
 *
 */
public final class SundayToThursdayWeekDayService extends WeekDayService {

    private static SundayToThursdayWeekDayService instance = null;

    private SundayToThursdayWeekDayService() {
        // Exists only to defeat instantiation.
    }

    // Trying to make the class as singleton
    public static SundayToThursdayWeekDayService getInstance() {
        if(instance == null) {
            // Will be protect from multi threading not allow to create a new instance
            synchronized (SundayToThursdayWeekDayService.class){
                if (instance == null) {
                    instance = new SundayToThursdayWeekDayService();
                }
            }
        }
        return instance;
    }

    /**
     * Resolve weekend and return next working day
     *
     * @param date need to work on
     * @return date object
     */
    @Override
    public LocalDate getNextWorkingDay(LocalDate date) {
        LocalDate res;
        switch (date.getDayOfWeek()) {
            case FRIDAY:
                res = date.plusDays(2);
                break;
            case SATURDAY:
                res = date.plusDays(1);
                break;
            default:
                res = date;
                break;
        }

        return res;
    }
}
