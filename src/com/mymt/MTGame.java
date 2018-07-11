package com.mymt;

import com.mymt.bean.BaseBean;
import com.mymt.bean.DialoguesBean;
import com.mymt.bean.ItemsBean;
import com.mymt.bean.MonsterBean;

import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;


/**
 * MTGame 类
 * <p>
 * 魔塔游戏主类。
 * 主要是根据 魔塔 v1.12 这个版本 进行仿制。
 * <p>
 * 目前所使用的资源文件（72 pix）仅对屏幕分辨率 1920 * 1080 或以上的进行了适配。后续将适配 1280 * 720（54 pix）
 * <p>
 * GitHub 地址：https://github.com/gdut-yy/MagicTower
 *
 * @author ZYY
 * @since 2018-7-9
 */
public class MTGame extends JPanel {

    public final static int GAME_PIX = 72;    // 像素值。需跟资源文件的像素值相匹配。

    public static JPanel game;
    public static JFrame fr = new JFrame("魔塔 v1.12 Java仿制版     【 GitHub地址：https://github.com/gdut-yy/MagicTower 】");
    public static int currentFloor = 0;
    public static int maxFloor = 0;
    public static int gameMin = 0;
    public static double gameSec = 0;
    public static JLayeredPane forecastFrame = new JLayeredPane();     // 预测信息面板
    public static JLayeredPane jumpFrame = new JLayeredPane();         // 跳跃信息面板
    public static JLayeredPane battleFrame = new JLayeredPane();       // 战斗信息面板
    public static JLayeredPane msgPane = new JLayeredPane();           // 提示信息面板
    public static JLabel msg = new JLabel();
    public static JLabel time;
    public static BufferedImage mapImgSheet;       // 地图素材获取
    public static BufferedImage enemyImgSheet;     // 怪物素材获取
    public static BufferedImage itemImgSheet;      // 道具素材获取
    public static BufferedImage dialogueImgSheet;  // 对话图片获取
    public static BufferedImage background;        // 背景图片获取
    public static PlayerBean playerBean_1 = new PlayerBean();
    public static boolean inConversation = false;
    public static boolean hasCross = false;
    public static boolean hasPickaxe = false;
    public static boolean talked = false;
    public static boolean fly = false;
    public static boolean forecast = false;

    public static Object[][] enemyDict = new Object[33][6];

