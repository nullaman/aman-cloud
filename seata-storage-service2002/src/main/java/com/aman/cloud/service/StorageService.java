package com.aman.cloud.service;

import com.aman.cloud.domain.Storage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface StorageService extends IService<Storage> {

    void decrease(Long productId, Integer count);


}
