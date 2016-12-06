@echo off
mvn -U -up clean cobertura:cobertura javadoc:jar source:jar install
xcopy /q /y target\query-bin.jar .\query.jar