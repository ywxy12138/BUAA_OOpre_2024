public interface Guard {
    boolean fight(Adventurer adventurer);//adventure同怪物战斗

    String getName();//返回怪物类型
}