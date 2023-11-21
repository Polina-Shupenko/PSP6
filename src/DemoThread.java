import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class DemoThread extends JFrame {

    private int cloudWidth = 1000;
    private int cloudHeight = 150;
    private int puddleHeight = 200;
    private int puddleY = 400;
    private int dropX = 200;
    private int dropY = 50;
    private int dropX1 = 500;
    private int dropY1 = 505;
    private int dropX2 = 550;
    private int dropY2 = 505;
    private int dropX3 = 640;
    private int dropY3 = 415;
    private int dropX4 = 752;
    private int dropY4 = 420;
    private int dropX5 = 670;
    private int dropY5 = 385;
    private int dropX6 = 480;
    private int dropY6 = 5;

    private static Image background;
    private static Image drop;
    private static Image puddle;
    private static Image cloud;
    JButton startButton;

    public DemoThread() {

        setTitle("Demo app");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        
        setContentPane(new Background()); // панель устанавливается как contentPane в наследнике JFrame
        Container content = getContentPane(); // теперь все элементы интерфейса будут на этой панели.

        startButton = new JButton("Старт");
        startButton.setPreferredSize(new Dimension(1000, 50));
        startButton.setBackground(Color.white);
        startButton.setForeground(Color.BLACK);
        
        startButton.addActionListener(e -> {

            startButton.setVisible(false);

            Thread cloudThread = new Thread(new CloudThread());
            cloudThread.start();

            Thread dropThread = new Thread(new DropThread());
            dropThread.start();

        });
        
        content.add(startButton);
        content.add(new CloudRain());
        
    }

    private static class Background extends JPanel { // отрисовка нового фона

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            try {
                background = ImageIO.read(new File("src/background.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g.drawImage(background, 0, 0, null);
        }
    }

    private class CloudRain extends JPanel {

        public CloudRain() {
            setOpaque(false); //прозрачность
            setPreferredSize(new Dimension(1000, 600));
            try {
                cloud = ImageIO.read(new File("src/cloud.png"));
                puddle = ImageIO.read(new File("src/puddle.png"));
                drop = ImageIO.read(new File("src/drop.png"));
            } catch (IOException exc) {
            }

        }

        @Override
        protected void paintComponent(Graphics g) {
            
            super.paintComponent(g);
            
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.drawImage(cloud, 0, 0, cloudWidth, cloudHeight, this);
            
            int puddleWidth = 1000;
            graphics2D.drawImage(puddle, 0, puddleY, puddleWidth, puddleHeight, this);

            graphics2D.drawImage(drop, dropX, dropY, 25, 25, this);
            graphics2D.drawImage(drop, dropX1, dropY1, 55, 55, this);
            graphics2D.drawImage(drop, dropX2, dropY2, 35, 35, this);
            graphics2D.drawImage(drop, dropX3, dropY3, 45, 45, this);
            graphics2D.drawImage(drop, dropX4, dropY4, 35, 35, this);
            graphics2D.drawImage(drop, dropX5, dropY5, 45, 45, this);
            graphics2D.drawImage(drop, dropX6, dropY6, 25, 25, this);
        }
    }

    public class CloudThread implements Runnable {
        @Override
        public void run() {
            while (cloudHeight > 0) {
                cloudHeight -= 2;
                cloudWidth -= 2;
                puddleHeight += 4;
                puddleY -= 4;
                repaint();
                try {
                    Thread.sleep(130);
                } catch (Exception exc) {
                }
                ;
            }
        }
    }

    public class DropThread implements Runnable {
        @Override
        public void run() {
            while (cloudHeight > 0) {
                
                dropX = ThreadLocalRandom.current().nextInt(0, cloudWidth + 1);
                dropY = ThreadLocalRandom.current().nextInt(cloudHeight, puddleY + 1);

                dropX1 = ThreadLocalRandom.current().nextInt(0, cloudWidth + 1);
                dropY1 = ThreadLocalRandom.current().nextInt(cloudHeight, puddleY + 1);
                dropX2 = ThreadLocalRandom.current().nextInt(0, cloudWidth + 1);
                dropY2 = ThreadLocalRandom.current().nextInt(cloudHeight, puddleY + 1);

                dropX3 = ThreadLocalRandom.current().nextInt(0, cloudWidth + 1);
                dropY3 = ThreadLocalRandom.current().nextInt(cloudHeight, puddleY + 1);

                dropX4 = ThreadLocalRandom.current().nextInt(0, cloudWidth + 1);
                dropY4 = ThreadLocalRandom.current().nextInt(cloudHeight, puddleY + 1);

                dropX5 = ThreadLocalRandom.current().nextInt(0, cloudWidth + 1);
                dropY5 = ThreadLocalRandom.current().nextInt(cloudHeight, puddleY + 1);

                dropX6 = ThreadLocalRandom.current().nextInt(0, cloudWidth + 1);
                dropY6 = ThreadLocalRandom.current().nextInt(cloudHeight, puddleY + 1);
                
                try {
                    Thread.sleep(150);
                } catch (Exception exc) {
                }
            }
        }
    }

    public static void main(String[] args) {
        new DemoThread().setVisible(true);
    }
}
