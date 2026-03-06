plugins {
    id("examplesbuild.kotlin-conventions")
}

kotlin {
    js(IR) {
        nodejs { }
        compilations.named("test") {
            packageJson { customField("mocha", mapOf("require" to arrayOf(
                // configures coroutines default dispatcher to NodeDispatcher
                "../../../../../../tanstack-react-router-nodejs-test/mocha-support/append-jsdom-to-user-agent-name.js",
                // installs browser-like environment used by react-testing-library to run tests
                "global-jsdom/register"
            )))}
        }
    }
}

dependencies {
    jsMainImplementation(kotlinWrappers.js)
    jsMainImplementation(kotlinWrappers.react)
    jsMainImplementation(kotlinWrappers.reactUse)
    jsMainImplementation(kotlinWrappers.reactDom)
    jsMainImplementation(kotlinWrappers.tanstack.reactRouter)
    jsTestImplementation(kotlin("test"))
    jsTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")
    jsTestImplementation(kotlinWrappers.testingLibraryReact)
    // Why do I have to import this?
    jsTestImplementation(npm("@testing-library/react", "latest"))
    jsTestImplementation(kotlinWrappers.testingLibraryDom)
    jsTestImplementation(kotlinWrappers.testingLibraryUserEvent)
    jsTestImplementation(npm("global-jsdom", "latest"))
}

tasks.named<org.jetbrains.kotlin.gradle.targets.js.testing.KotlinJsTest>("jsNodeTest") {
    // required to make tanstack react router fire load events in mocha/node/jsdom tests
    environment("NODE_ENV", "test")
}
