package org.thehandsomecoder.fieresults;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by scottomalley on 21/02/15.
 */
public class FIEScraper
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

    private final String genderParameter = "calendar_models_Calendars[GenderId]";
    private final String weaponParameter = "calendar_models_Calendars[WeaponId]";
    private final String yearParameter = "calendar_models_Calendars[CPYear]";
    private final String competitionCategoryParameter = "calendar_models_Calendars[CompCatId]";
    private final String paginationParameter = "calendar_models_Calendars[CompCatId]";

    private final String[] categoryCodes = new String[]{"ME", "MF", "MS", "WE", "WF", "WS"};
    private final String[] countryCodes = new String[]{"AFG","ALB","ALG","ANT","ARG","ARM","ARU","AUS","AUT","AZE","BAH","BAN","BAR","BEL","BEN","BER","BIZ","BLR","BOL","BOT","BRA","BRN","BRU","BUL","BUR","CAM","CAN","CGO","CHI","CHN","CIV","CMR","COD","COL","CRC","CRO","CUB","CYP","CZE","DEN","DMA","DOM","ECU","EGY","ESA","ESP","EST","FIE","FIN","FRA","GAB","GBR","GEO","GEQ","GER","GHA","GRE","GUA","GUI","GUM","GUY","HKG","HON","HUN","INA","IND","IRI","IRL","IRQ","ISL","ISR","ISV","ITA","JAM","JOR","JPN","KAZ","KGZ","KOR","KSA","KUW","LAT","LBA","LIB","LTU","LUX","MAC","MAD","MAR","MAS","MDA","MEX","MGL","MKD","MLI","MLT","MON","MRI","MTN","MYA","NAM","NCA","NED","NEP","NGR","NIG","NOR","NZL","PAN","PAR","PER","PHI","PLE","POL","POR","PRK","PUR","QAT","ROU","RSA","RUS","RWA","SAM","SEN","SIN","SLE","SLO","SMR","SOM","SRB","SRI","SUI","SVK","SWE","SYR","THA","TJK","TKM","TOG","TPE","TUN","TUR","UAE","UGA","UKR","URU","USA","UZB","VEN","VIE","YEM"};


    private final String fieResultsURL = "http://fie.org/results-statistic/result";
    private final String fieBaseURL = "http://fie.org";
    protected Connection connection;
    private List<FIECompetition> competitions = new ArrayList<FIECompetition>();

    String categoryCode;
    String year;
    String countryCode;


    public FIEScraper()
    {
        connection = Jsoup.connect(fieResultsURL);
    }


    public FIEScraper(String[] parameters)
    {
        connection = Jsoup.connect(fieResultsURL);
        setConnectionParameters(parameters);
    }

    private void setConnectionParameters(String[] connectionParameters)
    {
        try
        {
            connection.data(createConnectionParametersFromArguments(connectionParameters));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Map<String, String> createConnectionParametersFromArguments(String[] parameters) throws Exception
    {
        categoryCode = parameters[0];
        year = parameters[1];
        countryCode = parameters[2];

        Map<String, String> connectionParameters = new HashMap<String, String>();


        if (checkParametersAreValid(categoryCode, countryCode))
        {
            connectionParameters.putAll(createWeaponAndGenderParameters(categoryCode));
            connectionParameters.putAll(createYearParameter(year));
            connectionParameters.putAll(createCategoryParameter("SA"));
        }

        return connectionParameters;
    }

    private Map<String, String> createCategoryParameter(String category)
    {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put(competitionCategoryParameter, category);
        return parameters;
    }

    private boolean checkParametersAreValid(String categoryCode, String countryCode)
    {
        return categoryCodeIsValid(categoryCode) && countryCodeIsValid(countryCode);
    }

    private Map<String, String> createWeaponAndGenderParameters(String categoryCode)
    {
        Map<String, String> parameters = new HashMap<String, String>();

        String gender = categoryCode.substring(0, 1);
        String weapon = categoryCode.substring(1, 2);

        parameters.put(weaponParameter, weapon);
        parameters.put(genderParameter, gender);

        return parameters;
    }

    private Map<String, String> createYearParameter(String year)
    {
        Map<String, String> parameters = new HashMap<String, String>();

        parameters.put(yearParameter, year);

        return parameters;
    }

    protected boolean categoryCodeIsValid(String categoryCode)
    {
        for (String s : categoryCodes)
        {
            if (s.equals(categoryCode))
            {
                return true;
            }
        }
        return false;
    }

    protected boolean countryCodeIsValid(String countryCode)
    {
        for (String s : countryCodes)
        {
            if (s.equals(countryCode))
            {
                return true;
            }
        }
        return false;
    }

    public void scrapeCompetitionsFromFIESite()
    {
        try
        {
            Document doc = connection.get();
            Elements events = doc.select("#result-calendar-grid table tr");
            for (int i = 1; i < events.size(); i++)
            {
                FIECompetition competition = createCompetitionFromElement(events.get(i));
                scrapeResultsFromCompetitionURL(competition);
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }


    }

    private void scrapeResultsFromCompetitionURL(FIECompetition competition) throws IOException
    {
        Document doc = Jsoup.connect(competition.competitionURL).get();
        Elements results = doc.select("#result-competition-grid table tr");
        competition.setNumberOfEntries(results.size() - 1);
        results = filterResultsByCurrentCountryCode(results);
        competition.addResults(results);
        competitions.add(competition);
    }

    private Elements filterResultsByCurrentCountryCode(Elements results)
    {
        Elements filteredResults = new Elements();
        for (Element e : results)
        {
            if (e.child(3).text().equals(countryCode))
            {
                filteredResults.add(e);
            }
        }
        return filteredResults;
    }


    private FIECompetition createCompetitionFromElement(Element e)
    {
        FIECompetition competition = new FIECompetition();
        competition.competitionName = e.child(1).text();
        competition.competitionDate = e.child(3).text();
        competition.competitionURL = fieBaseURL + e.select("a").first().attr("href");
        return competition;
    }


    public void printResults()
    {
        for (FIECompetition c : competitions)
        {
            if (c.hasResults())
            {
                System.out.println(c);
            }
        }
    }
}
