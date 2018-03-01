[Uno.Compiler.UxGenerated]
public partial class Raindrop: Fuse.Controls.Panel
{
    global::Uno.UX.Property<float4> temp_Color_inst;
    static Raindrop()
    {
    }
    [global::Uno.UX.UXConstructor]
    public Raindrop()
    {
        InitializeUX();
    }
    void InitializeUX()
    {
        var temp = new Fuse.Controls.Image();
        temp_Color_inst = new weatherapp_FuseControlsImage_Color_Property(temp, __selector0);
        var temp1 = new Fuse.Rotation();
        var temp2 = new Fuse.Reactive.DataBinding<float4>(temp_Color_inst, "dropletcolor");
        temp.Width = new Uno.UX.Size(15f, Uno.UX.Unit.Unspecified);
        temp.Height = new Uno.UX.Size(15f, Uno.UX.Unit.Unspecified);
        temp.File = new global::Uno.UX.BundleFileSource(import global::Uno.IO.BundleFile("../../../Assets/raindrops.png"));
        temp.Children.Add(temp1);
        temp.Bindings.Add(temp2);
        temp1.Degrees = 20.6f;
        this.Children.Add(temp);
    }
    static global::Uno.UX.Selector __selector0 = "Color";
}
