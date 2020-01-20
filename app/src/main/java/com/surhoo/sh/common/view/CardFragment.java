package com.surhoo.sh.common.view;


import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.surhoo.sh.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends Fragment {


    private CardView mCardView;


    public CardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_home_artist_cardview, container, false);
        mCardView = (CardView) view.findViewById(R.id.item_home_artist_card);
        mCardView.setMaxCardElevation(mCardView.getCardElevation()
                * CardAdapter.MAX_ELEVATION_FACTOR);
        return view;
    }

    public CardView getCardView() {
        return mCardView;
    }

}
