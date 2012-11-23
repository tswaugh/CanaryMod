/**
 * Interface for a projectile
 * 
 * @author gregthegeek
 *
 */
public class Projectile extends BaseEntity {
	
	/**
	 * Wraps a projectile
	 * 
	 * @param base the projectile to wrap
	 */
	public Projectile(OEntity base) {
		super(base);
	}
	
	/**
	 * Launch/shoot this projectile
	 */
	public void launch() {
		getWorld().launchProjectile(this);
	}
	
	/**
	 * Returns the shooter of this projectile. null if none.
	 * 
	 * @return
	 */
	public BaseEntity getShooter() {
		OEntity me = getEntity();
		if(me instanceof OEntityThrowable) {
			return ((OEntityThrowable) me).h() == null ? null : ((OEntityThrowable) me).h().getEntity();
		} else if(me instanceof OEntityArrow) {
			return ((OEntityArrow) me).c == null ? null : ((OEntityArrow) me).c.getEntity();
		} else if(me instanceof OEntityFireball) {
			return ((OEntityFireball) me).a == null ? null : ((OEntityFireball) me).a.getEntity();
		}
		return null;
	}
	
	/**
	 * Sets the shooter of this projectile
	 * 
	 * @return whether or not the operation was successful
	 */
	public boolean setShooter(LivingEntity shooter) {
		OEntity me = getEntity();
		if(me instanceof OEntityThrowable) {
			((OEntityThrowable) me).setShooter(shooter.getEntity());
			return true;
		} else if(me instanceof OEntityArrow) {
			((OEntityArrow) me).c = shooter.getEntity();
			return true;
		} else if(me instanceof OEntityFireball) {
			((OEntityFireball) me).a = shooter.getEntity();
			return true;
		}
		return false;
	}
}
