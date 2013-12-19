conjure-jmeter-plugin
=====================

JMeter plugin utilizing Conjure pattern to generate random data values.

It utilized conjure project (https://github.com/housejester/d8a-conjure) to generate random variables.

Helps generating dummy data for load testing.

Usage
=====

1. Build the project
    mvn clean install
2. Copy target/conjurer-jmeter-plugin-selfcontained.jar to ${jmeter.home}/lib/ext
3. Add the element from Config -> Conjure Random Variable Config
