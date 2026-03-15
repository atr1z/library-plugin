# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

A Gradle plugin (`mx.com.atriz.library`) that provides standardized Android library module configuration. When applied, it automatically configures `com.android.library` and `kotlin-android` plugins with consistent SDK versions, Java 21 compatibility, View Binding, and BuildConfig generation.

## Build Commands

```bash
./gradlew build      # Build the plugin
./gradlew publish    # Publish to Maven Central (requires signing keys and Sonatype credentials)
```

## Architecture

Single-module Gradle plugin exposing two plugin IDs:

- **`mx.com.atriz.library`** (`Library.kt`) — Base plugin. Applies `com.android.library` + `kotlin-android`, sets SDK versions, compileOptions, buildConfig. No UI features.
- **`mx.com.atriz.library.ui`** (`LibraryUi.kt`) — Extends the base plugin and enables View Binding. Use for modules with layouts/UI.
- `Version.kt` — Central version constants (Compile SDK 35, Target SDK 35, Min SDK 26, Java 21).

## Key Versions

| Component | Version |
|-----------|---------|
| Kotlin | 2.0.20 |
| Java/JDK | 21 |
| Gradle | 8.8 |
| Android Gradle Plugin | 8.8.1 |

## Publishing & Versioning

Published to Maven Central via `com.vanniktech.maven.publish`. Version is derived automatically from git tags (`git describe --tags --abbrev=0`, stripping the `v` prefix). Falls back to `0.0.0-SNAPSHOT` if no tags exist. CI publishes only on version tags (`v*`). Signing is conditional — only runs when `SIGNING_KEY` env var is present.

## No Tests

There is no test infrastructure in this project currently.
