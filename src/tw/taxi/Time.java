package tw.taxi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Time {
    public static final int MINUTES_PER_HOUR = 60;
    public static final int MORNING_TIME = 6;
    public static final int EVENING_TIME = 23;
    public static final int MINUTES_IN_ONE_DAY = 60 * 24;

    private int hour;
    private int minute;

    private static final String regex = "(\\d*):(\\d*)";

    public Time(String time_str) {
        parse(time_str);
    }

    private void parse(String time_str) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(time_str);

        if (m.matches()) {
            hour = Integer.parseInt(m.group(1));
            minute = Integer.parseInt(m.group(2));
        }
    }

    public int diff(Time target) {
        int minutes = hour * MINUTES_PER_HOUR + minute;
        int minutes_target = target.hour * MINUTES_PER_HOUR + target.minute;
        if (minutes > minutes_target)
            return MINUTES_IN_ONE_DAY - (minutes - minutes_target);
        else
            return minutes_target - minutes;
    }

    public boolean isEvening() {
        if (hour > MORNING_TIME && hour < EVENING_TIME)
            return false;
        else
            return true;
    }

    public int hour() {
        return hour;
    }

    public int minute() {
        return minute;
    }
}
