package bg.softuni.app.models.cores;

public class ParaCore extends AbstractCore implements Core {

    public ParaCore(long durability) {
        super(durability / 3);
    }

//    @Override
//    public void setDurability(long durability) {
//        super.setDurability(durability * 3);
//    }
}