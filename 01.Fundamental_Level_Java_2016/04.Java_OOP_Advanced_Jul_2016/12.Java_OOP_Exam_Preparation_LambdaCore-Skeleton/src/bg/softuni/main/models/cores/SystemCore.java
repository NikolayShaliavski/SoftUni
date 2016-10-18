package bg.softuni.main.models.cores;

public class SystemCore extends BaseCore {

    public SystemCore(String name, int durability) {
        super(name);
        this.setMaxDurability(durability);
        this.setDurability(durability);
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
