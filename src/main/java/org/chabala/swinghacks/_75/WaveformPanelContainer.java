package org.chabala.swinghacks._75;

import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class WaveformPanelContainer extends JPanel {

    WaveformPanelContainer() {
        setLayout(new GridLayout(0,1));
    }

    void setAudioToDisplay(AudioInputStream audioInputStream) throws IOException {
        AudioContainer audioContainer = new AudioContainer(audioInputStream);
        final int channels = audioInputStream.getFormat().getChannels();
        for (int channelIndex = 0; channelIndex < channels; channelIndex++) {
            add(createChannelDisplay(audioContainer, channelIndex));
        }
    }

    private JComponent createChannelDisplay(AudioContainer audioContainer, int channelIndex) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new SingleWaveformPanel(audioContainer, channelIndex), BorderLayout.CENTER);
        panel.add(new JLabel("Channel " + ++channelIndex), BorderLayout.NORTH);
        return panel;
    }
}
