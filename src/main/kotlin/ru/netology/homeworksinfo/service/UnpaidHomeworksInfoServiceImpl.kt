package ru.netology.homeworksinfo.service

import org.apache.logging.log4j.kotlin.Logging
import org.springframework.stereotype.Service
import ru.netology.homeworksinfo.api.dto.UnpaidHomeworkInfo
import ru.netology.homeworksinfo.integration.netology.NetologyApiClient
import ru.netology.homeworksinfo.integration.netology.const.DEFAULT_HOMEWORK_PAGE

@Service
class UnpaidHomeworksInfoServiceImpl(
    private val netologyApiClient: NetologyApiClient,
    private val pricesOfHomeworkTypes: Map<String, Int>
) : UnpaidHomeworksInfoService {
    companion object : Logging

    override suspend fun getUnpaidHomeworksInfo(taskType: String): UnpaidHomeworkInfo {
        logger.info("get unpaid homeworks info with task type=$taskType - start")
        val typePrice = pricesOfHomeworkTypes.getOrElse(taskType) {
            throw RuntimeException("task type=$taskType was not found")
        }
        val unpaidHomeworksSummary = getUnpaidHomeworksSummary(taskType)
        val amountDue = typePrice.times(unpaidHomeworksSummary)
        logger.info("get unpaid homeworks info with task type=$taskType - finish successfully")
        return UnpaidHomeworkInfo(unpaidHomeworksSummary, amountDue)
    }

    private suspend fun getUnpaidHomeworksSummary(taskType: String): Int {
        logger.info("fetching unpaid homeworks summary from netology with type=$taskType - start")
        val result = netologyApiClient.fetchUnpaidHomeworksPage(taskType = taskType).let { unpaidHomeworkPage ->
            val totalPages = unpaidHomeworkPage.totalPages ?: DEFAULT_HOMEWORK_PAGE
            (unpaidHomeworkPage.homeworks?.size ?: 0) + (DEFAULT_HOMEWORK_PAGE + 1..totalPages).sumOf {
                netologyApiClient.fetchUnpaidHomeworksPage(taskType = taskType, page = it).homeworks?.size ?: 0
            }
        }
        logger.info("fetching unpaid homeworks summary from netology with type=$taskType - end, result: $result")
        return result
    }
}