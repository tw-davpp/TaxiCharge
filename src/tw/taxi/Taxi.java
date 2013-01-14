package tw.taxi;

public class Taxi {
    private static final int PRICE_PER_KM = 1;
    private static final int INITIATE_DISTANCE = 2;
    private static final int INITIATE_PRICE = 3;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int WAITING_PER_MINUTE = 1;
    private static final int WAITING_SPEED = 120;
    private static final int FUEL_ADDITION_PRICE = 1;
    private static final int EVENING_PRICE = 1;

    private int price;
    private int totalDistance;
    private int waitingPrice;
    private int eveningPrice;

    public Taxi() {
        price = INITIATE_PRICE;
    }

    public void update(Time start_at, Time end_at, int distance) {
        totalDistance += distance;

        if (totalDistance > INITIATE_DISTANCE) {
            if (crossInitiateDistance(distance)) {
                distance = totalDistance - INITIATE_DISTANCE;
            } else {
                waitingPrice = calculateWaitingPrice(start_at, end_at, distance);
                eveningPrice = calculateEveningPrice(start_at, distance);
                price += waitingPrice + eveningPrice;
            }
            price += PRICE_PER_KM * distance;
        }
    }

    private boolean crossInitiateDistance(int distance) {
        return totalDistance - distance < INITIATE_DISTANCE;
    }

    private int calculateEveningPrice(Time start_at, int distance) {
        if (start_at.isEvening())
            return EVENING_PRICE * distance;
        else
            return 0;
    }

    private int calculateWaitingPrice(Time start_at, Time end_at, int distance) {
        if (speed(start_at, end_at, distance) < WAITING_SPEED)
            return WAITING_PER_MINUTE;
        else
            return 0;
    }

    private int speed(Time start_at, Time end_at, int km) {
        int minutes = start_at.diff(end_at);
        return MINUTES_PER_HOUR * km / minutes;
    }

    public int price() {
        if (totalDistance >= INITIATE_PRICE)
            return price + FUEL_ADDITION_PRICE;
        else
            return price;
    }

    public int waitingPrice() {
        return waitingPrice;
    }

    public int eveningPrice() {
        return eveningPrice;
    }
}
