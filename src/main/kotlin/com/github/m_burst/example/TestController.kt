package com.github.m_burst.example

import org.camunda.bpm.engine.RuntimeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val runtimeService: RuntimeService
) {

    @GetMapping("/test")
    fun test(): String {
        return runtimeService.createProcessInstanceQuery().count().toString()
    }
}
