package com.example.mymiwokutil;

public class Word {
    private String englishName;
    private String miwokName;
    private int imgResource = 0;
    private int audioResource = 0;
    private boolean hasImg = false;

    public Word(String eng, String miwok) {
        englishName = eng;
        miwokName = miwok;
    }

    public Word(String eng, String miwok, int imgResource) {
        englishName = eng;
        miwokName = miwok;
        this.imgResource = imgResource;
        hasImg = true;
    }

    public String getMiwokName() {
        return miwokName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public int getImgResource() {
        return imgResource;
    }

    public int getAudioResource() {
        return audioResource;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public void setMiwokName(String miwokName) {
        this.miwokName = miwokName;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public void setAudioResource(int audioResource) {
        this.audioResource = audioResource;
    }

    public boolean hasImg() {
        return hasImg;
    }

    @Override
    public String toString() {
        return "Word{" +
                "englishName='" + englishName + '\'' +
                ", miwokName='" + miwokName + '\'' +
                ", imgResource=" + imgResource +
                ", audioResource=" + audioResource +
                ", hasImg=" + hasImg +
                '}';
    }
}
