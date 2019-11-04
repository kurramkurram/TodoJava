package com.example.todojava;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.todojava.model.Todo;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ArrayList<Todo> mTodoList;
    private boolean mIsAllComplete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView allCompleted = findViewById(R.id.all_completed);
        final EditText entry = findViewById(R.id.todo_entry);
        final ListView listView = findViewById(R.id.todo_list_view);

        mTodoList = new ArrayList<>();

        entry.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Log.d(TAG, "#View.OnKeyListener$onKey " + entry.getText().toString());
                if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    String task = entry.getText().toString();
                    if(!"".equals(task)) {
                        Todo todo = new Todo(
                                mTodoList.size() + 1, task, false);
                        mTodoList.add(todo);
                        // ソフトキーボードを非表示
                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                                hideSoftInputFromWindow(view.getWindowToken(), 0);
                        // EditTextの内容をクリア
                        entry.getEditableText().clear();
                    }
                }

                return false;
            }
        });

        allCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIsAllComplete = !mIsAllComplete;
                for (int i = 0; i < mTodoList.size(); i++) {
                    mTodoList.get(i).setCompleted(mIsAllComplete);
                }
                TodoListAdapter adapter = (TodoListAdapter) listView.getAdapter();
                adapter.notifyDataSetChanged();
            }
        });

        TodoListAdapter adapter =
                new TodoListAdapter(this, R.layout.todo_list_view, mTodoList);
        listView.setAdapter(adapter);
    }
}
