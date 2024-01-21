plugins {
    application
    idea
    jacoco
    checkstyle
    id("io.freefair.lombok") version "8.4"
    id("se.patrikerdes.use-latest-versions") version "0.2.18"
    id("com.github.ben-manes.versions") version "0.50.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("org.sonarqube") version "4.4.1.3373"


}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(20))
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(20)
}


dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    reports {
        xml.required = true
        html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
    }
}

sonar {
    properties {
        property("sonar.projectKey", "sergeloie_java-project-72")
        property("sonar.organization", "sergeloie")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

application {
    mainClass.set("hexlet.code.App")
}
