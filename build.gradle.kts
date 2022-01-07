import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.2" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10" apply false
    kotlin("plugin.jpa") version "1.6.10" apply false
}

java.sourceCompatibility = JavaVersion.VERSION_11

allprojects {
    group = "com.sample"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            /**
             * -X options
             * jsr305=strict :: to have null-safety taken in account in Kotlin types inferred from libraries APIs
             * opt-in=kotlin.RequiresOptIn :: experimental features enabled by default
             */
            freeCompilerArgs = listOf("-Xjsr305=strict", "-Xopt-in=kotlin.RequiresOptIn")
            jvmTarget = JavaVersion.VERSION_11.majorVersion
        }
    }
}

subprojects {
    repositories {
        mavenCentral()
    }

    apply {
        plugin("idea")
        plugin("kotlin")
        plugin("kotlin-kapt")
        plugin("io.spring.dependency-management")
    }

    dependencies {
        implementation(kotlin("stdlib"))
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        implementation("io.github.microutils:kotlin-logging:2.1.21")
        implementation("ch.qos.logback:logback-classic:1.2.10")
        runtimeOnly("com.h2database:h2:2.0.206")
    }
}
