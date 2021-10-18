package com.example.model.text;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextToSpeech {
    public static Voice[] voices;

    public TextToSpeech() {
    }

    public void speak(String word) {
        try {
            VoiceManager vm = VoiceManager.getInstance();
            voices = vm.getVoices();
            Voice voice = vm.getVoice("kevin16");
            voice.allocate();
            voice.speak(word);
            voice.deallocate();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }
}
