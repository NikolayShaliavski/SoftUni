package bg.softuni.main.models.cores;

public class ParaCore extends BaseCore{

    public ParaCore(String name, int durability) {
        super(name);
        this.setMaxDurability(durability / 3);
        this.setDurability(durability / 3);
    }

    @Override
    protected void setDurability(int durability) {
        super.setDurability(durability);
    }

    @Override
    protected void setMaxDurability(int durability) {
        super.setMaxDurability(durability);
    }
}
