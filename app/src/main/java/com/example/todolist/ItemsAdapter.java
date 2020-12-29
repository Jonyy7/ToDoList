package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnClickListener {
        void OnItemClicked(int position);
    }

    public interface OnLongClickListener {
        void OnItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;
    OnClickListener clickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener clickListener) {
        // Setting the member variable equal to the variable passed in through the constructor
        this.items = items;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    // Responsible for creating each view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use a layout inflater to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        // wrap it inside a View Holder and return it
        return new ViewHolder(todoView);
    }

    @Override
    // Responsible for taking data at a particular position and taking it into a ViewHolder (Binding data to a particular View Holder)
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Grab the item at the position
        String item = items.get(position);
        // Bind the item to the specified view holder
        holder.bind(item);
    }

    @Override
    // The number of items available in the data (Tells the RV how many items are in the list)
    public int getItemCount() {
        return items.size();
    }

    // Container to provide easy access to views that represent each row of the list
    // ViewHolder is a way to provide access to the views for each row in the list

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        // Update the view inside of the view holder with the this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.OnItemClicked(getAdapterPosition());
                }
            });
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // Notify the listener which position was long pressed
                    longClickListener.OnItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
