package com.example.mymiwokstudy;


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

import com.example.mymiwokutil.Common;
import com.example.mymiwokutil.MiwokDict;
import com.example.mymiwokutil.MyListAdapter;
import com.example.mymiwokutil.Word;

import java.util.ArrayList;

import static android.content.Context.AUDIO_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    private ListView wordListView;
    private String[] numbersInEnglish, numbersInMiwok;
    private int[] numberImgRes, numberAudioRes;
    private int listItemLayoutId, listBgColorId;
    private MyListAdapter myListAdapter;
    private MediaPlayer mediaPlayer;
    private AudioManager mAudioManager;
    private AudioAttributes mAudioAttributes;
    private AudioFocusRequest mFocusRequest;
    private int requestResult;
    private boolean isAudioFocusDelayed, isAudioFocusToResume;
    private Word currentWord;
    private Context mContext;
    private static String LOG_TAG = "NumbersFragment";

    public NumbersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.layout_word_list, container, false);
        mContext = getActivity();

        // UI
        wordListView = (ListView) rootView.findViewById(R.id.word_list);
        listItemLayoutId = R.layout.layout_list_item;
        listBgColorId = R.color.category_numbers;

        // data list
        ArrayList<Word> wordList = new ArrayList<>();

        // adapter
        myListAdapter = new MyListAdapter(mContext, listItemLayoutId, wordList, listBgColorId);

        // binding
        wordListView.setAdapter(myListAdapter);

        // get raw data
        numbersInEnglish = MiwokDict.getNumbersInEng();
        numbersInMiwok = MiwokDict.getNumbersInMiwok();
        numberImgRes = MiwokDict.getNumbersImgRes();
        numberAudioRes = MiwokDict.getNumbersAudioRes();

        // update display with data
        if (numbersInEnglish.length == numbersInMiwok.length) {
            for (int i = 0; i < numbersInEnglish.length; i++) {
                Word newWord = new Word(numbersInEnglish[i], numbersInMiwok[i], numberImgRes[i]);
                newWord.setAudioResource(numberAudioRes[i]);
                wordList.add(newWord);
            }
        } else {
            Log.w(LOG_TAG, "list length of numbersInEnglish does not match with numbersInMiwok");
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