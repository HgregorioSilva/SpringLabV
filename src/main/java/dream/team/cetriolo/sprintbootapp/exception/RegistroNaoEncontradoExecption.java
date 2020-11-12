package dream.team.cetriolo.sprintbootapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RegistroNaoEncontradoExecption extends RuntimeException {

    	private static final long serialVersionUID = 2896679206308439481L;

	public RegistroNaoEncontradoExecption() {
		super();
	}

	public RegistroNaoEncontradoExecption(String message) {
		super(message);
	}
	
	public RegistroNaoEncontradoExecption(Throwable cause) {
		super(cause);
	}
	
	public RegistroNaoEncontradoExecption(String message,Throwable cause) {
		super(message,cause);
	}
    
}