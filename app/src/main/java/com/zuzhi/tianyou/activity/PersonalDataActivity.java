package com.zuzhi.tianyou.activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.R;
import com.zuzhi.tianyou.base.BaseActivity;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.Logs;
import com.zuzhi.tianyou.utils.SimpleMultipartEntity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * personal information activity 个人资料页
 */
public class PersonalDataActivity extends BaseActivity implements View.OnClickListener, com.bigkoo.alertview.OnItemClickListener, com.bigkoo.alertview.OnDismissListener {

    TextView tv_personal_name;
    CircleImageView civ_personal_head;
    RelativeLayout rl_my_coupons;
    private AlertView head_av;
    private Drawable[] mDrawables;

    public static final int NONE = 0;
    public static final String IMAGE_UNSPECIFIED = "image/*";
    public static final int PHOTOHRAPH = 1;// 拍照
    public static final int PHOTOZOOM = 2; // 缩放
    public static final int PHOTORESOULT = 3;// 结果
    public static final int TO_SELECT_PHOTO = 3;// 选择文件
    public String imageName;
    private String picPath;

    @Override
    protected int setContent() {
        return R.layout.activity_personal_info;
    }

    @Override
    protected void initViews() {
        tv_personal_name = (TextView) findViewById(R.id.tv_personal_name);
        civ_personal_head = (CircleImageView) findViewById(R.id.civ_personal_head);
        rl_my_coupons = (RelativeLayout) findViewById(R.id.rl_my_coupons);
        rl_my_coupons.setOnClickListener(this);

        mDrawables = new Drawable[]{
                getResources().getDrawable(R.drawable.ic_launcher),
                getResources().getDrawable(R.drawable.ic_launcher)
        };

        head_av = new AlertView(mDrawables, null, "请选择喜欢的照片作为头像",
                getResources().getString(R.string.cancel),
                new String[]{"拍照", "从手机相册选择"},
                null, this, AlertView.Style.ActionSheet, this)
                .setCancelable(true)
                .setOnDismissListener(this);

        civ_personal_head.setOnClickListener(this);

        //修改姓名看是否跳页，如果不跳改成edittext
        tv_personal_name.setOnClickListener(this);

        //set user information
        tv_personal_name.setText(MyApplication.user.getName());
        ImageLoader.getInstance().displayImage(
                Cons.IMG_HOST + MyApplication.user.getHeadImg(),
                civ_personal_head,
                MyApplication.dis_ImgOptions
        );
    }

    @Override
    protected void initTitleBar() {
        ll_title_bar_left = (LinearLayout) findViewById(R.id.ll_title_bar_left);
        tv_title_bar_text = (TextView) findViewById(R.id.tv_title_bar_title);
        bt_title_bar_left = (Button) findViewById(R.id.bt_title_bar_left);
    }

    @Override
    protected void setTitleBar() {
        //open the steep mode 沉浸模式
        TitileBarSteep(getWindow().getDecorView());

        ll_title_bar_left.setVisibility(View.VISIBLE);
        tv_title_bar_text.setVisibility(View.VISIBLE);
        bt_title_bar_left.setVisibility(View.VISIBLE);

        tv_title_bar_text.setText(R.string.personal_data);
        ll_title_bar_left.setOnClickListener(this);
        bt_title_bar_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            //back 返回
            case R.id.bt_title_bar_left:
            case R.id.ll_title_bar_left:
                finish();
                break;

            //change head icon 修改头像
            case R.id.civ_personal_head:
                Toast.makeText(this, R.string.modify_phone_num, Toast.LENGTH_LONG).show();
                break;

            case R.id.rl_my_coupons:
                head_av.show();
                break;

        }
    }


    public void onDismiss(Object o) {

    }


    public void onItemClick(Object o, int position) {
        Logs.i(Cons.ACTIVITY_COMMODITYINFO, "点击了位置为" + position + "的按钮");
        if (o == head_av) {
            switch (position) {
                //拍照
                case 0:
                    imageName = "/" + ".jpg";
                    // 调用系统的拍照功能
                    Intent intent = new Intent(
                            MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                            .fromFile(new File(Environment
                                    .getExternalStorageDirectory(),
                                    imageName)));
                    startActivityForResult(intent, PHOTOHRAPH);
                    break;

                //从相册选择
                case 1:
                    imageName = "/" + ".jpg";
                    Intent intent1 = new Intent(Intent.ACTION_PICK,
                            null);
                    intent1.setDataAndType(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            IMAGE_UNSPECIFIED);
                    // 调用剪切功能
                    startActivityForResult(intent1, PHOTOZOOM);
                    break;
            }

        }

    }

    private void dpPostPhotoImage(final File file) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    SimpleMultipartEntity entity = new SimpleMultipartEntity();

                    entity.addPart("file", file, true);

                    String url = "/Api/Upload/image.html";
                    HttpPost req = new HttpPost(url);
                    req.setEntity(entity);
                    HttpResponse resp = httpclient.execute(req);
                    int statusCode = resp.getStatusLine().getStatusCode();
                    String responseString = EntityUtils.toString(resp
                            .getEntity());

                    if (statusCode == 200) {
                        @SuppressWarnings("unused")
                        JSONObject json = new JSONObject(responseString);
                        Looper.prepare();
                        Looper.loop();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == NONE)
            return;
        // 拍照
        if (requestCode == PHOTOHRAPH) {
            // 设置文件保存路径这里放在跟目录下
            File picture = new File(Environment.getExternalStorageDirectory()
                    + imageName);
            startPhotoZoom(Uri.fromFile(picture));
        }

        if (data == null)
            return;

        // 读取相册缩放图片
        if (requestCode == PHOTOZOOM) {
            startPhotoZoom(data.getData());
        }
        if (requestCode == TO_SELECT_PHOTO) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap photo = extras.getParcelable("data");
                FileOutputStream fos = null;
                BufferedOutputStream bos = null;
                ByteArrayOutputStream baos = null; // 字节数组输出流 = new
                // ByteArrayOutputStream();
                try {
                    baos = new ByteArrayOutputStream();
                    photo.compress(Bitmap.CompressFormat.JPEG, 75, baos);// (0
                    // -//
                    // 100)压缩文件
                    byte[] byteArray = baos.toByteArray();// 字节数组输出流转换成字节数组
                    File file = new File(
                            Environment.getExternalStorageDirectory(),
                            imageName);
                    // 将字节数组写入到刚创建的图片文件中
                    fos = new FileOutputStream(file);
                    bos = new BufferedOutputStream(fos);
                    bos.write(byteArray);
                    picPath = Environment.getExternalStorageDirectory()
                            + imageName;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (baos != null) {
                        try {
                            baos.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (bos != null) {
                        try {
                            bos.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                civ_personal_head.setImageBitmap(photo);
                File file = new File(picPath);
                dpPostPhotoImage(file);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTORESOULT);
    }

}
