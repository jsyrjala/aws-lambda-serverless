# {{name}}

AWS Lambda function for XXX

* https://aws.amazon.com/lambda/

## Usage

### Configuration

Lambda function is configured via environment variables TODO


## Development

TODO

### Deploying

TODO

### Testing

You can test function locally:

```
lein run
```

### Serverless

AWS Lambdas are managed with [Serverless](https://serverless.com/) framework.

Creating stack
```
npm i -g serverless
lein uberjar
cd serverless
serverless deploy -v --stack dev
```

TODO

# TODO 
- Add log rotation for CloudWatch log to serverless.yml
- Use Serverless' stage instead of --stack parameter