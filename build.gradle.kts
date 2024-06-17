import java.io.FileFilter

plugins {
    java
    kotlin("jvm") version "2.0.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

sourceSets {
    main {
        kotlin {
            srcDirs(
                *file("LeetCode").listFiles(FileFilter { it.isDirectory }) ?: emptyArray()
            )
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("script-runtime"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

// workaround IDEA can't detect code change before build
tasks.getByName("compileKotlin")
    .dependsOn(tasks.getByName("clean"))
