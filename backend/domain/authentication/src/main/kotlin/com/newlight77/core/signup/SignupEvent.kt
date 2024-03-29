package com.newlight77.core.signup

import com.newlight77.core.metafile.MetafileDomain
import com.newlight77.core.metafile.Representation

class SignupStatusUpdatedEvent(val username: String, val status: String) {
    fun isUseer() = "USER" === status
}

class PortraitUploadedEvent(val metafile: MetafileDomain) {
    val username = metafile.username
    fun isPortrait() : Boolean {
        return this.metafile.username.isNotBlank()
                && this.metafile.filename.isNotBlank()
                && this.metafile.representation == Representation.PORTRAIT
    }
}

class ResumeUploadedEvent(var metafile: MetafileDomain) {
    val username = metafile.username
    fun isResume() : Boolean {
        return this.metafile.username.isNotBlank()
                && this.metafile.filename.isNotBlank()
                && this.metafile.representation == Representation.CV
    }
}

class ResumeLinkedinUploadedEvent(var metafile: MetafileDomain) {
    val username = metafile.username
    fun isResumeLinkedin() : Boolean {
        return this.metafile.username.isNotBlank()
                && this.metafile.filename.isNotBlank()
                && this.metafile.representation == Representation.CV_LINKEDIN
    }
}

class MissionResumeUploadedEvent(var metafile: MetafileDomain) {
    val username = metafile.username
    fun isMissionResume() : Boolean {
        return this.metafile.username.isNotBlank()
                && this.metafile.filename.isNotBlank()
                && this.metafile.representation == Representation.CV_MISSION
    }
}