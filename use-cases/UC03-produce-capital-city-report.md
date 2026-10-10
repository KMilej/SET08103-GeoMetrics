# USE CASE: 3 Produce a Capital City Report

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *analyst* I want *to produce a capital city report by population* so that *I can analyse population distribution.*

### Scope

GeoMetrics.

### Level

Primary task.

### Preconditions

The population database is available and contains capital city, country, continent, region, and population data.

### Success End Condition

A capital city report for the selected geographical scope is produced, with capital cities ordered from largest to smallest population.

### Failed End Condition

A capital city report is not produced.

### Primary Actor

Analyst.

### Trigger

The analyst requests a capital city report.

## MAIN SUCCESS SCENARIO

1. Analyst requests a capital city report.
2. Analyst specifies the geographical scope of the report.
3. If the scope is a continent or region, the analyst specifies the required continent or region.
4. Analyst specifies whether the report should contain all capital cities or the top N capital cities.
5. If a Top N report is requested, the analyst specifies the value of N.
6. GeoMetrics retrieves the capital cities matching the selected geographical scope.
7. GeoMetrics orders the capital cities from largest to smallest population.
8. If Top N was selected, GeoMetrics limits the results to the requested number of capital cities.
9. GeoMetrics produces the capital city report.
10. GeoMetrics presents the report to the analyst.

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

**DUE DATE: **: