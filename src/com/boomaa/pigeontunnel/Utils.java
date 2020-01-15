package com.boomaa.pigeontunnel;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;

public class Utils {
	public static AudioFormat AUDIO_FORMAT = new AudioFormat(Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
}
