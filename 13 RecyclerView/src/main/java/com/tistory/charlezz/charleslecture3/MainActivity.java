package com.tistory.charlezz.charleslecture3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        rv = (RecyclerView) findViewById(R.id.rv);
        //linear, staggered, grid를 레이아웃 매니저로 결정
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyAdapter());
    }


    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {


        private String[] items = {"동해물과", "백두산이", "마르고", "닳도록", "하느님이", "보우하사", "우리나라", "만세", "무궁화", "삼천리", "화려강산"};

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);

            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.tv.setText(items[position]);
            holder.btn.setText("pos:" + position);

            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "position " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return items.length;
        }
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        private Button btn;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
            btn = (Button) itemView.findViewById(R.id.btn);
        }
    }


}
