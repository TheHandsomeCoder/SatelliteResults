package org.thehandsomecoder.fieresults;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scottomalley on 24/02/15.
 */
public class FIECompetition
{
    String competitionName;
    String competitionDate;
    String numberOfEntries;
    List<String> results;

    public FIECompetition()
    {
        results = new ArrayList<String>();
    }

    public void addResult(String placing, String name)
    {
        String template = "%s/t%s";
        results.add(String.format(template, placing, name));
    }

    @Override
    public String toString()
    {
        String template = "Competition: %s/t Date: %s/t Entries: %s/n";

        String output = String.format(template, competitionName, competitionDate, numberOfEntries);
        for (String r : results)
        {
            output += r;
            output += "/n";
        }

        return output;
    }
}
