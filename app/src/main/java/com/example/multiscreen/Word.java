package com.example.multiscreen;

public class Word {
    private String DefulteTranslation;
    private String MiwakTranslation;
    private static final  int No_Imaage_Provide = -1;
    private int ImageResourceId =No_Imaage_Provide;
    private int AudioResourceId;

    public  Word(String defultetranslation,String miwaktranslation,int audioResourceId){
        DefulteTranslation =defultetranslation;
        MiwakTranslation =miwaktranslation;
        AudioResourceId = audioResourceId;

    }

    public  Word(String defultetranslation,String miwaktranslation,int imageResourceId,int audioResourceId){
        DefulteTranslation =defultetranslation;
        MiwakTranslation =miwaktranslation;
        ImageResourceId = imageResourceId;
        AudioResourceId = audioResourceId;
    }

    public String getDefulteTranslation(){
        return  DefulteTranslation;
    }

    public String getMiwakTranslation() {
        return MiwakTranslation;
    }

    public int getImage_path() {
        return ImageResourceId;
    }

    public Boolean hasImage(){
        return ImageResourceId != No_Imaage_Provide;
    }

    public int getAudioResourceId() {
        return AudioResourceId;
    }

    @Override
    public String toString() {
        return "Word{" +
                "DefulteTranslation='" + DefulteTranslation + '\'' +
                ", MiwakTranslation='" + MiwakTranslation + '\'' +
                ", ImageResourceId=" + ImageResourceId +
                ", AudioResourceId=" + AudioResourceId +
                '}';
    }
}
