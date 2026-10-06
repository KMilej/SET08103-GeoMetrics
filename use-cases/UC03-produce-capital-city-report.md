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
3. Analyst specifies whether the report should contain all capital cities or the top N capital cities.
4. If a Top N report is requested, the analyst specifies the value of N.
5. GeoMetrics retrieves the capital cities matching the selected geographical scope.
6. GeoMetrics orders the capital cities from largest to smallest population.
7. If Top N was selected, GeoMetrics limits the results to the requested number of capital cities.
8. GeoMetrics produces the capital city report.
9. GeoMetrics presents the report to the analyst.

## EXTENSIONS

2. **Selected geographical scope does not exist**:
    1. GeoMetrics informs the analyst that the selected geographical scope could not be found.

4. **Invalid value of N is provided**:
    1. GeoMetrics informs the analyst that the value of N is invalid.
    2. Analyst provides a valid value of N.

5. **No capital cities match the selected geographical scope**:
    1. GeoMetrics informs the analyst that no matching capital cities were found.

## SUB-VARIATIONS

1. Geographical scope:
    1. World.
    2. Continent.
    3. Region.

2. Report size:
    1. All capital cities.
    2. Top N capital cities.

## SCHEDULE

**DUE DATE **: