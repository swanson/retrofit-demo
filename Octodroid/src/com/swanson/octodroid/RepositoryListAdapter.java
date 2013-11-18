package com.swanson.octodroid;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RepositoryListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Repository> mRepositories = new ArrayList<Repository>();

    public RepositoryListAdapter(Context context) {
        super();

        mContext = context;
    }

    public void setRepositories(List<Repository> repos) {
        mRepositories = repos;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mRepositories.size();
    }

    @Override
    public Repository getItem(int i) {
        return mRepositories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.repo_item_row, null);

            RepositoryHolder holder = new RepositoryHolder();
            holder.name = (TextView) row.findViewById(R.id.repo_name);
            holder.language = (TextView) row.findViewById(R.id.repo_language);
            row.setTag(holder);
        }

        RepositoryHolder holder = (RepositoryHolder) row.getTag();
        Repository repo = mRepositories.get(position);
        holder.name.setText(repo.fullName());
        holder.language.setText(repo.language);

        return row;
    }

    static class RepositoryHolder {
        TextView name;
        TextView language;
    }
}
