# aws-lambda-serverless

[![Build Status](https://travis-ci.org/jsyrjala/aws-lambda-serverless.svg?branch=master)](https://travis-ci.org/jsyrjala/aws-lambda-serverless) [![Clojars Project](https://img.shields.io/clojars/v/aws-lambda-serverless/lein-template.svg)](https://clojars.org/aws-lambda-serverless/lein-template)

A [Leiningen](https://leiningen.org/) template for creating a [AWS Lambda](https://aws.amazon.com/lambda/) 
that gets deployed with [Serverless](https://serverless.com/).

The template is published to [Clojars](https://clojars.org/aws-lambda-serverless/lein-template).


## Functionality 

`aws-lambda-serverless` template creates a Clojure project that is suitable for creating 
an AWS Lambda function with a Serverless template for deploying the AWS Lambda function 
easily. 

The template sets up [Logback](https://logback.qos.ch/) based logging via 
[org.clojure/tools.logging](https://github.com/clojure/tools.logging).

The logs are stored to [Amazon CloudWatch](https://aws.amazon.com/cloudwatch) by 
default with 30 days retention.

## Usage

```
lein new aws-lambda-serverless my-project
```

## License

Copyright © 2017 Juha Syrjälä

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

# TODO 
- Use Serverless' stage instead of --stack parameter
