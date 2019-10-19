#!/usr/bin/env bash -e
# - Create the template
# - Create a project based on the template
# - Build the project
# - Run uberjar

echo lein jar
lein jar

cd target

echo
echo lein new aws-lambda-serverless my-project
lein new aws-lambda-serverless my-project

cd my-project

echo
echo "lein test (tests will fail)"
lein test

echo
echo lein uberjar
lein uberjar

echo
echo java -jar target/my-project-0.1.0-SNAPSHOT-standalone.jar func1
java -jar target/my-project-0.1.0-SNAPSHOT-standalone.jar func1

echo
echo java -jar target/my-project-0.1.0-SNAPSHOT-standalone.jar func2
java -jar target/my-project-0.1.0-SNAPSHOT-standalone.jar func2

echo Test done
echo See target/my-project
