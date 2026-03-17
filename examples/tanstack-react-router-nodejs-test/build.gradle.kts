import org.jetbrains.kotlin.gradle.targets.js.testing.KotlinJsTest
import kotlin.text.toBoolean

plugins {
    id("examplesbuild.kotlin-conventions")
    id("com.google.devtools.ksp") version "2.3.6"
    id("io.kotest") version "6.1.7"
}

kotlin {
    js {
        nodejs()
        compilations.named("test") {
            packageJson {
                customField(
                    "mocha", mapOf(
                        "require" to arrayOf(
                            // configures coroutines default dispatcher to NodeDispatcher -- needed if using DefaultDispatcher instead of kotlinx-coroutines-test
//                            projectDir.resolve("mocha-support/force-correct-default-coroutines-dispatcher.js").absolutePath,
                            // installs browser-like environment used by react-testing-library to run tests
                            "global-jsdom/register"
                        )
                    )
                )
            }
        }
    }
}

dependencies {
    jsMainImplementation(kotlinWrappers.js)
    jsMainImplementation(kotlinWrappers.react)
    jsMainImplementation(kotlinWrappers.reactUse)
    jsMainImplementation(kotlinWrappers.reactDom)
    jsMainImplementation(kotlinWrappers.tanstack.reactRouter)
    jsTestImplementation(libs.kotlin.test)
    jsTestImplementation(libs.coroutines.test)
    jsTestImplementation(kotlinWrappers.testingLibraryUserEvent)
    jsTestImplementation(kotlinWrappers.testingLibraryReact)
    jsTestImplementation(kotlinWrappers.testingLibraryDom)
    jsTestImplementation(npm("global-jsdom", "28.0.0"))
    jsTestImplementation("io.kotest:kotest-framework-engine-js:6.1.7")
}

tasks.named<KotlinJsTest>("jsNodeTest") {
    // required to make tanstack react router fire load events in mocha/node/jsdom tests
    environment("NODE_ENV", "test")

    doFirst {
        var currentClass: Class<*>? = filter.javaClass
        var field: java.lang.reflect.Field? = null

        // Search through the class hierarchy for the commandLineIncludeTestNames field
        while (currentClass != null && field == null) {
            try {
                field = currentClass.getDeclaredField("commandLineIncludeTestNames")
            } catch (_: NoSuchFieldException) {
                currentClass = currentClass.superclass
            }
        }

        if (field != null) {
            field.isAccessible = true
            val patterns = field.get(filter) as? MutableSet<*>
            // CRITICAL: Clear the commandLineIncludeTestNames so Gradle doesn't validate
            patterns?.clear()
            println("Cleared command line test patterns to prevent Gradle validation")
        }
    }

}
