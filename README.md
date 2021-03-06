# Cafe Order Project
with Micro Service Architecture

### 개발 스펙
* Spring Boot v.1.5.10
* Kafka v.2.1.0

### Micro Service
* MSA-Service-Order : 커피 주문을 받는 서비스
* MSA-Service-Member : 회원 확인을 하는 서비스
* MSA-Service-Status : 커피 주문처리를 받는 서비스

### MSA-Cafe

build.gradle

```
apply plugin: 'java-library'

repositories {
    jcenter()
}

dependencies {
    api 'org.apache.commons:commons-math3:3.6.1'
    implementation 'com.google.guava:guava:23.0'
    testImplementation 'junit:junit:4.12'
}

buildscript {
    ext {
        springBootVersion = '1.5.10.RELEASE'
    }
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
        classpath('io.spring.gradle:dependency-management-plugin:1.0.0.RELEASE')
    }
}


subprojects {
    group = 'me.sml'
    version = '0.0.1-SNAPSHOT'
    apply plugin: 'java'
    apply plugin: 'org.springframework.boot'
    apply plugin: 'io.spring.dependency-management'

    sourceCompatibility = '1.8'

    repositories {
        mavenCentral()
        maven { url 'https://repo.spring.io/milestone' }
    }

    task initSourceFolders{
        sourceSets*.java.srcDirs*.each{
            if ( !it.exists()) it.mkdir()
        }
        sourceSets*.resources.srcDirs*.each{
            if ( !it.exists()) it.mkdir()
        }
    }
    dependencies {
        compile('org.projectlombok:lombok:1.18.4')
        compile('org.projectlombok:lombok-utils:1.18.4')
        compile('junit:junit:4.12')
        testCompile("org.springframework.boot:spring-boot-starter-test")
    }
}
```

settings.gradle
```
rootProject.name = 'cafe'

include 'MSA-Service-Order', 'MSA-Service-Member', 'MSA-Service-Status'
```

### MSA-Service-Member

h2 sql 파일 실행 방법

* classpath(resources/)에 schema.sql, data.sql을 생성한다. 여기서 schema.sql은 DDL을 의미하고 data.sql은 DML을 의미한다.
* 참조) https://dodo4513.github.io/2018/05/05/spring_h2_mybatis/