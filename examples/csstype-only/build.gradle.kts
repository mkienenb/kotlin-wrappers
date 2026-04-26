plugins {
    id("examplesbuild.kotlin-conventions")
}

kotlin {
    js {
        useEsModules()
    }
}

dependencies {
    jsMainImplementation(kotlinWrappers.js)
    jsMainImplementation(kotlinWrappers.react)
    jsMainImplementation(kotlinWrappers.reactDom)
    jsMainImplementation(kotlinWrappers.emotion.react)
}
