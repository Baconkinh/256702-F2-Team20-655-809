package com.game;

import java.util.List;

public class Skill {
    public String Name;    // ชื่อสกิล
    public int mana;       // ค่า Mana ที่ใช้
    public double Dmg;     // ดาเมจของสกิล
    public List<SpecialEffect> Sp;  // ผลกระทบพิเศษของสกิล

    @Override
    public String toString() {
        return "Skill{Name='" + Name + "', mana=" + mana + ", Dmg=" + Dmg + ", Sp=" + Sp + "}";
    }
}