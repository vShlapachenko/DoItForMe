package com.example.doitforme.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.doitforme.MainMenu;
import com.example.doitforme.R;
import com.example.doitforme.api.interraction.AsyncRegisterResponse;
import com.example.doitforme.api.interraction.Register;
import com.example.doitforme.data.model.UserDTO;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    Button buttonRegister;
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText password;
    EditText confirmPassword;
    Register register = new Register();

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        connectViews(view);
        buttonRegister.setOnClickListener(v -> {
            UserDTO userDTO = new UserDTO(firstName.getText().toString(), lastName.getText().toString(),
                    password.getText().toString(), confirmPassword.getText().toString(), email.getText().toString());
            Register register = new Register(userDTO, output -> {
                if (output == 201){
                    Intent intent = MainMenu.makeIntent(getContext());
                    startActivity(intent);
                }
            });
            register.execute();
        });

        return view;
    }

    public void register(View view){

    }

    private void connectViews(View view) {
        buttonRegister = view.findViewById(R.id.register_action_button);
        firstName = view.findViewById(R.id.first_name_register);
        lastName = view.findViewById(R.id.last_name_register);
        email = view.findViewById(R.id.email_register);
        password = view.findViewById(R.id.password_register);
        confirmPassword = view.findViewById(R.id.password_confirm_register);
    }
}