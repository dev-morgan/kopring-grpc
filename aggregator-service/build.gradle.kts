import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")

    kotlin("plugin.spring")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    tasks {
        getByName<BootJar>("bootJar") {
            enabled = false
        }

        getByName<Jar>("jar") {
            enabled = true
        }

        test {
            useJUnitPlatform()
        }
    }
}
