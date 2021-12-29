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

val grpcVersion = "1.43.1"
val grpcKotlinVersion = "1.2.0"
val protobufKotlinVersion = "3.19.1"

dependencies {
    implementation("com.google.protobuf:protobuf-kotlin:$protobufKotlinVersion")
    implementation("io.grpc:grpc-netty-shaded:$grpcVersion")
    implementation("io.grpc:grpc-protobuf:$grpcVersion")
    implementation("io.grpc:grpc-stub:$grpcVersion")
    implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")

    compileOnly("org.apache.tomcat:annotations-api:6.0.53")

    testImplementation("io.kotest:kotest-runner-junit5:5.0.2")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protobufKotlinVersion" // compiler for .proto files.
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion" // The protoc plugin for gRPC Java
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:$grpcKotlinVersion:jdk7@jar"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach { task ->
            task.plugins {
                id("grpc")
                id("grpckt")
            }
            task.builtins {
                id("kotlin")
            }
        }
    }
}

idea {
    module {
        generatedSourceDirs.add(file("${protobuf.protobuf.generatedFilesBaseDir}/main/grpc"))
    }
}
