package com.newlight77.core.signup

import com.newlight77.core.cgu.CguAcceptedEvent
import com.newlight77.core.metafile.MetafileDomain
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class SignupEventPublisher(private val applicationEventPublisher: ApplicationEventPublisher) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun publishStatusUpdatedEvent(username: String, status: String) {
        try {
            applicationEventPublisher.publishEvent(
                    SignupStatusUpdatedEvent(username, status)
            )
            logger.info("A UserStatusUpdatedEvent has been published user $username")
        } catch (ex: Exception) {
            logger.error("Failed to publish a SignupStatusUpdatedEvent for user $username")
            throw UserStatusPublicationException("Failed to publish a SignupStatusUpdatedEvent for user $username")
        }
    }

}

class UserStatusPublicationException(private val msg: String) : Throwable(msg) {}
