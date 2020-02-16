package com.romanidze.studeeper.modules.user.services.implementations

import com.romanidze.studeeper.modules.email.dto.MailDTO
import com.romanidze.studeeper.modules.email.services.interfaces.EmailSendService
import com.romanidze.studeeper.modules.security.enums.State
import com.romanidze.studeeper.modules.user.domain.Profile
import com.romanidze.studeeper.modules.user.domain.ProfileConfirmation
import com.romanidze.studeeper.modules.user.dto.MessageResponseDTO
import com.romanidze.studeeper.modules.user.repositories.interfaces.ProfileConfirmationRepository
import com.romanidze.studeeper.modules.user.repositories.interfaces.ProfileRepository
import com.romanidze.studeeper.modules.user.repositories.interfaces.UserRepository
import com.romanidze.studeeper.modules.user.services.interfaces.ProfileConfirmationService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.lang.IllegalArgumentException
import java.net.InetAddress
import java.util.UUID

/**
 * 16.02.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Service
class ProfileConfirmationServiceImpl(

    @Value("\${server.port}")
    private val port: Int,

    private val emailService: EmailSendService,
    private val repo: ProfileConfirmationRepository,
    private val profileRepo: ProfileRepository,
    private val userRepo: UserRepository

): ProfileConfirmationService {

    override fun createConfirmation(input: Profile): Mono<ProfileConfirmation> {

        val profileConfirm = ProfileConfirmation(
            profileID = input.id!!,
            confirmString = UUID.randomUUID().toString()
        )

        val confirmMono = this.repo.save(profileConfirm)

        val host = InetAddress.getLocalHost().hostAddress
        val address = "http://${host}:${port}/api/security/confirm/${profileConfirm.confirmString}"

        val message = "Please, confirm your email. URL is $address"

        val mailObj = MailDTO(
           from = "admin@studeeper.com",
           to = input.email!!,
           subject = "Studeeper Email confirmation",
           message = message
        )

        return this.emailService.sendMail(mailObj).flatMap {
            confirmMono
        }

    }

    override fun confirmProfile(input: String): Mono<MessageResponseDTO> {

        val fallback = Mono.error<ProfileConfirmation>(
             IllegalArgumentException("No confirmation with link ${input}")
        )

        val confirmLink = this.repo.findByConfirmString(input)
                                   .switchIfEmpty(fallback)

        val profile = confirmLink.flatMap {
            this.profileRepo.findByID(it.profileID)
        }

        val user = profile.flatMap {
            this.userRepo.findByID(it.userID!!)
        }

        val changedUser = user.flatMap {
            val changedModel = it.copy(
                 state = State.CONFIRMED.toString()
            )

            this.userRepo.update(changedModel)
        }

        return changedUser.flatMap {
            if(it.modifiedCount != 1L){
                Mono.error<MessageResponseDTO>(IllegalArgumentException("Confirmation didn't updated"))
            }

            MessageResponseDTO("Profile confirmed").toMono()
        }

    }
}