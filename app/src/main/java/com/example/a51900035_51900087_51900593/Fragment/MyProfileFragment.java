package com.example.a51900035_51900087_51900593.Fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a51900035_51900087_51900593.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyProfileFragment extends Fragment {

    String personName;
    String personGivenName;
    String personFamilyName;
    String personEmail;
    String personId;
    Uri personPhoto;
    TextInputEditText etFullname, etGivenname, etFamilyname, etEmail, etId;
    ImageView img_avatar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyProfileFragment newInstance(String param1, String param2) {
        MyProfileFragment fragment = new MyProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getContext());
        personName = acct.getDisplayName();
        personGivenName = acct.getGivenName();
        personFamilyName = acct.getFamilyName();
        personEmail = acct.getEmail();
        personId = acct.getId();
        personPhoto = acct.getPhotoUrl();
        ((TextInputEditText) view.findViewById(R.id.etFullname)).setText("Full name: "+personName);
        ((TextInputEditText) view.findViewById(R.id.etGivenname)).setText("Given name: "+personGivenName);
        ((TextInputEditText) view.findViewById(R.id.etFamilyname)).setText("Family name: "+personFamilyName);
        ((TextInputEditText) view.findViewById(R.id.etEmail)).setText("Email: "+personEmail);
        ((TextInputEditText) view.findViewById(R.id.etId)).setText("Id: "+personId);
        Glide.with(this).load(personPhoto).into(((ImageView) view.findViewById(R.id.img_avatar)));
        return view;
    }


//    public void showUserInformation(){
//
//
////        etFullname.setText("Full name: "+personName);
////        etGivenname.setText("Given name: "+personGivenName);
////        etFamilyname.setText("Family name: "+etFamilyname);
////        etEmail.setText("Email: "+etEmail);
////        etId.setText("Id: "+etId);
////        Glide.with(this).load(personPhoto).error(R.drawable.ic_avatart_default).into(img_avatar);
//        else{
//            Toast.makeText(getContext(), "Null", Toast.LENGTH_SHORT).show();
//        }
//
//    }
}