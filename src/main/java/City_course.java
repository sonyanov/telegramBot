import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;

public class City_course {

    private static final String url = "https://ru.myfin.by/currency/";
    public static LinkedList<String> valutes = new LinkedList<>();

    public static String getCityCourse(String city, String valute ) throws IOException{

        String rez = "";
        Document doc = Jsoup.connect(url + valute + "/" + city).get();
        Elements table  = doc.select("#g_bank_rates > table > tbody > tr");
        int count = 0;

        for (Element el: table){
            if(el.attr("data-key") != "") {
                Elements info = el.select("td.bank_name > a");
                String data = info.text() + ": \n";
                data += "-покупка: " + el.select("td:nth-child(2)").text() + "\n" +
                        "-продажа: " + el.select("td:nth-child(3)").text() + "\n\n";
                rez += data;
                count++;
            }
            if (count == 5)
                break;
        }

        if (rez.isEmpty()) return "В этом городе нет такого";

        return "--- " + valute.toUpperCase() + " ---\n" + rez + "\n" ;
    }

    public static String getByCity(String city) throws IOException {

        String rez = "";

        for (String valute: valutes){
            rez += getCityCourse(city, valute);
        }
        return rez + "\n";
    }
}
