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

public class RegisterFragment extends Fragment {

    @Override public View onCreateView(@NonNull LayoutInflater inf, ViewGroup c, Bundle b) {
        View v = inf.inflate(R.layout.fragment_register, c, false);
        EditText name = v.findViewById(R.id.etFullName);
        EditText user = v.findViewById(R.id.etRegUser);
        EditText p1   = v.findViewById(R.id.etRegPwd);
        EditText p2   = v.findViewById(R.id.etRegPwd2);

        v.findViewById(R.id.btnCreate).setOnClickListener(l -> {
            String n  = name.getText().toString().trim();
            String u  = user.getText().toString().trim();
            String pw1 = p1.getText().toString();
            String pw2 = p2.getText().toString();

            if (TextUtils.isEmpty(n) || TextUtils.isEmpty(u) || TextUtils.isEmpty(pw1)) {
                toast("Please fill in all fields");
                return;
            }
            if (!pw1.equals(pw2)) {
                toast("Passwords do not match");
                return;
            }

            UserRepository.EXEC.execute(() -> {
                boolean ok = UserRepository.get(requireContext()).register(u, n, pw1);
                new Handler(Looper.getMainLooper()).post(() -> {
                    if (ok) {
                        toast("Registration successful, please log in");
                        NavHostFragment.findNavController(this)
                                .navigate(R.id.action_registerFragment_to_loginFragment);
                    } else {
                        toast("Username already exists");
                    }
                });
            });
        });
        return v;
    }

    private void toast(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }
}


