package duynn.gotogether.service;

import duynn.gotogether.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface GeneralService<T>  {

    List<T> findAll() throws Exception;

    T findById(Long id) throws Exception;

    T create(T t) throws Exception;

    T update(T t) throws  Exception;

    int delete(Long id);

}

