import org.thehandsomecoder.fieresults.FIEScraper;

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
        if (args.length == 3)
        {
            FIEScraper scraper = new FIEScraper(args);
            scraper.scrapeCompetitionsFromFIESite();
            scraper.printResults();
        } else
        {
            System.out.println("Not enough parameters supplied");
        }

    }
}

