package org.thehandsomecoder.fieresults;

import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by scottomalley on 21/02/15.
 */
public class FIEResultsScraperTest extends TestCase
{
    FIEResultsScraper scraper;
    public void setUp() throws Exception
    {
        super.setUp();
        scraper = new FIEResultsScraper();
    }

    public void testCreateConnectionFromParameters() throws Exception
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("calendar_models_Calendars[WeaponId]", "E");
        map.put("calendar_models_Calendars[GenderId]", "M");
        map.put("calendar_models_Calendars[CPYear]", "2015");
        map.put("calendar_models_Calendars[CompCatId]", "SA");

        assertEquals(map, scraper.createConnectionParametersFromArguments(new String[]{"ME", "2015", "IRL"}));
    }

    public void testCategoryCodeIsValid() throws Exception
    {
        assertTrue(scraper.categoryCodeIsValid("ME"));
        assertTrue(scraper.categoryCodeIsValid("MF"));
        assertTrue(scraper.categoryCodeIsValid("MS"));
        assertTrue(scraper.categoryCodeIsValid("WF"));
        assertTrue(scraper.categoryCodeIsValid("WS"));
        assertTrue(scraper.categoryCodeIsValid("WE"));
        assertFalse(scraper.categoryCodeIsValid("TE"));
    }

    public void testCountryCodeIsValid() throws Exception
    {
        assertTrue(scraper.countryCodeIsValid("IRL"));
        assertFalse(scraper.countryCodeIsValid("RED"));
    }
}
