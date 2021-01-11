package com.api.service.impl.account;

import com.api.entity.account.Account;
import com.api.mapper.AccountMapper;
import com.api.service.able.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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

    /**
     * 收支
     *
     * @param accountId 账户id
     * @param typeTwo   操作类型（1、支出，2、收入）
     * @param type      类型（1、新增，2、回滚）
     * @param money     金钱（分）
     * @return
     */
    public boolean IncomeAndExpenditure(String accountId, int typeTwo, int type, BigDecimal money) {
        Account accountSon = getById(accountId);
        Account account = new Account();
        account.setId(accountId);
        if (typeTwo == 1) {
            if (type == 1) {
                account.setAccountConsumption(accountSon.getAccountConsumption().add(money));
            } else {
                account.setAccountConsumption(accountSon.getAccountConsumption().subtract(money));
            }
            account.setAccountIncome(accountSon.getAccountIncome());
        } else if (typeTwo == 2) {
            if (type == 1) {
                account.setAccountIncome(accountSon.getAccountIncome().add(money));
            } else {
                account.setAccountIncome(accountSon.getAccountIncome().subtract(money));
            }
            account.setAccountConsumption(accountSon.getAccountConsumption());
        }
        account.setAccountBalance(account.getAccountIncome().subtract(account.getAccountConsumption()));
        return updateById(account);
    }

    /**
     * 转账
     *
     * @param accountIdA 扣款账户id
     * @param accountIdB 收款账户id
     * @param type       类型（1、新增，2、回滚）
     * @param money      金钱（分）
     * @return
     */
    public boolean transferAccounts(String accountIdA, String accountIdB, int type, BigDecimal money) {
        Account accountA = getById(accountIdA);//扣款账户
        Account accountB = getById(accountIdB);//收款账户
        if (type == 1) {
            accountA.setAccountConsumption(accountA.getAccountConsumption().add(money));//扣款账户支出金钱增加
            accountB.setAccountIncome(accountB.getAccountIncome().add(money));//收款账户收入金钱增加
        } else if (type == 2) {
            accountA.setAccountConsumption(accountA.getAccountConsumption().subtract(money));//扣款账户支出金钱增加
            accountB.setAccountIncome(accountB.getAccountIncome().subtract(money));//收款账户收入金钱减少
        }
        accountA.setAccountBalance(accountA.getAccountIncome().subtract(accountA.getAccountConsumption()));
        accountB.setAccountBalance(accountB.getAccountIncome().subtract(accountB.getAccountConsumption()));
        return updateById(accountA) && updateById(accountB);
    }
}
