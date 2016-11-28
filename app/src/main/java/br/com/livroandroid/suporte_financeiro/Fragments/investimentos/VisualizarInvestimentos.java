package br.com.livroandroid.suporte_financeiro.Fragments.investimentos;


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

import br.com.livroandroid.suporte_financeiro.Adapter.InvestimentoAdapter.InvestimentoAdapter;
import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Investimento;
import br.com.livroandroid.suporte_financeiro.domain.Services.InvestimentoService;

/**
 * A simple {@link Fragment} subclass.
 */
public class VisualizarInvestimentos extends Fragment {

    private SwipeRefreshLayout sweep;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private List<Investimento> investimentos;
    public VisualizarInvestimentos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_visualizar_investimentos, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progress1);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerviewinvestimento);
        sweep = (SwipeRefreshLayout) view.findViewById(R.id.swipetorefreshinvestimento);
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
                taskInvestimento(true);
            }
        };
    }

    private void taskInvestimento(boolean pullRefresh) {
        new GetInvestimentoTask(pullRefresh).execute();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        taskInvestimento(false);
    }

    private  class GetInvestimentoTask extends AsyncTask<Void,Void,List<Investimento>> {
        private boolean pull;
        public GetInvestimentoTask(boolean pullRefresh) {
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
        protected List<Investimento> doInBackground(Void... params) {
            try {
                return InvestimentoService.getInvestimentos(getContext());
            } catch (IOException e) {
                Log.d(e.getMessage(),String.valueOf(e));
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Investimento> investimentos) {
            super.onPostExecute(investimentos);
            if(investimentos != null) {
                VisualizarInvestimentos.this.investimentos = investimentos;
                recyclerView.setAdapter(new InvestimentoAdapter(investimentos,getContext(), investimentoOnClickListener(), getFragmentManager(), getActivity()));
            }
            if(pull == false) {
                progressBar.setVisibility(View.GONE);
            }
        }

    }

    private InvestimentoAdapter.InvestimentoOnClickListener investimentoOnClickListener() {
        return new InvestimentoAdapter.InvestimentoOnClickListener() {
            @Override
            public void OnClickInvestimento(View view, int idx) {
                Investimento investimento = investimentos.get(idx);
                Toast.makeText(getContext(),"Usuario: " + investimento.getNomeInvestimento(), Toast.LENGTH_SHORT).show();
            }
        };
    }

}
