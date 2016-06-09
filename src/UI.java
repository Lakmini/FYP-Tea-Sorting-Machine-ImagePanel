import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class UI {
	static JFrameWin jFrameWindow;
	public static void main(String[] args) {
		SwingUtilities.invokeLater(runJFrameLater);
	}
	public static class JFrameWin extends JFrame {
		public JFrameWin() {
			this.setSize(1000, 960);
			int width = 0;
			int height = 0;
			try {
				BufferedReader inConf = new BufferedReader(new FileReader(
						"config.txt"));
				height = Integer.valueOf(inConf.readLine().split(":")[1]);
				width = Integer.valueOf(inConf.readLine().split(":")[1]);
				inConf.close();
			} catch (IOException e) {
			}
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			BufferedImage currentImage;
			ReadYUYV ryuv = new ReadYUYV(width, height);
			ryuv.startReading("C:\\video_color_test_pattern.YUYV");
			currentImage = ryuv.getImage();
			try {
				ImageIO.write(currentImage, "BMP", new File("Formated_BMP.bmp"));
				ImageIO.write(currentImage, "GIF", new File("Formated_GIF.gif"));
			} catch (IOException e) {

				e.printStackTrace();
			}
			JLabel jLabel = new JLabel(new ImageIcon(currentImage));
			JPanel jPanel = new JPanel();
			jPanel.add(jLabel);
			this.add(jPanel);
		}
	}
	static Runnable runJFrameLater = new Runnable() {

		@Override
		public void run() {
			jFrameWindow = new JFrameWin();
			jFrameWindow.setVisible(true);

		}

	};

}