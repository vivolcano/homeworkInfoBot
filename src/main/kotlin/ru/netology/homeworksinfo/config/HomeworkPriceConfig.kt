package ru.netology.homeworksinfo.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.netology.homeworksinfo.integration.netology.const.TASK_TASK_TYPE_DIPLOMA
import ru.netology.homeworksinfo.integration.netology.const.TASK_TASK_TYPE_HOMEWORK

@Configuration
class HomeworkPriceConfig(
    @Value("\${netology.homework.price.homework}") private val homeworkPrice: Int,
    @Value("\${netology.homework.price.diploma}") private val diplomaPrice: Int,
) {

    @Bean
    fun pricesOfHomeworkTypes() = mapOf(
        TASK_TASK_TYPE_HOMEWORK to homeworkPrice,
        TASK_TASK_TYPE_DIPLOMA to diplomaPrice
    )
}