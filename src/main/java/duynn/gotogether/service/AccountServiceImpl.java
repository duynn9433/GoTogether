package duynn.gotogether.service;

import duynn.gotogether.entity.Account;
import duynn.gotogether.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl implements GeneralService<Account> {
    @Autowired
    AccountRepository accountRepository;

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
        Account res = accountRepository.save(account);
        return res;
    }

    @Override
    public Account update(Account account) throws Exception {
        Account account1 = accountRepository.save(account);
        if (account1 == null) {
            throw new Exception("Không thể cập nhật dữ liệu");
        }
        return account1;
    }

    @Override
    public int delete(Long id) {
        int res = accountRepository.deleteAccountById(id);
        return res;
    }

    public boolean login(Account data) {
        Account account = accountRepository.findAccountByUsername(data.getUsername());
        if(account == null) {
            return false;
        }
        return account.getPassword().equals(data.getPassword());
    }
}
