package eduardocruz.listaeletronica2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import eduardocruz.listaeletronica2.R;
import eduardocruz.listaeletronica2.entidades.Listas;

public class ListasAdapterListView extends BaseAdapter{


    private LayoutInflater mInflater;
    private ArrayList<Listas> itens;

    public ListasAdapterListView(Context context, ArrayList<Listas> itens) {
        this.itens = itens;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return itens.size();
    }

    public Listas getItem(int position) {
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
            itemHolder.total = ((TextView) view.findViewById(R.id.precoItem_itemAdapter));
            view.setTag(itemHolder);
        } else {
            itemHolder = (ItemSuporte) view.getTag();
        }

        Listas item = itens.get(position);
        itemHolder.nome.setText(item.getNome());
        itemHolder.total.setText("R$: "+item.getTotal());
        return view;

    }

    private class ItemSuporte {

        TextView nome;
        TextView total;

    }
}
