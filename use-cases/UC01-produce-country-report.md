# USE CASE: 2 View all countries by population

## CHARACTERISTIC INFORMATION

### Goal in Context

As an analyst, I want to view all countries in a chosen region by population, so that I can analyse population distribution.

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

1. The user selects a country report.
2. The user enters the continent and region if required, and N for a top N report.
3. The application gets the matching countries from the database, ordered by population from largest to smallest. For a top N report, it gets up to N top countries.
4. The application displays the country report.

## EXTENSIONS

2. Invalid location or N.
    1. The application asks the user to enter a valid location or a positive whole number for N.

3. No countries found.
    1. The application informs the user that no countries were found.

## SUB-VARIATIONS

1. The user can select one of the following reports:
    1. All countries in the world by population, organised from the largest population to smallest.
    2. All countries in the continent by population, organised from the largest population to smallest.
    3. All countries in the region by population, organised from the largest population to smallest.
    4. Top N countries in the world, organised from the largest population to smallest.
    5. Top N countries in the chosen continent, organised from the largest population to smallest.
    6. Top N countries in the chosen region, organised from the largest population to smallest.

## SCHEDULE

**DUE DATE**: To be confirmed