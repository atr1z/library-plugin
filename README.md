# Library Plugin

A Gradle plugin that provides standardized configuration for Android library modules. Eliminates boilerplate by applying consistent SDK versions, build features, and compiler settings across all your library modules.

## Setup

Add the plugin to your project's `settings.gradle.kts`:

```kotlin
pluginManagement {
    plugins {
        id("mx.com.atriz.library") version "<version>"
        id("mx.com.atriz.library.ui") version "<version>"
    }
}
```

## Usage

### Base library (`mx.com.atriz.library`)

For modules that contain only logic, data, or domain code (no UI):

```kotlin
plugins {
    id("mx.com.atriz.library")
}
```

### UI library (`mx.com.atriz.library.ui`)

For modules that contain layouts, views, or any UI-related code:

```kotlin
plugins {
    id("mx.com.atriz.library.ui")
}
```

This applies the base library plugin automatically, so you don't need both.

## What it configures

### Base plugin (`mx.com.atriz.library`)

| Setting | Value |
|---------|-------|
| `com.android.library` plugin | Applied |
| `kotlin-android` plugin | Applied |
| Compile SDK | 35 |
| Min SDK | 26 |
| Java source/target compatibility | 21 |
| BuildConfig generation | Enabled |
| ProGuard minification (release) | Disabled |
| Unit test coverage (release) | Enabled |
| Test instrumentation runner | `AndroidJUnitRunner` |

### UI plugin (`mx.com.atriz.library.ui`)

Everything from the base plugin, plus:

| Setting | Value |
|---------|-------|
| View Binding | Enabled |

## Which plugin should I use?

| Module type | Plugin |
|-------------|--------|
| Networking, repositories, use cases | `mx.com.atriz.library` |
| Data models, utilities, domain logic | `mx.com.atriz.library` |
| UI components, screens, custom views | `mx.com.atriz.library.ui` |
| Modules with XML layouts | `mx.com.atriz.library.ui` |

## Versioning

Plugin versions are derived from git tags. Tag a release with `v` prefix (e.g., `v1.0.0`) and CI will publish it automatically.

## License

```
Copyright 2024 Atriz

Licensed under the Apache License, Version 2.0
```
