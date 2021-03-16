package br.com.programadorjm.chucknorrisapiretrofitandpicasso;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    final private List<String> categoryList;
    final private onClickListener onClickListener;

    public MainAdapter(List<String> categoryList, MainAdapter.onClickListener onClickListener) {
        this.categoryList = categoryList;
        this.onClickListener = onClickListener;
    }

    public List<String> getCategoryList() {
        return categoryList;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycle, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapter.MainViewHolder holder, int position) {
        String categoryTitle = categoryList.get(position);
        holder.bind(categoryTitle);
        holder.itemView.setOnClickListener(v -> onClickListener.onItemClickListener(position));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder{
        TextView categoryTitle;

        public MainViewHolder(View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.category_title);
        }

        private void bind(String categoryTitle){
            this.categoryTitle.setText(categoryTitle);
        }
    }

    interface onClickListener{
        void onItemClickListener(int position);
    }
}
