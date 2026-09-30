# USE CASE: 1 View all countries in the world.

## CHARACTERISTIC INFORMATION

### Goal in Context

As an analyst, I want to view all countries in the world organised from largest to smallest population, so that I can decide where to focus resources.

### Scope

System (black-box)

### Level

Primary task

### Preconditions

- Analyst has access to application.
- Active connection between application and database.
- Database contains country data.

### Success End Condition

Report is produced and displays all the countries in the world organised by largest population to smallest with columns:
- Code.
- Name.
- Continent.
- Region.
- Population.
- Capital.

### Failed End Condition

No report is produced.

### Primary Actor

Analyst

### Trigger

Request for report. 

## MAIN SUCCESS SCENARIO
1. Analyst accesses application.
2. Analyst chooses option to view report of all countries in the world organised from largest to smallest population.
3. The system displays the country report.


## EXTENSIONS

3. Connection to database failed.
    1. System informs there is no report available. 

## SUB-VARIATIONS

None

## SCHEDULE

**DUE DATE**: to be confirmed 