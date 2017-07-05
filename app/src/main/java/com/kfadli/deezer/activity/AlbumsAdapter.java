package com.kfadli.deezer.activity;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kfadli.deezer.R;
import com.kfadli.deezer.helper.ImageHelper;
import com.kfadli.deezer.services.cache.CacheManager;
import com.kfadli.deezer.services.executor.FutureTaskPriority;
import com.kfadli.deezer.services.executor.task.GetImageCallable;
import com.kfadli.deezer.enums.PriorityEnum;
import com.kfadli.deezer.services.executor.TaskPoolManager;
import com.kfadli.deezer.model.Album;
import com.kfadli.deezer.model.Artist;

import java.util.List;
import java.util.concurrent.FutureTask;

/**
 * Created by Karim Fadli on 02/07/2017.
 */

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.ViewHolder> {

    private static final String TAG = "AlbumsAdapter";
    private List<Album> mAlbums;
    private CacheManager mCache;
    private ImageHelper mImageHelper;

    public AlbumsAdapter(CacheManager cache, ImageHelper imageHelper, List<Album> albums) {
        this.mCache = cache;
        this.mImageHelper = imageHelper;
        this.mAlbums = albums;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Album item = mAlbums.get(position);
        final Artist artist = item.getArtist();

        Bitmap bitmap = mCache.getBitmap(item.getId() + "");
        Log.d(TAG, "[onBindViewHolder] position: " + position + ", bitmap: " + (bitmap != null));

        //handle onClick
        holder.mCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageHelper.updateImage(holder.mCover, new GetImageCallable(mCache, holder.mCover, artist.getPictureBig(), artist.getId()), PriorityEnum.HIGH);
            }
        });

        this.mImageHelper.updateImage(holder.mCover, new GetImageCallable(mCache, holder.mCover, item.getCoverBig(), item.getId()), PriorityEnum.LOW);
    }

    @Override
    public int getItemCount() {
        return mAlbums.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mCover;

        public ViewHolder(View itemView) {
            super(itemView);
            mCover = (ImageView) itemView.findViewById(R.id.imageView);
        }

    }
}
