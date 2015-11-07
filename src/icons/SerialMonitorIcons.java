package icons;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

/**
 * @author Dmitry_Cherkas
 */
public class SerialMonitorIcons {
    private static Icon load(String path) {
        return IconLoader.getIcon(path, SerialMonitorIcons.class);
    }

    public static final Icon OpenSerial = load("/icons/OpenSerial.png"); // 16x16
    public static final Icon OpenSerial_13x13 = load("/icons/OpenSerial_13x13.png"); // 16x16
    public static final Icon OpenSerial_32x32 = load("/icons/OpenSerial_32x32.png"); // 32x32

    public static final Icon Connect = load("/icons/Connect_16x16.png"); // 16x16
}
