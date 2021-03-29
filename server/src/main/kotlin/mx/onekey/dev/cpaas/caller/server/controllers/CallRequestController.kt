package mx.onekey.dev.cpaas.caller.server.controllers

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/request/call")
class CallRequestController {

    @PostMapping("/{routeName}")
    fun requestCall(@PathVariable routeName: String) {
    }

}