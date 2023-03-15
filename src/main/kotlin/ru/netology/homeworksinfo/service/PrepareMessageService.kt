package ru.netology.homeworksinfo.service

import org.telegram.telegrambots.meta.api.objects.Update

interface PrepareMessageService {

    suspend fun preparingMessage(update: Update) : String
}