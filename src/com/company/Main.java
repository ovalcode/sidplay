package com.company;

import javax.sound.sampled.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Mixer.Info[]	aInfos = AudioSystem.getMixerInfo();
        Mixer		mixer = null;
        mixer = AudioSystem.getMixer(aInfos[0]);
        //mixer.
        float	fFrameRate = 44100.0F;
        AudioFormat	audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, fFrameRate, 16, 2, 4, fFrameRate, false);
        DataLine.Info	sourceInfo = new DataLine.Info(SourceDataLine.class, audioFormat, 40960);

        SourceDataLine	m_sourceLine = null;

        try {
            m_sourceLine = (SourceDataLine) mixer.getLine(sourceInfo);
            m_sourceLine.open(audioFormat, 40960);
            m_sourceLine.start();
            byte[] ee = new byte[1024*1024];
            int del = 0;
            for (int i = 0; i < 1000000; i = i + 4) {
                if (del > 40)
                    del = 0;
                if (del < 20) {
                    ee[i + 0] = 0;
                    ee[i + 1] = (byte)120;
                    ee[i + 2] = (byte)0;
                    ee[i + 3] = (byte)120;

                } else {
                    ee[i + 0] = 0;
                    ee[i + 1] = 0;
                    ee[i + 2] = 0;
                    ee[i + 3] = 0;


                }
                del++;
            }
            m_sourceLine.write(ee,0,500000);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }


    }
}
