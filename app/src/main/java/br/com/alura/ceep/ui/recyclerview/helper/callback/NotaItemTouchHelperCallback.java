package br.com.alura.ceep.ui.recyclerview.helper.callback;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import br.com.alura.ceep.dao.NotaDAO;
import br.com.alura.ceep.ui.recyclerview.adapter.ListaNotasAdapter;

public class NotaItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private final ListaNotasAdapter adapter;

    public NotaItemTouchHelperCallback(ListaNotasAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int marcacoesDeDeslize = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        int marcacoesDeArrastar = ItemTouchHelper.UP|ItemTouchHelper.DOWN
                |ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT;
        return makeMovementFlags(marcacoesDeArrastar, marcacoesDeDeslize);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int posicaoInicial = viewHolder.getAdapterPosition();
        int pocicaoFinal = target.getAdapterPosition();
        trocaNotas(posicaoInicial, pocicaoFinal);
        return false;
    }

    private void trocaNotas(int posicaoInicial, int pocicaoFinal) {
        new NotaDAO().troca(posicaoInicial, pocicaoFinal);
        adapter.troca(posicaoInicial, pocicaoFinal);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int notaRemovidaPosition = viewHolder.getAdapterPosition();
        removeNota(notaRemovidaPosition);
    }

    private void removeNota(int notaRemovidaPosition) {
        new NotaDAO().remove(notaRemovidaPosition);
        adapter.remove(notaRemovidaPosition);
    }
}
