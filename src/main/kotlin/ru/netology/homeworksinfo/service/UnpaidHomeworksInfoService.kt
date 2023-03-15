package ru.netology.homeworksinfo.service

import ru.netology.homeworksinfo.api.dto.UnpaidHomeworkInfo

interface UnpaidHomeworksInfoService {

    suspend fun getUnpaidHomeworksInfo(taskType: String): UnpaidHomeworkInfo
}