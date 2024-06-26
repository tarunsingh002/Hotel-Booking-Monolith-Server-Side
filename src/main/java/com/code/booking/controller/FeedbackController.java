package com.code.booking.controller;

import com.code.booking.entity.Feedback;
import com.code.booking.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/api/v1/feedback/add")
    public Feedback addFeedback(@RequestBody Feedback feedback){
        return feedbackService.addFeedback(feedback);
    }

    @GetMapping("/api/v1/feedback/get")
    public List<Feedback> getFeedbacks(){
        return feedbackService.getFeedbacks();
    }
}
