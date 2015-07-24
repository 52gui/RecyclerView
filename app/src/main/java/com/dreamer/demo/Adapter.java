package com.dreamer.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dreamer on 2015/7/24.
 */
public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Article> articles;
    private SparseBooleanArray selectedItems;

    private ItemClickListener itemClickListener;

    public Adapter(Context context, List<Article> articles) {
        this.articles = articles;
        this.context = context;
        selectedItems = new SparseBooleanArray();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        myViewHolder.title.setText(articles.get(position).getTitle());
        myViewHolder.content.setText(articles.get(position).getContent());
        viewHolder.itemView.setSelected(selectedItems.get(position, false));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private TextView content;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(!selectedItems.get(getLayoutPosition())){
                for (int i = 0; i < articles.size(); i++) {
                    selectedItems.put(i, false);
                }
                selectedItems.put(getLayoutPosition(), true);
                notifyDataSetChanged();
                if (itemClickListener != null)
                    itemClickListener.onItemClick(getLayoutPosition());
            }
        }
    }

    interface ItemClickListener {
        public void onItemClick(int position);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
