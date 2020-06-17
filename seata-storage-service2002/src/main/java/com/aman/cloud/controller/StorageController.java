package com.aman.cloud.controller;

import com.aman.cloud.entities.ResultData;
import com.aman.cloud.service.StorageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StorageController {

    @Resource
    private StorageService storageService;

    @RequestMapping("/storage/decrease")
    public ResultData<Object> decrease(Long productId, Integer count) {
        storageService.decrease(productId, count);
        return ResultData.success(200, "扣减库存成功!");
    }
}


