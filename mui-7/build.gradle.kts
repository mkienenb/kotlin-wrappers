plugins {
    id("wrappersbuild.kotlin-conventions")
    `maven-publish`
}

group = "org.jetbrains.kotlin-wrappers.experimental"
version = "7.3.10-pre.1"

dependencies {
    jsMainApi(kotlinWrappers.js)
    jsMainApi(kotlinWrappers.react)
    jsMainApi(kotlinWrappers.reactDom)
    jsMainApi(kotlinWrappers.csstype)

    webMainApi(npm("@emotion/react", "11.14.0"))
    webMainApi(npm("@emotion/styled", "11.14.1"))
    webMainApi(npm("@mui/icons-material", "7.3.10"))
    webMainApi(npm("@mui/material", "7.3.10"))
    webMainApi(npm("@mui/system", "7.3.10"))
    webMainApi(npm("react", "19.2.5"))
    webMainApi(npm("react-dom", "19.2.5"))
}

publishing {
    repositories {
        maven {
            name = "localBuildRepo"
            url = layout.buildDirectory.dir("repo").get().asFile.toURI()
        }
    }
}
