import java.util.Random;

public abstract class OEntityMob extends OEntityCreature
  implements OIMob
{
  protected int d = 2;
  protected OEntityLiving ole;
  protected LivingEntity le;
  public OEntityMob(OWorld paramOWorld) {
    super(paramOWorld);
    this.bB = 5;
    ole = (OEntityLiving)this;
    le = new LivingEntity(ole);
  }

  public void f() {
    float f = a(1.0F);
    if (f > 0.5F) {
      this.bU += 2;
    }
    super.f();
  }

  public void b() {
    super.b();
    if ((!this.X.I) && (this.X.v == 0)) J(); 
  }

  protected OEntity k()
  {
    OEntityPlayer localOEntityPlayer = this.X.b(this, 16.0D);
    if ((localOEntityPlayer != null) && (i(localOEntityPlayer))){
              // CanaryMod: calls onMobTarget
        if(!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET,(Player) localOEntityPlayer.entity.getPlayer(), le))
        return localOEntityPlayer;
        else{
            return null;
        }
    }
    return null;
  }

  public boolean a(ODamageSource paramODamageSource, int paramInt) {
    if (super.a(paramODamageSource, paramInt)) {
      OEntity localOEntity = paramODamageSource.a();
      if ((this.V == localOEntity) || (this.W == localOEntity)) return true;

      if (localOEntity != this) {
          if(localOEntity.entity.isPlayer())
              // CanaryMod: calls onMobTarget
          if(!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET,(Player) localOEntity.entity.getPlayer(), le))
        this.a = localOEntity;
          else
              this.a = null;
      }
      return true;
    }
    return false;
  }

  protected boolean f(OEntity paramOEntity)
  {
    int i = this.d;
    if (a(OPotion.g)) {
      i += (3 << b(OPotion.g).c());
    }
    if (a(OPotion.t)) {
      i -= (2 << b(OPotion.t).c());
    }

    return paramOEntity.a(ODamageSource.a(this), i);
  }

  protected void a(OEntity paramOEntity, float paramFloat) {
    if ((this.bx <= 0) && (paramFloat < 2.0F) && (paramOEntity.al.e > this.al.b) && (paramOEntity.al.b < this.al.e)) {
      this.bx = 20;
      f(paramOEntity);
    }
  }

  protected float a(int paramInt1, int paramInt2, int paramInt3) {
    return 0.5F - this.X.k(paramInt1, paramInt2, paramInt3);
  }

  public void a(ONBTTagCompound paramONBTTagCompound) {
    super.a(paramONBTTagCompound);
  }

  public void b(ONBTTagCompound paramONBTTagCompound) {
    super.b(paramONBTTagCompound);
  }

  protected boolean B_() {
    int i = OMathHelper.b(this.ab);
    int j = OMathHelper.b(this.al.b);
    int k = OMathHelper.b(this.ad);
    if (this.X.a(OEnumSkyBlock.a, i, j, k) > this.aH.nextInt(32)) return false;

    int m = this.X.j(i, j, k);

    if (this.X.v()) {
      int n = this.X.k;
      this.X.k = 10;
      m = this.X.j(i, j, k);
      this.X.k = n;
    }
    return m <= this.aH.nextInt(8);
  }

  public boolean v_()
  {
    return (B_()) && (super.v_());
  }
}