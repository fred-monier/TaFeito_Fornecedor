package br.pe.recife.tafeito.negocio;

import java.util.Date;

public class Oferta {

    private long id;
    private Servico servico;
    private Date dataHoraInicio;
    private Date dataHoraFim;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Date getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(Date dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public Date getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(Date dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }
}
