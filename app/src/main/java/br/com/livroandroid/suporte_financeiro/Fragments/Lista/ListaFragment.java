package br.com.livroandroid.suporte_financeiro.Fragments.Lista;


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

import br.com.livroandroid.suporte_financeiro.Adapter.ProdutoAdapter.ProdutoAdapter;
import br.com.livroandroid.suporte_financeiro.Adapter.RendaAdapter.RendaAdapter;
import br.com.livroandroid.suporte_financeiro.Fragments.Renda.VisualizarRenda;
import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Produto;
import br.com.livroandroid.suporte_financeiro.domain.Renda;
import br.com.livroandroid.suporte_financeiro.domain.Services.ProdutoService;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaFragment extends Fragment {

    private SwipeRefreshLayout sweep;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private List<Produto> produtos;
    public ListaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progress1);
        sweep = (SwipeRefreshLayout) view.findViewById(R.id.swipetorefreshProduto);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerviewProduto);
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
                TaskP(true);
            }
        };
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TaskP(false);
    }

    public void TaskP(boolean pullrefresh) {
         new GetProdutoTask(pullrefresh).execute();
    }

    private class GetProdutoTask extends AsyncTask<Void,Void,List<Produto>> {
        private boolean pull;
        public GetProdutoTask(boolean pullrefresh) {
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
        protected List<Produto> doInBackground(Void... params) {
            try {
                return ProdutoService.getProdutos(getContext());
            } catch (IOException e) {
                Log.d(e.getMessage(),String.valueOf(e));
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Produto> produtos) {
            super.onPostExecute(produtos);
            if(produtos != null) {
                ListaFragment.this.produtos = produtos;
                recyclerView.setAdapter(new ProdutoAdapter(getContext(), produtos,OnClickProduto(),getFragmentManager(),getActivity()));
            }
            if (pull == false) {
                progressBar.setVisibility(View.GONE);
            }
        }
    }

        private ProdutoAdapter.ProdutoOnClicklistener OnClickProduto() {
            return new ProdutoAdapter.ProdutoOnClicklistener() {
                @Override
                public void OnClickProduto(View view, int idx) {
                    Produto p = produtos.get(idx);
                    Toast.makeText(getContext(),"Produto: " + p.getNomeProduto(),Toast.LENGTH_SHORT).show();
                }
            };
        }
}
