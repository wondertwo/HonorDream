package com.cheetah.honordream.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cheetah.honordream.R;
import com.cheetah.honordream.utils.KeyboardChangeListener;

/**
 * 发布页面
 *
 * Created by wondertwo on 2017/7/24.
 */

public class PublishActivity extends Activity
        implements KeyboardChangeListener.KeyBoardListener {

    private final String TAG = "PublishActivity";
    private static boolean mCanReadSDCard = false; //能否读取SD卡

    private RelativeLayout mImageCover;
    private ImageView mImageBtn1;
    private ImageView mImageBtn2;
    private ImageView mImageBtn3;
    private ImageView mImageBtn4;
    private ImageView mImageBtn5;
    private ImageView mImageBtn6;
    private ImageView mImageBtn7;
    private ImageView mImageBtn8;

    private HorizontalScrollView mScrollView;
    private Button mPublishConfirm;
    private EditText mIdleName;
    private EditText mIdleDesc;
    private KeyboardChangeListener mKeyboardChangeListener;

    private ImageButton mPublishCancel;
    private TextView mPublishSecret;
    private LinearLayout mPublishClassification;

    private LinearLayout mPublishIdleTag;
    private LinearLayout mPublishWhatIWant;

    private static int mGalleryRequestCode = 1024;
    private int mCurrentSelectedId;
    private String mCurrentImgPath;
    private static int mPreviewRequestCode = 1000;
    private String[] mImgPaths = new String[8]; //图片路径
    private int[] mImgIds = {
            R.id.publish_click_to_add_1,
            R.id.publish_click_to_add_2,
            R.id.publish_click_to_add_3,
            R.id.publish_click_to_add_4,
            R.id.publish_click_to_add_5,
            R.id.publish_click_to_add_6,
            R.id.publish_click_to_add_7,
            R.id.publish_click_to_add_8 }; //图片ID

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);

        //确认发布
        mPublishConfirm = (Button) findViewById(R.id.publish_confirm);
        mPublishConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到店铺页，将物品数据生成JSON数据传给小胖，在店铺页中加载我已发布的物品信息流
                Intent intent = new Intent(PublishActivity.this, MyShopActivity.class);
                startActivityForResult(intent, mPreviewRequestCode);
            }
        });
        //取消发布
        mPublishCancel = (ImageButton) findViewById(R.id.publish_cancel);
        mPublishCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 关闭当前页面
            }
        });
        //发布秘籍
        mPublishSecret = (TextView) findViewById(R.id.publish_secret);
        mPublishSecret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PublishActivity.this, "点击获取更多发布秘籍哦", Toast.LENGTH_SHORT).show();
            }
        });

        initializeImgBtn(); //初始化图片点击事件

        //标题、描述
        mIdleName = (EditText) findViewById(R.id.publish_idle_name);
        //mIdleName.setCursorVisible(false);
        mIdleDesc = (EditText) findViewById(R.id.publish_idle_desc);
        //mIdleDesc.setCursorVisible(false);
        mIdleName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIdleName.setFocusable(true);
                mIdleName.setCursorVisible(true);
            }
        });
        mIdleDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIdleDesc.setFocusable(true);
                mIdleDesc.setCursorVisible(true);
            }
        });
