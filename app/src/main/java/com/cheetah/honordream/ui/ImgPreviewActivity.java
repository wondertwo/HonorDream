package com.cheetah.honordream.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheetah.honordream.R;

/**
 * 图片预览
 *
 * Created by wondertwo on 2017/7/25.
 */

public class ImgPreviewActivity extends Activity {

    private ImageButton mImgPreviewBack;
    private TextView mImgPreviewDelete;
    private ImageView mImgPreviewImage;
    private TextView mImgPreviewSetCover;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_preview);

        //解析Bundle数据
        Bundle bundle = getIntent().getExtras();
        final int location = bundle.getInt("location");
        String imgPath = bundle.getString("imgPath");

        //获取控件
        mImgPreviewImage = (ImageView) findViewById(R.id.img_preview_image_view);
        mImgPreviewBack = (ImageButton) findViewById(R.id.img_preview_back);
        mImgPreviewDelete = (TextView) findViewById(R.id.img_preview_delete);
        mImgPreviewSetCover = (TextView) findViewById(R.id.img_preview_set_cover);

        //大图预览
        Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
        mImgPreviewImage.setImageBitmap(bitmap);
        if (location == 1) {
            mImgPreviewSetCover.setText("封面");
        } else {
            mImgPreviewSetCover.setText("设为封面");
        }

        mImgPreviewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mImgPreviewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除图片的逻辑
                Intent deleteIntent = new Intent();
                Bundle deleteBundle = new Bundle();
                deleteBundle.putBoolean("whichOption", true); //true表示删除操作
                deleteBundle.putInt("deleteLocation", location);
                deleteIntent.putExtras(deleteBundle);
                setResult(RESULT_OK, deleteIntent);
                finish();
            }
        });
        mImgPreviewSetCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (location == 1) return;
                //设为封面的逻辑
                Intent setCoverIntent = new Intent();
                Bundle setCoverBundle = new Bundle();
                setCoverBundle.putBoolean("whichOption", false); //false表示设置封面操作
                setCoverBundle.putInt("setCoverLocation", location);
                setCoverIntent.putExtras(setCoverBundle);
                setResult(RESULT_OK, setCoverIntent);
                finish();
            }
        });
    }

}
