package mx.com.nmp.dis.elastic.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Parametros {

    private Long       idCorrida;
    private List<Long>  idsDonatarias;
    private List<Long> idsIndicadores;
    private Long       idTipoIntegrante;
    private List<Long> idsGrupos;

    public Long getIdCorrida() {
        return idCorrida;
    }

    public void setIdCorrida(Long idCorrida) {
        this.idCorrida = idCorrida;
    }

    public List<Long> getIdsDonatarias() {
        return idsDonatarias;
    }

    public void setIdsDonatarias(List<Long> idsDonatarias) {
        this.idsDonatarias = idsDonatarias;
    }

    public List<Long> getIdsIndicadores() {
        return idsIndicadores;
    }

    public void setIdsIndicadores(List<Long> idsIndicadores) {
        this.idsIndicadores = idsIndicadores;
    }

    public Long getIdTipoIntegrante() {
        return idTipoIntegrante;
    }

    public void setIdTipoIntegrante(Long idTipoIntegrante) {
        this.idTipoIntegrante = idTipoIntegrante;
    }

    public List<Long> getIdsGrupos() {
        return idsGrupos;
    }

    public void setIdsGrupos(List<Long> idsGrupos) {
        this.idsGrupos = idsGrupos;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{ idCorrida:");
        builder.append(idCorrida);
        builder.append(", idsDonatarias:");
        builder.append(idsDonatarias);
        builder.append(", idsIndicadores:");
        builder.append(idsIndicadores);
        builder.append(", idTipoIntegrante:");
        builder.append(idTipoIntegrante);
        builder.append(", idsGrupos:");
        builder.append(idsGrupos);
        builder.append("}");
        return builder.toString();
    }



}
