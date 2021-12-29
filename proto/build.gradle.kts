import com.google.protobuf.gradle.*

buildscript {
    repositories {
        maven {
            setUrl("https://plugins.gradle.org/m2/")
            mavenCentral()
        }
    }
    dependencies {
        classpath("com.google.protobuf:protobuf-gradle-plugin:0.8.18")
    }
}

plugins {
    id("com.google.protobuf") version "0.8.18"
}

dependencies {
    implementation("io.grpc:grpc-netty-shaded:1.43.1")
    implementation("io.grpc:grpc-protobuf:1.43.1")
    implementation("io.grpc:grpc-stub:1.43.1")

    compileOnly("org.apache.tomcat:annotations-api:6.0.53")

    testImplementation("io.kotest:kotest-runner-junit5:5.0.2")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.19.1"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.42.1"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach { task ->
            task.plugins {
                id("grpc")
            }
        }
    }
}

idea {
    module {
        generatedSourceDirs.add(file("${protobuf.protobuf.generatedFilesBaseDir}/main/grpc"))
    }
}
