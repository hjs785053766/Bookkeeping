package com.api.service.impl.account;

import com.api.entity.account.Account;
import com.api.mapper.AccountMapper;
import com.api.service.able.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账户表 服务实现类
 * </p>
 *
 * @author hjs
 * @since 2020-10-21
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

}
