# USE CASE: UC05 - Produce Population Distribution Report
## CHARACTERISTIC INFORMATION

### Goal in Context
Produce a population distribution of people living and not living in a city.


### Scope
Primary


### Level
User Goal


### Preconditions
City and country population data is a valid integer and is stored in an accessible database
Each city must be associated with a country
Each country must be associated with a region and a continent.
The city's population does not exceed its country's population



### Success End Condition
The system displays the population distribution report, including the number of people living and not living in cities.


### Failed End Condition
An error message is displayed and no population distribution report is generated. 


### Primary Actor
User


### Trigger
User selects 'Generate a  Population Distribution Report'


## MAIN SUCCESS SCENARIO

1. The user selects 'Generate a Population Distribution Report'
2. The system prompts user to select category: [Country, Region, Continent]
3. The user selects a category.
4. The system prompts user to select an area associated with the selected category.
5. The user selects an associated area.
6. The system determines the total population of selected area using associated country/countries population(s).
7. The system calculates total city populations of the associated country/countries
8. The system calculates the total number of people living and not living in city within the selected area using the total city population and overall total population.
9. The system displays the total number of people living and not living in city of the selected area.

## EXTENSIONS

3a. **User selects invalid category**:

    1. The system displays an error message
    2. The system prompts the user to select a valid category.

5a. **User selects invalid area**:

    1. The system displays an error message
    2. The system prompts the user to select a valid area.

6a. **Population data is unavailable for one or more associated countries**:

    1. The system excludes countries with no available population data.
    2. The system continues with available data
    3. The system displays a warning that one or more countries has no available population data.

6b. **Population data is unavailable for any associated countries.**
    1. The system displays an error message 'No Population Data Available for Country / Associated Countries'
    2. The system exits the program
 
7a. **One or more city population is unavailable**:

    1. The system excludes cities with no available population data.
    2. The system continues with available data
    3. The system displays a warning that one or more countries has no available population data.

7b. **No city population data is available.**
    1. The system displays a message to inform user that population distribution cannot be calculated due to lack of city population data for the selected area.
    2. The system exits the program.


## SUB-VARIATIONS

None.


## SCHEDULE

**DUE DATE**:
