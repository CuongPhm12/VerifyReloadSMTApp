package com.example.verifyreloadsmt.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.verifyreloadsmt.R;

import java.util.List;

public class RecyclerViewAdapter_Get_TotalByMachine extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    private List<Get_TotalByMachine_Response> _dataList;

    public RecyclerViewAdapter_Get_TotalByMachine(List<Get_TotalByMachine_Response> dataList) {
        this._dataList = dataList;
    }

    @Override
    public int getItemViewType(int position) {
        // Return VIEW_TYPE_HEADER for the first row, and VIEW_TYPE_ITEM for other rows
        if (position == 0) {
            return VIEW_TYPE_HEADER;
        }
        return VIEW_TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            //Inflate header layout for the first row
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_layout, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            return new ItemViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            //Bind the header view(titles)
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.txtHeaderWO.setText("WO");
            headerViewHolder.txtHeaderLine.setText("LINE");
            headerViewHolder.txtHeaderMachine.setText("MACHINE");
            headerViewHolder.txtHeaderSlot.setText("SLOT");
        } else if (holder instanceof ItemViewHolder) {
            Get_TotalByMachine_Response data = _dataList.get(position - 1);//Subtract 1 because the header is at position 0
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.txtWO.setText(data.getPRODUCTION_ORDER_ID());
            itemViewHolder.txtLine.setText(data.getLINE_ID());
            itemViewHolder.txtMachine.setText(data.getMACHINE_ID());
            itemViewHolder.txtSlot.setText(data.getMACHINE_SLOT());
        }

    }

    @Override
    public int getItemCount() {
        return _dataList.size() + 1;//+1 for the header row
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView txtWO, txtLine, txtMachine, txtSlot;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txtWO = itemView.findViewById(R.id.txtWO);
            txtLine = itemView.findViewById(R.id.txtLine);
            txtMachine = itemView.findViewById(R.id.txtMachine);
            txtSlot = itemView.findViewById(R.id.txtSlot);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView txtHeaderWO, txtHeaderLine, txtHeaderMachine, txtHeaderSlot;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHeaderWO = itemView.findViewById(R.id.txtHeaderWO);
            txtHeaderLine = itemView.findViewById(R.id.txtHeaderLine);
            txtHeaderMachine = itemView.findViewById(R.id.txtHeaderMachine);
            txtHeaderSlot = itemView.findViewById(R.id.txtHeaderSlot);
        }
    }
}
