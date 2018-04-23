/**
 * Copyright 2017 Harish Sridharan
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.culturer.yoo_home.function.world.recommend;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.culturer.yoo_home.R;


public class ItemHolder extends RecyclerView.ViewHolder {

    private static Context context;

    private TextView mTitleView;
    private TextView mDescView;
    private ImageView mThumbnailView;
    private TextView mSummaryView;

    public static ItemHolder newInstance(ViewGroup container, int type) {
        View root = LayoutInflater.from(container.getContext()).inflate(getLayoutResourceId(type),
                container, false);
        context = container.getContext();
        return new ItemHolder(root);
    }

    private ItemHolder(View itemView) {
        super(itemView);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivity.class);
                context.startActivity(intent);
            }
        };
        mTitleView =  itemView.findViewById(R.id.card_title);
        mDescView =  itemView.findViewById(R.id.card_subtitle);
        mSummaryView =  itemView.findViewById(R.id.card_summary);
        mThumbnailView = itemView.findViewById(R.id.card_image);
        mTitleView.setOnClickListener(listener);
        mDescView.setOnClickListener(listener);
        mSummaryView.setOnClickListener(listener);
        mThumbnailView.setOnClickListener(listener);
    }

    public void bind(ItemCard card) {
        mTitleView.setText(card.getTitle());
        mDescView.setText(card.getDescription());
        mSummaryView.setText(card.getSummaryText());

        Glide.with(itemView.getContext()).load(card.getThumbnailUrl()).into(mThumbnailView);
    }

    private static int getLayoutResourceId(int type) {
        int selectedLayoutResource;
        switch (type) {
            case BaseUtils.TYPE_LIST:
                selectedLayoutResource = R.layout.layout_news_card;
                break;
            case BaseUtils.TYPE_SECOND_LIST:
                selectedLayoutResource = R.layout.layout_second_news_card;
                break;
            case BaseUtils.TYPE_GRID:
            case BaseUtils.TYPE_SECOND_GRID:
                selectedLayoutResource = R.layout.layout_ecom_item;
                break;
            default:
                selectedLayoutResource = 0;
        }
        return selectedLayoutResource;
    }
}
