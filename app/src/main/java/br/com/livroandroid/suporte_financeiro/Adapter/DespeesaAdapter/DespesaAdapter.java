package br.com.livroandroid.suporte_financeiro.Adapter.DespeesaAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.livroandroid.suporte_financeiro.domain.Despesa;

/**
 * Created by Rrafael on 01/11/2016.
 */

public class DespesaAdapter extends RecyclerView.Adapter<DespesaAdapter.DespesaViewHolder> {
    protected static final String TAG="DespesaSuporte";
    private final List<Despesa> despesas;
    private final Context context;
    private DespesaOnClickListener despesaOnClickListener;

    public DespesaAdapter(List<Despesa> despesas, Context context, DespesaOnClickListener despesaOnClickListener) {
        this.despesas = despesas;
        this.context = context;
        this.despesaOnClickListener = despesaOnClickListener;
    }

    public interface  DespesaOnClickListener {
        public void onClickDespesa(View view, int idx);
    }
    @Override
    public DespesaAdapter.DespesaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DespesaAdapter.DespesaViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return this.despesas != null ? this.despesas.size() : 0;
    }

    public static class DespesaViewHolder extends RecyclerView.ViewHolder {

        public DespesaViewHolder(View itemView) {
            super(itemView);
        }
    }
}
