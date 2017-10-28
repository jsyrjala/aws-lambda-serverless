# aws-lambda-serverless

[![Build Status](https://travis-ci.org/jsyrjala/aws-lambda-serverless.svg?branch=master)](https://travis-ci.org/jsyrjala/aws-lambda-serverless)

A [Leiningen](https://leiningen.org/) template for creating a [AWS Lambda](https://aws.amazon.com/lambda/) 
that gets deployed with [Serverless](https://serverless.com/).

## Functionality 
`aws-lambda-serverless` template creates a Clojure project that is suitable for creating 
an AWS Lambda function with a Serverless template for deploying the AWS Lambda function 
easily. 

The template sets up [Logback](https://logback.qos.ch/) based logging via 
[org.clojure/tools.logging](https://github.com/clojure/tools.logging).

The logs are stored to [Amazon CloudWatch](https://aws.amazon.com/cloudwatch) by 
default with 30 days retention.

## Usage

This template is not yet published to [Clojars](https://clojars.org)

For now use following to create projects

```
git clone git@github.com:jsyrjala/aws-lambda-serverless.git
cd aws-lambda-serverless
lein jar
cd target
lein new aws-lambda-serverless my-project
cd my-project
```

## License

Copyright © 2017 Juha Syrjälä

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
