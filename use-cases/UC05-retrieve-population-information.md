# USE CASE: 05 RETRIEVE POPULATION INFORMATION

## CHARACTERISTIC INFORMATION

### Goal in Context

As an analyst, I want to retrieve different population data based on country, city, district, continent, region and the whole world, this is so that I can view the population of the selected city, country, etc

### Scope

System (black-box)

### Level

User Goal

### Preconditions

- The Analyst has access to the application
- An active connection between the application and the database
- Database contains the required country data

### Success End Condition

A Report that generates based on the analysts choice when running the application
The reports should be one of:
- Retrieve and Generate Country Population Report
- Retrieve and Generate District Population Report
-  Retrieve and Generate City Population Report
-  Retrieve and Generate Continent Population Report
-  Retrieve and Generate Region Population Report
-  Retrieve and Generate The Entire World Population Report

### Failed End Condition

- Any Of The Reports Don't Generate

### Primary Actor

Analyst

### Trigger

A request for a certain population report

## MAIN SUCCESS SCENARIO

1. Analyst can access the application
2. Analyst can choose which population report to access
3. The system will query the database to display the appropriate report back to the analyst

## EXTENSIONS

3. If connection fails
    1. System will send an error back to the analyst to say there is no report to be found

## SUB-VARIATIONS

1. The population of the world.
2. The population of a continent.
3. The population of a region.
4. The population of a country.
5. The population of a district.
6. The population of a city.
## SCHEDULE

Due Date TBC
