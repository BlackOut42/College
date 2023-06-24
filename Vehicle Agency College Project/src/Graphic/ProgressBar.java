// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package Graphic;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ProgressBar extends JFrame {
    private JProgressBar progressBar;
    private JLabel timeLabel;

    public ProgressBar(String vehicleModel) {
    	super("Driving " + vehicleModel);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        timeLabel = new JLabel();
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(progressBar, BorderLayout.CENTER);
        add(timeLabel, BorderLayout.SOUTH);
    }

    public void showProgress(long finishTimeMillis) {
        long startTimeMillis = System.currentTimeMillis();
        long totalTimeMillis = finishTimeMillis - startTimeMillis;

        Timer timer = new Timer(100, e -> {
            long currentTimeMillis = System.currentTimeMillis();
            long elapsedTimeMillis = currentTimeMillis - startTimeMillis;

            int progress = (int) ((elapsedTimeMillis * 100) / totalTimeMillis);
            progressBar.setValue(progress);

            String remainingTime = formatTime(totalTimeMillis - elapsedTimeMillis);
            timeLabel.setText("Remaining Time: " + remainingTime);

            if (elapsedTimeMillis >= totalTimeMillis) {
                ((Timer) e.getSource()).stop();
                timeLabel.setText("Task Completed!");
                dispose();
            }
        });
        timer.start();

    }

    private String formatTime(long millis) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss.SSS");
        return dateFormat.format(new Date(millis));
    }

}