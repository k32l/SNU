[Uno.Compiler.UxGenerated]
public partial class MainView: Fuse.App
{
    [Uno.Compiler.UxGenerated]
    public partial class Template: Uno.UX.Template
    {
        [Uno.WeakReference] internal readonly MainView __parent;
        [Uno.WeakReference] internal readonly MainView __parentInstance;
        public Template(MainView parent, MainView parentInstance): base(null, false)
        {
            __parent = parent;
            __parentInstance = parentInstance;
        }
        global::Uno.UX.Property<float> temp_Y_inst;
        global::Uno.UX.Property<string> temp1_Value_inst;
        global::Uno.UX.Property<string> temp2_Value_inst;
        global::Uno.UX.Property<string> temp3_Value_inst;
        global::Uno.UX.Property<string> temp4_Value_inst;
        global::Uno.UX.Property<string> temp5_Value_inst;
        global::Uno.UX.Property<Fuse.Drawing.Brush> self_Background_inst;
        static Template()
        {
        }
        public override object New()
        {
            var self = new Fuse.Controls.Page();
            var temp = new Fuse.Translation();
            temp_Y_inst = new weatherapp_FuseTranslation_Y_Property(temp, __selector0);
            var temp1 = new Fuse.Controls.Text();
            temp1_Value_inst = new weatherapp_FuseControlsText_Value_Property(temp1, __selector1);
            var temp2 = new Fuse.Controls.Text();
            temp2_Value_inst = new weatherapp_FuseControlsText_Value_Property(temp2, __selector1);
            var temp3 = new Body();
            temp3_Value_inst = new weatherapp_Body_Value_Property(temp3, __selector1);
            var temp4 = new Body();
            temp4_Value_inst = new weatherapp_Body_Value_Property(temp4, __selector1);
            var temp5 = new Body();
            temp5_Value_inst = new weatherapp_Body_Value_Property(temp5, __selector1);
            self_Background_inst = new weatherapp_FuseControlsPage_Background_Property(self, __selector2);
            var temp6 = new Fuse.Reactive.DataBinding<float>(temp_Y_inst, "ypos");
            var temp7 = new Fuse.Controls.StackPanel();
            var temp8 = new Fuse.Reactive.DataBinding<string>(temp1_Value_inst, "TOD");
            var temp9 = new Fuse.Reactive.DataBinding<string>(temp2_Value_inst, "Temp");
            var temp10 = new Fuse.Controls.StackPanel();
            var temp11 = new Fuse.Reactive.DataBinding<string>(temp3_Value_inst, "Summary");
            var temp12 = new Fuse.Reactive.DataBinding<string>(temp4_Value_inst, "Wind");
            var temp13 = new Fuse.Reactive.DataBinding<string>(temp5_Value_inst, "Humidity");
            var temp14 = new Fuse.Navigation.ExitingAnimation();
            var temp15 = new Fuse.Animations.Move();
            var temp16 = new Fuse.Navigation.EnteringAnimation();
            var temp17 = new Fuse.Animations.Move();
            var temp18 = new Fuse.Navigation.EnteringAnimation();
            var temp19 = new Fuse.Animations.Move();
            var temp20 = new Fuse.Reactive.DataBinding<Fuse.Drawing.Brush>(self_Background_inst, "bgcolor");
            self.ClipToBounds = true;
            temp.RelativeTo = Fuse.TranslationModes.Size;
            temp.Bindings.Add(temp6);
            temp7.Width = new Uno.UX.Size(43f, Uno.UX.Unit.Percent);
            temp7.Height = new Uno.UX.Size(100f, Uno.UX.Unit.Percent);
            temp7.Alignment = Fuse.Elements.Alignment.TopLeft;
            temp7.Margin = float4(10f, 10f, 10f, 10f);
            temp7.Children.Add(temp1);
            temp7.Children.Add(temp2);
            temp7.Children.Add(temp10);
            temp1.FontSize = 19f;
            temp1.TextColor = float4(1f, 1f, 1f, 0.5019608f);
            temp1.Margin = float4(0f, 14f, 0f, 0f);
            temp1.Font = global::MainView.NunitoBold;
            temp1.Bindings.Add(temp8);
            temp2.FontSize = 32f;
            temp2.TextColor = float4(1f, 1f, 1f, 1f);
            temp2.Alignment = Fuse.Elements.Alignment.CenterLeft;
            temp2.Margin = float4(0f, 0f, 0f, 14f);
            temp2.Font = global::MainView.NunitoLight;
            temp2.Bindings.Add(temp9);
            temp10.Alignment = Fuse.Elements.Alignment.TopLeft;
            temp10.Children.Add(temp3);
            temp10.Children.Add(temp4);
            temp10.Children.Add(temp5);
            temp10.Children.Add(temp14);
            temp10.Children.Add(temp16);
            temp3.FontSize = 26f;
            temp3.Bindings.Add(temp11);
            temp4.Bindings.Add(temp12);
            temp5.Bindings.Add(temp13);
            temp14.Animators.Add(temp15);
            temp15.Y = 0.5f;
            temp15.Easing = Fuse.Animations.Easing.Linear;
            temp15.RelativeTo = Fuse.TranslationModes.ParentSize;
            temp16.Animators.Add(temp17);
            temp17.Y = 0.5f;
            temp17.Easing = Fuse.Animations.Easing.Linear;
            temp17.RelativeTo = Fuse.TranslationModes.ParentSize;
            temp18.Animators.Add(temp19);
            temp19.Y = 0.3334f;
            temp19.RelativeTo = Fuse.TranslationModes.Size;
            self.Children.Add(temp);
            self.Children.Add(temp7);
            self.Children.Add(temp18);
            self.Bindings.Add(temp20);
            return self;
        }
        static global::Uno.UX.Selector __selector0 = "Y";
        static global::Uno.UX.Selector __selector1 = "Value";
        static global::Uno.UX.Selector __selector2 = "Background";
    }
    global::Uno.UX.Property<object> temp_Items_inst;
    [global::Uno.UX.UXGlobalResource("NunitoRegular")] public static readonly Fuse.Font NunitoRegular;
    [global::Uno.UX.UXGlobalResource("NunitoLight")] public static readonly Fuse.Font NunitoLight;
    [global::Uno.UX.UXGlobalResource("NunitoBold")] public static readonly Fuse.Font NunitoBold;
    internal Fuse.Navigation.LinearNavigation lnav;
    global::Uno.UX.NameTable __g_nametable;
    static string[] __g_static_nametable = new string[] {
        "lnav"
    };
    static MainView()
    {
        NunitoRegular = new Fuse.Font(new global::Uno.UX.BundleFileSource(import global::Uno.IO.BundleFile("../../../Assets/Nunito-Regular.ttf")));
        NunitoLight = new Fuse.Font(new global::Uno.UX.BundleFileSource(import global::Uno.IO.BundleFile("../../../Assets/Nunito-Light.ttf")));
        NunitoBold = new Fuse.Font(new global::Uno.UX.BundleFileSource(import global::Uno.IO.BundleFile("../../../Assets/Nunito-Bold.ttf")));
        global::Uno.UX.Resource.SetGlobalKey(NunitoRegular, "NunitoRegular");
        global::Uno.UX.Resource.SetGlobalKey(NunitoLight, "NunitoLight");
        global::Uno.UX.Resource.SetGlobalKey(NunitoBold, "NunitoBold");
    }
    [global::Uno.UX.UXConstructor]
    public MainView()
    {
        InitializeUX();
    }
    void InitializeUX()
    {
        var temp1 = new Fuse.Reactive.FuseJS.TimerModule();
        var temp2 = new Fuse.Reactive.FuseJS.Http();
        var temp3 = new Fuse.Triggers.BusyTaskModule();
        var temp4 = new Fuse.FileSystem.FileSystemModule();
        var temp5 = new Fuse.Storage.StorageModule();
        var temp6 = new Polyfills.Window.WindowModule();
        var temp7 = new FuseJS.Globals();
        var temp8 = new FuseJS.Lifecycle();
        var temp9 = new FuseJS.Environment();
        var temp10 = new FuseJS.Base64();
        var temp11 = new FuseJS.Bundle();
        var temp12 = new FuseJS.FileReaderImpl();
        var temp13 = new FuseJS.UserEvents();
        __g_nametable = new global::Uno.UX.NameTable(null, __g_static_nametable);
        var temp = new Fuse.Reactive.Each();
        temp_Items_inst = new weatherapp_FuseReactiveEach_Items_Property(temp, __selector0);
        var temp14 = new Fuse.Controls.DockPanel();
        var temp15 = new Fuse.Reactive.JavaScript(__g_nametable);
        var temp16 = new Fuse.iOS.StatusBarConfig();
        var temp17 = new Fuse.Controls.StackPanel();
        var temp18 = new Fuse.Controls.StatusBarBackground();
        var temp19 = new Fuse.Controls.Text();
        var temp20 = new Fuse.Drawing.StaticSolidColor(float4(0.5450981f, 0.6588235f, 0.572549f, 1f));
        var temp21 = new Fuse.Controls.BottomBarBackground();
        var temp22 = new Fuse.Controls.Panel();
        lnav = new Fuse.Navigation.LinearNavigation();
        var temp23 = new Fuse.Motion.NavigationMotion();
        var temp24 = new Fuse.Navigation.SwipeNavigate();
        var temp25 = new Template(this, this);
        var temp26 = new Fuse.Reactive.DataBinding<object>(temp_Items_inst, "tabs");
        this.Background = float4(0.9333333f, 0.9333333f, 0.9333333f, 1f);
        temp14.Children.Add(temp15);
        temp14.Children.Add(temp16);
        temp14.Children.Add(temp17);
        temp14.Children.Add(temp21);
        temp14.Children.Add(temp22);
        temp15.LineNumber = 3;
        temp15.FileName = "MainView.ux";
        temp15.File = new global::Uno.UX.BundleFileSource(import global::Uno.IO.BundleFile("../../../MainView.js"));
        temp16.Style = Uno.Platform.iOS.StatusBarStyle.Light;
        global::Fuse.Controls.DockPanel.SetDock(temp17, Fuse.Layouts.Dock.Top);
        temp17.Background = temp20;
        temp17.Children.Add(temp18);
        temp17.Children.Add(temp19);
        temp19.Value = "Attendance History";
        temp19.FontSize = 18f;
        temp19.TextColor = float4(1f, 1f, 1f, 0.5019608f);
        temp19.Alignment = Fuse.Elements.Alignment.Left;
        temp19.Margin = float4(5f, 5f, 0f, 5f);
        temp19.Font = global::MainView.NunitoBold;
        global::Fuse.Controls.DockPanel.SetDock(temp21, Fuse.Layouts.Dock.Bottom);
        global::Fuse.Controls.DockPanel.SetDock(temp22, Fuse.Layouts.Dock.Fill);
        temp22.Children.Add(lnav);
        temp22.Children.Add(temp24);
        temp22.Children.Add(temp);
        lnav.Name = __selector1;
        lnav.Motion = temp23;
        temp23.GotoEasing = Fuse.Animations.Easing.QuadraticOut;
        temp23.GotoDuration = 0.3f;
        temp23.Overflow = Fuse.Motion.OverflowType.Clamp;
        temp24.SwipeDirection = Fuse.Navigation.SwipeDirection.Down;
        temp.Templates.Add(temp25);
        temp.Bindings.Add(temp26);
        __g_nametable.This = this;
        __g_nametable.Objects.Add(lnav);
        this.Children.Add(temp14);
    }
    static global::Uno.UX.Selector __selector0 = "Items";
    static global::Uno.UX.Selector __selector1 = "lnav";
}
