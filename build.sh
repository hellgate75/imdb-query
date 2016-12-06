#!/bin/sh
mvn -U -up clean javadoc:jar source:jar install 
cp -f target/query-bin.jar ./query.jar
