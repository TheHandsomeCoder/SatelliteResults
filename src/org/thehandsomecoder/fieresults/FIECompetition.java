package org.thehandsomecoder.fieresults;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
    String competitionURL;

    public FIECompetition()
    {
        results = new ArrayList<String>();
    }

    private void addResult(String placing, String name)
    {
        String template = "%s\t%s";
        results.add(String.format(template, placing, name));
    }

    @Override
    public String toString()
    {
        String template = "Competition: %s\t Date: %s\t Entries: %s\n";

        String output = String.format(template, competitionName, competitionDate, numberOfEntries);
        for (String r : results)
        {
            output += r;
            output += "\n";
        }

        return output;
    }


    public void addResults(Elements results)
    {
        for (Element e : results)
        {
            this.addResult(e.child(0).text(), e.child(2).text());
        }
    }

    public boolean hasResults()
    {
        return results.size() > 0;
    }

    public void setNumberOfEntries(int numberOfEntries)
    {
        this.numberOfEntries = String.valueOf(numberOfEntries);
    }
}
