# tanstack-react-router-nodejs-test

## Example to demonstrate how to use react-testing-library to test react applications using tanstack-react-router with the jsNodeTest task

* Environment
  - node
  - mocha
  - global-jsdom

  *  `react-testing-library` (RTL) expects to run in a `node`/`jsdom` environment to provide a browser-like environment.
     `Mocha` does not provide `jsdom` by default.  Change `build.gradle.kts` to modify the test compilation’s generated
     `package.json` so that `Mocha` automatically loads two setup scripts before tests run.
     - `global-jsdom/register` sets up a browser-like environment.
  * `kotlinx.coroutines` will pick the wrong default dispatcher in a `node`/`jsdom` environment unless the user-agent contains `jsdom`
     - `append-jsdom-to-user-agent-name.js` adds ` jsdom` to the end of the default node user-agent string.

```kotlin
        compilations.named("test") {
            packageJson { customField("mocha", mapOf("require" to arrayOf(
                            "../../../../tools/mocha-support/append-jsdom-to-user-agent-name.js",
                            "global-jsdom/register"
            )))}
        }
```


### Run

```shell
cd ..
./gradlew tanstack-react-router-nodejs-test:jsNodeTest
```


## TODO

.version search for npm defs
