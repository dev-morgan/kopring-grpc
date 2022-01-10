import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")

    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":proto"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("net.devh:grpc-client-spring-boot-starter:2.13.1.RELEASE")
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
