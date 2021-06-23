# Bus-Watcher
This application was brought to us by OneBusAway.

## Goal:
Due to the Coronavirus pandemic in Seattle, our client wants to ensure bus routes in Seattle are still operating as they should. Our job is to track each bus' arrival/departure times so that data can be used for graphing, etc.

## Tools:
- We used java to calculate the on timeness of the buses
- Curled certain API calls to read live data.
- We are parsing JSON after querying a RESTful API from OneBusAway,
  - EX: Distance from stop, Predicted Arrival Time, Scheduled Arrival Time, Current Time. 
- Note: The current times are presented as Time Stamps. 

## Algorithm:
To calculate the delay, we used this formula,
- Execute a thread for a set number of bus stops identified by a unique ID number
- on each thread, based on the API rate-limiting frequency, calculate the following
  - Delay = Predicted Arrival Time - Scheduled Arrival Time
  - Now = Predicted Arrival Time - Current Time
  - Prevent saving of duplicate entries by comparing against what was last inserted for this bus stop
  - output to CSV file on next line

## Future Development: 
We planned to use the Java server deployed on Heroku and integrate with Google Sheets API to fill out a table for the end user, but the project was ended.
