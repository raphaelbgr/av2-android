package meu_curriculo.av2.dias.rafael.meucurriculo.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import meu_curriculo.av2.dias.rafael.meucurriculo.R;
import meu_curriculo.av2.dias.rafael.meucurriculo.dao.CurriculumDAO;
import meu_curriculo.av2.dias.rafael.meucurriculo.model.Curriculum;


public class ProfileFragment extends Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Perfil");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile, container, false);
        CurriculumDAO dao = new CurriculumDAO(view.getContext());
        Curriculum cv = dao.retornarUltimo();
        TextView txView = view.findViewById(R.id.tx_name);
        txView.setText(cv.getName());
        return view;
    }
}
