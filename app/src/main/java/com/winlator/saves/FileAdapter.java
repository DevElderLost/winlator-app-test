package com.winlator.saves;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.winlator.R;

import java.util.List;
import java.util.function.Consumer;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {

    private final List<FileItem> items;
    private final Consumer<FileItem> onItemClickListener;

    public FileAdapter(List<FileItem> items, Consumer<FileItem> onItemClickListener) {
        this.items = items;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_folder_picker, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Context context = holder.itemView.getContext();
        FileItem item = items.get(position);

        holder.tvName.setText(item.name);

        // Resolve warna dari theme attr
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorPrimaryVariant, typedValue, true);
        int iconColor = typedValue.data;

        context.getTheme().resolveAttribute(R.attr.colorPrimaryText, typedValue, true);
        int textColor = typedValue.data;

        holder.ivIcon.setImageResource(R.drawable.icon_open);
        holder.ivIcon.setColorFilter(iconColor, android.graphics.PorterDuff.Mode.SRC_IN);
        holder.tvName.setTextColor(textColor);

        holder.itemView.setOnClickListener(v -> onItemClickListener.accept(item));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}
