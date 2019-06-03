# Change Log
All notable changes to this project will be documented in this file. This change log follows 
the conventions of [keepachangelog.com](http://keepachangelog.com/).

## [Unreleased]
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

[Unreleased]: https://github.com/jsyrjala/aws-lambda-serverless/compare/1.0.1...HEAD
[1.0.1]: https://github.com/jsyrjala/aws-lambda-serverless/compare/1.0.0...1.0.1
[1.0.0]: https://github.com/jsyrjala/aws-lambda-serverless/compare/0.1.0...1.0.0
