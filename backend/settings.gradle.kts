rootProject.name = "core"

include(":application")

include(":domain")
include(":domain:authentication")
include(":domain:metafile")
include(":domain:note")

include(":infrastructure")
include(":infrastructure:encryption")
include(":infrastructure:login")
include(":infrastructure:notification")
include(":infrastructure:note")
include(":infrastructure:signup")
include(":infrastructure:storage")

include(":infrastructure:iam-client")
include(":infrastructure:iam-client:iam-client-interface")
include(":infrastructure:iam-client:okta-client")
