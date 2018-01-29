package com.jpmorgan.services.logic;

import java.time.LocalDate;

/**
 *  Impalement the WeekDayService of getNextWorkingDay as of Monday to Friday working days
 *  when the Currency is AED or SAR the working days should be Sunday to Thursday
 *
 */
public final class MondayToFridayWeekDayService extends WeekDayService {

    private static MondayToFridayWeekDayService instance = null;

    private MondayToFridayWeekDayService() {
        // Exists only to defeat instantiation.
    }

    // Trying to make the class as singleton
    public static MondayToFridayWeekDayService getInstance() {
        if(instance == null) {
            // Will be protect from multi threading not allow to create a new instance
            synchronized (MondayToFridayWeekDayService.class){
                if (instance == null) {
                    instance = new MondayToFridayWeekDayService();
                }
            }
        }
        return instance;
    }

    /**
     * Resolve weekend and return next working day
     *
     * @param date date need to process
     * @return date object
     */
    @Override
    public LocalDate getNextWorkingDay(LocalDate date) {
        LocalDate res;
        switch (date.getDayOfWeek()) {
            case SATURDAY:
                res = date.plusDays(2);
                break;
            case SUNDAY:
                res = date.plusDays(1);
                break;
            default:
                res = date;
                break;
        }

        return res;
    }
}
