package ru.netology.homeworksinfo.api.dto

data class UnpaidHomeworkInfo(val unpaidHomeworks: Int, val amountDue: Int) {
    fun userMessage() = "Всего неоплаченных работ: $unpaidHomeworks на сумму $amountDue рублей"
}