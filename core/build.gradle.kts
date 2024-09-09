plugins {
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.0"
}

application {
    mainClass = "io.pinger.plusbot.DiscordBot"
}

dependencies {
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("mysql:mysql-connector-java:8.0.33")
}