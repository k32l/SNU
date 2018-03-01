[Uno.Compiler.UxGenerated]
public partial class Body: Fuse.Controls.Text
{
    static Body()
    {
    }
    [global::Uno.UX.UXConstructor]
    public Body()
    {
        InitializeUX();
    }
    void InitializeUX()
    {
        this.FontSize = 18f;
        this.TextColor = float4(1f, 1f, 1f, 1f);
        this.Alignment = Fuse.Elements.Alignment.Left;
        this.Font = MainView.NunitoRegular;
    }
}
