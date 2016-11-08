package br.com.livroandroid.suporte_financeiro.Adapter.InvestimentoAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.livroandroid.suporte_financeiro.domain.Investimento;

/**
 * Created by Rrafael on 01/11/2016.
 */

public class InvestimentoAdapter extends RecyclerView.Adapter<InvestimentoAdapter.InvestimentoViewHolder> {
    protected static final String TAG = "InvestimentoSuporte";
    private List<Investimento> investimentos;
    private Context context;
    private InvestimentoOnClickListener investimentoOnClickListener;

    public interface InvestimentoOnClickListener {
        public void OnClickInvestimento(View view, int idx);
    }
    @Override
    public InvestimentoAdapter.InvestimentoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(InvestimentoAdapter.InvestimentoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return this.investimentos != null ? this.investimentos.size() : 0;
    }

    public static class InvestimentoViewHolder extends RecyclerView.ViewHolder {

        public InvestimentoViewHolder(View itemView) {
            super(itemView);
        }
    }

}
