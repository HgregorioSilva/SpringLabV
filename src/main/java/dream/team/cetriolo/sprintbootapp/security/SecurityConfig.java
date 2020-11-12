package dream.team.cetriolo.sprintbootapp.security;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //prePostEnabled onde anotar está seguro
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().httpBasic().and()//metodo de segurança por token, o sping criando as paginas, está desabilitado
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//STATELESSfaz a requisição e joga a memoria fora
    }


    
}