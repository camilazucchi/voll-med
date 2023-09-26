package med.voll.api.service.utility;

import med.voll.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
// A anotação "@Service" é como uma etiqueta que você coloca em uma classe em um projeto Spring (como o Spring Boot)
// para dizer ao Spring que essa classe é importante e deve ser gerenciada por ele.
public class ServiceAuthentication implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

}
