package com.example.a51900035_51900087_51900593.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a51900035_51900087_51900593.Model.Lichsu;
import com.example.a51900035_51900087_51900593.Adapter.LichsuAdapter;
import com.example.a51900035_51900087_51900593.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SuachuaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SuachuaFragment extends Fragment {

    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference _myRef;
    RecyclerView rvSuachua;
    List<Lichsu> lstLichsu;
    ArrayList<String> mKeys = new ArrayList<>();
    LichsuAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SuachuaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SuachuaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SuachuaFragment newInstance(String param1, String param2) {
        SuachuaFragment fragment = new SuachuaFragment();
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
        View view = inflater.inflate(R.layout.fragment_suachua, container, false);
        rvSuachua = view.findViewById(R.id.rvSuachua);
        getData();
        rvSuachua.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new LichsuAdapter(getContext(),lstLichsu);
        rvSuachua.setAdapter(adapter);
        return view;
    }

    public void getData(){
        lstLichsu =new ArrayList<>();
        _myRef = mDatabase.getReference("Suachua");
        _myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Lichsu ls = snapshot.getValue(Lichsu.class);
                if (ls != null) {
                    lstLichsu.add(ls);
                    String key = snapshot.getKey();
                    mKeys.add(key);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Lichsu ls = snapshot.getValue(Lichsu.class);
                if (ls == null || lstLichsu == null || lstLichsu.isEmpty())
                    return;
                String key = snapshot.getKey();
                int index = mKeys.indexOf(key);
                lstLichsu.set(index, ls);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Lichsu ls = snapshot.getValue(Lichsu.class);
                if (ls == null || lstLichsu == null || lstLichsu.isEmpty())
                    return;
                String key = snapshot.getKey();
                int index = mKeys.indexOf(key);
                if (index != -1) {
                    lstLichsu.remove(index);
                    mKeys.remove(index);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (adapter != null)
            adapter.release();
    }
}