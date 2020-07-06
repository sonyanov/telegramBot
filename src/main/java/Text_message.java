import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import exception.groupNoInputInfoException;
import exception.haveNoTextException;
import org.telegram.telegrambots.api.objects.Message;
import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;


public class Text_message {

    private static HashMap<String, String> city;
    private static HashMap<String, String> valute;
    private static LinkedList<String> coursepatern;
    private static String region = "moskva";
    private static final Logger logger = Logger.getLogger(Text_message.class.getName());


    public static void load() throws IOException{
      coursepatern = new LinkedList<>();
      BufferedReader reader = new BufferedReader(new FileReader(new File("./src/main/resources/patern/coursePatern.txt")));

      String line = reader.readLine();
      while (line != null){
          Valute.valutes.add(line);
          coursepatern.add(line);
          line = reader.readLine();
      }
      reader.close();

       Gson gson = new Gson();
       city = gson.fromJson(new JsonReader(new FileReader
                       ("./src/main/resources/patern/City.json")),
               new TypeToken<HashMap<String, String>>(){}.getType());
       valute = gson.fromJson(new JsonReader(new FileReader
                       ("./src/main/resources/patern/valutePatern.json")),
               new TypeToken<HashMap<String, String>>(){}.getType());
    }

    public static String getMessage(Message message) throws IOException, ParseException, groupNoInputInfoException, haveNoTextException {
        if (message.hasText()) {
            String[] words = message.getText().split("\\s+");
            boolean isCourse = false;
            String valute = "";
            String city = "";

            boolean isLogged = false;

            for (int i = 0; i < words.length; i++) {
                String word = words[i];

                if (word.matches("\\d{2}(-|\\/)\\d{2}(-|\\/)\\d{4}")) {
                    return CB.getCourse(word);
                }

                if(city.isEmpty()) city = getCity(word);
                if(valute.isEmpty()) valute = getValute(word);
                if(!isCourse) isCourse = isCourse(word);

                if(isCourse || !valute.isEmpty()){
                    if(!isLogged){
                        logger.log(Level.INFO,"Request: "  + message.getText());
                        isLogged = true;
                    }
                    if(!city.isEmpty())
                        region = city;
                    if(!valute.isEmpty() && !city.isEmpty())
                        return City_course.getCityCourse(region, valute);
                    if(i == words.length - 1){
                        if(city.isEmpty() && !valute.isEmpty())
                            return City_course.getCityCourse(region, valute);
                        return City_course.getByCity(region);
                    }
                }

                switch (word) {
                    case "/start":
                        return "Привет, чем могу помочь?\n" + "Для продолжения введите /help";
                    case "/help":
                        return "Получение информации о боте: /info.\n" +
                                "Получение списка валют: /valute.\n" +
                                "Получение справки о курсе валют в ЦБ: /CB.";
                    case "/info":
                        return "Вы можете узнать курсы валют в 5 самых популярных банков региона и Центробанка.";
                    case "/CB":
                        return "Если вы хотите узнать курс валют ЦентроБанка, введите дату.\nНапример: 21/06/2020";
                    case "/valute":
                        return "Вы можете узнать курс валют, таких как:\n" +
                                "1.Доллар\n2.Евро\n3.Фунт стерлингов\n4.Юань\n5.Йена\n";
                    default:
                }
            }
        }
        else if(!message.isGroupMessage())
            throw new HaveNoTextException();
        if(message.isGroupMessage())
            throw new GroupNoInputInfoException();
        return "Не хватает информации для вывода или сообщение введено не коррентно";
    }
    public static String getCity(String word){
        for (Map.Entry<String, String> item : city.entrySet()){
            if (Pattern.matches(item.getKey(), word.toLowerCase())) return item.getValue();
        }
        return "";
    }
    public static String getValute(String word){
        for (Map.Entry<String, String> item : valute.entrySet()){
            if (Pattern.matches(item.getKey(), word.toLowerCase())) return item.getValue();
        }
        return "";
    }

    public static boolean isCourse(String word){
        for (String pattern: coursepatern){
            if(Pattern.matches(pattern, word.toLowerCase()))
                return true;
        }
        return false;
    }
}

