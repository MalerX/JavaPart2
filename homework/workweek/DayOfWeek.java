package polymorphism.homework.workweek;

public enum DayOfWeek {

    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;

    private static final int WORKS_HOURS = 8;
    private final static int WORKS_DAY = 5;

    public static String getWorkingHours(DayOfWeek dayOfWeek) {
        int hoursLeft = WORKS_HOURS;
        if (dayOfWeek != DayOfWeek.SATURDAY & dayOfWeek != DayOfWeek.SUNDAY) {
            for (int i = dayOfWeek.ordinal(); i != WORKS_DAY; i++) {
                hoursLeft += WORKS_HOURS;
            }
            return  "До конца рабочей недели " + hoursLeft + " часов.";
        } else
            return  "Сегодня выходной";
    }
}
