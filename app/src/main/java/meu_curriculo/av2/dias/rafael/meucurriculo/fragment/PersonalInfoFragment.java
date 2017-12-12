package meu_curriculo.av2.dias.rafael.meucurriculo.fragment;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import meu_curriculo.av2.dias.rafael.meucurriculo.R;
import meu_curriculo.av2.dias.rafael.meucurriculo.dao.CurriculumDAO;
import meu_curriculo.av2.dias.rafael.meucurriculo.util.CEPCrawler;

/**
 * Created by rafadias on 09/12/17.
 */

public class PersonalInfoFragment extends Fragment implements View.OnClickListener, View.OnFocusChangeListener{

    private View fragmentView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Informações pessoais");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
        fragmentView = inflater.inflate(R.layout.personal_info, container, false);
        Button b = (Button) fragmentView.findViewById(R.id.button_send);
        b.setOnClickListener(this);
        EditText editCep = fragmentView.findViewById(R.id.edit_cep);
        editCep.setOnFocusChangeListener(this);
        return fragmentView;
    }

    @Override
    public void onClick(View view) {
        //carregando os campos
        EditText txtNome = getView().findViewById(R.id.edit_name);
        EditText txEmail = getView().findViewById(R.id.edit_email);
        EditText txPhone = getView().findViewById(R.id.edit_phone);
        EditText txCEP = getView().findViewById(R.id.edit_cep);
        EditText txtRua = getView().findViewById(R.id.edit_street);
        EditText txCidade = getView().findViewById(R.id.edit_city);
        EditText txEstado = getView().findViewById(R.id.edit_uf);

        String nome = txtNome.getText().toString();
        String email = txEmail.getText().toString();
        String phone = txPhone.getText().toString();
        String cep = txCEP.getText().toString();
        String rua = txtRua.getText().toString();
        String cidade = txCidade.getText().toString();
        String estado = txEstado.getText().toString();

        //salvando os dados
        CurriculumDAO dao = new CurriculumDAO(view.getContext());
        boolean sucesso = dao.salvar(nome, email, phone, cep, rua, cidade, estado);
        if(sucesso) {
            Snackbar.make(view, "Dados inseridos com sucesso.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    @Override
    public void onFocusChange(View editTextView, boolean b) {
        // Get the search string from the input field.
        EditText cepView = fragmentView.findViewById(R.id.edit_cep);
        EditText streetView = fragmentView.findViewById(R.id.edit_street);
        EditText cityView = fragmentView.findViewById(R.id.edit_city);
        EditText ufView = fragmentView.findViewById(R.id.edit_uf);

        String queryString = cepView.getText().toString();

        // Check the status of the network connection.
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If the network is active and the search field is not empty, start a FetchBook AsyncTask.
        if (networkInfo != null && networkInfo.isConnected() && queryString.length()!=0) {
            new CEPCrawler(streetView, cityView, ufView).execute(queryString);
        }
    }
}
