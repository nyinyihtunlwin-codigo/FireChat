package projects.nyinyihtunlwin.firechat.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import projects.nyinyihtunlwin.firechat.R;

public class ConversationActivity extends AppCompatActivity {

    public static final String CONVERSATION_ID = "CONVERSATION_ID";

    public static Intent newIntent(Context context, String conversationId) {
        Intent intent = new Intent(context, ConversationActivity.class);
        intent.putExtra(CONVERSATION_ID, conversationId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
