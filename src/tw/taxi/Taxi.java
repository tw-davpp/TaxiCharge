package tw.taxi;

public class Taxi {
    private static final int PRICE_PER_KM = 1;
    private static final int INITIATE_KM = 2;
    private static final int INITIATE_PRICE = 3;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int WAITING_PER_MINUTE = 1;
    private static final int WAITING_SPEED = 120;
    private static final int FUEL_ADDITION = 1;
    private static final int EVENING_PRICE = 1;

    private int price;
    private int total_distance;
    private int waiting_price;
    private int evening_price;

    public void update(Time start_at, Time end_at, int distance) {
        total_distance += distance;

        if (total_distance <= INITIATE_KM) {
            price = INITIATE_PRICE;
        } else {
            waiting_price = calculateWaitingPrice(start_at, end_at, distance);
            evening_price = calculateEveningPrice(start_at, distance);
            price += PRICE_PER_KM * distance + evening_price + waiting_price;
        }
    }

    private int calculateEveningPrice(Time start_at, int distance) {
        if (start_at.isEvening())
            return EVENING_PRICE * distance;
        else
            return 0;
    }

    private int calculateWaitingPrice(Time start_at, Time end_at, int distance) {
        if (speed(start_at, end_at, distance) >= WAITING_SPEED)
            return WAITING_PER_MINUTE;
        else
            return 0;
    }

    private int speed(Time start_at, Time end_at, int km) {
        int minutes = end_at.diff(start_at);
        return MINUTES_PER_HOUR * km / minutes;
    }

    public int price() {
        if (total_distance >= INITIATE_PRICE)
            return price + FUEL_ADDITION;
        else
            return price;
    }

    public int waitingPrice() {
        return waiting_price;
    }

    public int eveningPrice() {
        return evening_price;
    }
}
