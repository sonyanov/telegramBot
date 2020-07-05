import org.junit.Test;
import java.io.IOException;
import java.text.ParseException;
import static org.junit.Assert.assertEquals;


public class CBTest {

    @Test
    public void CB_dateTest() throws IOException, ParseException {
        String expected = "Курс валют в Центробанке РФ\n" +
                "GBP за 1.0 : 115.0692\n" +
                "USD за 1.0 : 79.4951\n" +
                "EUR за 1.0 : 89.8454\n" +
                "CNY за 1.0 : 12.0925\n" +
                "JPY за 100.0 : 70.7063\n";
        String actual = CB.getCourse("13/02/2016");
        assertEquals(expected, actual);
    }

    @Test
    public void fail_dateTest() throws IOException, ParseException{
        String expected = "Курс за указанную дату не может быть выведен.\n" +
                "Введите дату от 01/07/1992";
        String actual = CB.getCourse("02/06/1991");
        assertEquals(expected, actual);
    }
}
