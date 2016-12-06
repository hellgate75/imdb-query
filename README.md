# IMDb Serch Shell


##Goals

This software has the goal to provide a simple IMBd crawler. This crawler at the moment has not configuration to allow to different Movie Databases.



##Technology

This software is entirely written in Java 1.8 and it's provided of a command line suite to activate the crawling. It's a passive system surfer using multiple threads and collecting the hierarchy in a specific standard output.

The system is provided of a single running interface and a multiple customizable Output formatter and Output device interface. These classes are sibling of two interfaces in order : `ResultFormatter` and `ResultCollector`.

The software has been written with the following minimal design patterns :
  * **Builder** (`WebRestQueryConfigurationBuilder`)
  * **Method Factory** (`WebRestQueryHelper#parseParametersAndRunCrawler`)
  * **Abstract Factory** (`WebRestQuery` and `WebRestQueryConfiguration`)
  * **Command** (`WebRestQuery`) recursive on itself on the scheduler request.
  * **Bridge** (`ResultFormatter`, `ResultCollector`) 
  * **Observer** (`WebRestQuery` in order to `WebRestQueryProcess`) 
  * **Visitor** (`WebRestQueryConfiguration` is a Visitor behalf `WebRestQueryProcess` for the `Result` surfing state)

The application is a snapshot release because it doesn't gather the my minimum level of isolation and test harness, due to the first few effort provided.

In the future we will improve this application providing a wider range of configuration attributes, writing multiple formatters and collectors, providing a specific interpreter for the target platforms, define a Spring Boot and Spring Web configuration, isolating the API and providing a persistent layer to operate offline on API Search Project (dry-run, re-run, time schedule, remote access for defying and/or run processes behalf deamon agents). Just right now, we detach a Project UID that is transversable to all the application layer, and it can be persisted on a SQL (such as H2, ...), NoSQL (such as MongoDb, ..), File (such as a json file directory, ...) and/or configurable cache systems (such as ehcache, ...) all that in a kind of node environment application server, with specific a configuration, providing a configuration, a clustering, an administration and a maintenance services.

The current command usage is completely dynamic and the current formatters and collectors are recovered by the related implementation package. Implementing new elements automatically are presented to the user. Any attribute has a default value included in a properties file, including the web site URL, so the errors can be related to the human mistakes, only!! :D



##Prerequisites 

**Java 1.8** or upper and **Maven 3.x**. The exported variable **JAVA_HOME** pointing to the Java8 base folder.


##NOTE 

Due to reduced time most of the test has been put in hold at the moment. We decline any responsability related to damages directly or indirectly related to the use of this study library. 


##Build 

To build the application you can use the scripts *build.cmd* or *build.sh*.

The Maven 3.x command line to build the project is : 

`mvn -U -up clean cobertura:cobertura javadoc:jar source:jar install`



##Command Line interface

To test the command line interface there is a **windows command** and a **linux shell executable** shell scripts in the `bin` directory. To have the usage list you need simply use the `--help` option. Alternatively you can use the folder bin jar `query.jar` in the base project folder.

Here an example of use:

Usage: api-search [options]

samples: api-search --format=simple --api imdb --movie "Indiana Jones"
         api-search --movie "Herry Potter"
options:
--format		Identifies the site map format
     available formats :
     plain		Plain Text Site Map Format
default : plain
--output		Identifies output device
     available output :
     stdout		Standard Output Writer
default : stdout
--extends		Identifies the number of threads extension on the surf. This is the minimum number of threads
			running on the site hierarchy discovery
default : 10
-Dmovie=<value>		Identifies the movie title query name
--movie		Identifies the movie title query name
default : null
-DexactSearch=<value>		Identifies the exact title search
--exactSearch		Identifies the exact title search
default : true
-Dapi=<value>		Identifies the default used API database
--api		Identifies the default used API database
     available engines :
     Engine Factory : com.web.libraries.imdbquery.api.controller.APIEngineFactory@3d0f8e03
     Engine : com.web.libraries.imdbquery.api.controller.engines.TMDBApiEngine@6366ebe0
     tmdb		The Movie DB Rest API implementation (use -DexactSearch=0)
     Engine : com.web.libraries.imdbquery.api.controller.engines.IMDBApiEngine@44f75083
     imdb		IMDB Rest API implementation
     Engine : com.web.libraries.imdbquery.api.controller.engines.OMDBApiEngine@2698dc7
     omdb		OMDB Rest API implementation - APIKey activation in progress
default : com.web.libraries.imdbquery.api.controller.engines.TMDBApiEngine@6366ebe0



## Release Version

`1.0.0-SNAPSHOOT`



##Road Map

* *T.B.D.*