# wordcount

assumptions based upon example
fullstops should be ignored
special characters such as & are words
special characters in a word are considered part of the work - eg 18/07/2018

to build and run
./mvnw clean package
./mvnw spring-boot:run

To upload a file and see contents in json, goto
http://localhost:8080/index.html

To upload a file and see contents as a formatted string (for th test output), goto
http://localhost:8080/index2.html

example files are in -
arc/main/resources/examplesfiles
bible_daily.txt
test.txt
test2.txt

