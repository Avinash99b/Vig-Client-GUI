plugins {
    id("java")
    application  // Added this
    id ("com.github.johnrengelman.shadow") version "7.1.2"

}


group = "com.avinash"
version = "1.0-SNAPSHOT"
application {
    mainClass = "com.avinash.MainApp" // Update this based on your main class
}

java{
    toolchain{
        languageVersion = JavaLanguageVersion.of(17)
    }
}
repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

}


// Use the Shadow plugin to create a fat JAR
tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveBaseName.set("client-gui")
    archiveClassifier.set("") // No "-all" suffix
    archiveVersion.set("")
    manifest {
        attributes["Main-Class"] = "com.avinash.MainApp" // Change to your main class
    }
    mergeServiceFiles()
}