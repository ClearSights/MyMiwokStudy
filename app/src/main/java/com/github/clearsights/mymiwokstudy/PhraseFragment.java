package com.github.clearsights.mymiwokstudy;


import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.clearsights.mymiwokutil.Common;
import com.github.clearsights.mymiwokutil.MiwokDict;
import com.github.clearsights.mymiwokutil.MyListAdapter;
import com.github.clearsights.mymiwokutil.Word;

import java.util.ArrayList;

import static android.content.Context.AUDIO_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhraseFragment extends Fragment {

    private ListView wordListView;
    private String[] phrasesInEnglish, phrasesInMiwok;
    private int listItemLayoutId, listBgColorId;
    private int[] phraseAudioRes;
    private MyListAdapter myListAdapter;
    private MediaPlayer mediaPlayer;
    private AudioManager mAudioManager;
    private AudioAttributes mAudioAttributes;
    private AudioFocusRequest mFocusRequest;
    private int requestResult;
    private boolean isAudioFocusDelayed, isAudioFocusToResume;
    private Word currentWord;
    private Context mContext;
    private static String LOG_TAG = "PhraseFragment";

    public PhraseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = getLayoutInflater().inflate(R.layout.layout_word_list, container, false);
        mContext = getActivity();

        // UI
        wordListView = (ListView) rootView.findViewById(R.id.word_list);
        listItemLayoutId = R.layout.layout_list_item;
        listBgColorId = R.color.category_phrases;

        // data list
        ArrayList<Word> wordList = new ArrayList<>();

        // adapter
        myListAdapter = new MyListAdapter(mContext, listItemLayoutId, wordList, listBgColorId);

        // binding
        wordListView.setAdapter(myListAdapter);

        // get data
        phrasesInEnglish = MiwokDict.getPhrasesInEng();
        phrasesInMiwok = MiwokDict.getPhrasesInMiwok();
        phraseAudioRes = MiwokDict.getPhrasesAudioRes();

        // update display with data
        if (phrasesInEnglish.length == phrasesInMiwok.length) {
            for (int i = 0; i < phrasesInEnglish.length; i++) {
                Word newWord = new Word(phrasesInEnglish[i], phrasesInMiwok[i]);
                newWord.setAudioResource(phraseAudioRes[i]);
                wordList.add(newWord);
            }
        } else {
            Log.w(LOG_TAG, "list length of phrasesInEnglish does not match with phrasesInMiwok");
        }

        // prepare audio focus request
        mAudioManager = (AudioManager) mContext.getSystemService(AUDIO_SERVICE);
        if (Common.isApiLevelAfterO()) {
            mAudioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build();
            mFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
                    .setAudioAttributes(mAudioAttributes)
                    .setAcceptsDelayedFocusGain(true)
                    .setOnAudioFocusChangeListener(afChangeListener)
                    .build();
        }

        // handle list item click event
        wordListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentWord = myListAdapter.getItem(position);
                Log.v(LOG_TAG, "Current word: " + currentWord);

                // clean up resource
                stopPlayerAndCleanup();

                // request audio focus
                if (Common.isApiLevelAfterO()) {
                    requestResult = mAudioManager.requestAudioFocus(mFocusRequest);
                } else {
                    requestResult = mAudioManager.requestAudioFocus(afChangeListener,
                            AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                }

                // process request result
                switch (requestResult) {
                    case AudioManager.AUDIOFOCUS_REQUEST_GRANTED:
                        isAudioFocusDelayed = false;
                        // play now
                        startPlay();
                        break;
                    case AudioManager.AUDIOFOCUS_REQUEST_DELAYED:
                        isAudioFocusDelayed = true;
                        break;
                    case AudioManager.AUDIOFOCUS_REQUEST_FAILED:
                        isAudioFocusDelayed = false;
                        break;
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        stopPlayerAndCleanup();
    }

    private AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    if (isAudioFocusDelayed || isAudioFocusToResume) {
                        startPlay();
                        isAudioFocusDelayed = false;
                        isAudioFocusToResume = false;
                    }
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    stopPlayerAndCleanup();
                    isAudioFocusToResume = false;
                    isAudioFocusDelayed = false;
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    pausePlay();
                    mediaPlayer.seekTo(0);
                    isAudioFocusToResume = true;
                    isAudioFocusDelayed = false;
                    break;
            }
        }
    };

    // start playing audio
    public void startPlay() {
        mediaPlayer = MediaPlayer.create(mContext, currentWord.getAudioResource());

        // play
        mediaPlayer.start();

        // cleanup on completion
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlayerAndCleanup();
            }
        });
    }

    // pause playing audio
    public void pausePlay() {
        mediaPlayer.pause();
    }

    // clean up media player resources
    private void stopPlayerAndCleanup() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            if (Common.isApiLevelAfterO()) {
                mAudioManager.abandonAudioFocusRequest(mFocusRequest);
            } else {
                mAudioManager.abandonAudioFocus(afChangeListener);
            }
        }
    }
}
