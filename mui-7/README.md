# mui-7

Standalone Kotlin/JS wrappers for the scoped MUI 7 surface implemented in this directory.

## What this publishes

- Group: `org.jetbrains.kotlin-wrappers.experimental`
- Artifact: `mui-7`
- JS artifact: `mui-7-js`
- Version: `7.3.10-pre.1`

The wrapper exposes these Kotlin packages:

- `mui.types`
- `mui.system`
- `mui.material`
- `mui.material.styles`
- `mui.icons.material`

## Build

From the repository root:

```bash
./gradlew -p mui-7 compileKotlinJs :proof:compileKotlinJs
```

This compiles:

- the standalone `mui-7` library
- the `proof` module that exercises the intended consumer API

## Publish To Maven Local

From the repository root:

```bash
./gradlew -p mui-7 publishToMavenLocal
```

That installs the published artifacts into your local Maven cache (`~/.m2/repository`).

If you want a repo on disk without touching Maven Local, publish to the project-local repo instead:

```bash
./gradlew -p mui-7 publishAllPublicationsToLocalBuildRepoRepository
```

That writes artifacts to:

```text
mui-7/build/repo
```

## Consume In Another Repo

Add `mavenLocal()` to the consuming repo so Gradle can resolve the local publication:

```kotlin
repositories {
    mavenLocal()
    mavenCentral()
}
```

Then depend on the wrapper:

```kotlin
dependencies {
    implementation("org.jetbrains.kotlin-wrappers.experimental:mui-7:7.3.10-pre.1")
}
```

For a version catalog, the equivalent entry is:

```toml
[libraries]
mui7 = { module = "org.jetbrains.kotlin-wrappers.experimental:mui-7", version = "7.3.10-pre.1" }
```

```kotlin
dependencies {
    implementation(libs.mui7)
}
```

## Usage

After adding the dependency, import the wrapper packages directly:

```kotlin
import mui.icons.material.Person
import mui.material.Button
import mui.material.styles.ThemeProvider
import mui.material.styles.createTheme
import mui.system.Box
import mui.system.sx
```

Example:

```kotlin
import js.objects.unsafeJso
import mui.material.Button
import mui.material.ButtonVariant
import mui.material.styles.ThemeProvider
import mui.material.styles.createTheme
import mui.system.Box
import mui.system.sx
import react.FC
import react.Props
import web.cssom.px

private val theme = createTheme(
    unsafeJso {
        palette = unsafeJso<dynamic> {
            primary = unsafeJso<dynamic> {
                main = "#0f766e"
            }
        }
    }
)

val Screen = FC<Props> {
    ThemeProvider {
        theme = theme

        Box {
            sx {
                padding = 16.px
            }

            Button {
                variant = ButtonVariant.outlined
                +"Hello from mui-7"
            }
        }
    }
}
```

## Notes For The Consumer Repo

- `mui.material.styles` is included directly, so you do not need a local `@mui/material/styles` shim.
- The scope is intentionally narrow and only covers the APIs implemented in this module.
- The underlying npm MUI 7 packages are declared by the published Gradle metadata, so a normal Kotlin/JS Gradle consumer should pick them up automatically.
