package com.romanidze.studeeper.modules.users.repositories

import com.romanidze.studeeper.application.Application
import com.romanidze.studeeper.modules.user.repositories.interfaces.UserRepository
import io.kotlintest.specs.WordSpec
import org.springframework.boot.test.context.SpringBootTest

/**
 * 28.11.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@SpringBootTest(classes = [Application::class])
class UserRepositoryTest(repo: UserRepository): WordSpec() {}