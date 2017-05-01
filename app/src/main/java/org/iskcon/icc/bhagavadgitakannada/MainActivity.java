package org.iskcon.icc.bhagavadgitakannada;

import android.content.ComponentName;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import org.iskcon.icc.bhagavadgitakannada.services.ControlPlayerService;

public class MainActivity extends AppCompatActivity {
    private static final int STATE_PAUSED = 0;
    private static final int STATE_PLAYING = 1;

    private int mCurrentState;

    private MediaBrowserCompat mMediaBrowserCompat;
    private MediaControllerCompat mMediaControllerCompat;
    private Button playButton;
    private MediaBrowserCompat.ConnectionCallback mMediaBrowserCompatConnectionCallback = new MediaBrowserCompat.ConnectionCallback() {

        @Override
        public void onConnected() {
            super.onConnected();
            try {
                mMediaControllerCompat = new MediaControllerCompat(MainActivity.this, mMediaBrowserCompat.getSessionToken());
                mMediaControllerCompat.registerCallback(mMediaControllerCompatCallback);
                setSupportMediaController(mMediaControllerCompat);
                //getSupportMediaController().getTransportControls().playFromMediaId(String.valueOf(R.raw.srila_prabhupada), null);

            } catch( RemoteException e ) {

            }
        }
    };

    private MediaControllerCompat.Callback mMediaControllerCompatCallback = new MediaControllerCompat.Callback() {

        @Override
        public void onPlaybackStateChanged(PlaybackStateCompat state) {
            super.onPlaybackStateChanged(state);
            if( state == null ) {
                return;
            }

            switch( state.getState() ) {
                case PlaybackStateCompat.STATE_PLAYING: {
                    mCurrentState = STATE_PLAYING;
                    break;
                }
                case PlaybackStateCompat.STATE_PAUSED: {
                    mCurrentState = STATE_PAUSED;
                    break;
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //playButton = (Button) findViewById(R.id.button);
        //mMediaBrowserCompat = new MediaBrowserCompat(this, new ComponentName(this, ControlPlayerService.class),
         //       mMediaBrowserCompatConnectionCallback, getIntent().getExtras());

        //mMediaBrowserCompat.connect();
        //playChapter(0);
        /*playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentState == STATE_PAUSED) {
                    getSupportMediaController().getTransportControls().play();
                    mCurrentState = STATE_PLAYING;
                } else {
                    if (getSupportMediaController().getPlaybackState().getState() == PlaybackStateCompat.STATE_PLAYING) {
                        getSupportMediaController().getTransportControls().pause();
                    }
                    mCurrentState = STATE_PAUSED;
                }
            }
        });*/
    }

    public void playChapter(int chapterIndex) {
        //TODO Logic to get the right Verses to play
        //getSupportMediaController().getTransportControls().playFromMediaId(String.valueOf(R.raw.srila_prabhupada),null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //if( getSupportMediaController().getPlaybackState().getState() == PlaybackStateCompat.STATE_PLAYING ) {
        //    getSupportMediaController().getTransportControls().pause();
        //}

        //mMediaBrowserCompat.disconnect();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
