Feature: Based on the response get weather and timestamp for all the data entries

  Scenario Outline: Get the state code for the lat long
    Given I have the api key <key>
    And I have Latitude as <latitude> and longitude as <longitude>
    When I do GET on <endPoint> for state code
    Then I receive the weather forecast with state code as <stateCode>
    Examples:
    |key |endPoint | latitude   | longitude | stateCode |
    |fd825cacc7cc4beb8fc1839aa9499526 |https://api.weatherbit.io/v2.0/current | 40.730610  | -73.935242 | NY |


  Scenario Outline: Get the time stamp and weather for given postal code
    Given I have the api key <key>
    And I have post code <postCode> to check three hourly weather
    When I do GET on <endPoint> for weather report
    Then I receive the utc time stamp
    And I receive the weather
    Examples:
      |key|postCode|endPoint|
      |fd825cacc7cc4beb8fc1839aa9499526|28546|https://api.weatherbit.io/v2.0/forecast/3hourly|