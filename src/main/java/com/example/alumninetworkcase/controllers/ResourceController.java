package com.example.alumninetworkcase.controllers;

import com.example.alumninetworkcase.models.EventDTO.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("api/v1/resources")
public class ResourceController {

    @GetMapping("public")
    public ResponseEntity getPublic() {
        ResponseMessage message = new ResponseMessage();
        message.setMessage("Public resources");
        return ResponseEntity.ok(message);
    }

    @GetMapping("protected")
    public ResponseEntity getProtected() {
        ResponseMessage message = new ResponseMessage();
        message.setMessage("Protected resources");
        return ResponseEntity.ok(message);
    }

}