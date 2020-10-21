# WordCount App
Simple WordCount app built for Ordina

# How to run it
Navigate to the backend folder.
Open a shell and run "mvn clean install -Dtest=StringUtilTests,WordFrequencyAnalyzerTests test"  
**Note:** This does not work in PowerShell. Both test classes need to be run singlehandedly like so: "mvn clean install -Dtest=StringUtilTests test" and "mvn clean install -Dtest=WordFrequencyAnalyzerTests test"
