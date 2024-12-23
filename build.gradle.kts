plugins {
    kotlin("multiplatform") version "1.9.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"
val kotestversion = "5.6.2"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(BOTH) {
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
        }
    }
    // Native 타겟 설정 (필요에 따라 유지)
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("io.kotest:kotest-framework-api:$kotestversion")
                implementation("io.kotest:kotest-assertions-core:$kotestversion")
                implementation("io.kotest:kotest-property:$kotestversion")
                // kotlin-reflect 의존성 추가
                implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.10")
            }
        }
        val jvmMain by getting
        val jvmTest by getting {
            dependencies {
                implementation("io.kotest:kotest-runner-junit5:$kotestversion")
                // JVM 테스트 소스셋에 kotlin-reflect 의존성 추가 (선택 사항)
                implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.10")
            }
        }
        val jsMain by getting
        val jsTest by getting
        val nativeMain by getting
        val nativeTest by getting
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}