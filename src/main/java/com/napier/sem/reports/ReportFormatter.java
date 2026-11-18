package com.napier.sem.reports;

import com.napier.sem.models.City;
import com.napier.sem.models.Continent;
import com.napier.sem.models.Country;
import com.napier.sem.repositories.LanguageRepository.Language;
import com.napier.sem.repositories.LanguageRepository.LanguageData;
import com.napier.sem.repositories.PopulationRepository;
import com.napier.sem.repositories.PopulationRepository.PopulationDistributionData;

import java.util.List;
import java.util.Locale;


/**
 * Formats population reports into consistent tables.
 * Presentation only: no SQL, repository calls, or console printing.
 */
public final class ReportFormatter {

    private ReportFormatter() {
    }

    /**
     * Creates a consistent table layout for all reports.
     *
     * @param title  the report title
     * @param header the table column headings
     * @param rows   the formatted report rows
     * @return the complete formatted report
     */
    private static String table(String title, String header, String rows) {

        int width = Math.max(72, header.stripTrailing().length());

        String line = "=".repeat(width);
        String divider = "-".repeat(width);

        String centredTitle = " ".repeat(
                Math.max(0, (width - title.length()) / 2)
        ) + title;

        return "\n" + line + "\n"
                + centredTitle + "\n"
                + line + "\n"
                + header
                + divider + "\n"
                + rows
                + line + "\n";
    }

    // ============================================================
    // COUNTRY REPORTS
    // ============================================================

    /**
     * Formats a list of countries with numbered rows.
     *
     * @param title     the report title
     * @param countries the countries to display
     * @return the formatted country report
     */
    public static String formatCountries(
            String title, List<Country> countries) {

        String header = String.format(
                "%-5s | %-5s | %-45s | %-15s | %-26s | %13s | %s%n",
                "No.", "Code", "Name", "Continent",
                "Region", "Population", "Capital"
        );

        StringBuilder rows = new StringBuilder();

        if (countries == null || countries.isEmpty()) {
            rows.append("No countries found.\n");
        } else {

            int number = 1;

            for (Country country : countries) {

                rows.append(String.format(Locale.US,
                        "%-5d | %-5s | %-45s | %-15s | %-26s | %,13d | %s%n",
                        number++,
                        country.getCode(),
                        country.getName(),
                        country.getContinent(),
                        country.getRegion(),
                        country.getPopulation(),
                        country.getCapitalName()
                ));
            }
        }

        return table(title, header, rows.toString());
    }

    // ============================================================
    // CITY REPORTS
    // ============================================================

    /**
     * Formats a list of cities with numbered rows.
     *
     * @param title  the report title
     * @param cities the cities to display
     * @return the formatted city report
     */
    public static String formatCities(
            String title, List<City> cities) {

        String header = String.format(
                "%-5s | %-35s | %-45s | %-25s | %15s%n",
                "No.", "City", "Country Name",
                "District", "Population"
        );

        StringBuilder rows = new StringBuilder();

        if (cities == null || cities.isEmpty()) {
            rows.append("No cities found.\n");
        } else {

            int number = 1;

            for (City city : cities) {

                rows.append(String.format(Locale.US,
                        "%-5d | %-35s | %-45s | %-25s | %,15d%n",
                        number++,
                        city.getName(),
                        city.getCountryName(),
                        city.getDistrict(),
                        city.getPopulation()
                ));
            }
        }

        return table(title, header, rows.toString());
    }

    // ============================================================
    //  CAPITAL CITY REPORTS
    // ============================================================

