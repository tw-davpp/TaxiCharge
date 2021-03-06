package tw.taxi;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TimeTest {
    @Test
    public void test_time_ten_eleven() {
        Time time = new Time("10:11");
        assertThat(time.hour(), equalTo(10));
        assertThat(time.minute(), equalTo(11));
    }

    @Test
    public void test_diff_between_ten_eleven_and_eleven_two_is_fifty_one() {
        Time ten_eleven = new Time("10:11");
        Time eleven_two = new Time("11:2");
        assertThat(ten_eleven.diff(eleven_two), equalTo(51));
    }

    @Test
    public void test_diff_between_ten_ten_and_ten_eleven_is_one() {
        Time ten_ten = new Time("10:10");
        Time ten_eleven = new Time("10:11");
        assertThat(ten_ten.diff(ten_eleven), equalTo(1));
    }

    @Test
    public void test_diff_between_twenty_three_fifty_and_zero_five_is_ten() {
        Time twenty_three_fifty = new Time("23:55");
        Time zero_five = new Time("0:5");
        assertThat(twenty_three_fifty.diff(zero_five), equalTo(10));
    }

    @Test
    public void test_ten_eleven_is_not_evening() {
        Time ten_eleven = new Time("10:11");
        assertThat(ten_eleven.isEvening(), is(false));
    }

    @Test
    public void test_twenty_three_clock_is_evening() {
        Time twenty_two_clock = new Time("23:00");
        assertThat(twenty_two_clock.isEvening(), is(true));
    }
}
