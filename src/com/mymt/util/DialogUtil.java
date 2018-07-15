package com.mymt.util;

import com.mymt.data.ImageData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import static com.mymt.MTGame.*;

public class DialogUtil {
    public static JLayeredPane dialogLPane = new JLayeredPane();
    public static JLabel dialogBgImg = new JLabel();
    public static JTextArea text = new JTextArea(20, 20);
    public static JLabel imgIco = new JLabel();

    static {
        // 初始化 对话事件
        dialogLPane.setLayout(null);
        dialogBgImg.setIcon(new ImageIcon(ImageData.blankBgImg));
        dialogLPane.add(dialogBgImg, 1, 0);
        dialogBgImg.setBorder(BorderFactory.createLineBorder(new Color(204, 102, 0), 8, true));
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Serif", 0, 30));
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setOpaque(false);
        text.setEditable(false);
        text.setFocusable(false);
    }

    // 对话事件
    public static void talk(String[] messages, BufferedImage[] characters, int[] w, int[] h) {
        Insets insets = dialogLPane.getInsets();

        imgIco.setIcon(new ImageIcon(characters[0]));
        imgIco.setBounds(20 + insets.left, 20 + insets.top, GAME_PIX_72, GAME_PIX_72);
        text.setBounds(100 + insets.left, 20 + insets.top, w[0] - 100, h[0]);
        text.setText(messages[0]);
        dialogBgImg.setBounds(0, 0, w[0], h[0]);
        dialogLPane.setBounds(675, 560, w[0], h[0]);
        dialogLPane.add(imgIco, 2, 0);
        dialogLPane.add(text, 3, 0);
        gamePanel.add(dialogLPane);
        gamePanel.repaint();

        gameFrame.addKeyListener(new KeyListener() {
            int count = 0;
            int x = 0, y = 0;

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == e.VK_SPACE) {
                    dialogLPane.remove(imgIco);
                    dialogLPane.remove(text);
                    gamePanel.remove(dialogLPane);
                    count++;
                    if (count >= messages.length) {
                        inConversation = false;
                        gameFrame.removeKeyListener(this);
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
                    imgIco.setBounds(20 + insets.left, 20 + insets.top, GAME_PIX_72, GAME_PIX_72);
                    text.setBounds(100 + insets.left, 20 + insets.top, w[count] - 100, h[count]);
                    text.setText(messages[count]);
                    dialogBgImg.setBounds(0, 0, w[count], h[count]);
                    dialogLPane.setBounds(x, y, w[count], h[count]);
                    dialogLPane.add(imgIco, 2, 0);
                    dialogLPane.add(text, 3, 0);
                    gamePanel.add(dialogLPane);
                    gamePanel.repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
}
