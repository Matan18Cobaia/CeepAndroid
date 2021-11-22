package br.com.alura.ceep.ui.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.model.Nota;
import br.com.alura.ceep.ui.recyclerview.adapter.listener.OnItemClickListener;

public class ListaNotasAdapter extends RecyclerView.Adapter<ListaNotasAdapter.NotaViewHolder> {

    private Context context;
    private final List<Nota> listaDeNotas;
    private br.com.alura.ceep.ui.recyclerview.adapter.listener.OnItemClickListener OnItemClickListener;

    public ListaNotasAdapter(Context context, List<Nota> listaDeNotas) {
        this.context = context;
        this.listaDeNotas = listaDeNotas;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.OnItemClickListener = onItemClickListener;
    }

    @Override
    public ListaNotasAdapter.NotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_nota, parent, false);
        return new NotaViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(ListaNotasAdapter.NotaViewHolder holder, int position) {
        Nota nota = listaDeNotas.get(position);
        holder.vincula(nota);
    }

    @Override
    public int getItemCount() {
        return listaDeNotas.size();
    }

    public void altera(int posicaoRecebida, Nota notaRecebida) {
        listaDeNotas.set(posicaoRecebida, notaRecebida);
        notifyDataSetChanged();
    }

    public void remove(int notaRemovidaPosition) {
        listaDeNotas.remove(notaRemovidaPosition);
        notifyDataSetChanged();
    }

    public void troca(int posicaoInicial, int pocicaoFinal) {
        Collections.swap(listaDeNotas, posicaoInicial,pocicaoFinal);
        notifyDataSetChanged();
    }


    class NotaViewHolder extends RecyclerView.ViewHolder{
        private final TextView titulo;
        private final TextView descricao;
        private Nota nota;

        public NotaViewHolder(View itemView) {
            super(itemView);
            titulo=itemView.findViewById(R.id.item_nota_titulo);
            descricao=itemView.findViewById(R.id.item_nota_descricao);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OnItemClickListener.onItemClick(nota, getAdapterPosition());
                }
            });
        }

        public void vincula(Nota nota){
            this.nota=nota;
            preencheCampos(nota);
        }

        private void preencheCampos(Nota nota) {
            titulo.setText(nota.getTitulo());
            descricao.setText(nota.getDescricao());
        }
    }
    public void adiciona(Nota nota){
        listaDeNotas.add(nota);
        notifyDataSetChanged();
    }
}
