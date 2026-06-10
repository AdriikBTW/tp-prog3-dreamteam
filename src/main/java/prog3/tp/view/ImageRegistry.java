package prog3.tp.view;

import java.awt.Image;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

final class ImageRegistry {
    private static final Map<String, ImageIcon> CACHE =
            new HashMap<>();

    private static final int ICON_SIZE = 50;
    private static final String DEFAULT_KEY = "DEFAULT";
    private static final String DEFAULT_PATH =
            "/images/default.png";

    static {
        CACHE.put(DEFAULT_KEY, loadResource(DEFAULT_PATH));
    }

    private ImageRegistry() {
    }

    static void register(String name, String path) {
        CACHE.put(name, load(path));
    }

    static ImageIcon getIcon(String name) {
        return CACHE.getOrDefault(
                name,
                CACHE.get(DEFAULT_KEY)
        );
    }

    private static ImageIcon load(String path) {
        if (path == null || path.isBlank())
            return CACHE.get(DEFAULT_KEY);

        ImageIcon icon = new ImageIcon(path);

        if (icon.getIconWidth() <= 0)
            return CACHE.get(DEFAULT_KEY);

        Image image = icon.getImage().getScaledInstance(
                ICON_SIZE,
                ICON_SIZE,
                Image.SCALE_SMOOTH
        );

        return new ImageIcon(image);
    }

    private static ImageIcon loadResource(String path) {
        URL url = ImageRegistry.class.getResource(path);

        if (url == null)
            return new ImageIcon();

        ImageIcon icon = new ImageIcon(url);

        Image image = icon.getImage().getScaledInstance(
                ICON_SIZE,
                ICON_SIZE,
                Image.SCALE_SMOOTH
        );

        return new ImageIcon(image);
    }

    static int getScaledImageSize() {
        return ICON_SIZE;
    }
}
