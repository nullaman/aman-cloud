package com.aman.cloud.service.impl;

import com.aman.cloud.domain.Storage;
import com.aman.cloud.mapper.StorageMapper;
import com.aman.cloud.service.StorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService {
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("----> storage-service中扣减库存开始");
        baseMapper.decrease(productId, count);
        log.info("----> storage-service中扣减库存结束");
    }
}
