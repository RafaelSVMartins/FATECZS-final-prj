package br.com.livroandroid.suporte_financeiro.Adapter.RendaAdapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.livroandroid.suporte_financeiro.Fragments.Renda.BdCoreRenda;
import br.com.livroandroid.suporte_financeiro.Fragments.Renda.DeletarRendaDialog;
import br.com.livroandroid.suporte_financeiro.Fragments.Renda.EditarRendaDialog;
import br.com.livroandroid.suporte_financeiro.Fragments.usuarios.EditarUsuarioDialog;
import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Renda;
import br.com.livroandroid.suporte_financeiro.domain.Usuario;

/**
 * Created by Rrafael on 01/11/2016.
 */

public class RendaAdapter extends RecyclerView.Adapter<RendaAdapter.RendaViewHolder> {
    protected static final String TAG="RendaSuporte";
    private List<Renda> rendas;
    private Context context;
    private RendaOnClickListener rendaOnClickListener;
    private FragmentManager fm;
    private DeletarRendaDialog deletarRendaDialog = new DeletarRendaDialog();
    private EditarRendaDialog editarRendaDialog = new EditarRendaDialog();
    private FragmentActivity activ;

    public RendaAdapter(List<Renda> rendas, Context context, RendaOnClickListener rendaOnClickListener, FragmentManager fm, FragmentActivity activity) {
        this.rendas = rendas;
        this.context = context;
        this.rendaOnClickListener = rendaOnClickListener;
        this.fm = fm;
        this.activ = activity;
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
            final Renda renda = rendas.get(position);
            holder.NomeRenda.setText(renda.getNomeRenda());
            holder.TipoRenda.setText(renda.getTipoRenda());
            holder.ValorRenda.setText(renda.getValorRenda().toString());
            holder.imgRenda.setImageResource(R.drawable.renda);
            //holder.textusuario.setText(renda.getUsuario().getId().toString());

            holder.btnDeletar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deletarRendaDialog.show(fm, new DeletarRendaDialog.Callback() {
                        @Override
                        public void onClickYes() {
                            Toast.makeText(holder.itemView.getContext(),"Deletando ["+renda.getNomeRenda()+"] usuário.",Toast.LENGTH_SHORT).show();
                            BdCoreRenda db = new BdCoreRenda(activ);
                            db.delete(renda);
                        }
                    });
                }
            });

            holder.btnAlterar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editarRendaDialog.show(fm, renda, new EditarRendaDialog.Callback() {
                        @Override
                        public void UpDateRenda(Renda renda) {
                            Toast.makeText(holder.itemView.getContext(),"Deletando ["+renda.getNomeRenda()+"] usuário.",Toast.LENGTH_SHORT).show();
                            BdCoreRenda db = new BdCoreRenda(holder.itemView.getContext());
                            db.save(renda);
                        }
                    });

                    }
                });


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
         TextView NomeRenda;
         TextView TipoRenda;
         TextView ValorRenda;
         ImageView imgRenda;
         TextView textusuario;
         Button btnDeletar;
         Button btnAlterar;
         CardView cardView;

        public RendaViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view2);
            NomeRenda = (TextView) itemView.findViewById(R.id.RendaNome);
            TipoRenda = (TextView) itemView.findViewById(R.id.RendaTipo);
            ValorRenda = (TextView) itemView.findViewById(R.id.RendaValor);
            imgRenda = (ImageView) itemView.findViewById(R.id.imgRenda);
            textusuario = (TextView) itemView.findViewById(R.id.RendaUsuario);
            btnDeletar = (Button) itemView.findViewById(R.id.deletaRenda);
            btnAlterar = (Button) itemView.findViewById(R.id.alteraRenda);
        }
    }

}
