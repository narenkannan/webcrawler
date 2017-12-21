# webcrawler [![Build Status](https://travis-ci.org/narenkannan/webcrawler.svg?branch=master)](https://travis-ci.org/narenkannan/webcrawler) [![Inline docs](http://inch-ci.org/github/narenkannan/webcrawler.svg?branch=master&style=shields)](http://inch-ci.org/github/narenkannan/webcrawler) [![Maintainability](https://api.codeclimate.com/v1/badges/f17b5fa5101563862161/maintainability)](https://codeclimate.com/github/narenkannan/webcrawler/maintainability) [![Test Coverage](https://api.codeclimate.com/v1/badges/f17b5fa5101563862161/test_coverage)](https://codeclimate.com/github/narenkannan/webcrawler/test_coverage) [![HitCount](http://hits.dwyl.io/narenkannan/webcrawler.svg)](https://github.com/narenkannan/webcrawler)

### How to Run ?

##### Run Online 

Click this link to run the application hosted in cloud : <a style="text-align: center;">http://narenkannan.github.io</a>

The presentation layer is built using [Angular](https://angular.io/) and hosted using [Github Pages](https://pages.github.com/).
This Angular application is built using an online(SAAS) IDE named [plunkr](https://plnkr.co). The plunkr link for the presentation layer application is attached below

https://plnkr.co/edit/hUb64vBq9feTFhlMR8Ng

The Server side is built using Java 8,Spring Rest. This repository is CI (continiously integration) using [Travis CI](https://travis-ci.org/), so any changes to this repository will be immediately compiled,tested by Travis & published in [Heroku](https://dashboard.heroku.com/apps/webpagecrawler) cloud environment. 

##### This application takes 2 arguments:
     1. Protocol - (http,https)
     
     2. website  - (eg: www.github.com)
     
        syntax   : https://webpagecrawler.herokuapp.com/{protocol}/{website}/
        
        example  : https://webpagecrawler.herokuapp.com/http/www.moneycontrol.com/

##### Run locally

Running the application locally involves the below steps: 

   ##### Method 1: 

        1. Goto {projectfolder}/target using cmd prompt
        
        2. Now execute the below command : `java -jar -Dserver.port=90 webcrawler-0.0.1.jar`
        
        3. Open any web browser & type http://localhost:90/{protocol}/{website}
        
        4. If you are running the application behind a proxy, configure proxy settings using below:
        
              java -jar -Dhttp.proxyHost={replace-proxy-host} -Dhttp.proxyPort={replace-port} -Dserver.port=90 webcrawler-0.0.1.jar

##### Method 2: 

        1. Clone or download the sourcecode from 
        
        2. Using Eclipse/STS(Spring Tool Suite) import the project in below order:
            
            File -> Import -> Maven -> Existing Maven Projects.
            
        3. In the package explorer, rightclick the project and select RunAs -> Spring Boot application.
        
        4. Now open any web browser & type http://localhost:90/{protocol}/{website}
     

## About the project

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

Heroku Page : [Heroku](https://dashboard.heroku.com/apps/webpagecrawler)

Based on the test results status the code is deployed on online cloud compute service called [Heroku](https://www.heroku.com/). Heroku pulls the latest change & based on the project setup it uses pre configured strategy or dynos to run the application. Currently the application is configured using free licence of Heroku service.

### Scope for future enhancement

    * Provision to select the list of elements to be crawled. eg:link/images,videos,audios etc.
    
    * Tab based user interface for listing content type Links/Images/videos.
    
    * Dynamic index level.
    
    * Option to enable/disable crawl external Url's(other domain).
    
    * Multiple output format's. eg: xml,plain text,etc.
    
    * Export to file formats like xlsx,csv,etc.
    
    * Recently accessed Url's can be shown, based on visitors IP address.
    
    * Parallel Processing can be applied to speedup the turn around time.




