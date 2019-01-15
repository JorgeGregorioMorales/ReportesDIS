package mx.com.nmp.dis.elastic.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import mx.com.nmp.dis.enums.EstadoReporte;
import mx.com.nmp.dis.enums.TipoReporte;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Solicitud {

    private String        id;
    private TipoReporte   tipoReporte;
    private EstadoReporte estado;
    private Parametros    parametros;
    private Date          fechaActualizacion;
    private Double        porcentaje;
    private String        xml;
    private String        rutaArchivo;
    private String        comentarios;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoReporte getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(TipoReporte tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public EstadoReporte getEstado() {
        return estado;
    }

    public void setEstado(EstadoReporte estado) {
        this.estado = estado;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Parametros getParametros() {
        return parametros;
    }

    public void setParametros(Parametros parametros) {
        this.parametros = parametros;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{ id:");
        builder.append(id);
        builder.append(", tipoReporte:");
        builder.append(tipoReporte);
        builder.append(", estado:");
        builder.append(estado);
        builder.append(", parametros:");
        builder.append(parametros);
        builder.append(", fechaActualizacion:");
        builder.append(fechaActualizacion);
        builder.append(", porcentaje:");
        builder.append(porcentaje);
        builder.append(", xml:");
        builder.append(xml);
        builder.append(", rutaArchivo:");
        builder.append(rutaArchivo);
        builder.append(", comentarios:");
        builder.append(comentarios);
        builder.append("}");
        return builder.toString();
    }

   
    

}
