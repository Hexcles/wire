import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  application
  java
  kotlin("jvm")
  id("com.github.johnrengelman.shadow")
}

val main = "com.squareup.wire.whiteboard.SimpleGrpcClientKt"

application {
  mainClass.set(main)
}

tasks.jar {
  manifest{
    attributes("Main-Class" to main)
  }
}

tasks.withType<JavaCompile>().configureEach {
  sourceCompatibility = JavaVersion.VERSION_11.toString()
  targetCompatibility = JavaVersion.VERSION_11.toString()
}

tasks.withType<KotlinCompile>().configureEach {
  kotlinOptions {
    jvmTarget = "11"
    freeCompilerArgs += "-Xjvm-default=all"
    // https://kotlinlang.org/docs/whatsnew13.html#progressive-mode
    freeCompilerArgs += "-progressive"
  }
}

dependencies {
  implementation(projects.samples.wireGrpcSample.protos)
  implementation(projects.wireGrpcClient)

  runtimeOnly("org.slf4j:slf4j-simple:2.0.13")
}
