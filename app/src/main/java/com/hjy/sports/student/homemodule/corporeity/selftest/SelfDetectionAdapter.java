package com.hjy.sports.student.homemodule.corporeity.selftest;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.fy.baselibrary.base.recyclerv.adapter.RecyclerCommonAdapter;
import com.fy.baselibrary.base.recyclerv.adapter.ViewHolder;
import com.fy.baselibrary.entity.SelfDetection;
import com.hjy.sports.R;

import java.util.List;

/**
 * 自我检测 列表 adapter
 * Created by fangs on 2018/2/1.
 */
public class SelfDetectionAdapter extends RecyclerCommonAdapter<SelfDetection> {
    public SelfDetectionAdapter(Context context, List<SelfDetection> datas) {
        super(context, R.layout.item_self_detection, datas);
    }

    @Override
    public void convert(ViewHolder holder, SelfDetection item, int position) {
        holder.setText(R.id.tvSelfItem, item.getName());

        EditText editInput = holder.getView(R.id.editInput);
        if (editInput.getTag() instanceof TextWatcher) {
            editInput.removeTextChangedListener((TextWatcher) editInput.getTag());
        }

        editInput.setText(item.getInputContent());
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                item.setInputContent(editInput.getText().toString());
            }
        };

        editInput.addTextChangedListener(watcher);
        editInput.setTag(watcher);
    }
}
