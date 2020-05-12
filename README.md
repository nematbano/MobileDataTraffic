Problem
-------------
Requirements
Create a native Android/iOS application to display the amount the of data sent over Singapore’s mobile networks from 2008 to 2018.

https://data.gov.sg/dataset/mobile-data-usage

Example:
https://data.gov.sg/api/action/datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f&limit=5

Task ONE: Display a list of data
Data displayed can be just numbers.
Each entry in the list could be a card or composite view, or whichever view you think will demonstrate your capabilities.
Each entry shall display total data consumption for ONE year - not quarter.

Task TWO: Display a clickable image
Display a clickable image in the entry if any quarter in a year demonstrates a decrease in volume data.

Task THREE (Optional): Data Caching
Cache the data for offline usage.

Solution
----------
1. Basic Solution of task 1 and task 2 in Java with error handling.
2. Unit tests for all the Java Classes.
3. Espresso Test
4. Coverage Report

Running the Project
------------------------
To run the project, you need to open the project in Android Studio. It can be run from there. 

Scope of Future Work
--------------------
1. Code conversion to Kotlin.
2. Implement Task 3.
3. UI improvement.
4. Use RxJava, Retrofit for network communication. 
5. Use Gson for Json parsing.
5. Use Dagger 2 for dependency injection
