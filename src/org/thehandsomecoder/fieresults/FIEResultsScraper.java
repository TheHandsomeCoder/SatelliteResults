package org.thehandsomecoder.fieresults;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by scottomalley on 21/02/15.
 */
public class FIEResultsScraper
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
    protected Connection connection;

    String categoryCode;
    String year;
    String countryCode;

    public FIEResultsScraper()
    {
        connection = Jsoup.connect(fieResultsURL);
    }

    public Map<String, String> createConnectionParametersFromArguments(String[] parameters) throws Exception
    {
        Map<String, String> connectionParameters = new HashMap<String, String>();


        if(categoryCodeIsValid(categoryCode))
        {
            connectionParameters.putAll(createWeaponAndGenderParameters(categoryCode));
        }



        return null;
    }

    protected Map<String, String> createWeaponAndGenderParameters(String categoryCode)
    {
        Map<String, String> parameters = new HashMap<String, String>();


        return parameters;
    }

    protected boolean categoryCodeIsValid(String categoryCode)
    {
        for (String s : categoryCodes)
        {
            if (s == categoryCode)
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
            if (s == countryCode)
            {
                return true;
            }
        }
        return false;
    }


}
