package duynn.gotogether.service;

import duynn.gotogether.entity.Account;
import duynn.gotogether.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl implements GeneralService<Account> {

    @Autowired
    AccountRepository accountRepository;

    public Account findAccountByUsername(String username) {
        return accountRepository.findAccountByUsername(username);
    }

    public boolean isExist(String username) {
        return accountRepository.findAccountByUsername(username) != null;
    }

    @Override
    public List<Account> findAll() throws Exception {
        List<Account> accounts = accountRepository.findAll();
        if(accounts.isEmpty()) {
            throw new Exception("Không tìm thấy dữ liệu");
        }
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) throws Exception {
        Optional<Account> account = accountRepository.findById(id);
        if(!account.isPresent()) {
            throw new Exception("Không tìm thấy dữ liệu");
        }
        return account.get();
    }

    @Override
    public Account create(Account account) throws Exception {
        if(isExist(account.getUsername())) {
            throw new Exception("Username đã tồn tại");
        }
        accountRepository.save(account);
        return null;
    }

    @Override
    public Account update(Account account) throws Exception {
        accountRepository.save(account);
        return null;
    }

    @Override
    public int delete(Long id) {
        accountRepository.deleteAccountById(id);
        return 0;
    }
}
