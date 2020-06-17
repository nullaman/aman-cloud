package com.aman.cloud.service;

import com.aman.cloud.domain.Account;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

public interface AccountService extends IService<Account> {
    void decrease(Long userId, BigDecimal money);
}
