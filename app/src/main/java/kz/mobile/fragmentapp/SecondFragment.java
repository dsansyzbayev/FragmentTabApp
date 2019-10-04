package kz.mobile.fragmentapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    public static SecondFragment newInstance(Bundle bundle) {
        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private TextView textView;
    private TextView numberOfLikes;

    private String name;

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        name = args.getString("name");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = view.findViewById(R.id.name);

        if (name != null) {
            textView.setText(name);
        }

        numberOfLikes = (TextView) view.findViewById(R.id.textView2);

    }

    public void displayReceivedData(String counter) {
        numberOfLikes.setText("Number of Likes: " + counter);
    }

}
