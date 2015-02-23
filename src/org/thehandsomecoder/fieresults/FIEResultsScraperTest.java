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
        scraper.createConnectionParametersFromArguments(new String[]{"ME","2015", "IRL"});
    }

    public void testWeaponAndGenderSetByParam() throws Exception
    {
        Map<String, String> testMap = new HashMap<String, String>();

        assertEquals(testMap, scraper.createWeaponAndGenderParameters("ME"));
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
}
