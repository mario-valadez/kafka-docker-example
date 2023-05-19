
rootProject.name = "kafka-docker-example"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://packages.confluent.io/maven/")
        maven(url = "https://jitpack.io")
    }
}