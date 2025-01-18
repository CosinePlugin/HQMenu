plugins {
    kotlin("jvm") version "1.7.21"
}

group = "kr.cosine.menu"
version = "1.1.1"

repositories {
    maven("https://maven.hqservice.kr/repository/maven-public")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    compileOnly("org.spigotmc", "spigot-api", "1.17.1-R0.1-SNAPSHOT")
    compileOnly("me.clip", "placeholderapi", "2.11.6")

    compileOnly("kr.hqservice", "hqframework-bukkit-core", "1.0.2-SNAPSHOT") {
        exclude("io.papermc.paper")
        exclude("org.spigotmc")
    }
    compileOnly("kr.hqservice", "hqframework-bukkit-command", "1.0.2-SNAPSHOT") {
        exclude("io.papermc.paper")
        exclude("org.spigotmc")
    }
    compileOnly("kr.hqservice", "hqframework-bukkit-inventory", "1.0.2-SNAPSHOT") {
        exclude("io.papermc.paper")
        exclude("org.spigotmc")
    }
    compileOnly("kr.hqservice", "hqframework-bukkit-nms", "1.0.2-SNAPSHOT") {
        exclude("io.papermc.paper")
        exclude("org.spigotmc")
    }

    testImplementation(kotlin("test"))
    testImplementation(kotlin("reflect"))
}

tasks {
    test {
        useJUnitPlatform()
    }
    jar {
        archiveFileName.set("${rootProject.name}-${rootProject.version}.jar")
    }
}