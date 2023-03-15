package ru.netology.homeworksinfo.integration.netology

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.withContext
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.web.util.UriBuilder
import ru.netology.homeworksinfo.integration.netology.dto.HomeworksPage
import ru.netology.homeworksinfo.integration.netology.const.*

@Component
class NetologyApiClient(
    @Value("\${webClient.pathUrl}") private val pathUrl: String,
    @Value("\${netology.userId}") private val userId: String,
    @Value("\${netology.authToken}") private val authToken: String,
    private val webClient: WebClient,
) {
    companion object : Logging

    suspend fun fetchUnpaidHomeworksPage(
        isUnpaid: Boolean = HOMEWORK_IS_UNPAID,
        taskType: String = TASK_TASK_TYPE_HOMEWORK,
        page: Int = DEFAULT_HOMEWORK_PAGE,
    ): HomeworksPage = withContext(Dispatchers.IO) {
        webClient.get()
            .uri { constructUri(it, isUnpaid, taskType, page) }
            .cookie(HTTP_X_AUTHENTICATION, authToken)
            .retrieve()
            .bodyToMono<HomeworksPage>()
            .awaitSingle()
    }

    private fun constructUri(uriBuilder: UriBuilder, isUnpaid: Boolean, taskType: String, page: Int) =
        with(uriBuilder) {
            path(pathUrl)
            queryParam(EVENTS_USER_ID_EQ, userId)
            queryParam(LESSON_TASK_TASK_TYPE_EQ, taskType)
            queryParam(UNPAID, isUnpaid)
            queryParam(PAGE, page)
            build()
        }
}
