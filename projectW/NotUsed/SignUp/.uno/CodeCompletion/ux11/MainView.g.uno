[Uno.Compiler.UxGenerated]
public partial class MainView: Fuse.App
{
    global::Uno.UX.Property<bool> changeWidth_Value_inst;
    global::Uno.UX.Property<bool> loadCircle_Value_inst;
    global::Uno.UX.Property<Uno.UX.Size> rectNormalScale_Width_inst;
    global::Uno.UX.Property<float> text_Opacity_inst;
    global::Uno.UX.Property<float> loadingCircle_Opacity_inst;
    global::Uno.UX.Property<float> loadingCircle_LengthAngleDegrees_inst;
    [global::Uno.UX.UXGlobalResource("Green")] public static readonly Uno.Float4 Green;
    [global::Uno.UX.UXGlobalResource("Purple")] public static readonly Uno.Float4 Purple;
    [global::Uno.UX.UXGlobalResource("White")] public static readonly Uno.Float4 White;
    [global::Uno.UX.UXGlobalResource("Gray")] public static readonly Uno.Float4 Gray;
    [global::Uno.UX.UXGlobalResource("TopGray")] public static readonly Uno.Float4 TopGray;
    [global::Uno.UX.UXGlobalResource("BottomGray")] public static readonly Uno.Float4 BottomGray;
    [global::Uno.UX.UXGlobalResource("GrayBlue")] public static readonly Uno.Float4 GrayBlue;
    [global::Uno.UX.UXGlobalResource("SkyBlue")] public static readonly Uno.Float4 SkyBlue;
    [global::Uno.UX.UXGlobalResource("Pink")] public static readonly Uno.Float4 Pink;
    [global::Uno.UX.UXGlobalResource("DarkGray")] public static readonly Uno.Float4 DarkGray;
    internal Fuse.Controls.Text text;
    internal Fuse.Controls.Panel loadingCirclePanel;
    internal Fuse.Controls.Circle loadingCircle;
    internal Fuse.Controls.Rectangle rectNormalScale;
    internal Fuse.Controls.Panel loadingButton;
    internal Fuse.Scaling loginButtonScaling;
    internal Fuse.Triggers.WhileTrue loading;
    internal Fuse.Triggers.WhileTrue changeWidth;
    internal Fuse.Triggers.WhileTrue loadCircle;
    static MainView()
    {
        Green = float4(0.07843138f, 1f, 0.7098039f, 1f);
        Purple = float4(0.3686275f, 0.1803922f, 0.5686275f, 1f);
        White = float4(1f, 1f, 1f, 1f);
        Gray = float4(0.6f, 0.6f, 0.6f, 1f);
        TopGray = float4(0.6235294f, 0.5137255f, 0.7411765f, 1f);
        BottomGray = float4(0.4941176f, 0.345098f, 0.654902f, 1f);
        GrayBlue = float4(0.3254902f, 0.4313726f, 0.5137255f, 1f);
        SkyBlue = float4(0.627451f, 0.8666667f, 0.8117647f, 1f);
        Pink = float4(0.9843137f, 0.5176471f, 0.4470588f, 1f);
        DarkGray = float4(0.3529412f, 0.3529412f, 0.3529412f, 1f);
        global::Uno.UX.Resource.SetGlobalKey(Green, "Green");
        global::Uno.UX.Resource.SetGlobalKey(Purple, "Purple");
        global::Uno.UX.Resource.SetGlobalKey(White, "White");
        global::Uno.UX.Resource.SetGlobalKey(Gray, "Gray");
        global::Uno.UX.Resource.SetGlobalKey(TopGray, "TopGray");
        global::Uno.UX.Resource.SetGlobalKey(BottomGray, "BottomGray");
        global::Uno.UX.Resource.SetGlobalKey(GrayBlue, "GrayBlue");
        global::Uno.UX.Resource.SetGlobalKey(SkyBlue, "SkyBlue");
        global::Uno.UX.Resource.SetGlobalKey(Pink, "Pink");
        global::Uno.UX.Resource.SetGlobalKey(DarkGray, "DarkGray");
    }
    [global::Uno.UX.UXConstructor]
    public MainView()
    {
        InitializeUX();
    }
    void InitializeUX()
    {
        var temp = new Fuse.Reactive.FuseJS.TimerModule();
        var temp1 = new Fuse.Reactive.FuseJS.Http();
        var temp2 = new Fuse.Triggers.BusyTaskModule();
        var temp3 = new Fuse.FileSystem.FileSystemModule();
        var temp4 = new Fuse.Storage.StorageModule();
        var temp5 = new Polyfills.Window.WindowModule();
        var temp6 = new FuseJS.Globals();
        var temp7 = new FuseJS.Lifecycle();
        var temp8 = new FuseJS.Environment();
        var temp9 = new FuseJS.Base64();
        var temp10 = new FuseJS.Bundle();
        var temp11 = new FuseJS.FileReaderImpl();
        var temp12 = new FuseJS.UserEvents();
        changeWidth = new Fuse.Triggers.WhileTrue();
        changeWidth_Value_inst = new signUp_FuseTriggersWhileTrue_Value_Property(changeWidth, __selector0);
        loadCircle = new Fuse.Triggers.WhileTrue();
        loadCircle_Value_inst = new signUp_FuseTriggersWhileTrue_Value_Property(loadCircle, __selector0);
        rectNormalScale = new Fuse.Controls.Rectangle();
        rectNormalScale_Width_inst = new signUp_FuseControlsRectangle_Width_Property(rectNormalScale, __selector1);
        text = new Fuse.Controls.Text();
        text_Opacity_inst = new signUp_FuseControlsText_Opacity_Property(text, __selector2);
        loadingCircle = new Fuse.Controls.Circle();
        loadingCircle_Opacity_inst = new signUp_FuseControlsCircle_Opacity_Property(loadingCircle, __selector2);
        loadingCircle_LengthAngleDegrees_inst = new signUp_FuseControlsCircle_LengthAngleDegrees_Property(loadingCircle, __selector3);
        var temp13 = new Fuse.iOS.StatusBarConfig();
        var temp14 = new Fuse.Controls.Panel();
        var temp15 = new Fuse.Controls.Panel();
        loadingCirclePanel = new Fuse.Controls.Panel();
        var temp16 = new Fuse.Drawing.Stroke();
        var temp17 = new Fuse.Drawing.StaticSolidColor(float4(1f, 1f, 1f, 1f));
        var temp18 = new Fuse.Gestures.Clicked();
        var temp19 = new Fuse.Triggers.Actions.Toggle();
        var temp20 = new Fuse.Drawing.StaticSolidColor(float4(1f, 0.2f, 0.4f, 1f));
        loadingButton = new Fuse.Controls.Panel();
        var temp21 = new Fuse.Controls.Circle();
        var temp22 = new Fuse.Drawing.StaticSolidColor(float4(1f, 0.2f, 0.4f, 1f));
        loginButtonScaling = new Fuse.Scaling();
        loading = new Fuse.Triggers.WhileTrue();
        var temp23 = new Fuse.Animations.Change<bool>(changeWidth_Value_inst);
        var temp24 = new Fuse.Animations.Change<bool>(loadCircle_Value_inst);
        var temp25 = new Fuse.Animations.Change<Uno.UX.Size>(rectNormalScale_Width_inst);
        var temp26 = new Fuse.Animations.Change<float>(text_Opacity_inst);
        var temp27 = new Fuse.Animations.Change<float>(loadingCircle_Opacity_inst);
        var temp28 = new Fuse.Animations.Spin();
        var temp29 = new Fuse.Animations.Cycle<float>(loadingCircle_LengthAngleDegrees_inst);
        temp13.Style = Uno.Platform.iOS.StatusBarStyle.Light;
        temp15.Width = new Uno.UX.Size(150f, Uno.UX.Unit.Unspecified);
        temp15.Height = new Uno.UX.Size(60f, Uno.UX.Unit.Unspecified);
        global::Fuse.Controls.Grid.SetRow(temp15, 2);
        temp15.Children.Add(text);
        temp15.Children.Add(loadingCirclePanel);
        temp15.Children.Add(temp18);
        temp15.Children.Add(rectNormalScale);
        temp15.Children.Add(loadingButton);
        text.Value = "Sign in";
        text.FontSize = 18f;
        text.TextColor = float4(1f, 1f, 1f, 1f);
        text.Alignment = Fuse.Elements.Alignment.Center;
        text.Name = __selector4;
        loadingCirclePanel.Name = __selector5;
        loadingCirclePanel.Children.Add(loadingCircle);
        loadingCircle.StartAngleDegrees = 0f;
        loadingCircle.LengthAngleDegrees = 90f;
        loadingCircle.Width = new Uno.UX.Size(70f, Uno.UX.Unit.Percent);
        loadingCircle.Height = new Uno.UX.Size(70f, Uno.UX.Unit.Percent);
        loadingCircle.Opacity = 0f;
        loadingCircle.Name = __selector6;
        loadingCircle.Strokes.Add(temp16);
        temp16.Width = 1f;
        temp16.Brush = temp17;
        temp18.Actions.Add(temp19);
        temp19.Target = loading;
        rectNormalScale.CornerRadius = float4(30f, 30f, 30f, 30f);
        rectNormalScale.Width = new Uno.UX.Size(300f, Uno.UX.Unit.Unspecified);
        rectNormalScale.Height = new Uno.UX.Size(60f, Uno.UX.Unit.Unspecified);
        rectNormalScale.Name = __selector7;
        rectNormalScale.Fill = temp20;
        loadingButton.Width = new Uno.UX.Size(1320f, Uno.UX.Unit.Unspecified);
        loadingButton.Height = new Uno.UX.Size(1320f, Uno.UX.Unit.Unspecified);
        loadingButton.Alignment = Fuse.Elements.Alignment.Center;
        loadingButton.Opacity = 0f;
        loadingButton.Name = __selector8;
        loadingButton.Children.Add(temp21);
        loadingButton.Children.Add(loginButtonScaling);
        temp21.Fill = temp22;
        loginButtonScaling.Factor = 0.04545f;
        loginButtonScaling.Name = __selector9;
        loading.Name = __selector10;
        loading.Animators.Add(temp23);
        loading.Animators.Add(temp24);
        temp23.Value = true;
        temp23.DelayBack = 0;
        temp24.Value = true;
        temp24.DelayBack = 0;
        changeWidth.Name = __selector11;
        changeWidth.Animators.Add(temp25);
        temp25.Value = new Uno.UX.Size(60f, Uno.UX.Unit.Unspecified);
        temp25.Easing = Fuse.Animations.Easing.CircularInOut;
        temp25.Duration = 0.5;
        temp25.DurationBack = 0;
        loadCircle.Name = __selector12;
        loadCircle.Animators.Add(temp26);
        loadCircle.Animators.Add(temp27);
        loadCircle.Animators.Add(temp28);
        loadCircle.Animators.Add(temp29);
        temp26.Value = 0f;
        temp26.Duration = 0.2;
        temp26.DurationBack = 0;
        temp27.Value = 1f;
        temp27.Duration = 0.3;
        temp27.DurationBack = 0;
        temp27.DelayBack = 0;
        temp27.Delay = 0.2;
        temp28.Frequency = 2;
        temp28.Target = loadingCircle;
        temp29.Low = 30f;
        temp29.High = 300f;
        temp29.Frequency = 0.7;
        this.Children.Add(temp13);
        this.Children.Add(temp14);
        this.Children.Add(temp15);
        this.Children.Add(loading);
        this.Children.Add(changeWidth);
        this.Children.Add(loadCircle);
    }
    static global::Uno.UX.Selector __selector0 = "Value";
    static global::Uno.UX.Selector __selector1 = "Width";
    static global::Uno.UX.Selector __selector2 = "Opacity";
    static global::Uno.UX.Selector __selector3 = "LengthAngleDegrees";
    static global::Uno.UX.Selector __selector4 = "text";
    static global::Uno.UX.Selector __selector5 = "loadingCirclePanel";
    static global::Uno.UX.Selector __selector6 = "loadingCircle";
    static global::Uno.UX.Selector __selector7 = "rectNormalScale";
    static global::Uno.UX.Selector __selector8 = "loadingButton";
    static global::Uno.UX.Selector __selector9 = "loginButtonScaling";
    static global::Uno.UX.Selector __selector10 = "loading";
    static global::Uno.UX.Selector __selector11 = "changeWidth";
    static global::Uno.UX.Selector __selector12 = "loadCircle";
}
