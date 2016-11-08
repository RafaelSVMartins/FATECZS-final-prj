package br.com.livroandroid.suporte_financeiro.Adapter.RendaAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.livroandroid.suporte_financeiro.domain.Renda;

/**
 * Created by Rrafael on 01/11/2016.
 */

public class RendaAdapter extends RecyclerView.Adapter<RendaAdapter.RendaViewHolder> {
    protected static final String TAG="RendaSuporte";
    private List<Renda> rendas;
    private Context context;
    private RendaOnClickListener rendaOnClickListener;

    public RendaAdapter(List<Renda> rendas, Context context, RendaOnClickListener rendaOnClickListener) {
        this.rendas = rendas;
        this.context = context;
        this.rendaOnClickListener = rendaOnClickListener;
    }

    public interface RendaOnClickListener {
        public void OnClickRenda(View view, int idx);
    }

    @Override
    public RendaAdapter.RendaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RendaAdapter.RendaViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return this.rendas != null ? this.rendas.size() : 0;
    }

    public static class RendaViewHolder extends RecyclerView.ViewHolder {
        public RendaViewHolder(View itemView) {
            super(itemView);
        }
    }

}
