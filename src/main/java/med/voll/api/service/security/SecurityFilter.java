package med.voll.api.service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
// O "@Component" é utilizado para que o Spring carregue uma classe/componente genérico.
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("CHAMANDO FILTER");
        var tokenJWT = retrieveTokenJWT(request);

        if (tokenJWT != null) {
            var subject = tokenService.getSubject(tokenJWT);
            var user = repository.findByLogin(subject);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            // Cria um objeto "UsernamePasswordAuthenticationToken" no contexto da autenticação em um aplicativo
            // Spring Security.
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("LOGADO NA REQUISIÇÃO");
        }

        filterChain.doFilter(request, response);
        // Essa linha precisa ser executada para chamar o próximo filtro!

    }

    private String retrieveTokenJWT(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "").trim();
        }
        return null;
    }

}
