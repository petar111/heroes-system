@echo off
for /d /r %%d in (..\..\code\*) do (
    echo Processing subdirectory: "%%d"
    if exist "%%d\mvnw" (
        echo --------------------------------------------
        echo Maven Wrapper project: %%d
        echo Running mvnw clean package -DskipTests
        echo --------------------------------------------
        pushd "%%d"
        call mvnw clean package -DskipTests
        popd
        echo.
    )

    if exist "%%d\gradlew" (
        echo --------------------------------------------
        echo Gradle Wrapper project: %%d
        echo Running gradlew clean build -x test
        echo --------------------------------------------
        pushd "%%d"
        call gradlew clean build -x test
        popd
        echo.
    )

    
)