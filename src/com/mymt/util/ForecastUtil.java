package com.mymt.util;

import com.mymt.MTGame;
import com.mymt.bean.MonsterBean;
import com.mymt.data.MonsterData;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import static com.mymt.MTGame.*;
import static com.mymt.data.MapData.LvMap;

/**
 * ForecastUtil 工具类
 * <p>
 * 对应物品 [圣光徽] 按 L 键查看怪物的基本情况
 *
 * @author ZYY
 * @since 2018-7-14
 */
public class ForecastUtil {
    public static JLayeredPane forecastFrame = new JLayeredPane();     // 预测信息面板

    static {
        // 初始化 预测面板
        forecastFrame.setLayout(null);
        forecastFrame.setBounds(6 * GAME_PIX, GAME_PIX, GAME_PIX * 11, GAME_PIX * 11);
        forecastFrame.setBackground(Color.BLACK);
        forecastFrame.setOpaque(true);
        forecastFrame.setVisible(false);
    }

    // 查看怪物功能
    public static void displayForecast() {
        inConversation = true;
        forecastFrame.setVisible(true);
        int cnt = 0;
        //ArrayList<Integer> idList = new ArrayList<Integer>();

        HashSet<Integer> forecastSet = new HashSet<>();
        // 遍历当前楼层
        for (int x = 0; x < LvMap[currentFloor].length; x++) {
            for (int y = 0; y < LvMap[currentFloor][x].length; y++) {
                int id = LvMap[currentFloor][x][y];
                // 如果 id 对应 怪物 且 在 forecastSet 中不存在 id 值
                if ((id >= 40 && id <= 70 || id == 129) && !forecastSet.contains(id)) {
                    forecastSet.add(id);
                    //idList.add(baseBeanMap[currentFloor][x][y].getId());
                    JLabel temp = new JLabel(
                            "名称：" + (MonsterData.monsterMap.get(id).getName())
                                    + "  生命：" + (MonsterData.monsterMap.get(id)).getHp()
                                    + "  攻击：" + (MonsterData.monsterMap.get(id)).getAttack()
                                    + "  防御：" + (MonsterData.monsterMap.get(id)).getDefend()
                                    + "  金币：" + (MonsterData.monsterMap.get(id)).getMoney()
                                    + "  经验：" + (MonsterData.monsterMap.get(id)).getExp()
                                    + "  损失：" + forecast(MonsterData.monsterMap.get(id)));

                    temp.setBounds(100, 20 + 80 * cnt, 11 * GAME_PIX, 30);
                    temp.setForeground(Color.WHITE);
                    temp.setFont(new Font("Serif", 0, 20));
                    JLabel head = new JLabel();
                    try {
                        // head.setIcon(new ImageIcon(baseBeanMap[currentFloor][x][y].draw()));
                        BufferedImage a = ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/" + LvMap[currentFloor][x][y] + ".png"));
                        head.setIcon(new ImageIcon(a));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    head.setBounds(10, 20 + 80 * cnt, GAME_PIX, GAME_PIX);
                    cnt++;
                    forecastFrame.add(temp);
                    forecastFrame.add(head);
                }
            }
        }
    }
    // 预测功能
    public static String forecast(MonsterBean e) {
        if (MTGame.playerBean_1.getAttack() <= e.getDefend()) {
            return "???";
        } else if (MTGame.playerBean_1.getDefend() >= e.getAttack()) {
            return 0 + "";
        } else {
            // int one = (e.hp/(attack-e.defend))-1; //how many times MonsterBean will strike before it dies
            return ((e.getHp() / (MTGame.playerBean_1.getAttack() - e.getDefend())) * (e.getAttack() - MTGame.playerBean_1.getDefend())) + "";
        }
    }
}
