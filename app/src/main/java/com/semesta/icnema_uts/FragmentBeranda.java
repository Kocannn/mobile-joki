package com.semesta.icnema_uts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentBeranda extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private List<Movie> movieList;

    public FragmentBeranda() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.beranda, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView_movies);

        // Set LayoutManager
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); // 2 columns

        // Initialize Movie List
        movieList = new ArrayList<>();
        movieList.add(new Movie("La La Land", R.drawable.afilm1));
        movieList.add(new Movie("The Joker", R.drawable.afilm2));
        movieList.add(new Movie("Pulp Fiction", R.drawable.afilm3));
        movieList.add(new Movie("FightClub", R.drawable.afilm4));
        movieList.add(new Movie("Inglorious Basterd", R.drawable.afilm5));
        movieList.add(new Movie("Ocean 8", R.drawable.afilm6));
        movieList.add(new Movie("Oppenheimer", R.drawable.afilm7));
        movieList.add(new Movie("Arcane", R.drawable.afilm8));

        // Set Adapter
        adapter = new MovieAdapter(getContext(), movieList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
