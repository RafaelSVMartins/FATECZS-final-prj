package br.com.livroandroid.suporte_financeiro.Adapter.RendaAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.livroandroid.suporte_financeiro.R;
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
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_rendalayout,parent,false);
        RendaViewHolder holder = new RendaViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RendaAdapter.RendaViewHolder holder, final int position) {
            Renda renda = rendas.get(position);
            holder.NomeRenda.setText(renda.getNomeRenda());
            holder.TipoRenda.setText(renda.getTipoRenda());
            holder.ValorRenda.setText(renda.getValorRenda().toString());
            holder.imgRenda.setImageResource(R.drawable.renda);

            if(rendaOnClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rendaOnClickListener.OnClickRenda(holder.itemView,position);
                    }
                });
            }
    }

    @Override
    public int getItemCount() {
        return this.rendas != null ? this.rendas.size() : 0;
    }

    public static class RendaViewHolder extends RecyclerView.ViewHolder {
        private TextView NomeRenda;
        private TextView TipoRenda;
        private TextView ValorRenda;
        private ImageView imgRenda;

        public RendaViewHolder(View itemView) {
            super(itemView);
            NomeRenda = (TextView) itemView.findViewById(R.id.RendaNome);
            TipoRenda = (TextView) itemView.findViewById(R.id.RendaTipo);
            ValorRenda = (TextView) itemView.findViewById(R.id.RendaValor);
            imgRenda = (ImageView) itemView.findViewById(R.id.imgRenda);
        }
    }

}
