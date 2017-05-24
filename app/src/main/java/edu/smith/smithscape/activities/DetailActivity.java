package edu.smith.smithscape.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;

import com.basecamp.turbolinks.TurbolinksView;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.smith.smithscape.R;

/**
 * This class is a detail view, for individual Spaces/tech items.
 */
public class DetailActivity extends ScoutActivity{

    private static final String LOG_TAG = DetailActivity.class.getSimpleName();
    private String location;
    @BindView(R.id.turbolinks_view) TurbolinksView turbolinksView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        turbolinksView = (TurbolinksView) findViewById(R.id.turbolinks_view);

        location = getIntent().getStringExtra(CONSTANTS.INTENT_URL_KEY);

        turbolinksSession.progressView(LayoutInflater.from(this).inflate(com.basecamp.turbolinks.R.layout.turbolinks_progress, turbolinksView, false), com.basecamp.turbolinks.R.id.turbolinks_default_progress_indicator, Integer.MAX_VALUE)
                .activity(this)
                .adapter(this)
                .view(turbolinksView)
                .visit(location);
    }

    /**
     * Upon page load, set the ActionBar title to the page title
     */
    @Override
    public void visitCompleted() {
        String pageTitle = turbolinksSession.getWebView().getTitle();
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setTitle(pageTitle);
    }

    /**
     * Visits the URI provided with the Android system interpreter.
     */
    @Override
    public void visitProposedToLocationWithAction(String location, String action) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(location));
        startActivity(browserIntent);
    }
}