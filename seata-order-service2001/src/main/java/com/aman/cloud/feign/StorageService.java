package com.aman.cloud.feign;

import com.aman.cloud.entities.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-storage-service")
public interface StorageService {

    /**
     * 减库存
     * @param productId
     * @param count
     * @return
     */
    @PostMapping(value = "/storage/decrease")
    ResultData<Object> decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}