    /**
     * Formats capital cities with numbered rows.
     *
     * @param title         the report title
     * @param capitalCities the capital cities to display
     * @return the formatted capital city report
     */
    public static String formatCapitalCities(
            String title, List<City> capitalCities) {

        String header = String.format(
                "%-5s | %-45s | %-45s | %15s%n",
                "No.", "Name", "Country", "Population"
        );

        StringBuilder rows = new StringBuilder();

        if (capitalCities == null || capitalCities.isEmpty()) {
            rows.append("No capital cities found.\n");
        } else {

            int number = 1;

            for (City city : capitalCities) {

                rows.append(String.format(Locale.US,
                        "%-5d | %-45s | %-45s | %,15d%n",
                        number++,
                        city.getName(),
                        city.getCountryName(),
                        city.getPopulation()
                ));
            }
        }

        return table(title, header, rows.toString());
    }

    /**
     * Formats all capital cities worldwide.
     */
    public static String printAllCapitalCities(
            String title, List<City> cities) {

        return formatCapitalCities(title, cities);
    }

    /**
     * Formats all capital cities in a continent.
     */
    public static String printAllCapitalCitiesByContinent(
            Continent continent, List<City> cities) {

        return formatCapitalCities(
                "All Capital Cities in " + continent + " by Population",
                cities
        );
    }

    /**
     * Formats all capital cities in a region.
     */
    public static String printAllCapitalCitiesByRegion(
            String region, List<City> cities) {

        return formatCapitalCities(
                "All Capital Cities in " + region + " by Population",
                cities
        );
    }

    /**
     * Formats the top N capital cities worldwide.
     */
    public static String printTopNCapitalCities(
            int topN, List<City> cities) {

        return formatCapitalCities(
                "Top " + topN + " Capital Cities Worldwide",
                cities
        );
    }

    /**
     * Formats the top N capital cities in a continent.
     */
    public static String printTopNCapitalCitiesByContinent(
            int topN, Continent continent, List<City> cities) {

        return formatCapitalCities(
                "Top " + topN + " Capital Cities in " + continent,
                cities
        );
    }

    /**
     * Formats the top N capital cities in a region.
     */
    public static String printTopNCapitalCitiesByRegion(
            int topN, String region, List<City> cities) {

        return formatCapitalCities(
                "Top " + topN + " Capital Cities in " + region,
                cities
        );
    }

    // ============================================================
    // POPULATION INFORMATION REPORTS
    // ============================================================

    /**
     * Formats a single population value for a location.
     *
     * @param reportType   the type of population report
     * @param locationName the location being reported
     * @param population   the population value
     * @return the formatted population report
     */
    public static String formatPopulation(
            String reportType,
            String locationName,
            long population) {

        String header = String.format(
                "%-5s | %-35s | %20s%n",
                "No.", "Location", "Total Population"
        );

        String value = population == 0
                ? "No data available"
                : String.format(Locale.US, "%,d", population);

        String rows = String.format(
                "%-5d | %-35s | %20s%n",
                1,
                locationName,
                value
        );

        return table(
                reportType + " POPULATION REPORT",
                header,
                rows
        );
    }

    /**
     * Formats a city population report.
     *
     * @param cityName the requested city
     * @param cities   the matching city records
     * @return the formatted city population report
     */
    public static String formatCityPopulation(
            String cityName, List<City> cities) {

        String header = String.format(
                "%-5s | %-35s | %20s%n",
                "No.", "City Name", "Population"
        );

        StringBuilder rows = new StringBuilder();

        if (cities == null || cities.isEmpty()) {

            rows.append("No data found for city: ")
                    .append(cityName)
                    .append('\n');

        } else {

            int number = 1;

            for (City city : cities) {

                rows.append(String.format(Locale.US,
                        "%-5d | %-35s | %,20d%n",
                        number++,
                        city.getName(),
                        city.getPopulation()
                ));
            }
        }

        return table(
                "CITY POPULATION REPORT",
                header,
                rows.toString()
        );
    }

    // ============================================================
    //  LANGUAGE POPULATION REPORTS
    // ============================================================

