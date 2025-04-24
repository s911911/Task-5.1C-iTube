package com.example.task51citube.ui;

import android.os.*;
import android.text.TextUtils;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.task51citube.R;
import com.example.task51citube.data.repository.UserRepository;

public class LoginFragment extends Fragment {

    @Override public View onCreateView(@NonNull LayoutInflater inf,ViewGroup c,Bundle b){
        View v = inf.inflate(R.layout.fragment_login,c,false);
        EditText etUser = v.findViewById(R.id.etUsername);
        EditText etPwd  = v.findViewById(R.id.etPassword);

        v.findViewById(R.id.btnLogin).setOnClickListener(l->{
            String u = etUser.getText().toString().trim();
            String p = etPwd .getText().toString();
            if(TextUtils.isEmpty(u)||TextUtils.isEmpty(p)){toast("Please enter account and password");return;}

            UserRepository.EXEC.execute(()->{
                boolean ok = UserRepository.get(requireContext()).login(u,p);
                new Handler(Looper.getMainLooper()).post(()->{
                    if(ok){
                        NavHostFragment.findNavController(this)
                                .navigate(R.id.action_loginFragment_to_homeFragment);
                    }else toast("Error with account or password");
                });
            });
        });

        v.findViewById(R.id.btnSignup).setOnClickListener(l->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_loginFragment_to_registerFragment));
        return v;
    }
    private void toast(String s){ Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show(); }
}

