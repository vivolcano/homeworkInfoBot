package ru.netology.homeworksinfo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HomeworksInfoApplication

fun main(args: Array<String>) {
    runApplication<HomeworksInfoApplication>(*args)
}