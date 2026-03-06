# tanstack-react-router-nodejs-test

* Example to demonstrate how to use react-testing-library to test react applications using tanstack-react-router with the jsNodeTest task

* Environment
  - node
  - mocha
  - global-jsdom

* Libraries
  - react
  - coroutines
  - kotlin-test
  - react-testing-library
  - tanstack-react-router

## Issues
  * `react-testing-library` (RTL) expects to run in a `node`/`jsdom` environment to provide a browser-like environment.
     `Mocha` does not provide `jsdom` by default.
  * `kotlinx.coroutines` will pick the wrong default dispatcher in a `node`/`jsdom` environment unless the user-agent
    contains `jsdom`.
  * `Tanstack React Router` requires `NODE_ENV=test` to run in a node/jsdom environment.  By default, no `NODE_ENV` mode is set.

### Work-arounds
  1. Change `build.gradle.kts` to modify the test compilation’s generated `package.json` so that `Mocha` requires `global-jsdom`
  2. Change `build.gradle.kts` to modify the test compilation’s generated `package.json` so that `Mocha` requires
     `mocha-support/append-jsdom-to-user-agent-name.js`
  3. Change `build.gradle.kts` to set `NODE_ENV=test` when running jsNodeTest


## Run

```shell
cd ..
./gradlew tanstack-react-router-nodejs-test:jsNodeTest
```

