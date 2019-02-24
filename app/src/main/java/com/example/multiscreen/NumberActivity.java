package com.example.multiscreen;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumberActivity extends AppCompatActivity {
    private   MediaPlayer  mediaPlayer;
    private   AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener =
                    new AudioManager.OnAudioFocusChangeListener() {
        @Override
            public void onAudioFocusChange(int focusChange) {
                if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                        focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                    // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                    // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                    // our app is allowed to continue playing sound but at a lower volume. We'll treat
                    // both cases the same way because our app is playing short sound files.
                    // Pause playback and reset player to the start of the file. That way, we can
                    // play the word from the beginning when we resume playback.
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);
                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                    // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                    mediaPlayer.start();
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                    // Stop playback and clean up resources
                    releaseMediaPlayer();
                }
            }
            };


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        audioManager  = (AudioManager)getSystemService( Context.AUDIO_SERVICE );

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("One", "Lutti",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("Two", "otiiko",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("Three", "tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("Four", "oyyisa",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("Five", "massokka",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("Six", "temmokka",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("Seven", "kenekaku",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("Eight", "kkawinta",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("Nine", "wo'e",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("Ten", "na'aacha",R.drawable.number_ten,R.raw.number_ten));

        WordAdapter adapter = new WordAdapter(this, words,R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.NumbersLayout);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();

                int reslut = audioManager.requestAudioFocus(audioFocusChangeListener,
                                    AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(reslut == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    //audioManager.registerMediaButtonEventReceiver( RemoteControlRcceiver);
                    mediaPlayer = MediaPlayer.create(NumberActivity.this,word.getAudioResourceId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener( new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            releaseMediaPlayer();
                        }
                    } );

                }


            }
        } );
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
            audioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }

}
