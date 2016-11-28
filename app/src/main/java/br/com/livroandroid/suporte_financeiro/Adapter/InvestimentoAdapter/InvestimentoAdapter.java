package br.com.livroandroid.suporte_financeiro.Adapter.InvestimentoAdapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.livroandroid.suporte_financeiro.Fragments.investimentos.AlterarInvestimentoDialog;
import br.com.livroandroid.suporte_financeiro.Fragments.investimentos.BdCoreInvestimentos;
import br.com.livroandroid.suporte_financeiro.Fragments.investimentos.DeletarInvestimentoDialog;
import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Investimento;
import br.com.livroandroid.suporte_financeiro.domain.Usuario;

import static br.com.livroandroid.suporte_financeiro.R.drawable.despesa;

/**
 * Created by Rrafael on 01/11/2016.
 */

public class InvestimentoAdapter extends RecyclerView.Adapter<InvestimentoAdapter.InvestimentoViewHolder> {
    protected static final String TAG = "InvestimentoSuporte";
    private List<Investimento> investimentos;
    private Context context;
    private InvestimentoOnClickListener investimentoOnClickListener;
    private FragmentManager fm;
    private FragmentActivity activi;
    private AlterarInvestimentoDialog alterarInvestimentoDialog = new AlterarInvestimentoDialog();
    private DeletarInvestimentoDialog deletarInvestimentoDialog = new DeletarInvestimentoDialog();

    public interface InvestimentoOnClickListener {
        public void OnClickInvestimento(View view, int idx);
    }

    public InvestimentoAdapter(List<Investimento> investimentos, Context context, InvestimentoOnClickListener investimentoOnClickListener, FragmentManager fm, FragmentActivity activi) {
        this.investimentos = investimentos;
        this.context = context;
        this.investimentoOnClickListener = investimentoOnClickListener;
        this.fm = fm;
        this.activi = activi;
    }

    @Override
    public InvestimentoAdapter.InvestimentoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapterinvestimentos,parent,false);
        InvestimentoViewHolder holder = new InvestimentoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final InvestimentoAdapter.InvestimentoViewHolder holder, final int position) {
        final Investimento investimento = investimentos.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        holder.NomeInvestimento.setText(investimento.getNomeInvestimento());
        holder.ValorInvestimento.setText(investimento.getValorInvestimento().toString());
        holder.DataInvestimento.setText(sdf.format(investimento.getVencimentoInvestimento().getTime()));
        holder.imgInvestimento.setImageResource(R.drawable.investimento);

        holder.btndeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletarInvestimentoDialog.show(fm, new DeletarInvestimentoDialog.Callback() {
                    @Override
                    public void onClickYes() {
                        Toast.makeText(holder.itemView.getContext(),"Deletando ["+investimento.getNomeInvestimento()+"] investimento.",Toast.LENGTH_SHORT).show();
                        BdCoreInvestimentos db = new BdCoreInvestimentos(activi);
                        db.delete(investimento);
                    }
                });
            }
        });

        holder.btnalterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(),"Deletando ["+investimento.getNomeInvestimento()+"] investimento.",Toast.LENGTH_SHORT).show();
                alterarInvestimentoDialog.show(fm, investimento, new AlterarInvestimentoDialog.Callback() {
                    @Override
                    public void OnUpdateInvestimento(Investimento investimento) {
                        BdCoreInvestimentos db = new BdCoreInvestimentos(holder.itemView.getContext());
                        Usuario user = new Usuario();
                        user.setId(2l);
                        user.setNome("Juan");
                        investimento.setUsuario(user);
                        db.save(investimento);
                    }
                });
            }
        });

        if(investimentoOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        investimentoOnClickListener.OnClickInvestimento(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.investimentos != null ? this.investimentos.size() : 0;
    }

    public static class InvestimentoViewHolder extends RecyclerView.ViewHolder {
            TextView NomeInvestimento;
            TextView ValorInvestimento;
            Button btnalterar;
            Button btndeletar;
            TextView DataInvestimento;
            ImageView imgInvestimento;
        public InvestimentoViewHolder(View itemView) {
            super(itemView);
            NomeInvestimento = (TextView) itemView.findViewById(R.id.investimentonome);
            ValorInvestimento = (TextView) itemView.findViewById(R.id.investimentovalor);
            btnalterar = (Button) itemView.findViewById(R.id.alterainvestimento);
            btndeletar = (Button) itemView.findViewById(R.id.deletainvestimento);
            DataInvestimento = (TextView) itemView.findViewById(R.id.datainvestimento);
            imgInvestimento = (ImageView) itemView.findViewById(R.id.imginvestimento);
        }
    }

}
