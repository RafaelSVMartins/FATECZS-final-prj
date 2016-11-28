package br.com.livroandroid.suporte_financeiro.Adapter.DespeesaAdapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.livroandroid.suporte_financeiro.Fragments.despesa.AlterarDespesaDialog;
import br.com.livroandroid.suporte_financeiro.Fragments.despesa.BdCoreDespesas;
import br.com.livroandroid.suporte_financeiro.Fragments.despesa.DeletarDespesaDialog;
import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Despesa;
import br.com.livroandroid.suporte_financeiro.domain.Usuario;

/**
 * Created by Rrafael on 01/11/2016.
 */

public class DespesaAdapter extends RecyclerView.Adapter<DespesaAdapter.DespesaViewHolder> {
    protected static final String TAG="DespesaSuporte";
    private final List<Despesa> despesas;
    private final Context context;
    private FragmentManager fm;
    private FragmentActivity activi;
    private AlterarDespesaDialog alterarDespesaDialog = new AlterarDespesaDialog();
    private DeletarDespesaDialog deletarDespesaDialog = new DeletarDespesaDialog();
    private DespesaOnClickListener despesaOnClickListener;

    public DespesaAdapter(List<Despesa> despesas, Context context, FragmentManager fragmentManager, FragmentActivity activity, DespesaOnClickListener despesaOnClickListener) {
        this.despesas = despesas;
        this.context = context;
        this.fm = fragmentManager;
        this.activi = activity;
        this.despesaOnClickListener = despesaOnClickListener;
    }

    public interface  DespesaOnClickListener {
        public void onClickDespesa(View view, int idx);
    }
    @Override
    public DespesaAdapter.DespesaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_despesalayout,parent,false);
        DespesaViewHolder holder = new DespesaViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final DespesaAdapter.DespesaViewHolder holder, final int position) {
        final Despesa despesa = despesas.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        holder.DespesaNome.setText(despesa.getNomeDespesa());
        holder.DespesaValor.setText(despesa.getValorDespesa().toString());
        holder.DespesaVencimento.setText(sdf.format(despesa.getDataVencimento().getTime()));
        holder.DespesaImportancia.setText(despesa.getImportancia());
        holder.imgDespesa.setImageResource(R.drawable.despesa);


        holder.alteradespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterarDespesaDialog.show(fm,despesa,new AlterarDespesaDialog.Callback() {
                    @Override
                    public void onUpdateDespesa(Despesa despesa) {
                        Toast.makeText(holder.itemView.getContext(),"Alterar ["+despesa.getNomeDespesa()+"] despesa.",Toast.LENGTH_SHORT).show();
                        BdCoreDespesas bd = new BdCoreDespesas(holder.itemView.getContext());
                        Usuario user = new Usuario();
                        user.setId(2l);
                        user.setNome("Juan");
                        despesa.setUsuario(user);
                        bd.save(despesa);
                    }
                });
            }
        });

        holder.deletadespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletarDespesaDialog.show(fm, new DeletarDespesaDialog.Callback() {
                    @Override
                    public void onClickYes() {
                        Toast.makeText(holder.itemView.getContext(),"Deletando ["+despesa.getNomeDespesa()+"] despesa.",Toast.LENGTH_SHORT).show();
                        BdCoreDespesas bd = new BdCoreDespesas(activi);
                        bd.Delete(despesa);
                    }
                });
            }
        });

        if (despesaOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    despesaOnClickListener.onClickDespesa(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.despesas != null ? this.despesas.size() : 0;
    }

    public static class DespesaViewHolder extends RecyclerView.ViewHolder {
        TextView DespesaNome;
        TextView DespesaValor;
        TextView DespesaVencimento;
        TextView DespesaImportancia;
        Button alteradespesa;
        Button deletadespesa;
        CardView cardView;
        ImageView imgDespesa;
        public DespesaViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view3);
            DespesaNome = (TextView) itemView.findViewById(R.id.DespesaNome);
            DespesaValor = (TextView) itemView.findViewById(R.id.DespesaValor);
            DespesaVencimento = (TextView) itemView.findViewById(R.id.DespesaData);
            alteradespesa = (Button) itemView.findViewById(R.id.alteraDespesa);
            deletadespesa = (Button) itemView.findViewById(R.id.deletaDespesa);
            imgDespesa = (ImageView) itemView.findViewById(R.id.imgDespesa);
            DespesaImportancia = (TextView) itemView.findViewById(R.id.importanciaDespesa);
        }
    }
}
