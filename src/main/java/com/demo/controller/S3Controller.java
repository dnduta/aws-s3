package com.demo.controller;

import com.demo.aws.CreateBucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class S3Controller {

    @Autowired
    private CreateBucket createBucket;

    @GetMapping("/run")
    public String run()
    {
        createBucket.launch();

        File file = new File("./sample.txt");
        createBucket.uploadFile(file, "Sample.txt");

        return "Bucket created and sample uploaded!";
    }
}
