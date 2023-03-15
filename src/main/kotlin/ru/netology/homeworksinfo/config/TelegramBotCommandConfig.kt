package ru.netology.homeworksinfo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.netology.homeworksinfo.integration.netology.const.TASK_TASK_TYPE_DIPLOMA
import ru.netology.homeworksinfo.integration.netology.const.TASK_TASK_TYPE_HOMEWORK

const val COMMAND_HOMEWORK = "/homework"
const val COMMAND_DIPLOMA = "/diploma"

@Configuration
class TelegramBotCommandConfig {

    @Bean
    fun telegramBotCommands() = mapOf(
        COMMAND_HOMEWORK to TASK_TASK_TYPE_HOMEWORK,
        COMMAND_DIPLOMA to TASK_TASK_TYPE_DIPLOMA
    )
}