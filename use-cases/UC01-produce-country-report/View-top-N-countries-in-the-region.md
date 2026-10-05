# USE CASE: 5 View the top N countries in a region by population

## CHARACTERISTIC INFORMATION

### Goal in Context

As an analyst, I want to view the top N countries in a chosen region by population, so that I can analyse population distribution.

### Scope

System (black-box)

### Level

Primary task

### Preconditions

- Analyst has access to the application.
- The application has an active connection to the database when the use case starts.
- The database contains country data.

### Success End Condition

A report is produced that displays the top N countries in the chosen region, organised from largest population to smallest, with the following columns:
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

1. Analyst accesses the application.
2. System displays the report type options.
3. Analyst chooses countries.
4. System displays the scope options.
5. Analyst chooses the scope: Region.
6. System asks for the name of the region.
7. Analyst enters the name of a region.
8. System displays the size options.
9. Analyst chooses the size: Top N.
10. System asks for the value of N.
11. Analyst enters a value for N.
12. System displays the top N countries in the chosen region, ordered by population from largest to smallest.

## EXTENSIONS

3a. Analyst chooses 0. Exit.
1. System closes the report menu without producing a report.
2. Use case ends.

3b. Analyst enters an input that is not on the menu.
1. System informs the analyst that the input is invalid.
2. System redisplays the report type options.
3. Use case resumes at step 3.

5a. Analyst chooses 0. Exit.
1. System returns to the report type options.
2. Use case resumes at step 2.

5b. Analyst enters an input that is not on the menu.
1. System informs the analyst that the input is invalid.
2. System redisplays the scope options.
3. Use case resumes at step 5.

7a. Analyst enters 0. Exit.
1. System returns to the report type options.
2. Use case resumes at step 2.

7b. Analyst enters a region name that is not recognised (for example, a misspelt or non-existent region).
1. System informs the analyst that the region was not found.
2. System asks again for the name of the region.
3. Use case resumes at step 7.

9a. Analyst chooses 0. Exit.
1. System returns to the report type options.
2. Use case resumes at step 2.

9b. Analyst enters an input that is not on the menu.
1. System informs the analyst that the input is invalid.
2. System redisplays the size options.
3. Use case resumes at step 9.

11a. Analyst enters 0. Exit.
1. System returns to the report type options.
2. Use case resumes at step 2.

11b. Analyst enters a value for N that is not a positive whole number (for example, text, a decimal or a negative number).
1. System informs the analyst that the value is invalid.
2. System asks again for the value of N.
3. Use case resumes at step 11.

12a. The database connection is lost, or the data cannot be retrieved, after the use case has started.
1. System informs the analyst that no report is available.
2. Use case ends.

## SUB-VARIATIONS

None

## SCHEDULE

**DUE DATE**: To be confirmed