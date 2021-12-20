# wordcount

###Assumptions based upon example

- fullstops should be ignored
- special characters such as & are words
- special characters in a word are considered part of the work - eg 18/07/2018

###To build, run tests and run

./mvnw clean package

./mvnw spring-boot:run


###To upload a file and see contents in json -

http://localhost:8080/indexJSON.html


###To upload a file and see contents as a formatted string (for required test output) -

http://localhost:8080/indexText.html

###Example test files are in -

src/main/resources/examplesfiles

 - bible_daily.txt
 - test.txt
 - test2.txt


