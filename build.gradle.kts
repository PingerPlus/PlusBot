plugins {
    id("java")
}

allprojects {
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("net.dv8tion:JDA:5.0.0-beta.3")
        implementation("org.apache.logging.log4j:log4j-api:2.19.0")
        implementation("org.apache.logging.log4j:log4j-core:2.19.0")
        implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.19.0")
        implementation("org.projectlombok:lombok:1.18.34")
        annotationProcessor("org.projectlombok:lombok:1.18.34")
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }
}

group = "io.pinger.plusbot"
version = "1.0.0"