    public static int[][][] LvMap = {
            {   // 第 0 层
                    {2, 4, 4, 4, 4, 8, 4, 4, 4, 4, 2},
                    {2, 4, 4, 4, 4, 1, 4, 4, 4, 4, 2},
                    {2, 4, 4, 4, 4, 1, 4, 4, 4, 4, 2},
                    {2, 4, 4, 4, 4, 1, 4, 4, 4, 4, 2},
                    {2, 4, 4, 4, 4, 1, 4, 4, 4, 4, 2},
                    {2, 4, 4, 4, 4, 1, 4, 4, 4, 4, 2},
                    {2, 2, 4, 4, 4, 1, 4, 4, 4, 2, 2},
                    {2, 2, 2, 2, 2, 5, 2, 2, 2, 2, 2},
                    {3, 2, 3, 2, 1, 1, 1, 2, 3, 2, 3},
                    {3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 3},
            },
            {   // 第 1 层
                    {8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
                    {1, 1, 1, 5, 1, 2, 1, 1, 1, 2, 1},
                    {1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1},
                    {2, 5, 2, 2, 1, 2, 2, 2, 1, 2, 1},
                    {1, 1, 1, 2, 1, 5, 1, 1, 1, 2, 1},
                    {1, 1, 1, 2, 1, 2, 2, 2, 2, 2, 1},
                    {2, 5, 2, 2, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 2, 2, 7, 2, 2, 2, 5, 2},
                    {1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1},
                    {1, 1, 1, 2, 1, 9, 1, 2, 1, 1, 1},
            },
            {   // 第 2 层
                    {9, 2, 1, 1, 1, 2, 1, 1, 1, 1, 2},
                    {1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 2},
                    {1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 2},
                    {1, 2, 1, 2, 1, 2, 2, 2, 2, 5, 2},
                    {1, 2, 1, 2, 1, 1, 1, 5, 1, 1, 2},
                    {1, 2, 5, 2, 2, 5, 2, 2, 5, 2, 2},
                    {1, 11, 1, 1, 1, 1, 2, 1, 1, 1, 2},
                    {1, 2, 5, 2, 2, 6, 2, 10, 2, 10, 2},
                    {1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2},
                    {1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2},
                    {8, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2},
            },
            {   // 第 3 层
                    {1, 1, 1, 2, 1, 1, 1, 2, 2, 2, 2},
                    {1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1},
                    {1, 1, 1, 2, 2, 5, 2, 2, 1, 2, 1},
                    {2, 5, 2, 2, 1, 1, 1, 2, 1, 2, 1},
                    {1, 1, 1, 2, 2, 2, 1, 2, 1, 2, 1},
                    {1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1},
                    {1, 2, 2, 2, 2, 2, 1, 1, 1, 2, 1},
                    {1, 1, 1, 1, 1, 2, 2, 5, 2, 2, 1},
                    {2, 2, 2, 2, 1, 2, 1, 1, 1, 2, 1},
                    {2, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1},
                    {9, 1, 2, 2, 2, 2, 1, 1, 1, 2, 8},
            },
            {   // 第 4 层
                    {1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1},
                    {5, 2, 5, 2, 1, 1, 1, 2, 5, 2, 5},
                    {1, 2, 1, 2, 2, 10, 2, 2, 1, 2, 1},
                    {1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1},
                    {1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1},
                    {1, 2, 1, 2, 2, 7, 2, 2, 1, 2, 1},
                    {1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1},
                    {1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1},
                    {1, 2, 1, 2, 2, 6, 2, 2, 1, 2, 1},
                    {1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1},
                    {8, 2, 1, 1, 1, 1, 1, 1, 1, 2, 9},
            },
            {   // 第 5 层
                    {1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1},
                    {1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1},
                    {1, 2, 1, 2, 1, 1, 2, 2, 5, 2, 2},
                    {1, 5, 1, 2, 1, 1, 2, 1, 1, 1, 1},
                    {1, 2, 1, 2, 2, 2, 2, 1, 1, 1, 1},
                    {1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 2, 2, 1, 2, 2, 2, 2, 1, 1, 1},
                    {1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1},
                    {2, 2, 2, 1, 2, 5, 2, 6, 2, 5, 2},
                    {1, 1, 2, 1, 2, 1, 2, 1, 5, 1, 2},
                    {9, 1, 1, 1, 1, 1, 2, 1, 2, 8, 2},
            },
            {   // 第 6 层
                    {1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1},
                    {1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1},
                    {1, 1, 6, 1, 6, 1, 1, 1, 2, 1, 1},
                    {1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1},
                    {2, 2, 2, 7, 2, 2, 2, 2, 2, 5, 2},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {1, 2, 1, 5, 1, 1, 1, 1, 1, 1, 2},
                    {1, 2, 5, 2, 5, 2, 2, 2, 2, 6, 2},
                    {1, 2, 1, 2, 1, 1, 2, 2, 1, 1, 2},
                    {1, 1, 1, 2, 8, 1, 5, 5, 1, 9, 2},
            },
            {   // 第 7 层
                    {8, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2},
                    {2, 2, 1, 1, 2, 6, 2, 1, 1, 2, 2},
                    {2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2},
                    {1, 1, 2, 2, 2, 10, 2, 2, 2, 1, 1},
                    {1, 1, 6, 1, 10, 1, 10, 1, 6, 1, 1},
                    {1, 2, 2, 2, 2, 10, 2, 2, 2, 2, 1},
                    {1, 2, 1, 1, 2, 1, 2, 1, 1, 2, 1},
                    {1, 2, 1, 1, 2, 6, 2, 1, 1, 2, 1},
                    {1, 2, 2, 1, 1, 1, 1, 1, 2, 2, 1},
                    {1, 1, 2, 2, 2, 7, 2, 2, 2, 1, 1},
                    {2, 1, 1, 5, 9, 1, 1, 1, 1, 1, 2},
            },
            {   // 第 8 层
                    {9, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1},
                    {1, 2, 1, 2, 2, 5, 2, 5, 2, 2, 1},
                    {1, 2, 1, 2, 1, 1, 6, 1, 1, 2, 1},
                    {1, 2, 1, 2, 1, 2, 2, 2, 1, 2, 1},
                    {1, 2, 1, 2, 1, 2, 8, 1, 1, 2, 1},
                    {1, 2, 1, 2, 1, 2, 2, 2, 2, 2, 1},
                    {1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 1},
                    {1, 2, 1, 2, 2, 2, 1, 2, 5, 2, 2},
                    {1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1},
                    {1, 2, 2, 2, 5, 2, 1, 2, 2, 2, 1},
                    {1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1},
            },
            {   // 第 9 层
                    {1, 1, 1, 2, 2, 2, 1, 1, 1, 2, 1},
                    {1, 1, 1, 5, 1, 1, 1, 2, 1, 5, 1},
                    {2, 5, 2, 2, 1, 2, 2, 2, 1, 2, 1},
                    {1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1},
                    {1, 1, 1, 7, 1, 2, 9, 2, 1, 2, 1},
                    {2, 6, 2, 2, 1, 2, 2, 2, 1, 2, 2},
                    {1, 1, 1, 2, 1, 2, 8, 2, 1, 2, 1},
                    {2, 5, 2, 2, 1, 1, 1, 5, 1, 2, 1},
                    {1, 1, 1, 2, 2, 6, 2, 2, 1, 2, 1},
                    {1, 1, 1, 2, 1, 1, 1, 2, 1, 5, 1},
                    {1, 1, 1, 5, 1, 1, 1, 2, 1, 2, 1},
            },
            {   // 第 10 层
                    {1, 2, 2, 1, 1, 2, 1, 1, 2, 2, 1},
                    {1, 1, 2, 2, 5, 2, 5, 2, 2, 1, 1},
                    {1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1},
                    {1, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2},
                    {1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1},
                    {1, 2, 1, 2, 2, 2, 2, 5, 2, 2, 1},
                    {1, 2, 1, 10, 1, 9, 2, 1, 5, 1, 1},
                    {1, 2, 2, 2, 2, 2, 2, 5, 2, 2, 1},
                    {1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1},
                    {1, 2, 1, 1, 1, 7, 1, 2, 1, 2, 1},
                    {8, 2, 1, 1, 1, 2, 1, 2, 1, 2, 1},
            },
            {   // 第 11 层
                    {1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 1},
                    {1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 1},
                    {1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 1},
                    {5, 2, 5, 2, 5, 2, 5, 2, 2, 6, 2},
                    {1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1},
                    {5, 2, 2, 6, 2, 2, 2, 6, 2, 2, 5},
                    {1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1},
                    {1, 2, 1, 2, 2, 2, 2, 2, 1, 2, 1},
                    {1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1},
                    {2, 2, 7, 2, 1, 1, 1, 2, 7, 2, 2},
                    {9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 8},
            },
            {   // 第 12 层
                    {1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1},
                    {1, 1, 2, 1, 2, 5, 2, 1, 2, 1, 1},
                    {1, 1, 2, 1, 2, 1, 2, 1, 2, 1, 1},
                    {1, 1, 2, 1, 2, 1, 2, 1, 2, 1, 1},
                    {1, 1, 2, 1, 2, 1, 2, 1, 2, 1, 1},
                    {2, 6, 2, 1, 2, 1, 2, 1, 2, 6, 2},
                    {1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1},
                    {2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2},
                    {1, 1, 5, 1, 1, 1, 1, 1, 5, 1, 1},
                    {2, 2, 2, 2, 2, 6, 2, 2, 2, 2, 2},
                    {8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9},
            },
            {   // 第 13 层
                    {1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1},
                    {1, 2, 2, 2, 2, 2, 5, 2, 1, 2, 1},
                    {1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1},
                    {1, 2, 7, 2, 2, 2, 1, 2, 1, 2, 1},
                    {1, 2, 1, 1, 1, 2, 1, 2, 1, 2, 1},
                    {1, 2, 1, 1, 10, 2, 1, 2, 1, 2, 1},
                    {1, 2, 1, 10, 1, 2, 1, 2, 1, 2, 1},
                    {1, 2, 2, 2, 2, 2, 1, 2, 1, 2, 1},
                    {1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1},
                    {2, 2, 1, 2, 1, 2, 2, 2, 2, 2, 1},
                    {9, 1, 1, 6, 1, 8, 2, 1, 1, 5, 1},
            },
            {   // 第 14 层
                    {2, 1, 1, 1, 8, 1, 1, 1, 1, 1, 2},
                    {2, 1, 1, 2, 2, 2, 2, 2, 1, 1, 2},
                    {2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2},
                    {2, 1, 2, 2, 2, 1, 2, 2, 2, 1, 2},
                    {2, 1, 2, 2, 2, 10, 2, 2, 2, 1, 2},
                    {2, 1, 1, 2, 2, 1, 2, 2, 1, 1, 2},
                    {2, 1, 4, 4, 2, 1, 2, 4, 4, 1, 2},
                    {2, 1, 4, 4, 2, 1, 2, 4, 4, 1, 2},
                    {2, 1, 4, 4, 2, 6, 2, 4, 4, 1, 2},
                    {2, 1, 1, 1, 6, 1, 6, 1, 1, 1, 2},
                    {2, 2, 2, 2, 2, 9, 2, 2, 2, 2, 2},
            },
            {   // 第 15 层
                    {1, 1, 1, 1, 9, 4, 8, 1, 1, 1, 1},
                    {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
                    {1, 4, 4, 2, 2, 2, 2, 2, 4, 4, 1},
                    {1, 4, 2, 2, 1, 2, 1, 2, 2, 4, 1},
                    {1, 4, 2, 2, 1, 2, 1, 2, 2, 4, 1},
                    {1, 4, 2, 2, 1, 2, 1, 2, 2, 4, 1},
                    {1, 4, 4, 2, 1, 2, 1, 2, 4, 4, 1},
                    {1, 4, 4, 2, 5, 2, 5, 2, 4, 4, 1},
                    {1, 4, 4, 4, 1, 1, 1, 4, 4, 4, 1},
                    {1, 4, 4, 4, 4, 7, 4, 4, 4, 4, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            },
            {   // 第 16 层
                    {4, 4, 4, 4, 4, 1, 9, 4, 4, 4, 4},
                    {4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4},
                    {4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4},
                    {4, 4, 4, 4, 2, 7, 2, 4, 4, 4, 4},
                    {4, 4, 4, 2, 2, 1, 2, 2, 4, 4, 4},
                    {4, 4, 4, 2, 2, 1, 2, 2, 4, 4, 4},
                    {4, 4, 4, 2, 2, 1, 2, 2, 4, 4, 4},
                    {4, 4, 4, 2, 2, 1, 2, 2, 4, 4, 4},
                    {4, 4, 4, 2, 2, 8, 2, 2, 4, 4, 4},
                    {4, 4, 4, 4, 2, 2, 2, 4, 4, 4, 4},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            },
            {   // 第 17 层
                    {4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 1},
                    {4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1},
                    {4, 1, 4, 1, 4, 4, 4, 4, 4, 4, 4},
                    {4, 1, 4, 1, 4, 1, 1, 1, 1, 1, 4},
                    {4, 1, 4, 1, 1, 1, 4, 4, 4, 1, 4},
                    {4, 1, 4, 4, 4, 4, 4, 1, 1, 1, 4},
                    {4, 1, 4, 4, 4, 9, 4, 1, 4, 4, 4},
                    {4, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
                    {8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            },
            {   // 第 18 层
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 4, 4, 4, 2, 2, 2, 4, 4, 4, 4},
                    {4, 4, 4, 2, 2, 1, 2, 2, 4, 4, 4},
                    {4, 4, 4, 2, 2, 10, 2, 2, 4, 4, 4},
                    {4, 4, 4, 2, 2, 7, 2, 2, 4, 4, 4},
                    {4, 4, 4, 4, 2, 7, 2, 4, 4, 4, 4},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                    {9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            },
            {   // 第 19 层
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 4, 1, 4, 4, 4, 4, 4, 1, 4, 1},
                    {1, 4, 1, 4, 4, 4, 4, 4, 1, 4, 1},
                    {1, 4, 1, 4, 4, 8, 4, 4, 1, 4, 1},
                    {1, 4, 1, 4, 4, 1, 4, 4, 1, 4, 1},
                    {1, 4, 1, 4, 4, 1, 4, 4, 1, 4, 1},
                    {1, 4, 10, 4, 4, 1, 4, 4, 10, 4, 1},
                    {1, 4, 1, 4, 4, 1, 4, 4, 1, 4, 1},
                    {1, 4, 4, 4, 4, 1, 4, 4, 4, 4, 1},
                    {1, 4, 4, 4, 4, 1, 4, 4, 4, 4, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9},
            },
            {   // 第 20 层
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 4, 1, 4, 1, 4, 1, 4, 1, 4, 1},
                    {4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4},
                    {1, 4, 1, 4, 1, 9, 1, 4, 1, 4, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 4, 1, 4, 1, 4, 1, 4, 1, 4, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 4, 1, 4, 1, 8, 1, 4, 1, 4, 1},
                    {4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4},
                    {1, 4, 1, 4, 1, 4, 1, 4, 1, 4, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            },
            {   // 第 21 层
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 4, 1, 1, 4, 1, 4, 1, 1, 4, 4},
                    {4, 1, 1, 4, 4, 1, 4, 4, 1, 1, 4},
                    {4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 4},
                    {4, 4, 1, 1, 1, 1, 1, 1, 1, 4, 4},
                    {4, 4, 1, 1, 1, 1, 1, 1, 1, 4, 4},
                    {4, 4, 4, 1, 1, 4, 1, 1, 4, 4, 4},
                    {4, 4, 4, 4, 11, 9, 11, 4, 4, 4, 4},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            },
    };
    public static BaseBean[][][] baseBeanMap = new BaseBean[22][11][11];

    static int[][] initPos = {
            {5, 9},
            {5, 9},
            {0, 1},
            {1, 10},
            {10, 9},
            {1, 10},
            {9, 9},
            {5, 10},
            {0, 1},
            {6, 3},
            {4, 6},
            {1, 10},
            {9, 10},
            {1, 10},
            {5, 9},
            {3, 0},
            {5, 0},
            {5, 8},
            {1, 10},
            {9, 10},
            {5, 4},
            {5, 5},
    };
    static int[][] finPos = {
            {5, 1},
            {1, 0},
            {0, 9},
            {10, 9},
            {0, 9},
            {9, 9},
            {5, 10},
            {1, 0},
            {7, 4},
            {6, 7},
            {0, 9},
            {9, 10},
            {1, 10},
            {4, 10},
            {5, 0},
            {7, 0},
            {5, 7},
            {1, 10},
            {9, 10},
            {5, 4},
            {5, 8},
            {},
    };

    public MTGame() throws IOException {
        setLayout(null);

        // 初始化 时间事件
        time = new JLabel();
        time.setBounds(80, 800, 250, 100);
        time.setForeground(Color.WHITE);
        time.setFont(new Font("Serif", 0, 25));

        // 初始化 对话事件
        conversation.setLayout(null);
        dialogueBackground.setIcon(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir") + "/res/dialogueBackground.png"))));
        conversation.add(dialogueBackground, 1, 0);
        dialogueBackground.setBorder(BorderFactory.createLineBorder(new Color(204, 102, 0), 8, true));
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Serif", 0, 30));
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setOpaque(false);
        text.setEditable(false);
        text.setFocusable(false);

        // 初始化 预测面板
        forecastFrame.setLayout(null);
        forecastFrame.setBounds(6 * GAME_PIX, GAME_PIX, GAME_PIX * 11, GAME_PIX * 11);
        forecastFrame.setBackground(Color.BLACK);
        forecastFrame.setOpaque(true);
        forecastFrame.setVisible(false);

        // 初始化 跳跃信息面板
        jumpFrame.setLayout(null);
        jumpFrame.setBounds(7 * GAME_PIX, 2 * GAME_PIX, GAME_PIX * 9, GAME_PIX * 9);
        jumpFrame.setBorder(BorderFactory.createLineBorder(new Color(204, 102, 0), 8, true));
        jumpFrame.setBackground(Color.BLACK);
        jumpFrame.setOpaque(true);
        jumpFrame.setVisible(false);

        // 初始化 战斗信息面板
        battleFrame.setLayout(null);
        battleFrame.setBounds(27, GAME_PIX * 2, 1242, 541);
        JLabel battleBackground = new JLabel(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir") + "/res/BattleBg.png"))));
        battleBackground.setBounds(0, 0, 1242, 541);
        battleFrame.add(battleBackground, 1, 0);
        battleFrame.setOpaque(true);
        battleFrame.setVisible(false);

        // 初始化 信息面板
        msgPane.setLayout(null);
        msgPane.setBounds(10, 270, GAME_PIX * 18 - 20, 150);
        msg.setBounds(0, 0, GAME_PIX * 18 - 20, 150);
        msg.setForeground(Color.WHITE);
        msg.setFont(new Font("Serif", 0, 50));
        msg.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel msgBackground = new JLabel(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir") + "/res/dialogueBackground.png"))));
        msgBackground.setLayout(null);
        msgBackground.setBounds(0, 0, GAME_PIX * 18 - 20, 150);
        msgPane.add(msgBackground, 1, 0);
        msgPane.add(msg, 2, 0);
        msgPane.setOpaque(true);
        msgPane.setVisible(false);

        // 初始化 地图
        background = ImageIO.read(new File(System.getProperty("user.dir") + "/res/bg.png"));
        mapImgSheet = ImageIO.read(new File(System.getProperty("user.dir") + "/res/property.png"));
        enemyImgSheet = ImageIO.read(new File(System.getProperty("user.dir") + "/res/enemyProperty.png"));
        itemImgSheet = ImageIO.read(new File(System.getProperty("user.dir") + "/res/itemProperty.png"));
        dialogueImgSheet = ImageIO.read(new File(System.getProperty("user.dir") + "/res/dialogueProperty.png"));

        // 读取怪物属性数据
        BufferedReader br = new BufferedReader(new FileReader("data/enemy.dat"));
        for (int x = 0; x < enemyDict.length; x++) {
            enemyDict[x][0] = br.readLine();
            for (int y = 1; y < 6; y++) {
                enemyDict[x][y] = Integer.parseInt(br.readLine());
            }
        }
        br.close();

        // 读取怪物位置数据
        BufferedReader br1 = new BufferedReader(new FileReader("data/enemyMap.dat"));
        String line;
        while ((line = br1.readLine()) != null) {
            String[] temp = line.split(" ");
            MTGame.baseBeanMap[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])][Integer.parseInt(temp[2])] = new MonsterBean(Integer.parseInt(temp[3]));
        }
        br1.close();

        // 读取道具位置数据
        BufferedReader br2 = new BufferedReader(new FileReader("data/itemMap.dat"));
        while ((line = br2.readLine()) != null) {
            String[] temp = line.split(" ");
            MTGame.baseBeanMap[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])][Integer.parseInt(temp[2])] = new ItemsBean(Integer.parseInt(temp[3]));
        }
        br2.close();

        // 读取 NPC 位置数据
        BufferedReader br3 = new BufferedReader(new FileReader("data/dialogueMap.dat"));
        while ((line = br3.readLine()) != null) {
            String[] temp = line.split(" ");
            MTGame.baseBeanMap[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])][Integer.parseInt(temp[2])] = new DialoguesBean(Integer.parseInt(temp[3]));
        }
        br3.close();

        // 初始化 动画
        new Timer(500, new ActionListener() {
            boolean change = true;
            BufferedImage one = ImageIO.read(new File(System.getProperty("user.dir") + "/res/property.png"));
            BufferedImage two = ImageIO.read(new File(System.getProperty("user.dir") + "/res/property2.png"));
            BufferedImage three = ImageIO.read(new File(System.getProperty("user.dir") + "/res/enemyProperty.png"));
            BufferedImage four = ImageIO.read(new File(System.getProperty("user.dir") + "/res/enemyProperty2.png"));
            BufferedImage five = ImageIO.read(new File(System.getProperty("user.dir") + "/res/dialogueProperty.png"));
            BufferedImage six = ImageIO.read(new File(System.getProperty("user.dir") + "/res/dialogueProperty2.png"));

            @Override
            public void actionPerformed(ActionEvent e) {
                MTGame.gameSec += 0.5;
                if (gameSec == 60) {
                    gameSec = 0;
                    gameMin++;
                }
                MTGame.time.setText(" 游戏时间：" + gameMin + " 分 " + (int) gameSec + " 秒");
                if (change) {
                    change = false;
                    mapImgSheet = one;
                    enemyImgSheet = three;
                    dialogueImgSheet = five;
                } else {
                    change = true;
                    mapImgSheet = two;
                    enemyImgSheet = four;
                    dialogueImgSheet = six;
                }
                repaint();
            }
        }).start();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(background, 0, 0, null);
        for (int x = 0; x < 11; x++)
            for (int y = 0; y < 11; y++)
                g2.drawImage(mapImgSheet.getSubimage(GAME_PIX * LvMap[currentFloor][x][y], 0, GAME_PIX, GAME_PIX), GAME_PIX * y + GAME_PIX * 6, GAME_PIX * x + GAME_PIX, null);

        try {
            g2.drawImage(playerBean_1.draw(), (playerBean_1.getPosX() + 6) * GAME_PIX, (playerBean_1.getPosY() + 1) * GAME_PIX, null);
            for (int x = 0; x < baseBeanMap[currentFloor].length; x++) {
                for (int y = 0; y < baseBeanMap[currentFloor].length; y++) {
                    if (baseBeanMap[currentFloor][x][y] != null)
                        g2.drawImage(baseBeanMap[currentFloor][x][y].draw(), (x + 6) * GAME_PIX, (y + 1) * GAME_PIX, null);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 绘制左侧面板的玩家数据
        g2.setFont(new Font("Arial", 0, 30));
        g2.setColor(Color.WHITE);
        g2.drawString(playerBean_1.getLevel() + "", 230, 150);
        g2.drawString(playerBean_1.getHp() + "", 220, 215);
        g2.drawString(playerBean_1.getAttack() + "", 220, 270);
        g2.drawString(playerBean_1.getDefend() + "", 220, 325);
        g2.drawString(playerBean_1.getMoney() + "", 220, 385);
        g2.drawString(playerBean_1.getExp() + "", 220, 440);

        g2.drawString(playerBean_1.getYkey() + "", 240, 530);
        g2.drawString(playerBean_1.getBkey() + "", 240, 605);
        g2.drawString(playerBean_1.getRkey() + "", 240, 680);
        g2.drawString(currentFloor + "", 200, 750);
    }


    // 交互函数
    public static void interaction(int x, int y) {
        // 如果打不赢怪物
        if (baseBeanMap[currentFloor][x][y] instanceof MonsterBean
                && (playerBean_1.forecast((MonsterBean) baseBeanMap[currentFloor][x][y]).equals("???")
                || Integer.parseInt(playerBean_1.forecast((MonsterBean) baseBeanMap[currentFloor][x][y])) >= playerBean_1.getHp()))
            return;
        else if (baseBeanMap[currentFloor][x][y] instanceof MonsterBean) {
            playerBean_1.battle((MonsterBean) baseBeanMap[currentFloor][x][y]);
            //System.out.println((enemyDict[baseBeanMap[currentFloor][x][y].id][0]).toString());
            MTGame.baseBeanMap[currentFloor][x][y] = null;
        } else if (baseBeanMap[currentFloor][x][y] instanceof ItemsBean) {
            ((ItemsBean) baseBeanMap[currentFloor][x][y]).use();
            baseBeanMap[currentFloor][x][y] = null;
        } else if (baseBeanMap[currentFloor][x][y] instanceof DialoguesBean) {
            int d = ((DialoguesBean) baseBeanMap[currentFloor][x][y]).getId();
            inConversation = true;
            try {
                ((DialoguesBean) baseBeanMap[currentFloor][x][y]).perform();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            switch (LvMap[currentFloor][y][x]) {
                case 1:
                    playerBean_1.move(x, y);
                    break;
                case 5: // 开黄门
                    if (playerBean_1.getYkey() > 0) {
                        LvMap[currentFloor][y][x] = 1;
                        playerBean_1.setYkey(playerBean_1.getYkey() - 1);
//                        playerBean_1.Ykey--;
                        playerBean_1.move(x, y);
                    }
                    break;
                case 6: // 开蓝门
                    if (playerBean_1.getBkey() > 0) {
                        LvMap[currentFloor][y][x] = 1;
                        playerBean_1.setBkey(playerBean_1.getBkey() - 1);
//                        playerBean_1.Bkey--;
                        playerBean_1.move(x, y);
                    }
                    break;
                case 7: // 开红门
                    if (playerBean_1.getRkey() > 0) {
                        LvMap[currentFloor][y][x] = 1;
                        playerBean_1.setRkey(playerBean_1.getRkey() - 1);
//                        playerBean_1.Rkey--;
                        playerBean_1.move(x, y);
                    }
                    break;
                case 8:
                    currentFloor++;
                    maxFloor = Math.max(maxFloor, currentFloor);
                    playerBean_1.move(initPos[currentFloor][0], initPos[currentFloor][1]);
                    break;
                case 9:
                    currentFloor--;
                    playerBean_1.move(finPos[currentFloor][0], finPos[currentFloor][1]);
                    break;
                case 10:
                    LvMap[currentFloor][y][x] = 1;
                    break;
            }
    }

    // 查看怪物功能
    public static void displayForecast() {
        inConversation = true;
        forecastFrame.setVisible(true);
        int cnt = 0;
        ArrayList<Integer> idList = new ArrayList<Integer>();
        for (int x = 0; x < baseBeanMap[currentFloor].length; x++)
            for (int y = 0; y < baseBeanMap[currentFloor][x].length; y++) {
                if (baseBeanMap[currentFloor][x][y] instanceof MonsterBean && !idList.contains(baseBeanMap[currentFloor][x][y].getId())) {
                    idList.add(baseBeanMap[currentFloor][x][y].getId());
                    JLabel temp = new JLabel("名称：" + (enemyDict[baseBeanMap[currentFloor][x][y].getId()][0]).toString()
                            + "  生命：" + ((MonsterBean) baseBeanMap[currentFloor][x][y]).getHp()
                            + "  攻击：" + ((MonsterBean) baseBeanMap[currentFloor][x][y]).getAttack()
                            + "  防御：" + ((MonsterBean) baseBeanMap[currentFloor][x][y]).getDefend()
                            + "  金币：" + ((MonsterBean) baseBeanMap[currentFloor][x][y]).getMoney()
                            + "  经验：" + ((MonsterBean) baseBeanMap[currentFloor][x][y]).getExp()
                            + "  损失：" + playerBean_1.forecast((MonsterBean) baseBeanMap[currentFloor][x][y]));
                    temp.setBounds(100, 20 + 90 * cnt, 11 * GAME_PIX, 30);
                    temp.setForeground(Color.WHITE);
                    temp.setFont(new Font("Serif", 0, 20));
                    JLabel head = new JLabel();
                    try {
                        head.setIcon(new ImageIcon(baseBeanMap[currentFloor][x][y].draw()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    head.setBounds(20, 20 + 90 * cnt, GAME_PIX, GAME_PIX);
                    cnt++;
                    forecastFrame.add(temp);
                    forecastFrame.add(head);
                }
            }
    }

    static JLabel[] choices = new JLabel[21];

    // 楼层跳跃功能
    public static void displayJump() {
        for (int x = 0; x < Math.min(8, maxFloor); x++) {
            JLabel temp = new JLabel("▷第 " + x + " 层");
            temp.setFont(new Font("Serif", 0, 30));
            temp.setForeground(Color.WHITE);
            temp.setBounds(50, 150 + 50 * x, 200, 50);
            jumpFrame.add(temp);
            choices[x] = temp;
        }
        if (maxFloor >= 8)
            for (int x = 8; x < 16; x++) {
                JLabel temp = new JLabel("▷第 " + x + " 层");
                temp.setFont(new Font("Serif", 0, 30));
                temp.setForeground(Color.WHITE);
                temp.setBounds(250, 150 + 50 * (x - 8), 200, 50);
                jumpFrame.add(temp);
                choices[x] = temp;
            }
        if (maxFloor >= 16)
            for (int x = 16; x < 21; x++) {
                JLabel temp = new JLabel("▷第 " + x + " 层");
                temp.setFont(new Font("Serif", 0, 30));
                temp.setForeground(Color.WHITE);
                temp.setBounds(450, 150 + 50 * (x - 16), 200, 50);
                jumpFrame.add(temp);
                choices[x] = temp;
            }

        jumpFrame.setVisible(true);

        fr.addKeyListener(new KeyListener() {
            int selection = 0;

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (selection != 20 && e.getKeyCode() == e.VK_W && choices[selection + 1] != null) {
                    choices[selection].setText(choices[selection].getText().replaceAll("▶", "▷"));
                    selection = selection + 1;
                    choices[selection].setText(choices[selection].getText().replaceAll("▷", "▶"));
                    fr.repaint();
                }
                if (selection != 0 && e.getKeyCode() == e.VK_S) {
                    choices[selection].setText(choices[selection].getText().replaceAll("▶", "▷"));
                    selection = selection - 1;
                    choices[selection].setText(choices[selection].getText().replaceAll("▷", "▶"));
                    fr.repaint();
                }
                if (e.getKeyCode() == e.VK_SPACE) {
                    choices[selection].setText(choices[selection].getText().replaceAll("▶", "▷"));
                    playerBean_1.move(initPos[selection][0], initPos[selection][1]);
                    currentFloor = selection;
                    fr.repaint();
                    inConversation = false;
                    jumpFrame.removeAll();
                    jumpFrame.setVisible(false);
                    fr.removeKeyListener(this);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    public static void displayMessage(String message) {
        msgPane.setVisible(true);
        inConversation = true;
        Timer animat = new Timer(1000, new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                if (count == 2) {
                    msgPane.setVisible(false);
                    inConversation = false;
                    ((Timer) e.getSource()).stop();
                }
                msg.setText(message);
                fr.repaint();
            }
        });
        animat.setInitialDelay(0);
        animat.start();
    }

    static JLayeredPane conversation = new JLayeredPane();
    static JLabel dialogueBackground = new JLabel();
    static JTextArea text = new JTextArea(20, 20);
    static JLabel imgIco = new JLabel();

    public static void talk(String[] messages, BufferedImage[] characters, int[] w, int[] h) {
        Insets insets = conversation.getInsets();

        imgIco.setIcon(new ImageIcon(characters[0]));
        imgIco.setBounds(20 + insets.left, 20 + insets.top, GAME_PIX, GAME_PIX);
        text.setBounds(100 + insets.left, 20 + insets.top, w[0] - 100, h[0]);
        text.setText(messages[0]);
        dialogueBackground.setBounds(0, 0, w[0], h[0]);
        conversation.setBounds(675, 560, w[0], h[0]);
        conversation.add(imgIco, 2, 0);
        conversation.add(text, 3, 0);
        game.add(conversation);
        game.repaint();

        fr.addKeyListener(new KeyListener() {
            int count = 0;
            int x = 0, y = 0;

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == e.VK_SPACE) {
                    conversation.remove(imgIco);
                    conversation.remove(text);
                    game.remove(conversation);
                    count++;
                    if (count >= messages.length) {
                        inConversation = false;
                        fr.removeKeyListener(this);
                        return;
                    }
                    if (count % 2 == 1) {
                        x = 400;
                        y = 310;
                    } else {
                        x = 675;
                        y = 560;
                    }
                    imgIco.setIcon(new ImageIcon(characters[count]));
                    imgIco.setBounds(20 + insets.left, 20 + insets.top, GAME_PIX, GAME_PIX);
                    text.setBounds(100 + insets.left, 20 + insets.top, w[count] - 100, h[count]);
                    text.setText(messages[count]);
                    dialogueBackground.setBounds(0, 0, w[count], h[count]);
                    conversation.setBounds(x, y, w[count], h[count]);
                    conversation.add(imgIco, 2, 0);
                    conversation.add(text, 3, 0);
                    game.add(conversation);
                    game.repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }


        });
    }

    static String[] choice = new String[4];


    public static void store(int id) {
        switch (id) {
            case 0:     // 对应 3 楼商店选项
                choice = new String[]{"▶增加 800 点生命（25 金币）", "▷增加 4 点攻击（25 金币）", "▷增加 4 点防御（25 金币）", "▷离开商店"};
                imgIco.setIcon(new ImageIcon(dialogueImgSheet.getSubimage(GAME_PIX * 5, 0, GAME_PIX, GAME_PIX)));
                break;
            case 1:
                choice = new String[]{"▶提升一级（需要 100 点）", "▷增加攻击5（需要 30 点） ", "▷增加防御5（需要 30 点）", "▷离开商店"};
                imgIco.setIcon(new ImageIcon(dialogueImgSheet.getSubimage(GAME_PIX * 7, 0, GAME_PIX, GAME_PIX)));
                break;
            case 2:
                choice = new String[]{"▶购买 1 把黄钥匙（$ 10）", "▷购买 1 把蓝钥匙（$ 50）", "▷购买 1 把红钥匙（$ 100）", "▷离开商店"};
                imgIco.setIcon(new ImageIcon(dialogueImgSheet.getSubimage(GAME_PIX * 8, 0, GAME_PIX, GAME_PIX)));
                break;
            case 3:
                choice = new String[]{"▶增加 4000 点生命（100 金币）", "▷增加 20 点攻击（100 金币）", "▷增加 20 点防御（100 金币）", "▷离开商店"};
                imgIco.setIcon(new ImageIcon(dialogueImgSheet.getSubimage(GAME_PIX * 10, 0, GAME_PIX, GAME_PIX)));
                break;
            case 4:     // 第 12 层商店
                choice = new String[]{"▶卖出 1 把黄钥匙（$ 7）", "▷卖出 1 把黄钥匙（$ 35）", "▷卖出 1 把黄钥匙（$ 70）", "▷离开商店"};
                imgIco.setIcon(new ImageIcon(dialogueImgSheet.getSubimage(GAME_PIX * 14, 0, GAME_PIX, GAME_PIX)));
                break;
            case 5:     // 第 13 层老人
                choice = new String[]{"▶提升三级（需要 270 点）", "▷增加攻击 17（需要 95 点）", "▷增加防御 17（需要 95 点）", "▷离开商店"};
                imgIco.setIcon(new ImageIcon(dialogueImgSheet.getSubimage(GAME_PIX * 15, 0, GAME_PIX, GAME_PIX)));
                break;
        }

        Insets insets = conversation.getInsets();
        imgIco.setBounds(20 + insets.left, 20 + insets.top, GAME_PIX, GAME_PIX);
        text.setBounds(100 + insets.left, 20 + insets.top, 460 - 100, 460);
        text.setText("选择一个: \n " + choice[0] + " \n " + choice[1] + " \n " + choice[2] + " \n " + choice[3]);
        dialogueBackground.setBounds(0, 0, 460, 460);
        conversation.setBounds(620, 230, 460, 460);
        conversation.add(imgIco, 2, 0);
        conversation.add(text, 3, 0);
        game.add(conversation);
        game.repaint();

        fr.addKeyListener(new KeyListener() {
            int selection = 0;
            String message = "选择一个: \n " + choice[0] + " \n " + choice[1] + " \n " + choice[2] + " \n " + choice[3];

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (selection != 3 && e.getKeyCode() == e.VK_S) {
                    choice[selection] = choice[selection].replaceAll("▶", "▷");
                    selection = selection + 1;
                    choice[selection] = choice[selection].replaceAll("▷", "▶");
                    message = "选择一个: \n " + choice[0] + " \n " + choice[1] + " \n " + choice[2] + " \n " + choice[3];
                    text.setText(message);
                    fr.repaint();
                }
                if (selection != 0 && e.getKeyCode() == e.VK_W) {
                    choice[selection] = choice[selection].replaceAll("▶", "▷");
                    selection = selection - 1;
                    choice[selection] = choice[selection].replaceAll("▷", "▶");
                    message = "选择一个: \n " + choice[0] + " \n " + choice[1] + " \n " + choice[2] + " \n " + choice[3];
                    text.setText(message);
                    fr.repaint();
                }
                if (e.getKeyCode() == e.VK_SPACE) {
                    switch (id) {
                        case 0:     // 对应 3 楼的商店选项
                            switch (selection) {
                                case 0:
                                    if (playerBean_1.getMoney() >= 25) {
                                        playerBean_1.setMoney(playerBean_1.getMoney() - 25);
                                        playerBean_1.setHp(playerBean_1.getHp() + 800);
//                                        playerBean_1.money -= 25;
//                                        playerBean_1.hp += 800;
                                    }
                                    break;
                                case 1:
                                    if (playerBean_1.getMoney() >= 25) {
                                        playerBean_1.setMoney(playerBean_1.getMoney() - 25);
                                        playerBean_1.setAttack(playerBean_1.getAttack() + 4);
//                                        playerBean_1.money -= 25;
//                                        playerBean_1.attack += 4;
                                    }
                                    break;
                                case 2:
                                    if (playerBean_1.getMoney() >= 25) {
                                        playerBean_1.setMoney(playerBean_1.getMoney() - 25);
                                        playerBean_1.setDefend(playerBean_1.getDefend() + 4);
//                                        playerBean_1.money -= 25;
//                                        playerBean_1.defend += 4;
                                    }
                                    break;
                                case 3:
                                    conversation.remove(imgIco);
                                    conversation.remove(text);
                                    game.remove(conversation);
                                    fr.repaint();
                                    inConversation = false;
                                    fr.removeKeyListener(this);
                                    break;
                            }
                            break;
                        case 1:     // 对应 5 楼的老人选项
                            switch (selection) {
                                case 0:
                                    if (playerBean_1.getExp() >= 100) {
                                        playerBean_1.setLevel(playerBean_1.getLevel() + 1);
                                        playerBean_1.setExp(playerBean_1.getExp() - 100);
                                        playerBean_1.setHp(playerBean_1.getHp() + 1000);
                                        playerBean_1.setAttack(playerBean_1.getAttack() + 7);
                                        playerBean_1.setDefend(playerBean_1.getDefend() + 7);
//                                        playerBean_1.level++;
//                                        playerBean_1.exp -= 100;
//                                        playerBean_1.hp += 1000;
//                                        playerBean_1.attack += 7;
//                                        playerBean_1.defend += 7;
                                    }
                                    break;
                                case 1:
                                    if (playerBean_1.getExp() >= 30) {
                                        playerBean_1.setExp(playerBean_1.getExp() - 30);
                                        playerBean_1.setAttack(playerBean_1.getAttack() + 5);
//                                        playerBean_1.exp -= 30;
//                                        playerBean_1.attack += 5;
                                    }
                                    break;
                                case 2:
                                    if (playerBean_1.getExp() >= 30) {
                                        playerBean_1.setExp(playerBean_1.getExp() - 30);
                                        playerBean_1.setDefend(playerBean_1.getDefend() + 5);
//                                        playerBean_1.exp -= 30;
//                                        playerBean_1.defend += 5;
                                    }
                                    break;
                                case 3:
                                    conversation.remove(imgIco);
                                    conversation.remove(text);
                                    game.remove(conversation);
                                    fr.repaint();
                                    inConversation = false;
                                    fr.removeKeyListener(this);
                                    break;
                            }
                            break;
                        case 2:     // 对应 5 楼的商人选项
                            switch (selection) {
                                case 0:
                                    if (playerBean_1.getMoney() >= 10) {
                                        playerBean_1.setMoney(playerBean_1.getMoney() - 10);
                                        playerBean_1.setYkey(playerBean_1.getYkey() + 1);
//                                        playerBean_1.money -= 10;
//                                        playerBean_1.Ykey++;
                                    }
                                    break;
                                case 1:
                                    if (playerBean_1.getMoney() >= 50) {
                                        playerBean_1.setMoney(playerBean_1.getMoney() - 50);
                                        playerBean_1.setBkey(playerBean_1.getBkey() + 1);
//                                        playerBean_1.money -= 50;
//                                        playerBean_1.Bkey++;
                                    }
                                    break;
                                case 2:
                                    if (playerBean_1.getMoney() >= 100) {
                                        playerBean_1.setMoney(playerBean_1.getMoney() - 100);
                                        playerBean_1.setRkey(playerBean_1.getRkey() + 1);
//                                        playerBean_1.money -= 100;
//                                        playerBean_1.Rkey++;
                                    }
                                    break;
                                case 3:
                                    conversation.remove(imgIco);
                                    conversation.remove(text);
                                    game.remove(conversation);
                                    fr.repaint();
                                    inConversation = false;
                                    fr.removeKeyListener(this);
                                    break;
                            }
                            break;
                        case 3:     // 对应 11 楼的商店选项
                            switch (selection) {
                                case 0:
                                    if (playerBean_1.getMoney() >= 100) {
                                        playerBean_1.setMoney(playerBean_1.getMoney() - 100);
                                        playerBean_1.setHp(playerBean_1.getHp() + 4000);
//                                        playerBean_1.money -= 100;
//                                        playerBean_1.hp += 4000;
                                    }
                                    break;
                                case 1:
                                    if (playerBean_1.getMoney() >= 100) {
                                        playerBean_1.setMoney(playerBean_1.getMoney() - 100);
                                        playerBean_1.setAttack(playerBean_1.getAttack() + 20);
//                                        playerBean_1.money -= 100;
//                                        playerBean_1.attack += 20;
                                    }
                                    break;
                                case 2:
                                    if (playerBean_1.getMoney() >= 100) {
                                        playerBean_1.setMoney(playerBean_1.getMoney() - 100);
                                        playerBean_1.setDefend(playerBean_1.getDefend() + 20);
//                                        playerBean_1.money -= 100;
//                                        playerBean_1.defend += 20;
                                    }
                                    break;
                                case 3:
                                    conversation.remove(imgIco);
                                    conversation.remove(text);
                                    game.remove(conversation);
                                    fr.repaint();
                                    inConversation = false;
                                    fr.removeKeyListener(this);
                                    break;
                            }
                            break;
                        case 4:     // 对应  楼的商人选项
                            switch (selection) {
                                case 0:
                                    if (playerBean_1.getYkey() > 0) {
                                        playerBean_1.setYkey(playerBean_1.getYkey() - 1);
                                        playerBean_1.setMoney(playerBean_1.getMoney() + 7);
//                                        playerBean_1.Ykey--;
//                                        playerBean_1.money += 7;
                                    }
                                    break;
                                case 1:
                                    if (playerBean_1.getBkey() > 0) {
                                        playerBean_1.setBkey(playerBean_1.getBkey() - 1);
                                        playerBean_1.setMoney(playerBean_1.getMoney() + 35);
//                                        playerBean_1.Bkey--;
//                                        playerBean_1.money += 35;
                                    }
                                    break;
                                case 2:
                                    if (playerBean_1.getRkey() > 0) {
                                        playerBean_1.setRkey(playerBean_1.getRkey() - 1);
                                        playerBean_1.setMoney(playerBean_1.getMoney() + 70);
//                                        playerBean_1.Rkey--;
//                                        playerBean_1.money += 70;
                                    }
                                    break;
                                case 3:
                                    conversation.remove(imgIco);
                                    conversation.remove(text);
                                    game.remove(conversation);
                                    fr.repaint();
                                    inConversation = false;
                                    fr.removeKeyListener(this);
                                    break;
                            }
                            break;
                        case 5:
                            switch (selection) {
                                case 0:
                                    if (playerBean_1.getExp() >= 270) {
                                        playerBean_1.setLevel(playerBean_1.getLevel() + 3);
                                        playerBean_1.setExp(playerBean_1.getExp() - 270);
                                        playerBean_1.setHp(playerBean_1.getHp() + 3000);
                                        playerBean_1.setAttack(playerBean_1.getAttack() + 21);
                                        playerBean_1.setDefend(playerBean_1.getDefend() + 21);
//                                        playerBean_1.level += 3;
//                                        playerBean_1.exp -= 270;
//                                        playerBean_1.hp += 3000;
//                                        playerBean_1.attack += 21;
//                                        playerBean_1.defend += 21;
                                    }
                                    break;
                                case 1:
                                    if (playerBean_1.getExp() >= 95) {
                                        playerBean_1.setExp(playerBean_1.getExp() - 95);
                                        playerBean_1.setAttack(playerBean_1.getAttack() + 17);
//                                        playerBean_1.exp -= 95;
//                                        playerBean_1.attack += 17;
                                    }
                                    break;
                                case 2:
                                    if (playerBean_1.getExp() >= 95) {
                                        playerBean_1.setExp(playerBean_1.getExp() - 95);
                                        playerBean_1.setDefend(playerBean_1.getDefend() + 17);
//                                        playerBean_1.exp -= 95;
//                                        playerBean_1.defend += 17;
                                    }
                                    break;
                                case 3:
                                    conversation.remove(imgIco);
                                    conversation.remove(text);
                                    game.remove(conversation);
                                    fr.repaint();
                                    inConversation = false;
                                    fr.removeKeyListener(this);
                                    break;
                            }
                            break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
}

