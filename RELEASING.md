# How to deploy SNAPSHOT to clojars?

```
lein deploy clojars
```

# How to deploy release to clojars?

First configure GPG to use tty properly on MacOS
```
GPG_TTY=`tty`
```
```
GPG_TTY=$(tty)
```
Then do the release
```
lein release :patch
```
