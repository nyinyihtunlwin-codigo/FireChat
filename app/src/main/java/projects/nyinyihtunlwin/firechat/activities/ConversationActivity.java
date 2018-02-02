package projects.nyinyihtunlwin.firechat.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.firechat.R;

public class ConversationActivity extends AppCompatActivity {

    public static final int PICK_IMAGE_REQUEST = 1;

    @BindView(R.id.iv_add_photo)
    ImageView ivAddPhoto;

    @BindView(R.id.iv_selected_photo)
    ImageView ivSelectedPhoto;

    @BindView(R.id.iv_close)
    ImageView ivClose;

    @BindView(R.id.rl_selected_photo)
    RelativeLayout rlSelectedPhoto;


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, ConversationActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        ButterKnife.bind(this, this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ivAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rlSelectedPhoto.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK && data != null
                && data.getData() != null) {
            Uri uri = data.getData();
            onPhotoTaken(uri.toString());
            //mTakenPhotosAdapter.addNewData(uri.toString());
        }
    }

    private void onPhotoTaken(String photoUrl) {
        if (TextUtils.isEmpty(photoUrl)) {
            Snackbar.make(ivAddPhoto, "ERROR : Path to photo is empty.", Snackbar.LENGTH_LONG).show();
            rlSelectedPhoto.setVisibility(View.GONE);
        } else {
            rlSelectedPhoto.setVisibility(View.VISIBLE);
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.mipmap.ic_launcher)
                    .centerCrop();
            Glide.with(getApplicationContext()).load(photoUrl).apply(requestOptions).into(ivSelectedPhoto);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
