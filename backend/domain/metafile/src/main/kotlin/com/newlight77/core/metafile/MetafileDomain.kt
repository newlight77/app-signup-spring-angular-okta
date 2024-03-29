package com.newlight77.core.metafile

import java.time.Instant

enum class Representation {
    PORTRAIT,
    CV,
    CV_LINKEDIN,
    CV_MISSION,
    KBIS,
    RIB,
    RC,
    URSSAF,
    FISCAL
}

class MetafileDomain(val username: String,
                 val filename: String,
                 val representation: Representation,
                 var contentType: String? = null,
                 var size: Long? = null,
                 var creationDate: Instant? = Instant.now())