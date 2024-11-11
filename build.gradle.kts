plugins {
    kotlin("jvm") version "1.7.21"
    id("maven-publish")
}

group = "kr.cosine.mczone.menu"
version = "1.0.0"

repositories {
    maven("https://maven.hqservice.kr/repository/maven-public")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    mavenLocal()
}

dependencies {
    compileOnly("kr.cosine.mczone.core", "MCZoneCore", "1.1.0")

    testImplementation(kotlin("test"))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = rootProject.group.toString()
            artifactId = rootProject.name
            version = rootProject.version.toString()

            from(components["java"])
        }
    }
}

tasks {
    test {
        useJUnitPlatform()
    }
    jar {
        archiveFileName.set("${rootProject.name}-${rootProject.version}.jar")
        destinationDirectory.set(file("D:\\서버\\1.20.1 - 마크존\\plugins"))
    }
}