# USE CASE: 3 Produce a Capital City Report

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *analyst* I want *to produce a capital city report by population* so that *I can analyse population distribution.*

### Scope

System (black-box)

### Level

User Goal

### Preconditions

- Analyst has access to the application.
- The application has an active connection to the database when the use case starts.
- The database contains capital city, country, continent, region and population data.

### Success End Condition

A report is produced that displays all or the top N (chosen by the user) capital cities in the world, a chosen continent or region, organised from largest population to smallest, with the following columns:
- Name
- Country
- Population

### Failed End Condition

No report is produced.

### Primary Actor

Analyst.

### Trigger

Request for report.

## MAIN SUCCESS SCENARIO

1. The analyst selects a capital city report.
2. The analyst enters the continent or region if required, and N for a top N report.
3. The application displays the capital city report.

## EXTENSIONS

**Selected geographical scope does not exist**:
    1. GeoMetrics informs the analyst that the selected geographical scope could not be found.

**Invalid value of N is provided**:
    1. GeoMetrics informs the analyst that the value of N is invalid.
    2. Analyst provides a valid value of N.

**No capital cities match the selected geographical scope**:
    1. GeoMetrics informs the analyst that no matching capital cities were found.

## SUB-VARIATIONS

1. All capital cities worldwide.
2. All capital cities in a selected continent.
3. All capital cities in a selected region.
4. Top N capital cities worldwide.
5. Top N capital cities in a selected continent.
6. Top N capital cities in a selected region.

## SCHEDULE

**DUE DATE: Code review 3**: