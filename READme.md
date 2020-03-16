# Bus-Watcher
This application was brought to us by OneBusWay.

## Goal:
Due to the Coronavirus pandemic in Seattle, the company OneBusWay company needs to reassure the bus routes in Seattle are still operating as they should. Our job is to make that calculation.

## Tools:
- We used java to calculate the on timeness of the buses
- Curled certain API calls to read live data.
- Heroku to keep the java program running for two weeks
- We are looking at certain patters in Json,
  - EX: Distance from stop, Predicted Arrival Time, Scheduled Arrival Time, Current Time. 
- Note: The current times are presented as Time Stamps. 

## Algorithm:
To calculate the delay, we used this formula, 
- Delay = Predicted Arrival Time - Scheduled Arrival Time
- Now = Predicted Arrival Time - Current Time

## Plan: 
We plan to use the java server on Heroky to run google sheet apis to fill out a table for the end user.
