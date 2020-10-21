# WordCount App
Simple WordCount app built for Ordina

# How to run the automated tests
Navigate to the backend folder.
Open a shell and run "mvn clean install -Dtest=StringUtilTests,WordFrequencyAnalyzerTests,ApiWordFrequencyAnalyzerTests test"  
**Note:** This does not work in PowerShell. All test classes need to be run singlehandedly like so: "mvn clean install -Dtest=StringUtilTests test", "mvn clean install -Dtest=WordFrequencyAnalyzerTests test" and "mvn clean install -Dtest=ApiWordFrequencyAnalyzerTests test"

# How to run the API
Navigate to the backend folder.
Open a shell and run "mvn clean install package". This should build the .jar file we want to run.
Now type "java -jar target/w" and press tab once. You should get something like this: "java -jar .\target\wordcount-1.0-SNAPSHOT.jar".
Press enter and the API should be up and running!

# Testing the API through Postman
If you would like to use Postman to checkout all the different calls then use this import link,https://www.getpostman.com/collections/554917a3fa781c07a1c3 , to get up and running quickly.
