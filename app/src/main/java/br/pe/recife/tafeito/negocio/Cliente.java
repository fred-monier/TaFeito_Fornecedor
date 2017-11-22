package br.pe.recife.tafeito.negocio;

public class Cliente extends Usuario {

    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
