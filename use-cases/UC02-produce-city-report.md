# USE CASE: 2 View cities by population

## CHARACTERISTIC INFORMATION

### Goal in Context

As an analyst, I want to view cities in a chosen location by population, so that I can analyse population distribution.

### Scope

System (black-box)

### Level

User Goal

### Preconditions

- Analyst has access to the application.
- The application has an active connection to the database when the use case starts.
- The database contains city and country data.

### Success End Condition

A report is produced that displays all or the top N (chosen by the user) cities in the world, a chosen continent, region, country, or district, organised from largest population to smallest, with the following columns:
- Name
- Country
- District
- Population

### Failed End Condition

No report is produced.

### Primary Actor

Analyst

### Trigger

Request for report.

## MAIN SUCCESS SCENARIO

1. The analyst selects a city report.
2. The analyst enters the continent, region, country or district if required, and N for a top N report.
3. The application displays the city report.

## EXTENSIONS

2a. Invalid location or N.
    1. The application asks the user to enter a valid location or a positive whole number for N.

3a. No cities found.
    1. The application informs the user that no cities were found.

## SUB-VARIATIONS

1. The analyst can select one of the following reports:
    1. All cities in the world by population: analyst provides nothing.
    2. All cities in the continent by population: analyst provides name of the continent.
    3. All cities in the region by population: analyst provides name of the region.
    4. All cities in the country by population: analyst provides name of the country.
    5. All cities in the district by population: analyst provides name of the district.
    6. Top N cities in the world: analyst provides number for top N cities.
    7. Top N cities in the chosen continent: analyst provides name of the continent and number.
    8. Top N cities in the chosen region: analyst provides name of the region and number.
    9. Top N cities in the chosen country: analyst provides name of the country and number.
    10. Top N cities in the chosen district: analyst provides name of the district and number.

## SCHEDULE

**DUE DATE**: Code Review 3
