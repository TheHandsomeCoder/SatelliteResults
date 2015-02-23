import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by scottomalley on 21/02/15.
 */
public class Main
{

//    calendar_models_Calendars[FencCatId]:
//    calendar_models_Calendars[WeaponId]:
//    calendar_models_Calendars[GenderId]:M
//    calendar_models_Calendars[CompTypeId]:
//    calendar_models_Calendars[CompCatId]:
//    calendar_models_Calendars[CPYear]:2015
//    calendar_models_Calendars[FedId]:IRL
//    calendar_models_Calendars[DateBegin]:
//    calendar_models_Calendars[DateEnd]:
//    calendar_models_Calendars_page:2




    public static void main(String args[])
    {
        Document doc = null;
        Connection connection;
        Elements newsHeadlines;
        int pagination;
        String fieResultsURL = "http://fie.org/results-statistic/result";
        try
        {
            connection = Jsoup.connect(fieResultsURL).timeout(50000);
            connection.data("calendar_models_Calendars[CPYear]", "2015");
            doc = connection.get();
            newsHeadlines = doc.select("#result-calendar-grid table tr");

            pagination  = doc.select("div.pagination_i ul li").size();
            System.out.println(pagination);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}

