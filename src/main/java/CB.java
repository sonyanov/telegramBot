import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CB {

    private static final String url = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=";

    public static String getCourse(String date) throws IOException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd/MM/yyyy");
        Date docDate= format.parse(date);
        Date date_start = format.parse("01/07/1992");

        if(docDate.compareTo(date_start) < 0)
            return "Курс за указанную дату не может быть выведен.\n" + "Введите дату от 01/07/1992";

        Document doc = Jsoup.connect(url + date).get();
        Elements valute = doc.select("Valute");

        String rez = "";
        for (Element el : valute){
            String val = el.select("CharCode").text();
            if(Valute.hasValute(val)){
                double nominal = Double.valueOf(el.select("Nominal").text());
                double value = Double.valueOf(el.select("Value").text().replace(',','.'));
                rez += val + " за " + nominal + " : " + value + "\n";
            }
        }
        return "Курс валют в Центробанке РФ\n" + rez;
    }
}
