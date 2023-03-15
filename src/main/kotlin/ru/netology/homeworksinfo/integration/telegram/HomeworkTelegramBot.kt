package ru.netology.homeworksinfo.integration.telegram

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import ru.netology.homeworksinfo.service.PrepareMessageService

@Service
class HomeworkTelegramBot(
    @Value("\${telegram.bot.username}") private val botUsername: String,
    @Value("\${telegram.bot.botToken}") private val token: String,
    private val prepareMessageService: PrepareMessageService,
) : TelegramLongPollingBot(token) {

    override fun getBotUsername() = botUsername

    override fun onUpdateReceived(update: Update) {
        CoroutineScope(Dispatchers.IO).launch {
            if (update.hasMessage() && update.message.hasText()) {
                val chatId = update.message.chatId
                val message = prepareMessageService.preparingMessage(update)
                sendMessage(chatId, message)
            }
        }
    }

    private suspend fun sendMessage(chatId: Long, text: String) = execute(SendMessage(chatId.toString(), text))
}