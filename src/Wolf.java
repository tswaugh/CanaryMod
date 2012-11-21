/**
 * Wrap around an OEntityWolf to provide extra methods not available to anything but wolves.
 *
 * @author Brian McCarthy
 *
 */
public class Wolf extends TamableEntity{

	/**
	 * Basic wolf constructor.
	 *
	 * @param entity An instance of OEntityWolf to wrap around.
	 */
	public Wolf(OEntityWolf entity){
		super(entity);
	}

	/**
	 * If this wolf is angry.
	 *
	 * @return Boolean of if this wolf is angry.
	 */
	public boolean isAngry(){
		return getEntity().bK();
	}

	/**
	 * Sets if this wolf is angry of not.
	 *
	 * @param angry New angry state of the wolf.
	 */
	public void setAngry(boolean angry){
        getEntity().i(angry);
	}

	public OEntityWolf getEntity() {
		return (OEntityWolf) entity;
	}

}
