sealed class weatherapp_FuseReactiveEach_Items_Property: Uno.UX.Property<object>
{
    [Uno.WeakReference] readonly Fuse.Reactive.Each _obj;
    public weatherapp_FuseReactiveEach_Items_Property(Fuse.Reactive.Each obj, global::Uno.UX.Selector name) : base(name) { _obj = obj; }
    public override global::Uno.UX.PropertyObject Object { get { return _obj; } }
    public override object Get() { return _obj.Items; }
    public override void Set(object v, global::Uno.UX.IPropertyListener origin) { _obj.Items = v; }
    public override bool SupportsOriginSetter { get { return false; } }
}
sealed class weatherapp_FuseControlsPage_Background_Property: Uno.UX.Property<Fuse.Drawing.Brush>
{
    [Uno.WeakReference] readonly Fuse.Controls.Page _obj;
    public weatherapp_FuseControlsPage_Background_Property(Fuse.Controls.Page obj, global::Uno.UX.Selector name) : base(name) { _obj = obj; }
    public override global::Uno.UX.PropertyObject Object { get { return _obj; } }
    public override Fuse.Drawing.Brush Get() { return _obj.Background; }
    public override void Set(Fuse.Drawing.Brush v, global::Uno.UX.IPropertyListener origin) { _obj.Background = v; }
    public override bool SupportsOriginSetter { get { return false; } }
}
sealed class weatherapp_FuseTranslation_Y_Property: Uno.UX.Property<float>
{
    [Uno.WeakReference] readonly Fuse.Translation _obj;
    public weatherapp_FuseTranslation_Y_Property(Fuse.Translation obj, global::Uno.UX.Selector name) : base(name) { _obj = obj; }
    public override global::Uno.UX.PropertyObject Object { get { return _obj; } }
    public override float Get() { return _obj.Y; }
    public override void Set(float v, global::Uno.UX.IPropertyListener origin) { _obj.Y = v; }
    public override bool SupportsOriginSetter { get { return false; } }
}
sealed class weatherapp_FuseControlsText_Value_Property: Uno.UX.Property<string>
{
    [Uno.WeakReference] readonly Fuse.Controls.Text _obj;
    public weatherapp_FuseControlsText_Value_Property(Fuse.Controls.Text obj, global::Uno.UX.Selector name) : base(name) { _obj = obj; }
    public override global::Uno.UX.PropertyObject Object { get { return _obj; } }
    public override string Get() { return _obj.Value; }
    public override void Set(string v, global::Uno.UX.IPropertyListener origin) { _obj.SetValue(v, origin); }
    public override bool SupportsOriginSetter { get { return true; } }
}
sealed class weatherapp_Body_Value_Property: Uno.UX.Property<string>
{
    [Uno.WeakReference] readonly Body _obj;
    public weatherapp_Body_Value_Property(Body obj, global::Uno.UX.Selector name) : base(name) { _obj = obj; }
    public override global::Uno.UX.PropertyObject Object { get { return _obj; } }
    public override string Get() { return _obj.Value; }
    public override void Set(string v, global::Uno.UX.IPropertyListener origin) { _obj.SetValue(v, origin); }
    public override bool SupportsOriginSetter { get { return true; } }
}
