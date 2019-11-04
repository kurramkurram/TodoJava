package com.example.todojava;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.todojava.model.Todo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

public class TodoListAdapter extends ArrayAdapter<Todo> {

    private int mResource;
    private List<Todo> mItems;
    private LayoutInflater mInflater;

    public TodoListAdapter(@NonNull Context context, int resource, @NonNull List<Todo> item) {
        super(context, resource, item);
        mResource = resource;
        mItems = item;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            view = mInflater.inflate(mResource, null);
        }

        View isCompleted = view.findViewById(R.id.todo_list_is_completed);
        TextView todoTask = view.findViewById(R.id.todo_list_content);
        View delete = view.findViewById(R.id.todo_list_delete);

        final Todo item = mItems.get(position);

        todoTask.setText(item.getTask());

        int resId = item.isCompleted() ?
                R.drawable.round_icon_complete : R.drawable.round_icon;
        Drawable res = ResourcesCompat.getDrawable(getContext().getResources(), resId, null);
        isCompleted.setBackground(res);
        isCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setCompleted(!item.isCompleted());
                notifyDataSetChanged();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItems.remove(position);
                notifyDataSetChanged();
            }
        });
        return view;
    }
}