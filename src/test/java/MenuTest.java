import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.text.ParseException;
import static org.junit.Assert.assertEquals;

public class MenuTest {

    @Test
    public void IsCourse1() throws IOException {
        Text_message.load();
        boolean actual = Text_message.isCourse("курсы");
        Assert.assertTrue(actual);
    }

    @Test
    public void IsCourse2() throws IOException {
        Text_message.load();
        boolean actual = Text_message.isCourse("валюта");
        Assert.assertTrue(actual);
    }

    @Test
    public void IsValute1() throws IOException {
        Text_message.load();
        String expected = "USD";
        String actual = Text_message.getValute("бакс");
        assertEquals(expected, actual);
    }

    @Test
    public void IsValute2() throws IOException {
        Text_message.load();
        String expected = "EUR";
        String actual = Text_message.getValute("евро");
        assertEquals(expected, actual);
    }

    @Test
    public void IsCity1() throws IOException {
        Text_message.load();
        String expected = "ekaterinburg";
        String actual = Text_message.getCity("екб");
        assertEquals(expected, actual);
    }

    @Test
    public void IsCity2() throws IOException {
        Text_message.load();
        String expected = "ulyanovsk";
        String actual = Text_message.getCity("ульяновск");
        assertEquals(expected, actual);
    }

}
