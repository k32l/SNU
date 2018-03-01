[Uno.Compiler.UxGenerated]
public partial class Snowflake: Fuse.Controls.Image
{
    global::Uno.UX.Property<float4> this_Color_inst;
    static Snowflake()
    {
    }
    [global::Uno.UX.UXConstructor]
    public Snowflake()
    {
        InitializeUX();
    }
    void InitializeUX()
    {
        this_Color_inst = new weatherapp_Snowflake_Color_Property(this, __selector0);
        var temp = new Fuse.Reactive.DataBinding<float4>(this_Color_inst, "snowflakeColor");
        this.Width = new Uno.UX.Size(25f, Uno.UX.Unit.Unspecified);
        this.Height = new Uno.UX.Size(25f, Uno.UX.Unit.Unspecified);
        this.Name = __selector1;
        this.File = new global::Uno.UX.BundleFileSource(import global::Uno.IO.BundleFile("../../../Assets/snowflake.png"));
        this.Bindings.Add(temp);
    }
    static global::Uno.UX.Selector __selector0 = "Color";
    static global::Uno.UX.Selector __selector1 = "sf";
}
