package org.chabala.swinghacks._75;

import javax.sound.sampled.AudioInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

class AudioContainer {

    private short[][] samplesByChannel;
    private short biggestSample;

    AudioContainer(AudioInputStream audioInputStream) throws IOException {
        byte[] bytes = readAudioInputStreamToByteArray(audioInputStream);

        //convert sample bytes to channel separated 16 bit samples
        samplesByChannel = convertToShortArray(bytes, audioInputStream.getFormat().getChannels());
    }

    private byte[] readAudioInputStreamToByteArray(AudioInputStream audioInputStream) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int numBytesRead;
        byte[] data = new byte[16384];

        while ((numBytesRead = audioInputStream.read(data, 0, data.length)) != -1) {
            out.write(data, 0, numBytesRead);
        }
        out.flush();
        return out.toByteArray();
    }

    private short[][] convertToShortArray(byte[] byteArray, int numChannels) {
        int lengthInFrames = byteArray.length / (Short.BYTES * numChannels);
        short[][] toReturn = new short[numChannels][lengthInFrames];
        int sampleIndex = 0;
        MinMaxStats stats = new MinMaxStats();
        for (int byteArrayIndex = 0; byteArrayIndex < byteArray.length; sampleIndex++) {
            for (int channel = 0; channel < numChannels; channel++) {
                byte low = byteArray[byteArrayIndex++];
                byte high = byteArray[byteArrayIndex++];
                short sample = getSixteenBitSample(high, low);
                stats.accept(sample);
                toReturn[channel][sampleIndex] = sample;
            }
        }
        //find biggest sample. used for interpolating the yScaleFactor
        biggestSample = (short) Math.max(stats.getMax(), -stats.getMin());
        return toReturn;
    }

    private short getSixteenBitSample(byte high, byte low) {
        return (short) ((high << 8) + (low & 0x00ff));
    }

    double getYScaleFactor(int panelHeight) {
        return (panelHeight / (biggestSample * 2 * 1.1));
    }

    short[] getSamples(int channel) {
        return samplesByChannel[channel];
    }

    int getIncrement(int panelWidth) {
        return (int) (samplesByChannel[0].length / (double) panelWidth);
    }
}
