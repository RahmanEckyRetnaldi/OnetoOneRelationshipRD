pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()

    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
        maven(url = "https://jitpack.io")
        maven(url = "https://maven.google.com")
        maven {
            isAllowInsecureProtocol = true
            setUrl("http://raw.github.com/saki4510t/libcommon/master/repository/")}
    }
}
rootProject.name = ("OnetoOneRelationshipRD")
include (":app")
