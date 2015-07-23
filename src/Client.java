import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;

/**
 * Created by dean on 7/23/15.
 */
public class Client {

    public static void main(String[] args) throws IOException {
        BufferedImage img = ImageIO.read(new File("tiger_left.png"));
        Socket sockt = new Socket();
        SocketAddress sa = new InetSocketAddress("",222);
        SocketAddress s2 = new InetSocketAddress(223);
        sockt.connect(sa,7200);
        sockt.bind(s2);
        Socket socket = new Socket("localhost", 9999);

        try ( OutputStream os = socket.getOutputStream();
                ImageOutputStream ios = ImageIO.createImageOutputStream(os);
                ) {

            ImageIO.write(img,"png",ios);
        }

    }

}
