package com.game;

public class SpecialEffect {
    public double Dmg;  // ดาเมจที่เพิ่มขึ้น (อาจเป็น 0 ถ้าไม่มีดาเมจ)
    public int mana;    // ค่า Mana ที่ใช้/เพิ่ม
    public int Hp;      // ค่า HP ที่ใช้/เพิ่ม
    public boolean turn; // true = เทิร์นปกติ, false = ไม่ข้ามเทิร์น

    @Override
    public String toString() {
        return "Effect{Dmg=" + Dmg + ", mana=" + mana + ", Hp=" + Hp + ", turn=" + turn + "}";
    }
}