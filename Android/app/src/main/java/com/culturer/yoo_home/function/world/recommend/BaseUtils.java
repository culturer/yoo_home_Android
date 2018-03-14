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
import android.content.res.Resources;
import android.support.annotation.StringRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;


import com.armour8.yooplus.yooplus.R;

import java.util.ArrayList;
import java.util.List;

public class BaseUtils {

    public static final int TYPE_LIST = 0;
    public static final int TYPE_GRID = 1;
    public static final int TYPE_SECOND_LIST = 2;
    public static final int TYPE_SECOND_GRID = 3;

    private static List<ItemCard> getListCards(Resources resources) {
        List<ItemCard> cards = new ArrayList<>();
        for ( int i=0 ; i<20 ;i++ ){
            ItemCard card = new ItemCard("mTitle","mDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescriptionmDescription","http://img5.imgtn.bdimg.com/it/u=4072367342,2834397289&fm=27&gp=0.jpg","隔壁老宋宋啊   今天20:35");
            cards.add(card);
        }
        return cards;
    }

    private static List<ItemCard> getGridCards(Resources resources) {
        List<ItemCard> cards = new ArrayList<>();
        for ( int i=0 ; i<20 ;i++ ){
            ItemCard card = new ItemCard("mTitle","mDescription","mThumbnailUrl","mSummaryText");
            cards.add(card);
        }
        return cards;
    }

    public static List<ItemCard> getCards(Resources resources, int type) {
        List<ItemCard> itemCards;

        switch (type) {
            case TYPE_LIST:
            case TYPE_SECOND_LIST:
                itemCards = getListCards(resources);
                break;
            case TYPE_GRID:
            case TYPE_SECOND_GRID:
                itemCards = getGridCards(resources);
                break;
            default:
                itemCards = null;
        }

        return itemCards;
    }

    public static DemoConfiguration getDemoConfiguration(int configurationType, Context context) {
        DemoConfiguration demoConfiguration;

        switch (configurationType) {
            case TYPE_LIST:
                demoConfiguration = new DemoConfiguration();
                demoConfiguration.setStyleResource(R.style.AppTheme);
                demoConfiguration.setLayoutResource(R.layout.fragment_recommend);
                demoConfiguration.setLayoutManager(new LinearLayoutManager(context));
                demoConfiguration.setTitleResource(-1);
                break;
            case TYPE_GRID:
                demoConfiguration = new DemoConfiguration();
                demoConfiguration.setStyleResource(R.style.AppTheme);
                demoConfiguration.setLayoutResource(R.layout.fragment_recommend);
                demoConfiguration.setLayoutManager(new GridLayoutManager(context, 2));
                demoConfiguration.setTitleResource(-1);
                break;
            default:
                demoConfiguration = null;
        }

        return demoConfiguration;
    }

    private static ItemCard createItemCard(Resources resources, @StringRes int title, @StringRes int imageUrl,
                                           @StringRes int description, @StringRes int summary) {
        ItemCard itemCard = new ItemCard();
        itemCard.setTitle(resources.getString(title));
        itemCard.setThumbnailUrl(resources.getString(imageUrl));
        itemCard.setDescription(resources.getString(description));
        itemCard.setSummaryText(resources.getString(summary));
        return itemCard;
    }
}
