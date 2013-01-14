package tw.taxi;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class TaxiTest {
    private Taxi taxi;

    @Before
    public void set_up() {
        taxi = new Taxi();
    }

    @Test
    public void test_one_mile_cost_three_price() {
        taxi.update(new Time("10:10"), new Time("10:11"), 1);
        assertThat(taxi.price(), equalTo(3));
    }

    @Test
    public void test_initiate_price() {
        taxi.update(new Time("10:10"),new Time( "10:11"), 1);
        assertThat(taxi.price(), equalTo(3));
        taxi.update(new Time("10:11"), new Time("10:12"), 1);
        assertThat(taxi.price(), equalTo(3));
        taxi.update(new Time("10:13"), new Time("10:14"), 0);
        assertThat(taxi.price(), equalTo(3));
    }

    @Test
    public void test_three_mile_cost_five_price_with_one_fuel_addition() {
        taxi.update(new Time("10:10"), new Time("10:11"), 3);
        assertThat(taxi.price(), equalTo(5));
    }

    @Test
    public void test_sixty_mile_per_hour_cost_one_waiting_price() {
        taxi.update(new Time("10:10"), new Time("10:11"), 2);
        taxi.update(new Time("10:11"), new Time("10:12"), 1);
        assertThat(taxi.waitingPrice(), equalTo(1));
    }

    @Test
    public void test_two_hundred_forty_mile_per_hour_cost_zero_waiting_price() {
        taxi.update(new Time("10:10"), new Time("10:11"), 2);
        taxi.update(new Time("10:11"), new Time("10:12"), 4);
        assertThat(taxi.waitingPrice(), equalTo(0));
    }

    @Test
    public void test_at_twenty_three_drive_ten_mile_cost_ten_evening_price() {
        taxi.update(new Time("22:59"), new Time("23:00"), 2);
        taxi.update(new Time("23:00"), new Time("23:01"), 10);
        assertThat(taxi.eveningPrice(), equalTo(10));
    }

    @Test
    public void test_at_ten_drive_ten_mile_cost_zero_evening_price() {
        taxi.update(new Time("9:59"), new Time("10:00"), 2);
        taxi.update(new Time("10:00"), new Time("10:01"), 10);
        assertThat(taxi.eveningPrice(), equalTo(0));
    }
}
