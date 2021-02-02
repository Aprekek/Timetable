rootProject.name = "timetable"

include(":app")

include(":features:main")
include(":features:lessoncreate")
include(":features:timetable")
include(":features:homework")
include(":features:dictionary")
include(":features:settings")

include(":libraries:navigation")
include(":libraries:themes")
include(":libraries:core")
include(":libraries:database")

include(":libraries:flowbinding")

include(
    ":shared:lesson:domain",
    ":shared:lesson:data"
)

include(
    ":shared:timeutils:domain",
    ":shared:timeutils:ui"
)

include(
    ":shared:settings:domain",
    ":shared:settings:data"
)

include(
    ":shared:notifications:domain",
    ":shared:notifications:ui"
)

include(
    ":shared:backup:domain",
    ":shared:backup:data"
)