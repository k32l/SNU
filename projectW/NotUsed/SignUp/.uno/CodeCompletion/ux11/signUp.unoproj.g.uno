sealed class signUp_FuseTriggersWhileTrue_Value_Property: Uno.UX.Property<bool>
{
    [Uno.WeakReference] readonly Fuse.Triggers.WhileTrue _obj;
    public signUp_FuseTriggersWhileTrue_Value_Property(Fuse.Triggers.WhileTrue obj, global::Uno.UX.Selector name) : base(name) { _obj = obj; }
    public override global::Uno.UX.PropertyObject Object { get { return _obj; } }
    public override bool Get() { return _obj.Value; }
    public override void Set(bool v, global::Uno.UX.IPropertyListener origin) { _obj.Value = v; }
    public override bool SupportsOriginSetter { get { return false; } }
}
sealed class signUp_FuseControlsRectangle_Width_Property: Uno.UX.Property<Uno.UX.Size>
{
    [Uno.WeakReference] readonly Fuse.Controls.Rectangle _obj;
    public signUp_FuseControlsRectangle_Width_Property(Fuse.Controls.Rectangle obj, global::Uno.UX.Selector name) : base(name) { _obj = obj; }
    public override global::Uno.UX.PropertyObject Object { get { return _obj; } }
    public override Uno.UX.Size Get() { return _obj.Width; }
    public override void Set(Uno.UX.Size v, global::Uno.UX.IPropertyListener origin) { _obj.Width = v; }
    public override bool SupportsOriginSetter { get { return false; } }
}
sealed class signUp_FuseControlsText_Opacity_Property: Uno.UX.Property<float>
{
    [Uno.WeakReference] readonly Fuse.Controls.Text _obj;
    public signUp_FuseControlsText_Opacity_Property(Fuse.Controls.Text obj, global::Uno.UX.Selector name) : base(name) { _obj = obj; }
    public override global::Uno.UX.PropertyObject Object { get { return _obj; } }
    public override float Get() { return _obj.Opacity; }
    public override void Set(float v, global::Uno.UX.IPropertyListener origin) { _obj.SetOpacity(v, origin); }
    public override bool SupportsOriginSetter { get { return true; } }
}
sealed class signUp_FuseControlsCircle_Opacity_Property: Uno.UX.Property<float>
{
    [Uno.WeakReference] readonly Fuse.Controls.Circle _obj;
    public signUp_FuseControlsCircle_Opacity_Property(Fuse.Controls.Circle obj, global::Uno.UX.Selector name) : base(name) { _obj = obj; }
    public override global::Uno.UX.PropertyObject Object { get { return _obj; } }
    public override float Get() { return _obj.Opacity; }
    public override void Set(float v, global::Uno.UX.IPropertyListener origin) { _obj.SetOpacity(v, origin); }
    public override bool SupportsOriginSetter { get { return true; } }
}
sealed class signUp_FuseControlsCircle_LengthAngleDegrees_Property: Uno.UX.Property<float>
{
    [Uno.WeakReference] readonly Fuse.Controls.Circle _obj;
    public signUp_FuseControlsCircle_LengthAngleDegrees_Property(Fuse.Controls.Circle obj, global::Uno.UX.Selector name) : base(name) { _obj = obj; }
    public override global::Uno.UX.PropertyObject Object { get { return _obj; } }
    public override float Get() { return _obj.LengthAngleDegrees; }
    public override void Set(float v, global::Uno.UX.IPropertyListener origin) { _obj.LengthAngleDegrees = v; }
    public override bool SupportsOriginSetter { get { return false; } }
}
