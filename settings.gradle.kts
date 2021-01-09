rootProject.name = "timetable"

include(":app")

include(":features:main")
include(":features:lessoncreate")
include(":features:timetable")
include(":features:homework")
include(":features:dictionary")
include(":features:settings")
include(":features:notifications")

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