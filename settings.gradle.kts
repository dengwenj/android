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
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MyApplication"
include(":app")
include(":simple_control")
include(":pm_activity")
include(":middle_control")
include(":data_storage")
include(":advanced_control")
include(":boradcast")
include(":broadcast2")
include(":broadcast3")
include(":broadcastDemo1")
include(":broadcastdemo2")
