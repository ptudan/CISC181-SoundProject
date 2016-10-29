/** Speech can be played directly using a SoundDevice
 * or save to a file in .wav format.
 */

import javax.sound.sampled.*;
import java.io.*;

public class Speech {

    private byte[] data;
    private int next;

    /**
     * Constructs a silent Speech to which content can be added.
     * @param duration maximum length in seconds.
     */
    public Speech(int duration) {
        data = new byte[2*duration*SoundDevice.SAMPLING_RATE];
        next = 0;
    }
    /**
     * Plays the Speech on a given device.
     * @param device the SoundDevice on which to play the Speech.
     */
    public void play(SoundDevice device) {
        SourceDataLine line = device.getLine();
        line.write(data, 0, next);
        line.drain();
    }

    /**
     * Saves the Speech on a file in WAV format.
     * @param format The AudioFormat used (can be retrieved from the SoundDevice).
     * @param file The File to be written.
     */
    public void save(AudioFormat format, File file) {
        try {
            ByteArrayInputStream bs = new ByteArrayInputStream(data);
            AudioInputStream as = new AudioInputStream(bs, format, next/2);
            AudioSystem.write(as, AudioFileFormat.Type.WAVE, file);
        }  catch (Exception e) {throw new RuntimeException(e);}
    }

    /**
     * Adds more content to the Speech, after previous content.
     * @param ds the content to be added.
     */
    public void add(double[] ds) {
        for (int i=0; i < ds.length; i++) {
            int temp = (short) (Math.min(Math.max(-1,ds[i]),1) * Short.MAX_VALUE);
            data[next] = (byte) temp;
            data[next+1] = (byte) (temp >> 8);
            next += 2;
        }
    }
}
