# aws-lambda-serverless

A Leiningen template for creating a [AWS Lambda](https://aws.amazon.com/lambda/) 
that gets deployed with [Serverless](https://serverless.com/).

## Usage

This template is not yet published to [clojars](https://clojars.org)

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

Copyright © 2017 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
