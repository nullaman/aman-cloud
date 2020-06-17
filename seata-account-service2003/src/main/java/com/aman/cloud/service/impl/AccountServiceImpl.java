package com.aman.cloud.service.impl;

import com.aman.cloud.domain.Account;
import com.aman.cloud.mapper.AccountMapper;
import com.aman.cloud.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
    @Override
    public void decrease(Long userId, BigDecimal money) {

        log.info("----> account-service中扣减用户余额开始");
        baseMapper.decrease(userId, money);
        log.info("----> account-service中扣减用户余额开始");

    }
}
