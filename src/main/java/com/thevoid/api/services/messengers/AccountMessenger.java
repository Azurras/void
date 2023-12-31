package com.thevoid.api.services.messengers;

import com.thevoid.api.exceptions.VoidAccountNotFoundException;
import com.thevoid.api.models.db.account.AccountDetailsEntity;
import com.thevoid.api.models.db.account.AccountEntity;
import com.thevoid.api.models.db.account.AccountSecurityEntity;
import com.thevoid.api.repositories.AccountDetailsRepository;
import com.thevoid.api.repositories.AccountRepository;
import com.thevoid.api.repositories.AccountSecurityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AccountMessenger {
    private final AccountRepository accountRepository;
    private final AccountDetailsRepository accountDetailsRepository;

    private final AccountSecurityRepository accountSecurityRepository;

    @Autowired
    public AccountMessenger(AccountRepository accountRepository,
                            AccountDetailsRepository accountDetailsRepository,
                            AccountSecurityRepository accountSecurityRepository) {
        this.accountRepository = accountRepository;
        this.accountDetailsRepository = accountDetailsRepository;
        this.accountSecurityRepository = accountSecurityRepository;
    }

    public List<AccountEntity> getAccountEntities() {
        return this.accountRepository.findAll();
    }

    public AccountEntity getAccountEntityById(Long id) throws VoidAccountNotFoundException {
        var maybeAccountEntity = this.accountRepository.findById(id);
        if(maybeAccountEntity.isEmpty()) {
            throw new VoidAccountNotFoundException();
        }
        return maybeAccountEntity.get();
    }
    public AccountEntity getAccountEntityByUsername(String username) throws VoidAccountNotFoundException {
        var maybeAccountEntity = this.accountRepository.findByUsername(username);
        if(maybeAccountEntity.isEmpty()) {
            throw new VoidAccountNotFoundException();
        }
        return maybeAccountEntity.get();
    }

    public AccountDetailsEntity getAccountDetailsEntityById(Long id) throws VoidAccountNotFoundException {
        var maybeAccountDetailsEntity = this.accountDetailsRepository.findById(id);
        if(maybeAccountDetailsEntity.isEmpty()) {
            throw new VoidAccountNotFoundException();
        }
        return maybeAccountDetailsEntity.get();
    }

    public AccountSecurityEntity getAccountSecurityEntityByEmail(String email) throws VoidAccountNotFoundException {
        var maybeAccountSecurityEntity = this.accountSecurityRepository.findByEmail(email);
        if(maybeAccountSecurityEntity.isEmpty()) {
            throw new VoidAccountNotFoundException();
        }
        return maybeAccountSecurityEntity.get();
    }

    public AccountSecurityEntity getAccountSecurityEntityById(Long id) throws VoidAccountNotFoundException {
        var maybeAccountSecurityEntity = this.accountSecurityRepository.findById(id);
        if(maybeAccountSecurityEntity.isEmpty()) {
            throw new VoidAccountNotFoundException();
        }
        return maybeAccountSecurityEntity.get();
    }

    public void saveAccountRepository(AccountEntity accountEntity) {
        this.accountRepository.save(accountEntity);
    }

    public void saveAccountDetailsRepository(AccountDetailsEntity accountDetailsEntity) {
        this.accountDetailsRepository.save(accountDetailsEntity);
    }

    public void saveAccountSecurityRepository(AccountSecurityEntity accountSecurityEntity) {
        this.accountSecurityRepository.save(accountSecurityEntity);
    }
}
