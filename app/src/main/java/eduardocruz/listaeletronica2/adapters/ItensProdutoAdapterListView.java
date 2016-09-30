package eduardocruz.listaeletronica2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import eduardocruz.listaeletronica2.R;
import eduardocruz.listaeletronica2.entidades.ItensLista;
import eduardocruz.listaeletronica2.entidades.Produto;

public class ItensProdutoAdapterListView extends BaseAdapter {


    private LayoutInflater mInflater;
    private ArrayList<Produto> produtos;
    private ArrayList<ItensLista> itensLista;

    public ItensProdutoAdapterListView(Context context, ArrayList<Produto> produtos, ArrayList<ItensLista> itensLista) {
        this.produtos = produtos;
        this.itensLista = itensLista;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return itensLista.size();
    }

    public ItensLista getItem(int position) {
        return itensLista.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {

        ItemSuporte itemHolder;

        if (view == null) {

            view = mInflater.inflate(R.layout.adapter_produto_item_list, null);

            itemHolder = new ItemSuporte();
            itemHolder.nome = ((TextView) view.findViewById(R.id.nomeItem_itemAdapter));
            itemHolder.preco = ((TextView) view.findViewById(R.id.precoItem_itemAdapter));
            view.setTag(itemHolder);

        } else {
            itemHolder = (ItemSuporte) view.getTag();
        }

        ItensLista item = itensLista.get(position);
        Produto p = searchProduto(item);

        itemHolder.nome.setText(p.getNome());
        itemHolder.preco.setText(item.getQuantidade()+" X R$: "+p.getPreco());
        return view;

    }

    private class ItemSuporte {

        TextView nome;
        TextView preco;

    }

    private Produto searchProduto(ItensLista item){

        Produto p = new Produto();
        for(Produto produto : produtos){

            if(item.getId_produto() == produto.getId()){
                p = produto;
            }

        }

        return p;

    }


}
