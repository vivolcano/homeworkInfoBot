package ru.netology.homeworksinfo.service

import org.apache.logging.log4j.kotlin.Logging
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class PrepareMessageServiceImpl(
    private val telegramBotCommands: Map<String, String>,
    private val unpaidHomeworksInfoService: UnpaidHomeworksInfoService,
) : PrepareMessageService {

    companion object : Logging

    override suspend fun preparingMessage(update: Update): String {
        val command = update.message.text
        return telegramBotCommands[command]?.let { commandName ->
            unpaidHomeworksInfoService.getUnpaidHomeworksInfo(commandName).also {
                logger.info("command='$command' was successfully executed with the result=$it")
            }.userMessage()
        } ?: run {
            logger.info("command='$command' not found")
            "Неизвестная команда"
        }
    }
}