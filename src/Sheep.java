
public class Sheep extends Mob {

	public Sheep(OEntitySheep entity) {
		super(entity);
	}

	public void setSheared(boolean sheared){
		int i = getEntity().bY.a(16);
		if (sheared){
			getEntity().bY.b(16, Byte.valueOf((byte)(i | 0x10)));
		} else {
			getEntity().bY.b(16, Byte.valueOf((byte)(i & 0xFFFFFFEF)));
		}
	}

	public boolean isSheared(){
		return (getEntity().bY.a(16) & 0x10) != 0;
	}

	public void setWoolColor(int color){
		int i = getEntity().bY.a(16);
		getEntity().bY.b(16, Byte.valueOf((byte)(i & 0xF0 | color & 0xF)));
	}

	public int getColor(){
		return getEntity().bY.a(16) & 0xF;
	}

	public OEntitySheep getEntity() {
		return (OEntitySheep) entity;
	}
}
