package eduardocruz.listaeletronica2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import eduardocruz.listaeletronica2.R;
import eduardocruz.listaeletronica2.entidades.Produto;

public class ProdutoAdapterListView extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<Produto> itens;

    public ProdutoAdapterListView(Context context, ArrayList<Produto> itens) {
        this.itens = itens;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return itens.size();
    }

    public Produto getItem(int position) {
        return itens.get(position);
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

        Produto item = itens.get(position);
        itemHolder.nome.setText(item.getNome());
        itemHolder.preco.setText("R$: "+item.getPreco());
        return view;

    }

    private class ItemSuporte {

        TextView nome;
        TextView preco;

    }

    //http://www.devmedia.com.br/android-criando-um-listview-customizado/26260

}
