package prog3.tp.view;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;
import javax.swing.JButton;

class ToolbarButton extends JButton {
    private Font _font;
    public ToolbarButton(String text) {
        super(text);
        loadFont();
        this.setFont(_font);
    }

    private void loadFont() {
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/jetbrains.ttf");
            _font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(20f);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(_font);
        } catch (Exception e) {
            _font = new Font("SansSerif", Font.PLAIN, 25);
        }

    }
}
