package br.pe.recife.tafeito.negocio;

public class Fornecedor extends Usuario {

    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Usuario gerarUsuario() {
        Usuario usuario = new Usuario();

        usuario.setId(this.getId());
        usuario.setHabilitado(this.isHabilitado());
        usuario.setNome(this.getNome());
        usuario.setEndereco(this.getEndereco());

        return usuario;
    }
}
