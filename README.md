## webcrawler [![Build Status](https://travis-ci.org/narenkannan/webcrawler.svg?branch=master)](https://travis-ci.org/narenkannan/webcrawler) [![Inline docs](http://inch-ci.org/github/narenkannan/webcrawler.svg?branch=master&style=shields)](http://inch-ci.org/github/narenkannan/webcrawler) [![Maintainability](https://api.codeclimate.com/v1/badges/f17b5fa5101563862161/maintainability)](https://codeclimate.com/github/narenkannan/webcrawler/maintainability) [![Test Coverage](https://api.codeclimate.com/v1/badges/f17b5fa5101563862161/test_coverage)](https://codeclimate.com/github/narenkannan/webcrawler/test_coverage) [![HitCount](http://hits.dwyl.io/narenkannan/webcrawler.svg)](https://github.com/narenkannan/webcrawler)

This application is a Rest Application, which takes a website url as input

### Introduction

A Web crawler starts with a list of URLs to visit, called the seeds. As the crawler visits these URLs, it identifies all the hyperlinks in the page and adds them to the list of URLs to visit, called the crawl frontier. URLs from the frontier are recursively visited according to a set of policies. If the crawler is performing archiving of websites it copies and saves the information as it goes.

### Continuous Integration

This repository is continuous integrated with [Travis CI](https://travis-ci.org/narenkannan/webcrawler). whenever new commits are pushed to that repository or a pull request is submitted, Travis CI will then check out the relevant branch and run the commands specified in .travis.yml, which usually build the software and run any automated tests. When that process has completed, Travis notifies the developer(s) in the way it has been configured to do so for example, by sending an email containing the test results (showing success or failure).

### Code coverage & quality 

Code quality and coverage is maintained using [codeclimate](https://codeclimate.com/)

### Continious Delivery 
<img src="https://search.maven.org/ajaxsolr/images/centralRepository_logo.png" width="300" height="50">
Once Travis compiles the source code and executes the tests. Once all the tests are successful, then travis is configured to publish the the binary/executable in the Maven central repository.

### Continious Deployment 
![Heroku](https://images.iwantmyname.com/apps/images/logo-developer-heroku.png)

Based on the test results status the code is deployed on online cloud compute service called [Heroku](https://www.heroku.com/). Heroku pulls the latest change & based on the project setup it uses pre configured strategy or dynos to run the application. Currently the application is configured using free licence of Heroku service.