//        //软键盘监听
//        mKeyboardChangeListener = new KeyboardChangeListener(PublishActivity.this);
//        mKeyboardChangeListener.setKeyBoardListener(new KeyboardChangeListener.KeyBoardListener() {
//            @Override
//            public void onKeyboardChange(boolean isShow, int keyboardHeight) {
//                if (isShow) return;
//                mIdleName.setFocusable(false);
//                mIdleName.setCursorVisible(false);
//                mIdleDesc.setFocusable(false);
//                mIdleDesc.setCursorVisible(false);
//            }
//        });

        initializeAttrItems(); //初始化物品属性

        //检查读SD卡权限
        checkPermissionREADEXTERNALSTORAGE(this);
    }

    //物品属性
    private void initializeAttrItems() {
        mPublishIdleTag = (LinearLayout) findViewById(R.id.publish_idle_tag);
        mPublishIdleTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PublishActivity.this, "点击可设置物品标签哦", Toast.LENGTH_SHORT).show();
            }
        });
        mPublishClassification = (LinearLayout) findViewById(R.id.publish_classification);
        mPublishClassification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PublishActivity.this, "点击可设置闲置分类哦", Toast.LENGTH_SHORT).show();
            }
        });
        mPublishWhatIWant = (LinearLayout) findViewById(R.id.publish_what_i_want);
        mPublishWhatIWant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PublishActivity.this, "点击获取更多发布秘籍哦", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializeImgBtn() {

        mImageCover = (RelativeLayout) findViewById(R.id.publish_click_to_add_cover);
        mImageBtn1 = (ImageView) findViewById(R.id.publish_click_to_add_1);
        mImageCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentSelectedId = R.id.publish_click_to_add_1;
                handleImgClick(mImageBtn1, 1);
            }
        });

        mImageBtn2 = (ImageView) findViewById(R.id.publish_click_to_add_2);
        mImageBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentSelectedId = R.id.publish_click_to_add_2;
                handleImgClick(mImageBtn2, 2);
            }
        });

        mImageBtn3 = (ImageView) findViewById(R.id.publish_click_to_add_3);
        mImageBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentSelectedId = R.id.publish_click_to_add_3;
                handleImgClick(mImageBtn3, 3);
            }
        });

        mImageBtn4 = (ImageView) findViewById(R.id.publish_click_to_add_4);
        mImageBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentSelectedId = R.id.publish_click_to_add_4;
                handleImgClick(mImageBtn4, 4);
            }
        });

        mImageBtn5 = (ImageView) findViewById(R.id.publish_click_to_add_5);
        mImageBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentSelectedId = R.id.publish_click_to_add_5;
                handleImgClick(mImageBtn5, 5);
            }
        });

        mImageBtn6 = (ImageView) findViewById(R.id.publish_click_to_add_6);
        mImageBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentSelectedId = R.id.publish_click_to_add_6;
                handleImgClick(mImageBtn6, 6);
            }
        });

        mImageBtn7 = (ImageView) findViewById(R.id.publish_click_to_add_7);
        mImageBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentSelectedId = R.id.publish_click_to_add_7;
                handleImgClick(mImageBtn7, 7);
            }
        });

        mImageBtn8 = (ImageView) findViewById(R.id.publish_click_to_add_8);
        mImageBtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentSelectedId = R.id.publish_click_to_add_8;
                handleImgClick(mImageBtn8, 8);
            }
        });
    }

    private void handleImgClick(ImageView imageView, int imgLocation) {
        if(imageView.getDrawable().getCurrent().getConstantState() ==
                getResources().getDrawable(R.drawable.img_publish_add_90).getConstantState()) {
            // 先检查读取权限
            /*if (!mCanReadSDCard) {
                checkPermissionREADEXTERNALSTORAGE(this);
                return;
            }*/
            // 未设置物品图片，调用系统相册
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            //intent.setType("image/*"); //intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, mGalleryRequestCode);
        }else{
            Intent intent = new Intent(PublishActivity.this, ImgPreviewActivity.class);
            // 通过Bundle传递数据
            if (mImgPaths[imgLocation-1] != null) {
                Bundle bundle = new Bundle();
                bundle.putInt("location", imgLocation); //String:key,int:value
                bundle.putString("imgPath", mImgPaths[imgLocation-1]);
                intent.putExtras(bundle);
            }
            // 跳转大图预览，包含设为封面、删除此图两个逻辑
            startActivityForResult(intent, mPreviewRequestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK || data == null) {
            return;
        }

        //获取图片路径
        if (requestCode == mGalleryRequestCode) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumns[0]);
            mCurrentImgPath = cursor.getString(columnIndex);
            cursor.close();
            //将图片路径保存到数组中
            saveImgPathToArray(mImgPaths, mCurrentSelectedId, mCurrentImgPath);
            //更新水平图片集合
            updateHorizontalImgs(mImgIds, mImgPaths);
        }

        //处理图片位移逻辑：删除图片、设置封面
        if (requestCode == mPreviewRequestCode) {
            Bundle bundle = data.getExtras();

            //哪种操作
            boolean whichOption = bundle.getBoolean("whichOption");
            if (whichOption) {
                //删除图片操作
                int deleteLocation = bundle.getInt("deleteLocation");
                //校验deleteLocation范围 1-8
                if (deleteLocation < 1 || deleteLocation >8) return;

                //-----------------------------------------------------------------
                // 此处有BUG，当删除前面图片时，最后一张图片会自动增加，且点击出现崩溃
                //-----------------------------------------------------------------
                //deleteLocation - 1 范围 0-7
                for (int i = deleteLocation - 1; i < mImgPaths.length; i++) {
                    if (i < 7) {
                        mImgPaths[i] = mImgPaths[i+1];
                    }
                    mImgPaths[7] = null;
                }
            } else {
                //设置封面操作
                int setCoverLocation = bundle.getInt("setCoverLocation");
                //校验setCoverLocation范围 2-8
                if (setCoverLocation < 2 || setCoverLocation >8) return;
                String coverPath = mImgPaths[setCoverLocation-1];
                mImgPaths[setCoverLocation-1] = mImgPaths[0];
                mImgPaths[0] = coverPath;
            }
            //更新图片显示
            updateHorizontalImgs(mImgIds, mImgPaths);
        }
    }

    //更新水平图片集合
    private void updateHorizontalImgs(int[] imgIds, String[] imgPaths) {
        for (int index = 0; index < imgPaths.length; index++) {
            if (imgPaths[index] == null) {
                ((ImageView) findViewById(imgIds[index])).setImageResource(R.drawable.img_publish_add_90);
                continue;
            }
            Bitmap bm = BitmapFactory.decodeFile(imgPaths[index]);
            ((ImageView) findViewById(imgIds[index])).setImageBitmap(bm);
        }
    }

    //把图片路径保存在对应位置的数组中
    private boolean saveImgPathToArray(String[] imgPaths, int currentId, String imgPath) {
        switch (currentId) {
            case R.id.publish_click_to_add_1:
                imgPaths[0] = imgPath;
                return true;
            case R.id.publish_click_to_add_2:
                imgPaths[1] = imgPath;
                return true;
            case R.id.publish_click_to_add_3:
                imgPaths[2] = imgPath;
                return true;
            case R.id.publish_click_to_add_4:
                imgPaths[3] = imgPath;
                return true;
            case R.id.publish_click_to_add_5:
                imgPaths[4] = imgPath;
                return true;
            case R.id.publish_click_to_add_6:
                imgPaths[5] = imgPath;
                return true;
            case R.id.publish_click_to_add_7:
                imgPaths[6] = imgPath;
                return true;
            case R.id.publish_click_to_add_8:
                imgPaths[7] = imgPath;
                return true;
            default:
                return false;
        }
    }

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1234;

    public boolean checkPermissionREADEXTERNALSTORAGE(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showPermissionDialog("External storage", context, Manifest.permission.READ_EXTERNAL_STORAGE);
                } else {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public void showPermissionDialog(final String msg, final Context context, final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[] { permission },
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mCanReadSDCard = true;
            } else {
                Toast.makeText(this, "读取内部存储权限被拒绝", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onKeyboardChange(boolean isShow, int keyboardHeight) {
        Log.d(TAG, "onKeyboardChange() called with: " + "isShow = [" + isShow + "], keyboardHeight = [" + keyboardHeight + "]");
        /*if (!isShow) {
            mIdleName.setFocusable(false);
            mIdleDesc.setFocusable(false);
        }*/
    }
}

//switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
//                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(this, "读取内部存储权限被拒绝", Toast.LENGTH_SHORT).show();
//                    //checkPermissionREADEXTERNALSTORAGE(this);
//                }
//                break;
//            default: super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        }
