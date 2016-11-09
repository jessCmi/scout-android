package edu.uw.scout.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;

import com.basecamp.turbolinks.TurbolinksSession;
import com.basecamp.turbolinks.TurbolinksView;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import edu.uw.scout.R;

public class FilterActivity extends ScoutActivity {

    private static final String LOG_TAG = FilterActivity.class.getSimpleName();
    private String location;
    @BindView(R.id.turbolinks_view)
    TurbolinksView turbolinksView;
    TurbolinksSession turbolinksSession;
    private String queryParams = "";
    @BindView(R.id.filter_submit)
    FloatingActionButton fab;
    private int filterType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar) ;
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        turbolinksView = (TurbolinksView) findViewById(R.id.turbolinks_view);

        location = getIntent().getStringExtra(CONSTANTS.INTENT_URL_KEY);
        filterType = getIntent().getIntExtra(CONSTANTS.FILTER_TYPE_KEY, 1);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        turbolinksSession = TurbolinksSession.getDefault(this);
        turbolinksSession.addJavascriptInterface(this, "scoutBridge");
        turbolinksSession.progressView(LayoutInflater.from(this).inflate(com.basecamp.turbolinks.R.layout.turbolinks_progress, turbolinksView, false), com.basecamp.turbolinks.R.id.turbolinks_default_progress_indicator, Integer.MAX_VALUE)
                .activity(this)
                .adapter(this)
                .view(turbolinksView)
                .visit(location);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear) {
            queryParams = "";
            onBackPressed();
            return true;
        } else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Upon page load, set the ActionBar title to the page title
     */
    @Override
    public void visitCompleted() {
        String pageTitle = TurbolinksSession.getDefault(this).getWebView().getTitle();
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setTitle(pageTitle);
    }

    /**
     * Visits the URI provided with the Android system interpreter.
     */
    @Override
    public void visitProposedToLocationWithAction(String location, String action) {

    }

    @Override
    public void onBackPressed(){
        submitForm();
        super.onBackPressed();
    }

    /**
     * Retrieve the filter URL from the app and then 
     */
    private void submitForm(){
        switch (filterType){
            case 1:
                userPreferences.saveFoodFilter(queryParams);
                break;
            case 2:
                userPreferences.saveStudyFilter(queryParams);
                break;
            case 3:
                userPreferences.saveTechFilter(queryParams);
                break;
        }
    }

    @JavascriptInterface
    public void setParams(String params){
        this.queryParams = params;
    }

}
