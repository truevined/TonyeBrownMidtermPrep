package tonyebrown.tonyebrown_midterm;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Description extends Fragment {

    private OnFragmentInteractionListener mListener;

    TextView My_txt;


    public Description() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("city", My_txt.getText().toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_description, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        My_txt = (TextView)getActivity().findViewById(R.id.textView02);
        My_txt.setMovementMethod(new ScrollingMovementMethod());
        if (savedInstanceState == null){
            My_txt.setText("Welcome to COMP2160 Fragment tutorial");
        }else{
            My_txt.setText(savedInstanceState.getString("city", ""));
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public void updateText(String data){
        if (data.equals(null)){
            My_txt.setText("Welcome to COMP2160 Fragment tutorial");
        }else {
               Resources res = getResources();
                String[] cities = res.getStringArray(R.array.desc);
                switch (data) {

                    case "Show Fragment":
                        //My_txt.setText(cities[0]);
                        break;
                    case "Hide Fragment":
                        My_txt.setText("Fragment shown");
                        break;
                    default:
                        //My_txt.setText(cities[2]);
                        break;
                }
        }


    }

    public void showHideFragment(final Fragment fragment){

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.animator.fade_in,
                android.R.animator.fade_out);

        if (fragment.isHidden()) {
            ft.show(fragment);
            Log.d("hidden", "Show");
        } else {
            ft.hide(fragment);
            Log.d("Shown","Hide");
        }

        ft.commit();
    }

    public interface OnFragmentInteractionListener {
        //
       // public void onFragmentInteraction(String data);

    }

}
