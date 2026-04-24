rootProject.name = "mui-7"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            val kotlinVersion = extra.properties["kotlin.version"] as String?
            if (kotlinVersion != null) {
                version("kotlin", kotlinVersion)
            }
        }

        create("kotlinWrappers") {
            val wrappersVersion = extra["kotlin-wrappers.version"] as String
            from("org.jetbrains.kotlin-wrappers:kotlin-wrappers-catalog:$wrappersVersion")
        }
    }
}

includeBuild("../build-logic")
includeBuild("../")

include("proof")