    /**
     * Formats language population statistics.
     * Preserves supported-language checks, missing-language
     * handling, and invalid-value rules.
     *
     * @param results the language population data
     * @return the formatted language report
     */
    public static String formatLanguages(
            List<LanguageData> results) {

        String header = String.format(
                "%-5s | %-25s | %20s | %20s%n",
                "No.", "Language",
                "No. of Speakers", "World Percentage"
        );

        StringBuilder rows = new StringBuilder();

        if (results == null || results.isEmpty()) {
            rows.append("No data available.\n");

            return table(
                    "LANGUAGE POPULATION REPORT",
                    header,
                    rows.toString()
            );
        }

        int number = 1;

        // Display supported languages from the results.
        for (LanguageData row : results) {

            if (row == null || row.language() == null) {
                continue;
            }

            boolean supported = false;

            for (Language language : Language.values()) {

                if (language.name().equalsIgnoreCase(row.language())) {
                    supported = true;
                    break;
                }
            }

            if (!supported) {
                continue;
            }

            String speakers = row.speakers() == null
                    ? "N/A"
                    : String.format(Locale.US, "%,d", row.speakers());

            Double percentageValue = row.worldPercentage();

            String percentage =
                    percentageValue == null
                            || !Double.isFinite(percentageValue)
                            || percentageValue < 0
                            || percentageValue > 100
                            ? "N/A"
                            : String.format(
                            Locale.US,
                            "%.2f%%",
                            percentageValue
                    );

            rows.append(String.format(
                    "%-5d | %-25s | %20s | %20s%n",
                    number++,
                    row.language(),
                    speakers,
                    percentage
            ));
        }

        // Display missing supported languages as N/A.
        for (Language language : Language.values()) {

            boolean found = results.stream().anyMatch(
                    row -> row != null
                            && row.language() != null
                            && row.language().equalsIgnoreCase(
                            language.name()
                    )
            );

            if (!found) {

                rows.append(String.format(
                        "%-5d | %-25s | %20s | %20s%n",
                        number++,
                        language.name(),
                        "N/A",
                        "N/A"
                ));
            }
        }

        return table(
                "LANGUAGE POPULATION REPORT",
                header,
                rows.toString()
        );
    }

    /**
     * Formats population distribution reports for continents,
     * regions, and countries.
     *
     * Displays city and non-city populations and their
     * percentages of the total population.
     *
     * Rows are numbered starting from 1.
     *
     * @param title the report title
     * @param data the population distribution data
     * @return the formatted population distribution table
     */
    public static String formatPopulationDistribution(
            String title,
            List<PopulationDistributionData> data)  {
        String header = String.format(
                "%-5s | %-30s | %18s | %18s | %12s | %12s%n",
                "No.",
                "Location",
                "City Population",
                "Non-City Pop.",
                "City %",
                "Non-City %"
        );

        StringBuilder rows = new StringBuilder();

        if (data == null || data.isEmpty()) {
            rows.append("No data available.\n");
        } else {
            int number = 1;

            for (PopulationDistributionData row : data) {

                long total = row.totalPopulation();
                long city = row.cityPopulation();

                boolean invalid =
                        total <= 0 || city < 0 || city > total;

                String cityText;
                String nonCityText;
                String cityPercent;
                String nonCityPercent;

                if (invalid) {
                    cityText = "N/A";
                    nonCityText = "N/A";
                    cityPercent = "N/A";
                    nonCityPercent = "N/A";
                } else {
                    long nonCity = total - city;

                    cityText = String.format(
                            Locale.US, "%,d", city
                    );

                    nonCityText = String.format(
                            Locale.US, "%,d", nonCity
                    );

                    cityPercent = String.format(
                            Locale.US,
                            "%.2f%%",
                            (double) city / total * 100
                    );

                    nonCityPercent = String.format(
                            Locale.US,
                            "%.2f%%",
                            (double) nonCity / total * 100
                    );
                }

                rows.append(String.format(
                        Locale.US,
                        "%-5d | %-30.30s | %18s | %18s | %12s | %12s%n",
                        number++,
                        row.location(),
                        cityText,
                        nonCityText,
                        cityPercent,
                        nonCityPercent
                ));
            }
        }

        return table(title, header, rows.toString());
    }
}





