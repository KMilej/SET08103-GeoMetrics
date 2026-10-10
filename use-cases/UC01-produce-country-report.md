# USE CASE: 2 View countries by population

## CHARACTERISTIC INFORMATION

### Goal in Context

As an analyst, I want to view countries in a chosen region by population, so that I can analyse population distribution.

### Scope

System (black-box)

### Level

Primary task

### Preconditions

- Analyst has access to the application.
- The application has an active connection to the database when the use case starts.
- The database contains region data.

### Success End Condition

A report is produced that displays all or the top N (chosen by the user) countries in the world, a chosen continent, or a chosen region, organised from largest population to smallest, with the following columns:
- Code
- Name
- Continent
- Region
- Population
- Capital

### Failed End Condition

No report is produced.

### Primary Actor

Analyst

### Trigger

Request for report.

## MAIN SUCCESS SCENARIO

1. The analyst selects a country report.
2. The analyst enters the continent or region if required, and N for a top N report.
3. The application displays the country report.

## EXTENSIONS

2a. Invalid location or N.
    1. The application asks the user to enter a valid location or a positive whole number for N.

3a. No countries found.
    1. The application informs the user that no countries were found.

## SUB-VARIATIONS

1. The analyst can select one of the following reports:
    1. All countries in the world by population: analyst provides nothing.
    2. All countries in the continent by population: analyst provides name of the continent.
    3. All countries in the region by population: analyst provides name of the region.
    4. Top N countries in the world: analyst provides number for top N countries.
    5. Top N countries in the chosen continent: analyst provides name of the continent and number. 
    6. Top N countries in the chosen region: analyst provides name of the region and number.

## SCHEDULE

**DUE DATE**: To be confirmed