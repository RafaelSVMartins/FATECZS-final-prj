package br.com.livroandroid.suporte_financeiro.Fragments.despesa;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import br.com.livroandroid.suporte_financeiro.Adapter.DespeesaAdapter.DespesaAdapter;
import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Despesa;
import br.com.livroandroid.suporte_financeiro.domain.Services.DespesaService;
import br.com.livroandroid.suporte_financeiro.domain.Usuario;

/**
 * A simple {@link Fragment} subclass.
 */
public class VisualizarDespesas extends Fragment {

    private SwipeRefreshLayout sweep;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private List<Despesa> despesas;

    public VisualizarDespesas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_visualizar_despesas, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progress1);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerviewDespesas);
        sweep = (SwipeRefreshLayout) view.findViewById(R.id.swipetorefreshDespesas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        sweep.setOnRefreshListener(OnRefreshListener());
        sweep.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3);
        return view;
    }

    private SwipeRefreshLayout.OnRefreshListener OnRefreshListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                taskDespesa(true);
            }
        };
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        taskDespesa(false);
    }

    private void taskDespesa(boolean pullRefresh) {
        new GetDespesasTask(pullRefresh).execute();
    }

    private class GetDespesasTask extends AsyncTask<Void,Void,List<Despesa>> {
        private boolean pull;
        public GetDespesasTask(boolean pullRefresh) {
            super();
            pull=pullRefresh;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(pull == false) {
                progressBar.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected List<Despesa> doInBackground(Void... params) {
            try {
                return DespesaService.getDespesas(getContext());
            } catch (IOException e) {
                Log.d(e.getMessage(),String.valueOf(e));
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Despesa> despesas) {
            super.onPostExecute(despesas);
            if (despesas != null) {
                VisualizarDespesas.this.despesas = despesas;
                recyclerView.setAdapter(new DespesaAdapter(despesas,getContext(), getFragmentManager(), getActivity(), despesaOnClickListener()));
            }
            if(pull == false) {
                progressBar.setVisibility(View.GONE);
            }
        }
    }

    private DespesaAdapter.DespesaOnClickListener despesaOnClickListener() {
        return new DespesaAdapter.DespesaOnClickListener() {
            @Override
            public void onClickDespesa(View view, int idx) {
                Despesa d = despesas.get(idx);
                Toast.makeText(getContext(),"Usuario: " + d.getNomeDespesa(), Toast.LENGTH_SHORT).show();
            }
        };
    }

}
