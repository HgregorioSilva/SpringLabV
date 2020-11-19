package dream.team.cetriolo.sprintbootapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dream.team.cetriolo.sprintbootapp.security.Login;

@RestController
@RequestMapping(value="/login")
@CrossOrigin
public class LoginController {

    public Login autenticar(@RequestBody Login login){
        return login;
    }
    
}