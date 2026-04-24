plugins {
    id("wrappersbuild.kotlin-conventions")
}

dependencies {
    jsMainImplementation(project(":"))
    jsMainImplementation(kotlinWrappers.js)
    jsMainImplementation(kotlinWrappers.react)
    jsMainImplementation(kotlinWrappers.reactDom)
    jsMainImplementation(kotlinWrappers.csstype)

    webMainImplementation(npm("@emotion/react", "^11.14.0"))
    webMainImplementation(npm("@emotion/styled", "^11.14.1"))
    webMainImplementation(npm("react", "^19.2.5"))
    webMainImplementation(npm("react-dom", "^19.2.5"))
}
