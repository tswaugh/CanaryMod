import java.util.Random;

public class OEntitySpider extends OEntityMob
{
    // CanaryMod start
    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    protected LivingEntity entity = new LivingEntity(this);
    // CanaryMod end
  public OEntitySpider(OWorld paramOWorld)
  {
    super(paramOWorld);
    this.ac = "/mob/spider.png";
    b(1.4F, 0.9F);
    this.aY = 0.8F;
  }

  protected void b()
  {
    super.b();
    
    this.bV.a(16, new Byte((byte) 0));
  }

  public void d()
  {
    super.d();
  }

  public void w_()
  {
    super.w_();

    if (!this.bf.I)
    {
      a(this.bv);
    }
  }

  public int c()
  {
    return 16;
  }

  public double q() {
    return this.bE * 0.75D - 0.5D;
  }

  protected boolean g_()
  {
    return false;
  }

  protected OEntity k() {
    float var1 = a(1.0F);
    double var2 = 16.0D;
     OEntityPlayer var3 = this.bf.b(this, var2);
    if (var1 < 0.5F && var3 != null && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, (Player) var3.entity.getPlayer(), entity) ) {
      
      return var3;
    }
    return null;
  }

  protected String c_() {
    return "mob.spider";
  }

  protected String m() {
    return "mob.spider";
  }

  protected String n() {
    return "mob.spiderdeath";
  }

  protected void a(OEntity var1, float var2) {
    float var3 = a(1.0F);
    if ((var3 > 0.5F) && (this.bP.nextInt(100) == 0)) {
      this.d = null;
      return;
    }

    if ((var2 > 2.0F) && (var2 < 6.0F) && (this.bP.nextInt(10) == 0)) {
      if (this.bu) {
        double var4 = var1.bj - this.bj;
        double var5 = var1.bl - this.bl;
        float var6 = OMathHelper.a(var4 * var4 + var5 * var5);
        this.bm = (var4 / var6 * 0.5D * 0.800000011920929D + this.bm * 0.2000000029802322D);
        this.bo = (var5 / var6 * 0.5D * 0.800000011920929D + this.bo * 0.2000000029802322D);
        this.bn = 0.4000000059604645D;
      }
    }
    else super.a(var1, var2);
  }

  public void b(ONBTTagCompound var1)
  {
    super.b(var1);
  }

  public void a(ONBTTagCompound var1) {
    super.a(var1);
  }

  protected int e() {
    return OItem.J.bM;
  }

  protected void a(boolean var1, int var2)
  {
    super.a(var1, var2);

    if ((var1) && ((this.bP.nextInt(3) == 0) || (this.bP.nextInt(1 + var2) > 0)))
      b(OItem.bt.bM, 1);
  }

  public boolean r()
  {
    return o_();
  }

  public void s()
  {
  }

  public OEnumCreatureAttribute t()
  {
    return OEnumCreatureAttribute.c;
  }

  public boolean a(OPotionEffect var1)
  {
    if (var1.a() == OPotion.u.H) {
      return false;
    }
    return super.a(var1);
  }

  public boolean o_() {
    return (this.bV.a(16) & 0x1) != 0;
  }

  public void a(boolean var1) {
    byte var2 = this.bV.a(16);
    if (var1)
      var2 = (byte)(var2 | 0x1);
    else {
      var2 = (byte)(var2 & 0xFFFFFFFE);
    }
    this.bV.b(16, Byte.valueOf(var2));
  }
}