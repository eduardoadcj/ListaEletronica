package eduardocruz.listaeletronica2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import eduardocruz.listaeletronica2.entidades.Produto;

public class AdapterListView extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Produto> itens;

    public AdapterListView(Context context, List<Produto> itens) {
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

            view = mInflater.inflate(R.layout.item_list, null);

            itemHolder = new ItemSuporte();
            itemHolder.nome = ((TextView) view.findViewById(R.id.nomeItem));

            view.setTag(itemHolder);
        } else {

            itemHolder = (ItemSuporte) view.getTag();
        }

        Produto item = itens.get(position);
        itemHolder.nome.setText(item.getNome());

        return view;
    }

    private class ItemSuporte {

        TextView nome;

    }

    //http://www.devmedia.com.br/android-criando-um-listview-customizado/26260

}
