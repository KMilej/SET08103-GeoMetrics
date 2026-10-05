# USE CASE: 2 Produce a City Report

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *GeoMetrics user* I want *to produce a city report* so that *I can compare city populations.*

### Scope

GeoMetrics.

### Level

Primary task.

### Preconditions

The application is connected to the world MySQL database. The database contains city and country data.

### Success End Condition

A city report is displayed, ordered by population from largest to smallest. The report contains Name, Country, District and Population.

### Failed End Condition

No report is produced.

### Primary Actor

GeoMetrics user.

### Trigger

The user selects City Reports from the main menu.

## MAIN SUCCESS SCENARIO

1. The user selects a city report.
2. The user enters the continent, region, country or district if required, and N for a top N report.
3. The application gets the matching cities from the database, ordered by population from largest to smallest. For a top N report, it gets up to N cities.
4. The application displays the city report.

## EXTENSIONS

2. **Invalid location or N**:
    1. The application asks the user to enter a valid location or a positive whole number for N.

3. **No cities found**:
    1. The application informs the user that no cities were found.

## SUB-VARIATIONS

1. The user can select one of the following reports:
    1. All cities in the world.
    2. All cities in a continent.
    3. All cities in a region.
    4. All cities in a country.
    5. All cities in a district.
    6. The top N populated cities in the world.
    7. The top N populated cities in a continent.
    8. The top N populated cities in a region.
    9. The top N populated cities in a country.
    10. The top N populated cities in a district.

## SCHEDULE

**DUE DATE**: 13 October 2026.
