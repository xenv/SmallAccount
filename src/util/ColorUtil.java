package util;

import java.awt.*;

/**
 * 工具类 ColorUtil 色彩
 */
public class ColorUtil {
    //预设颜色
    public static Color blueColor = Color.decode("#3399FF");
    public static Color grayColor = Color.decode("#999999");
    public static Color backgroundColor = Color.decode("#eeeeee");
    public static Color warningColor = Color.decode("#FF3333");

    //根据百分比绘制颜色
    public static Color getByPercentage(int per) {
        if (per > 100)
            per = 100;
        int b = 51;
        float rate = per / 100f;
        int r = (int) ((255 - 51) * rate + 51);
        int g = 255 - r + 51;
        return new Color(r, g, b);
    }
}
