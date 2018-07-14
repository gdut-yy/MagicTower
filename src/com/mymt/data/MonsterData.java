package com.mymt.data;

import com.mymt.bean.MonsterBean;

import java.util.HashMap;


/**
 * MonsterData 类
 * <p>
 * 用于存放魔塔 33 种怪物的属性数据。
 * <p>
 * KEY 值的具体对应图形 请详见
 * /res/map0
 * /res/map1
 *
 * @author ZYY
 * @since 2018-7-12
 */
public class MonsterData {

    public static HashMap<Integer, MonsterBean> monsterMap = new HashMap<>();

    static {
        monsterMap.clear();
        monsterMap.put(40, new MonsterBean(0, 50, 20, 1, 1, 1, "绿头怪"));
        monsterMap.put(41, new MonsterBean(1, 70, 15, 2, 2, 2, "红头怪"));
        monsterMap.put(42, new MonsterBean(2, 100, 20, 5, 3, 3, "小蝙蝠"));
        monsterMap.put(43, new MonsterBean(3, 200, 35, 10, 5, 5, "青头怪"));
        monsterMap.put(44, new MonsterBean(4, 110, 25, 5, 5, 4, "骷髅人"));
        monsterMap.put(45, new MonsterBean(5, 150, 40, 20, 8, 6, "骷髅士兵"));
        monsterMap.put(46, new MonsterBean(6, 300, 75, 45, 13, 10, "兽面人"));
        monsterMap.put(47, new MonsterBean(7, 450, 150, 90, 22, 19, "初级卫兵"));
        monsterMap.put(48, new MonsterBean(8, 150, 65, 30, 10, 8, "大蝙蝠"));
        monsterMap.put(49, new MonsterBean(9, 550, 160, 90, 25, 20, "红蝙蝠"));
        monsterMap.put(50, new MonsterBean(10, 1300, 300, 150, 40, 35, "白衣武士"));
        monsterMap.put(51, new MonsterBean(11, 700, 250, 125, 32, 30, "怪王"));
        monsterMap.put(52, new MonsterBean(12, 500, 400, 260, 47, 45, "红衣法师"));
        monsterMap.put(53, new MonsterBean(13, 15000, 1000, 1000, 100, 100, "红衣魔王"));
        monsterMap.put(54, new MonsterBean(14, 850, 350, 200, 45, 40, "金甲卫士"));
        monsterMap.put(55, new MonsterBean(15, 900, 750, 650, 77, 70, "金甲队长"));
        monsterMap.put(56, new MonsterBean(16, 400, 90, 50, 15, 12, "骷髅队长"));
        monsterMap.put(57, new MonsterBean(17, 1500, 830, 730, 80, 70, "灵法师"));
        monsterMap.put(58, new MonsterBean(18, 1200, 980, 900, 88, 75, "灵武士"));
        monsterMap.put(59, new MonsterBean(19, 30000, 1700, 1500, 250, 220, "冥灵魔王"));
        monsterMap.put(60, new MonsterBean(20, 250, 120, 70, 20, 17, "麻衣法师"));
        monsterMap.put(61, new MonsterBean(21, 2000, 680, 590, 70, 65, "冥战士"));
        monsterMap.put(62, new MonsterBean(22, 2500, 900, 850, 84, 75, "冥队长"));
        monsterMap.put(63, new MonsterBean(23, 125, 50, 25, 10, 7, "初级法师"));
        monsterMap.put(64, new MonsterBean(24, 100, 200, 110, 30, 25, "高级法师"));
        monsterMap.put(65, new MonsterBean(25, 500, 115, 65, 15, 15, "石头怪人"));
        monsterMap.put(66, new MonsterBean(26, 900, 450, 330, 50, 50, "兽面战士"));
        monsterMap.put(67, new MonsterBean(27, 1200, 620, 520, 65, 75, "双手剑士"));
        monsterMap.put(68, new MonsterBean(28, 1250, 500, 400, 55, 55, "冥卫兵"));
        monsterMap.put(69, new MonsterBean(29, 1500, 560, 460, 60, 60, "高级卫兵"));
        monsterMap.put(70, new MonsterBean(30, 3100, 1150, 1050, 92, 80, "影子战士"));
        monsterMap.put(188, new MonsterBean(31, 99999, 5000, 4000, 0, 0, "血影"));
        monsterMap.put(198, new MonsterBean(32, 99999, 9999, 5000, 0, 0, "魔龙"));
    }
}
