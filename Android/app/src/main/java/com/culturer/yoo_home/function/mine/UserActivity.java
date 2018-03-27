package com.culturer.yoo_home.function.mine;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Process;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.armour8.yooplus.yooplus.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.culturer.yoo_home.base.GlideApp;
import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.RxImageTool;
import com.vondear.rxtools.RxPhotoTool;
import com.vondear.rxtools.RxSPTool;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.dialog.RxDialogChooseImage;
import com.vondear.rxtools.view.dialog.RxDialogScaleView;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.vondear.rxtools.view.dialog.RxDialogChooseImage.LayoutType.TITLE;

public class UserActivity extends AppCompatActivity {

    TextView mTvBg;
    ImageView mIvAvatar;
    LinearLayout mLlAnchorLeft;
    RelativeLayout mRlAvatar;
    TextView mTvName;
    TextView mTvConstellation;
    TextView mTvBirthday;
    TextView mTvAddress;
    TextView mTvLables;
    TextView mTextView2;
    TextView mEditText2;
    Button mBtnExit;
    LinearLayout mActivityUser;

    private Uri resultUri;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        init();
    }

    private void init(){
        initData();
        initView();
    }

    private void initData(){
        context = UserActivity.this;
    }

    private void initView(){
        initBaseView();
    }

    private void initBaseView() {

        Resources r = context.getResources();
        resultUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(R.drawable.logo_black) + "/"
                + r.getResourceTypeName(R.drawable.logo_black) + "/"
                + r.getResourceEntryName(R.drawable.logo_black));

        mTvBg = findViewById(R.id.tv_bg);
        mIvAvatar = findViewById(R.id.iv_avatar);
        mLlAnchorLeft = findViewById(R.id.ll_anchor_left);
        mRlAvatar = findViewById(R.id.rl_avatar);
        mTvName = findViewById(R.id.tv_name);
        mTvConstellation = findViewById(R.id.tv_constellation);
        mTvBirthday = findViewById(R.id.tv_birthday);
        mTvAddress = findViewById(R.id.tv_address);
        mTvLables = findViewById(R.id.tv_lables);
        mTextView2 = findViewById(R.id.textView2);
        mEditText2 = findViewById(R.id.editText2);
        mBtnExit = findViewById(R.id.btn_exit);
        mActivityUser = findViewById(R.id.activity_user);

        mIvAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDialogChooseImage();
            }
        });
        mIvAvatar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                RxImageTool.showBigImageView(context, resultUri);
                RxDialogScaleView rxDialogScaleView = new RxDialogScaleView(context);
                rxDialogScaleView.setImageUri(resultUri);
                rxDialogScaleView.show();
                return false;
            }
        });

        mBtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("确定退出应用?")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                              杀掉当前的应用
                                int pid = Process.myPid();
                                Process.killProcess(pid);
                                finish();
                            }
                        }).create().show();
            }
        });
    }

    private void initDialogChooseImage() {
        RxDialogChooseImage dialogChooseImage = new RxDialogChooseImage(UserActivity.this, TITLE);
        dialogChooseImage.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RxPhotoTool.GET_IMAGE_FROM_PHONE://选择相册之后的处理
                if (resultCode == RESULT_OK) {
                    initUCrop(data.getData());
                }
                break;
            case RxPhotoTool.GET_IMAGE_BY_CAMERA://选择照相机之后的处理
                if (resultCode == RESULT_OK) {
                    initUCrop(RxPhotoTool.imageUriFromCamera);
                }
                break;
            case RxPhotoTool.CROP_IMAGE://普通裁剪后的处理

                GlideApp.with(UserActivity.this).
                        load(RxPhotoTool.cropImageUri).
                        diskCacheStrategy(DiskCacheStrategy.RESOURCE).
                        apply(RequestOptions.bitmapTransform(new CircleCrop())).
//                        bitmapTransform(new CropCircleTransformation(mContext)).
                        thumbnail(0.5f).
                        placeholder(R.drawable.logo_black).
                        priority(Priority.LOW).
                        error(R.drawable.logo_black).
                        fallback(R.drawable.logo_black).
                        into(mIvAvatar);
                break;

            case UCrop.REQUEST_CROP://UCrop裁剪之后的处理
                if (resultCode == RESULT_OK) {
                    resultUri = UCrop.getOutput(data);
                    roadImageView(resultUri, mIvAvatar);
                    RxSPTool.putContent(context, "AVATAR", resultUri.toString());
                } else if (resultCode == UCrop.RESULT_ERROR) {
                    final Throwable cropError = UCrop.getError(data);
                }
                break;
            case UCrop.RESULT_ERROR://UCrop裁剪错误之后的处理
                final Throwable cropError = UCrop.getError(data);
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //从Uri中加载图片 并将其转化成File文件返回
    private File roadImageView(Uri uri, ImageView imageView) {
        GlideApp.with(context).
                load(uri).
                diskCacheStrategy(DiskCacheStrategy.RESOURCE).
                apply(RequestOptions.bitmapTransform(new CircleCrop())).

//                bitmapTransform(new CropCircleTransformation(mContext)).
                thumbnail(0.5f).
                placeholder(R.drawable.logo_black).
                priority(Priority.LOW).
                error(R.drawable.logo_black).
                fallback(R.drawable.logo_black).
                into(imageView);

        return (new File(RxPhotoTool.getImageAbsolutePath(this, uri)));
    }

    private void initUCrop(Uri uri){
        //Uri destinationUri = RxPhotoTool.createImagePathUri(this);

        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
        long time = System.currentTimeMillis();
        String imageName = timeFormatter.format(new Date(time));

        Uri destinationUri = Uri.fromFile(new File(getCacheDir(), imageName + ".jpeg"));

        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //设置隐藏底部容器，默认显示
        //options.setHideBottomControls(true);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(this, R.color.colorPrimary));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryDark));

        //开始设置
        //设置最大缩放比例
        options.setMaxScaleMultiplier(5);
        //设置图片在切换比例时的动画
        options.setImageToCropBoundsAnimDuration(666);
        //设置裁剪窗口是否为椭圆
        //options.setOvalDimmedLayer(true);
        //设置是否展示矩形裁剪框
        // options.setShowCropFrame(false);
        //设置裁剪框横竖线的宽度
        //options.setCropGridStrokeWidth(20);
        //设置裁剪框横竖线的颜色
        //options.setCropGridColor(Color.GREEN);
        //设置竖线的数量
        //options.setCropGridColumnCount(2);
        //设置横线的数量
        //options.setCropGridRowCount(1);

        UCrop.of(uri, destinationUri)
                .withAspectRatio(1, 1)
                .withMaxResultSize(1000, 1000)
                .withOptions(options)
                .start(this);
    }

}
