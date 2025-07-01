pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        google ()
        maven { url = uri("https://realm.io/packages")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Riiwayatmingguan"
include(":app")

dependencyResolutionManagement {
    repositories {
        google()
        maven { url = uri("https://jitpack.io") } // benar untuk .kts
    }
}
}
