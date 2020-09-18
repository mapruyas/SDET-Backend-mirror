# SDET-test
## Challenge
Create pilot Java test framework for testing NASA's open API.

NASA has an open API: https://api.nasa.gov/index.html#getting-started. It grants access to different features e.g: Astronomy Picture of the Day, Mars Rover Photos, etc.

We would like to test different scenarios that the API offers:
1. Retrieve the first 10 Mars photos made by "Curiosity" on 1000 Martian sol.
2. Retrieve the first 10 Mars photos made by "Curiosity" on Earth date equal to 1000 Martian sol.
3. Retrieve and compare the first 10 Mars photos made by "Curiosity" on 1000 sol and on Earth date equal to 1000 Martian sol.
4. Validate that the amounts of pictures that each "Curiosity" camera took on 1000 Mars sol is not greater than 10 times the amount taken by other cameras on the same date.

## Instructions
You will need to fork the repository and build the solution in Github **publicly**. Once you are finished, let HR know and share a link to your fork or a Zip file with your solution and the URL of the repository.

Implementation deadline is 1 week. Please let us know the time that you spent to achieve the task.


# Deviget SDET coding challenge

## Framework for testing NASA's open api

## How to run

### Optional
if you want to configure the tests with your own NASA api key follow this steps

run `cp .env.example .env` and replace *you-nasa-api-key* with your own

### Run tests

run `./gradlew clean test`

## Failing tests

Some tests are failing, they are related to item 4 of the challenge and the reason they are failing is because they do not meet the item 4 criteria.

The criteria is not meet because Spirit and Opportunity rovers do not have photos in sol 1000 for camera *Miniature Thermal Emission Spectrometer (Mini-TES)*, then the amount of pictures by ten is not greater than each *Curiosity* camera photos taken the same day. Except for Curiosity *Mast Camera*, which on sol 1000 has 0 photos taken too, so Spirit and Opportunity photos taken by *PANORAMIC CAMERA* by ten is greater than Curiosity photos taken in that day.