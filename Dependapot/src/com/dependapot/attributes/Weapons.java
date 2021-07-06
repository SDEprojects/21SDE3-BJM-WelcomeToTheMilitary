package com.dependapot.attributes;

public class Weapons extends Item{

    Attributes weaponAttr;
    public int level;

//    Constructor should add magic, agility
    public Weapons(String _name,int strength, int vitality, int level){
        super(_name);
        this.weaponAttr = new Attributes(strength, vitality);
        this.level = level;
        this.type = "Weapon";
    }

//    public Weapons(int level){
//        super(name);
//        this.weaponAttr = new Attributes(strength, magic, agility, vitality);
//        this.level = level;
//        this.type = "Weapon";
//    }
//    public Weapon(int level){
//        super(GameRandom.weaponName(level));
//        this.weaponAttr = GameRandom.weaponAttributes(level);
//        this.level = level;
//        this.type = "Weapon";
//    }

    public static void inventory(){
        System.out.printf("(\"What weapon do you want to add into inventory: \n" +
                "Stess Card: By using this card you can conduct multiple acts while the enemy stays frozen. \n" +
                "Poppy's pocket knife: From carving your name in tress to opening MRE's!\n" +
                "SFAS packet: You don't know what SFAS means but a Specialist told you to put this in your binder. ");
    }

    public Attributes getAt(){
        return weaponAttr;
    }
    public int getLevel(){
        return level;
    }


}
