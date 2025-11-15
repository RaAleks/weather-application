plugins {
    java
    id("io.spring.dependency-management") version "1.1.7"
}

group = "org.openweathermap"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}


tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {

    implementation("org.springframework:spring-context:6.1.0")
    implementation("org.springframework:spring-core:6.1.0")
    implementation("org.springframework:spring-web:6.1.0")
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.2")


    implementation("org.slf4j:slf4j-api:2.0.13")
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")

    testImplementation("org.springframework.boot:spring-boot-starter-test:3.3.2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:junit-jupiter:1.20.3")
    testImplementation("org.testcontainers:postgresql:1.20.3")

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.mockito:mockito-core:5.5.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.5.0")

}
