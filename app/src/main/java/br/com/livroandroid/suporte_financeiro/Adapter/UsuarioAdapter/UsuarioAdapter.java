package br.com.livroandroid.suporte_financeiro.Adapter.UsuarioAdapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Usuario;

/**
 * Created by Rrafael on 01/11/2016.
 */

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {
    protected static final String TAG = "UsuarioSuporte";
    private List<Usuario> usuarios;
    private Context context;
    private UsuarioOnClickListener usuarioOnClickListener;

    public UsuarioAdapter(List<Usuario> usuarios, Context context, UsuarioOnClickListener usuarioOnClickListener) {
        this.usuarios = usuarios;
        this.context = context;
        this.usuarioOnClickListener = usuarioOnClickListener;
    }

    public interface UsuarioOnClickListener {
        public void OnClickUsuario(View view, int idx);
    }
    @Override
    public UsuarioAdapter.UsuarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapterusuariolayout,parent,false);
        UsuarioViewHolder holder = new UsuarioViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final UsuarioAdapter.UsuarioViewHolder holder, final int position) {
        Usuario usu = usuarios.get(position);
        holder.tNome.setText(usu.getNome());
        if (usu.getSexo() == "Masculino") {
            holder.img.setImageResource(R.drawable.masculino);
        } else {
            holder.img.setImageResource(R.drawable.masculino);
        }

        if(usuarioOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    usuarioOnClickListener.OnClickUsuario(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.usuarios != null ? this.usuarios.size() : 0;
    }

    public static class UsuarioViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tNome;
        ImageView img;
        public UsuarioViewHolder(View itemView)
        {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            tNome = (TextView) itemView.findViewById(R.id.UsuarioNome);
            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
