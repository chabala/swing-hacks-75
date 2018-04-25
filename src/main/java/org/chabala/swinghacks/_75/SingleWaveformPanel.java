package org.chabala.swinghacks._75;

import javax.swing.*;
import java.awt.*;

public class SingleWaveformPanel extends JPanel {
    private static final Color BACKGROUND_COLOR = Color.white;
    private static final Color REFERENCE_LINE_COLOR = Color.black;
    private static final Color WAVEFORM_COLOR = Color.red;

    private AudioContainer audioContainer;
    private int channelIndex;

    SingleWaveformPanel(AudioContainer audioContainer, int channelIndex) {
        this.audioContainer = audioContainer;
        this.channelIndex = channelIndex;
        setBackground(BACKGROUND_COLOR);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int referenceLineHeight = getHeight() / 2;
        g.setColor(REFERENCE_LINE_COLOR);
        g.drawLine(0, referenceLineHeight, getWidth(), referenceLineHeight);

        drawWaveform(g, audioContainer.getSamples(channelIndex));
    }

    private void drawWaveform(Graphics g, short[] samples) {
        if (samples == null) {
            return;
        }

        final int midpointHeight = getHeight() / 2;
        final int increment = audioContainer.getIncrement(getWidth());
        g.setColor(WAVEFORM_COLOR);

        final double scaleFactor = audioContainer.getYScaleFactor(getHeight());
        for (int sampleIndex = 0, xIndex = 0; sampleIndex < samples.length; sampleIndex += increment, xIndex++) {
            int endIndex = Math.min(sampleIndex + increment, samples.length);
            MinMaxStats stats = new MinMaxStats();
            for (int subIndex = sampleIndex; subIndex < endIndex; subIndex++) {
                stats.accept(samples[subIndex]);
            }

            int minY = (int) (midpointHeight - stats.getMin() * scaleFactor);
            int maxY = (int) (midpointHeight - stats.getMax() * scaleFactor);

            g.drawLine(xIndex, minY, xIndex, maxY);
        }
    }
}
