/*
  TODO 1 : Start playing the audio file automatically on activity start. This will solve the issue of onDestroy Null problems
  TODO 2 : Implement methods to get the right raw resources based on the position of the clicked ListView event
  TODO 3 : Implement next chapter, previous chapter logic and seek bar
  TODO 4 : Implement auto play next chapter when a chapter ends. It stops playback on last chapter. Button will change to start playing 1st
  TODO 5 :
*/

package org.iskcon.icc.bhagavadgitakannada;

import android.content.ComponentName;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import org.iskcon.icc.bhagavadgitakannada.services.ControlPlayerService;

public class MainActivity extends AppCompatActivity {
    private static final int STATE_PAUSED = 0;
    private static final int STATE_PLAYING = 1;

    private int mCurrentState;
    private static final String TAG = MainActivity.class.getSimpleName();
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
                String chapterRawResourceName = getIntent().getStringExtra("chapterRawResourceName");
                int chapterId = getApplicationContext().getResources().getIdentifier(chapterRawResourceName, "raw", getApplicationContext().getPackageName());
                getSupportMediaController().getTransportControls().playFromMediaId(String.valueOf(chapterId), null);
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

        playButton = (Button) findViewById(R.id.button);
        mMediaBrowserCompat = new MediaBrowserCompat(this, new ComponentName(this, ControlPlayerService.class),
                mMediaBrowserCompatConnectionCallback, getIntent().getExtras());
        Log.d(TAG,"MediaBrowser contains " + mMediaBrowserCompat.toString());
        mMediaBrowserCompat.connect();
        playButton.setOnClickListener(new View.OnClickListener() {
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
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getSupportMediaController().getPlaybackState() == null) return;
        if( getSupportMediaController().getPlaybackState().getState() == PlaybackStateCompat.STATE_PLAYING ) {
            getSupportMediaController().getTransportControls().pause();
        }

        mMediaBrowserCompat.disconnect();
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
