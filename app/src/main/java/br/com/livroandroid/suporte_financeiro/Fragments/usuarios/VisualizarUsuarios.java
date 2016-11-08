package br.com.livroandroid.suporte_financeiro.Fragments.usuarios;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import br.com.livroandroid.suporte_financeiro.Adapter.UsuarioAdapter.UsuarioAdapter;
import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Services.UsuarioService;
import br.com.livroandroid.suporte_financeiro.domain.Usuario;

/**
 * A simple {@link Fragment} subclass.
 */
public class VisualizarUsuarios extends Fragment {
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private List<Usuario> usuarios;
    public VisualizarUsuarios() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_visualizar_usuarios, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progress1);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipetorefresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        swipeRefreshLayout.setOnRefreshListener(OnRefreshListener());
        swipeRefreshLayout.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3);
        return view;
    }

    private SwipeRefreshLayout.OnRefreshListener OnRefreshListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                taskUsuario(true);
            }
        };
    }

    private void taskUsuario(boolean pullRefresh) {
        new GetUsuariosTask(pullRefresh).execute();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        taskUsuario(false);
    }

    private class GetUsuariosTask extends AsyncTask<Void,Void,List<Usuario>> {
        private boolean pull;
        public GetUsuariosTask(boolean pullRefresh) {
            super();
            pull= pullRefresh;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(pull == false) {
                progressBar.setVisibility(View.VISIBLE);
            }
        }


        @Override
        protected List<Usuario> doInBackground(Void... params) {
            try {
               return UsuarioService.getUsuarios(getContext());
            } catch (IOException e) {
                Log.d(e.getMessage(),String.valueOf(e));
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Usuario> usuarios) {
            super.onPostExecute(usuarios);
            if(usuarios != null) {
                VisualizarUsuarios.this.usuarios = usuarios;
                recyclerView.setAdapter(new UsuarioAdapter(usuarios,getContext(),onClickUsuario()));
            }
            if(pull == false) {
                progressBar.setVisibility(View.GONE);
            }
        }
    }

    UsuarioAdapter.UsuarioOnClickListener onClickUsuario() {
        return new UsuarioAdapter.UsuarioOnClickListener() {
            @Override
            public void OnClickUsuario(View view, int idx) {
                Usuario u = usuarios.get(idx);
                Toast.makeText(getContext(),"Usuario: " + u.getNome(), Toast.LENGTH_SHORT).show();
            }
        };
    }
}
