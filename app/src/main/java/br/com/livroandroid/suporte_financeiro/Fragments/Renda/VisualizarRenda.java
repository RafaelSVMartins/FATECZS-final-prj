package br.com.livroandroid.suporte_financeiro.Fragments.Renda;


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

import br.com.livroandroid.suporte_financeiro.Adapter.RendaAdapter.RendaAdapter;
import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Renda;
import br.com.livroandroid.suporte_financeiro.domain.Services.RendaService;
import br.com.livroandroid.suporte_financeiro.domain.Services.UsuarioService;

/**
 * A simple {@link Fragment} subclass.
 */
public class VisualizarRenda extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private List<Renda> rendas;
    public VisualizarRenda() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_visualizar_renda, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progress1);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipetorefreshRenda);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerviewRenda);
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
                TaskRendas(true);
            }
        };
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TaskRendas(false);
    }

    public void TaskRendas(boolean pullrefresh) {
        new GetRendasTask(pullrefresh).execute();
    }

    private class GetRendasTask extends AsyncTask<Void,Void,List<Renda>> {
        private boolean pull;
        public GetRendasTask(boolean pullrefresh) {
            super();
            this.pull = pullrefresh;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (pull == false) {
                progressBar.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected List<Renda> doInBackground(Void... params) {
            try {
                return RendaService.getRendas(getContext());
            } catch (IOException e) {
                Log.d(e.getMessage(),String.valueOf(e));
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Renda> rendas) {
            super.onPostExecute(rendas);
            if(rendas != null) {
                VisualizarRenda.this.rendas = rendas;
                recyclerView.setAdapter(new RendaAdapter(rendas,getContext(),OnClickRenda(),getFragmentManager(),getActivity()));
            }
            if (pull == false) {
                progressBar.setVisibility(View.GONE);
            }
        }
    }

    private RendaAdapter.RendaOnClickListener OnClickRenda() {
        return new RendaAdapter.RendaOnClickListener() {
            @Override
            public void OnClickRenda(View view, int idx) {
                Renda r = rendas.get(idx);
                Toast.makeText(getContext(),"Renda: " + r.getNomeRenda(),Toast.LENGTH_SHORT).show();
            }
        };
    }
}

