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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.firechat.R;
import projects.nyinyihtunlwin.firechat.data.models.FireChatModel;

public class ConversationActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int PICK_IMAGE_REQUEST = 1;

    public static final String PARTNER_ID = "partner_id";

    @BindView(R.id.iv_add_photo)
    ImageView ivAddPhoto;

    @BindView(R.id.iv_selected_photo)
    ImageView ivSelectedPhoto;

    @BindView(R.id.iv_close)
    ImageView ivClose;

    @BindView(R.id.rl_selected_photo)
    RelativeLayout rlSelectedPhoto;

    @BindView(R.id.iv_send)
    ImageView ivSend;

    @BindView(R.id.et_message)
    EditText etMessage;

    private String mPartnerId;
    private String mPhotoUrl;

    public static Intent newIntent(Context context, String partnerId) {
        Intent intent = new Intent(context, ConversationActivity.class);
        intent.putExtra(PARTNER_ID, partnerId);
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

        if (!getIntent().getStringExtra(PARTNER_ID).isEmpty()) {
            mPartnerId = getIntent().getStringExtra(PARTNER_ID);
        }

        FireChatModel.getInstance().startConversation(mPartnerId);

        ivAddPhoto.setOnClickListener(this);

        ivClose.setOnClickListener(this);

        ivSend.setOnClickListener(this);
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
        mPhotoUrl=photoUrl;
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_send:
                String message = etMessage.getText().toString();
                FireChatModel.getInstance().sendMessage(mPhotoUrl,message);
                break;
            case R.id.iv_close:
                rlSelectedPhoto.setVisibility(View.GONE);
                break;
            case R.id.iv_add_photo:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                break;
        }
    }
}
