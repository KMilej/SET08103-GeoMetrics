# USE CASE: 1 View all countries in the world by pupulation.

## CHARACTERISTIC INFORMATION

### Goal in Context

As an analyst, I want to view all countries worldwide by population, so that I can analyse population distribution.
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

1. Analyst accesses the application.
2. System displays the report type options.
3. Analyst chooses countries.
4. System displays the scope options.
5. Analyst chooses the scope: World.
6. System displays the size options.
7. Analyst chooses the size: All.
8. System displays all countries in the world, ordered by population from largest to smallest.

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

7a. Analyst chooses 0. Exit.
1. System returns to the report type options.
2. Use case resumes at step 2.

7b. Analyst enters an input that is not on the menu.
1. System informs the analyst that the input is invalid.
2. System redisplays the size options.
3. Use case resumes at step 7.

8a. The database connection is lost, or the data cannot be retrieved, after the use case has started.
1. System informs the analyst that no report is available.
2. Use case ends.

## SUB-VARIATIONS

None

## SCHEDULE

**DUE DATE**: to be confirmed 