package br.com.livroandroid.suporte_financeiro.Adapter.ProdutoAdapter;

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

import br.com.livroandroid.suporte_financeiro.Fragments.Produto.BdCoreProduto;
import br.com.livroandroid.suporte_financeiro.Fragments.Produto.DeletarProdutoDialog;
import br.com.livroandroid.suporte_financeiro.Fragments.Produto.EditarProdutoDialog;
import br.com.livroandroid.suporte_financeiro.Fragments.Renda.BdCoreRenda;
import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Produto;

/**
 * Created by Rrafael on 08/12/2016.
 */

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {
    protected static final String TAG = "ProdutoSuporte";
    private Context context;
    private List<Produto> produtos;
    private ProdutoOnClicklistener produtoOnClicklistener;
    private FragmentManager fm;
    private EditarProdutoDialog predit = new EditarProdutoDialog();
    private DeletarProdutoDialog deletarProdutoDialog = new DeletarProdutoDialog();
    private FragmentActivity activ;
    public ProdutoAdapter(Context context, List<Produto> produtos, ProdutoOnClicklistener produtoOnClicklistener, FragmentManager fm, FragmentActivity activ) {
        this.context = context;
        this.produtos = produtos;
        this.produtoOnClicklistener = produtoOnClicklistener;
        this.fm = fm;
        this.activ = activ;
    }

    public interface ProdutoOnClicklistener {
        public void OnClickProduto(View view, int idx);
    }
    @Override
    public ProdutoAdapter.ProdutoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_produto_layout,parent,false);
        ProdutoViewHolder holder = new ProdutoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ProdutoAdapter.ProdutoViewHolder holder, final int position) {
        final Produto produto = produtos.get(position);
        holder.nomeproduto.setText(produto.getNomeProduto());
        holder.quantidadeproduto.setText(produto.getQuantidadeProduto().toString());
        holder.imagemPorduto.setImageResource(R.drawable.ico_ecommerce);

        holder.btndeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletarProdutoDialog.show(fm, new DeletarProdutoDialog.Callback() {
                    @Override
                    public void onClickYes() {
                        Toast.makeText(holder.itemView.getContext(),"Deletando ["+produto.getNomeProduto()+"] produto.",Toast.LENGTH_SHORT).show();
                        BdCoreProduto db = new BdCoreProduto(activ);
                    }
                });
            }
        });

        holder.btnalterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                predit.show(fm, produto, new EditarProdutoDialog.Callback() {
                    @Override
                    public void UpDateProduto(Produto produto) {
                        Toast.makeText(holder.itemView.getContext(),"Alterando ["+produto.getNomeProduto()+"] produto.",Toast.LENGTH_SHORT).show();
                        BdCoreProduto db = new BdCoreProduto(holder.itemView.getContext());
                        db.save(produto);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.produtos != null ? this.produtos.size() : 0;
    }

    public static class ProdutoViewHolder extends RecyclerView.ViewHolder {
        TextView nomeproduto;
        TextView quantidadeproduto;
        Button btnalterar;
        Button btndeletar;
        ImageView imagemPorduto;
        CardView cardView;
        public ProdutoViewHolder(View itemView)
        {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view5);
            imagemPorduto =(ImageView) itemView.findViewById(R.id.imgProduto);
            nomeproduto = (TextView) itemView.findViewById(R.id.ProdutoNome);
            quantidadeproduto = (TextView) itemView.findViewById(R.id.ProdutoQuantidade);
            btnalterar = (Button) itemView.findViewById(R.id.alteraProduto);
            btndeletar = (Button) itemView.findViewById(R.id.deletaProduto);
        }

    }
}
