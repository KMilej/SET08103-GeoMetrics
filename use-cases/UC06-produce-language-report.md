# USE CASE: UC06 - Produce Language Report
## CHARACTERISTIC INFORMATION

### Goal in Context
Produce a language distribution report showing percentage and total number of people who speaks Chinese, English, Hindi, Spanish, and Arabic listed from greatest to smallest number of speakers.

### Scope
Primary

### Level
User Goal

### Preconditions
Language data for each country should be a valid number and available in the database.
Population data of relevant country should be a valid number be available.
Total world population should be a valid number and greater than total population of relevant country.
Population and language percentage should be calculable.


### Success End Condition
A language report is displayed for each specified language including the total number of speakers and their percentage in world population listed in descending order according to number of speakers. 

### Failed End Condition
An error message is displayed and no language report generated. 

### Primary Actor
User

### Trigger
User selects 'Generate a Language Report'

## MAIN SUCCESS SCENARIO

1. The user selects 'Generate a Language Report'
2. The system retrieves the total population of the world.
3. The system identifies and retrieve relevant country's population and the percentage that speaks each selected language.
4. The system calculates the total number of people who speaks the language.
5. The system calculates each selected language's total percentage in world population
6. The system lists the selected languages from greatest to smallest number of speakers and its percentage in world population.


## EXTENSIONS

2a. **Total world population is not available**:
    1. The system displays error message 'World population is unavailable'
    2. The system prompts the user to exit the program.

3a. **No relevant country or language percentage data is found for one or more selected language**:
    1. The system excludes the affected language(s) from the calculation.
    2. The system continues with available data.

3b. **No relevant country or language percentage data is found for all selected language**:
    1. The system displays a message 'No language data is available for selected languages'
    2. The system prompts the user to exit the program.
 
4a. **System cannot calculate the total number of speakers**:
    1. The system displays error message 'Unable to calculate total number of speakers'
    2. The system prompts the user to exit the program.


## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**:
