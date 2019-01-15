package mx.com.nmp.dis.enums;

public enum ErrorCode {

	ERROR_GENERICO(1, "Error no especificado"), 
	NO_ENCONTRADO(2, "No se encontró el elemento"), 
	ERROR_CONNECT_ELASTIC(3, "No se pudo conectar a la instancia de elastic Search"), 
    ERROR_DUPLICADO(4,"Ya existe un elemento con las mismas caracteristicas"), 
    ERROR_PARSING_SOLICITUD(5,"Error al obtener la información de la solicitud"),

    ERROR_DELETING_ELEMENT(6,"Error al eliminar el elemento solicitado");

	private int id;
	private String mensaje;

	private ErrorCode(int id, String mensaje) {
		this.id = id;
		this.mensaje = mensaje;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	
	}
