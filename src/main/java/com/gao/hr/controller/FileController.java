package com.gao.hr.controller;

import com.gao.hr.common.R;
import com.gao.hr.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author:Gao
 * @Date:2020-04-17 15:25
 */
@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping
    public R uploadOssFile(MultipartFile file){
        String url = fileService.uploadFile(file);
        return R.ok().data("url",url);
    }
}
