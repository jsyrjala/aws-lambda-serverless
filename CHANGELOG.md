# Change Log
All notable changes to this project will be documented in this file. This change log follows 
the conventions of [keepachangelog.com](http://keepachangelog.com/).

## [Unreleased]
### Added
- Create new files for generated project
  - CHANGELOG.md
  - a dummy failing unit test
  - .gitignore
  - .hgiginore
- Added `lambda-util/lambda-json-handler` to handle JSON formatted requests and responses

### Changed
- Update dependencies
  - clojure 1.10.1
  - slf4j 1.7.28
  - tools.logging 0.5.0
- `lambda-util/lambda-handler` renamed to `lambda-raw-handler`

## [1.1.0] - 2019-06-04
### Changed
- `dev.yml` and `prod.yml` are moved to the root
- Updated dependencies: clojure 1.10.0, slf4j 1.7.26

## [1.0.1] - 2018-11-28
### Changed
- Enable function versioning
- Set region from `AWS_DEFAULT_REGION` environment variable. Defaults to 'eu-central-1'

## [1.0.0] - 2018-06-01
### Changed
- Documentation updates
- `--stack` parameter replaced with Serverless' `--stage` parameter

## 0.1.0 - 2018-02-02
### Added
- Initial release

[Unreleased]: https://github.com/jsyrjala/aws-lambda-serverless/compare/1.1.0...HEAD
[1.1.0]: https://github.com/jsyrjala/aws-lambda-serverless/compare/1.0.1...1.1.0
[1.0.1]: https://github.com/jsyrjala/aws-lambda-serverless/compare/1.0.0...1.0.1
[1.0.0]: https://github.com/jsyrjala/aws-lambda-serverless/compare/0.1.0...1.0.0
