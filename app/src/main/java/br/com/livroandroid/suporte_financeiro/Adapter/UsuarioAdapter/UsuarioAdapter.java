package br.com.livroandroid.suporte_financeiro.Adapter.UsuarioAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
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

import java.util.List;

import br.com.livroandroid.suporte_financeiro.Fragments.usuarios.BdCoreUsuario;
import br.com.livroandroid.suporte_financeiro.Fragments.usuarios.DeletarUsuarioDialog;
import br.com.livroandroid.suporte_financeiro.Fragments.usuarios.EditarUsuarioDialog;
import br.com.livroandroid.suporte_financeiro.Fragments.usuarios.ExcluirUsuario;
import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Services.UsuarioService;
import br.com.livroandroid.suporte_financeiro.domain.Usuario;

/**
 * Created by Rrafael on 01/11/2016.
 */

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {
    protected static final String TAG = "UsuarioSuporte";
    private List<Usuario> usuarios;
    private Context context;
    private UsuarioOnClickListener usuarioOnClickListener;
    private FragmentManager fm;
    private DeletarUsuarioDialog del = new DeletarUsuarioDialog();
    private FragmentActivity activ;

    public UsuarioAdapter(List<Usuario> usuarios, Context context, UsuarioOnClickListener usuarioOnClickListener, FragmentManager fm, FragmentActivity activ) {
        this.usuarios = usuarios;
        this.context = context;
        this.usuarioOnClickListener = usuarioOnClickListener;
        this.fm = fm;
        this.activ = activ;
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
        final Usuario usu = usuarios.get(position);
        holder.tNome.setText(usu.getNome().toString());
        if(usu.getEmail() != null) {
            holder.tEmail.setText(usu.getEmail());
        }
        if (usu.getSexo() == "feminino") {
            holder.img.setImageResource(R.drawable.feminino);
        } else if (usu.getSexo() == "masculino"){
            holder.img.setImageResource(R.drawable.masculino);
        }

        holder.btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del.show(fm, new DeletarUsuarioDialog.Callback() {
                    @Override
                    public void onClickYes() {
                        Toast.makeText(holder.itemView.getContext(),"Deletando ["+usu.getNome()+"] usuário.",Toast.LENGTH_SHORT).show();
                        BdCoreUsuario db = new BdCoreUsuario(activ);
                        db.delete(usu);
                    }
                });
            }
        });

        if(usuarioOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    usuarioOnClickListener.OnClickUsuario(holder.itemView, position);
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
        TextView tEmail;
        Button btnDeletar;
        public UsuarioViewHolder(View itemView)
        {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            tNome = (TextView) itemView.findViewById(R.id.UsuarioNome);
            img = (ImageView) itemView.findViewById(R.id.img);
            tEmail = (EditText) itemView.findViewById(R.id.editTextEmail);
            btnDeletar = (Button) itemView.findViewById(R.id.deletaUsuario);
        }
    }
}
