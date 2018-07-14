package com.mymt.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.mymt.MTGame.GAME_PIX;
import static com.mymt.MTGame.fr;
import static com.mymt.MTGame.inConversation;

/**
 * MsgUtil 工具类
 * <p>
 * 绘制提示信息页面。（ 获得道具、击杀怪物 ）
 *
 * @author ZYY
 * @since 2018-7-14
 */
public class MsgUtil {
    public static JLayeredPane msgPane = new JLayeredPane();           // 提示信息面板
    public static JLabel msgLabel = new JLabel();

    static {
        // 初始化 信息面板
        msgPane.setLayout(null);
        msgPane.setBounds(10, 270, GAME_PIX * 18 - 20, 150);
        msgLabel.setBounds(0, 0, GAME_PIX * 18 - 20, 150);
        msgLabel.setForeground(Color.WHITE);
        msgLabel.setFont(new Font("Serif", 0, 50));
        msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel msgBackground = new JLabel(new ImageIcon(ImageUtil.blankBgImg));
        msgBackground.setLayout(null);
        msgBackground.setBounds(0, 0, GAME_PIX * 18 - 20, 150);
        msgBackground.setBorder(BorderFactory.createLineBorder(new Color(204, 102, 0), 8, true));
        msgPane.add(msgBackground, 1, 0);
        msgPane.add(msgLabel, 2, 0);
        msgPane.setOpaque(true);
        msgPane.setVisible(false);
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
                msgLabel.setText(message);
                fr.repaint();
            }
        });
        animat.setInitialDelay(0);
        animat.start();
    }
}
