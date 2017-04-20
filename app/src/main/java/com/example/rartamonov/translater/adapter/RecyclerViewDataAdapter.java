package com.example.rartamonov.translater.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.rartamonov.translater.R;
import com.example.rartamonov.translater.helper.RealmWorkingWithTables;
import com.example.rartamonov.translater.realm.FavouriteHistory;

import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;


public class RecyclerViewDataAdapter extends RecyclerView.Adapter<RecyclerViewDataAdapter.DataHolder>{

    private LayoutInflater mInflater;
    private Realm mRealm;
    private RealmResults<FavouriteHistory> mResults;

    public RecyclerViewDataAdapter(LayoutInflater inflater, Realm realm, RealmResults<FavouriteHistory> results) {
        mRealm = realm;
        mInflater = inflater;
        setResults(results);
    }

    public FavouriteHistory getItem(int position) {
        return mResults.get(position);
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recycler, parent, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        FavouriteHistory data = mResults.get(position);
        holder.setData(data.getWordFrom(), data.getWordTo(),data.getDirect(),data.getFavourite());
    }

    public void setResults(RealmResults<FavouriteHistory> results) {
        mResults = results;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    public void add(String from, String to, String direction, Boolean favourite, Boolean history) {

        //Create a new object that contains the data we want to add
        FavouriteHistory mFavourite = new FavouriteHistory();
        mFavourite.setWordFrom(from);
        mFavourite.setWordTo(to);
        mFavourite.setDirect(direction);
        mFavourite.setHistory(history);
        mFavourite.setFavourite(favourite);

        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(mFavourite);
        mRealm.commitTransaction();

        //Tell the Adapter to update what it shows.
        notifyDataSetChanged();
    }

    public void remove(int position) {

        mRealm.beginTransaction();
        mResults.deleteFromRealm(position);
        mRealm.commitTransaction();

        notifyItemRemoved(position);
    }

    public static class DataHolder extends RecyclerView.ViewHolder {

        ToggleButton toggle_fav;
        TextView text_word;
        TextView text_translation;
        TextView text_direction;

        private DataHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            toggle_fav = (ToggleButton) itemView.findViewById(R.id.toggle_fav);
            text_word = (TextView)itemView.findViewById(R.id.text_word);
            text_translation = (TextView)itemView.findViewById(R.id.text_translation);
            text_direction = (TextView)itemView.findViewById(R.id.text_direction);
            final Realm realm = Realm.getDefaultInstance();

            toggle_fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // добавляем в избранное
                    if (toggle_fav.isChecked()) {
                        RealmWorkingWithTables.addToFavouriteOrHistory(realm, text_word.getText().toString(), text_translation.getText().toString(), text_direction.getText().toString(), true, true);
                    } else { // убираем из избранного
                        RealmWorkingWithTables.addToFavouriteOrHistory(realm, text_word.getText().toString(), text_translation.getText().toString(), text_direction.getText().toString(), false, true);
                    }
                }
            });
        }

        private void setData(String from, String to, String direction, Boolean favourite) {
            text_word.setText(from);
            text_translation.setText(to);
            text_direction.setText(direction);
            toggle_fav.setChecked(favourite);
        }

    }
}
