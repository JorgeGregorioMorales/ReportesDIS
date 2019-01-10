package mx.com.nmp.dis.exception;

import mx.com.nmp.dis.enums.ErrorCode;

public class BusinessServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ErrorCode code;
	private String mensaje;

	public BusinessServiceException(ErrorCode code) {
		super(code.getMensaje());
		this.code = code;
	}

	public BusinessServiceException(String mensaje) {
		super();
		this.code = ErrorCode.ERROR_GENERICO;
		this.mensaje = mensaje;
	}

	public ErrorCode getCode() {
		return code;
	}

	public void setCode(ErrorCode code) {
		this.code = code;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
