package br.pe.recife.tafeito.negocio;

import java.util.Date;

public class Agendamento {

    private long id;
    private Oferta oferta;
    private Cliente cliente;
    private Date dataHoraRealizado;
    private Date getDataHoraCancelado;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDataHoraRealizado() {
        return dataHoraRealizado;
    }

    public void setDataHoraRealizado(Date dataHoraRealizado) {
        this.dataHoraRealizado = dataHoraRealizado;
    }

    public Date getGetDataHoraCancelado() {
        return getDataHoraCancelado;
    }

    public void setGetDataHoraCancelado(Date getDataHoraCancelado) {
        this.getDataHoraCancelado = getDataHoraCancelado;
    }
}
