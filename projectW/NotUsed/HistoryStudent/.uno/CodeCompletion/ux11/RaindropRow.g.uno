[Uno.Compiler.UxGenerated]
public partial class RaindropRow: Fuse.Controls.Grid
{
    internal Raindrop drop1;
    internal Raindrop drop2;
    internal Raindrop drop3;
    static RaindropRow()
    {
    }
    [global::Uno.UX.UXConstructor]
    public RaindropRow()
    {
        InitializeUX();
    }
    void InitializeUX()
    {
        drop1 = new Raindrop();
        drop2 = new Raindrop();
        drop3 = new Raindrop();
        this.ColumnCount = 3;
        drop1.Name = __selector0;
        drop2.Name = __selector1;
        drop3.Name = __selector2;
        this.Children.Add(drop1);
        this.Children.Add(drop2);
        this.Children.Add(drop3);
    }
    static global::Uno.UX.Selector __selector0 = "drop1";
    static global::Uno.UX.Selector __selector1 = "drop2";
    static global::Uno.UX.Selector __selector2 = "drop3";
}
