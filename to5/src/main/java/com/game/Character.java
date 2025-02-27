package com.game;

import java.util.List;

public class Character {
    public String Name;     // ชื่อตัวละคร
    public String Class;    // อาชีพ
    public int Hp;          // ค่า HP
    public String img_part; // รูปตัวละคร
    public List<Skill> Skill1;  // สกิลที่ 1
    public List<Skill> Skill2;  // สกิลที่ 2
    public List<Skill> Skill3;  // สกิลที่ 3

    @Override
    public String toString() {
        return "Character{Name='" + Name + "', Class='" + Class + "', Hp=" + Hp + ", img_part='" + img_part + "', Skill1=" + Skill1 + ", Skill2=" + Skill2 + ", Skill3=" + Skill3 + "}";
    }
}