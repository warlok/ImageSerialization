import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

/**
 * Created by dean on 7/23/15.
 */
public class Server {

    public static void main(String[] args) throws IOException {

        final BufferedImage img;
        ServerSocket ss = new ServerSocket(9999);
        Socket socket = ss.accept();

        try ( InputStream is = socket.getInputStream();
              ImageInputStream iis = ImageIO.createImageInputStream(is);
                ) {

            img = ImageIO.read(iis);

            JFrame f = new JFrame();
            JPanel p = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(img, 0, 0, new ImageObserver() {
                        @Override
                        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                            return false;
                        }
                    });
                }
            };
            f.setContentPane(p);
            f.setBounds(0,0,500,500);
            f.setVisible(true);
            p.repaint();

            ImageIO.write(img, "png", new File("Imagnic.png"));

        } finally {
            socket.close();
            ss.close();
        }

    }

}
