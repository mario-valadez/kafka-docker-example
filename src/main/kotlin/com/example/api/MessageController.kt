package com.example.api

import com.example.producer.MessageProducer
import com.example.service.MessageService
import org.springframework.web.bind.annotation.*
import kotlinx.coroutines.*

@RestController
@RequestMapping("/api/v1/messages")
class ScheduledJobController(
    val messageProducer: MessageProducer,
    val messageService: MessageService
) {
    @PostMapping
    suspend fun createMessage(@RequestBody message: String) =
        try{
            messageProducer.sendApplyPolicyChangesEvent(message)
            "Generated topic to create message"
        }
        catch(error: Exception){
            "Error unable to generate topic to create message: " + error.message
        }
    
    @GetMapping
    suspend fun getMessages() =
        messageService.getMessages()
}