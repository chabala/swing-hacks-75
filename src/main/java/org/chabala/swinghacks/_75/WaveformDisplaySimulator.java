package org.chabala.swinghacks._75;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class WaveformDisplaySimulator {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WaveformDisplaySimulator::new);
    }

    private WaveformDisplaySimulator() {
        JFrame frame = new JFrame("Waveform Display Simulator");
        frame.setBounds(20, 200, 1880, 700);

        File file = chooseFile(frame);
        frame.setTitle("Waveform Display Simulator - " + file.getName());
        WaveformPanelContainer container = new WaveformPanelContainer();
        try (AudioInputStream audioInputStream =
                     AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            container.setAudioToDisplay(audioInputStream);
        } catch (IOException | UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(container, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible(true);
        frame.validate();
        frame.repaint();
    }

    private File chooseFile(Component parent) {
        final JFileChooser fc = new JFileChooser(new File("."));
        fc.setFileFilter(new FileNameExtensionFilter("WAV files", "wav", "wave"));

        int returnVal = fc.showOpenDialog(parent);

        if (returnVal != JFileChooser.APPROVE_OPTION) {
            System.exit(0);
        }
        return fc.getSelectedFile();
    }
}
