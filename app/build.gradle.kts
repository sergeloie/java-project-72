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
    id("io.spring.dependency-management") version "1.1.4"


}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core")

    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("org.apache.commons:commons-text")
    implementation("gg.jte:jte")
    implementation("org.slf4j:slf4j-simple")

    implementation("io.javalin:javalin")
    implementation("io.javalin:javalin-bundle")
    implementation("io.javalin:javalin-rendering")

    implementation("com.zaxxer:HikariCP")
    implementation("com.h2database:h2")




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

dependencyManagement {
    dependencies {
        dependencySet("io.javalin:5.6.3") {
            entry("javalin")
            entry("javalin-bundle")
            entry("javalin-rendering")
        }
        dependency("gg.jte:jte:3.1.6")
        dependency("org.assertj:assertj-core:3.25.1")
        dependency("com.fasterxml.jackson.core:jackson-databind:2.16.1")
        dependency("org.apache.commons:commons-text:1.11.0")
        dependency("org.slf4j:slf4j-simple:2.1.0-alpha1")
        dependency("com.zaxxer:HikariCP:5.1.0")
        dependency("com.h2database:h2:2.2.224")


    }
}
