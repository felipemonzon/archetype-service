rootProject.name = "archetype"


pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    this.repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

include(
    ":mappie-api",
    ":compiler-plugin",
    ":gradle-plugin",
    ":maven-plugin",
)