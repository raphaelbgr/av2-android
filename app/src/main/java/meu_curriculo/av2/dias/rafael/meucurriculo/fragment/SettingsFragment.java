package meu_curriculo.av2.dias.rafael.meucurriculo.fragment;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import meu_curriculo.av2.dias.rafael.meucurriculo.R;



public class SettingsFragment extends Fragment implements View.OnClickListener{

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Configurações");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings, container, false);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Boolean result = sharedPref.getBoolean(getString(R.string.notification_ask), false);
        Log.d("Resultado", result.toString());
        CheckBox btnNotification = view.findViewById(R.id.btn_notification);
        btnNotification.setOnClickListener(this);
        btnNotification.setChecked(result);
        return view;
    }

    @Override
    public void onClick(View view) {
        Context context = getActivity();
        CheckBox btnNotification = getActivity().findViewById(R.id.btn_notification);
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(getString(R.string.notification_ask), btnNotification.isChecked());
        editor.apply();

    }
}
