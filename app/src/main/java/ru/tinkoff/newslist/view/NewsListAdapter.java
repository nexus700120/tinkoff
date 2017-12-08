package ru.tinkoff.newslist.view;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import ru.tinkoff.R;
import ru.tinkoff.newslist.data.OpenDetailPageEvent;
import ru.tinkoff.newslist.domain.model.NewsListResponse;
import ru.tinkoff.util.DateFormatter;

/**
 * Created by Vitaly on 06.12.2017.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListItemViewHolder> {

    private List<NewsListResponse.NewsListItem> mItemList;

    public void setItems(List<NewsListResponse.NewsListItem> items) {
        mItemList = items;
    }

    @Override
    public NewsListAdapter.NewsListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_news_list_item, parent, false);
        return new NewsListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsListItemViewHolder holder, int position) {
        NewsListResponse.NewsListItem item = mItemList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    class NewsListItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleView;
        private TextView mDateView;

        NewsListItemViewHolder(View itemView) {
            super(itemView);
            mTitleView = itemView.findViewById(R.id.title);
            mDateView = itemView.findViewById(R.id.date);
        }

        void bind(NewsListResponse.NewsListItem item) {
            mTitleView.setText(Html.fromHtml(item.text));
            if (item.date != null) {
                mDateView.setText(DateFormatter.formatTimeStamp(item.date.timestamp));
            }
            itemView.setOnClickListener(view ->
                    EventBus.getDefault().post(new OpenDetailPageEvent(item)));
        }
    }
}
