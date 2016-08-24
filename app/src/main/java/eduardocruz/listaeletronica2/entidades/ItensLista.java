package eduardocruz.listaeletronica2.entidades;

public class ItensLista {

    private Integer id;
    private Integer id_lista;
    private Integer id_produto;
    private Double quantidade;

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_produto() {
        return id_produto;
    }

    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
    }

    public Integer getId_lista() {
        return id_lista;
    }

    public void setId_lista(Integer id_lista) {
        this.id_lista = id_lista;
    }
}
